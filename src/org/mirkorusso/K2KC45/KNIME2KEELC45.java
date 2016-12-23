package org.mirkorusso.K2KC45;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.knime.core.data.DataColumnDomain;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;
import org.mirkorusso.K2KC45.K2K.KNIME2KEEL;


class KNIME2KEELC45 implements KNIME2KEEL {
	private String datasetName;
	private String mainDirectory;
	private String dataFilesDirectory;
	private String classColumnName;

	
	/**
	 * Constructor that initializes the instance variables with their input parameters.
	 * 
	 * @param datasetName Name of the dataset
	 * @param mainDirectory Directory path where methods will create the KEEL configuration file.
	 * @param dataFilesDirectory Directory path where methods will create the KEEL data file.
	 * @param classColumnName Name of the column on which the algorithm will perform the prediction.
	 */
	KNIME2KEELC45(String datasetName, String mainDirectory, String dataFilesDirectory, String classColumnName) {
		this.datasetName = datasetName;
		this.mainDirectory = mainDirectory;
		this.dataFilesDirectory = dataFilesDirectory;
		this.classColumnName = classColumnName;
	}

	
	@Override
	public void configFileCreator(String algorithmName, String resultFilesDirectory, String[] KEELSettings) 
			throws IOException {
		FileWriter configFile = new FileWriter(mainDirectory + "config.txt");
		BufferedWriter writer = new BufferedWriter(configFile);
        writer.write("algorithm = " + algorithmName + "\n");
        writer.write("inputData = \"" + mainDirectory  + dataFilesDirectory + datasetName + "-tra.dat\" \"" + mainDirectory + dataFilesDirectory + datasetName + "-tra.dat\" \"" + mainDirectory + dataFilesDirectory + datasetName + "-tst.dat\"\n");
        writer.write("outputData = \"" + mainDirectory + resultFilesDirectory + "result.tra\" \"" + mainDirectory + resultFilesDirectory + "result.tst\" \"" + mainDirectory + resultFilesDirectory + "result.txt\"\n\n"); 
        for (int i = 0; i < KEELSettings.length; i++)
            writer.write(KEELSettings[i] + "\n");
        writer.close();
        configFile.close();
	}
	
	
	@Override
	public void dataFileCreator(String dataFileName, BufferedDataTable inData) throws IOException {
		DataTableSpec inputTableSpec = inData.getDataTableSpec();
    	int numColumns = inputTableSpec.getNumColumns();
		DataColumnSpec[] dcs = new DataColumnSpec[numColumns];
    	for (int i = 0; i < numColumns; i++)
    		dcs[i] = inputTableSpec.getColumnSpec(i);
    	
    	DataColumnDomain[] dcd =  new DataColumnDomain[numColumns];
    	for (int i = 0; i < numColumns; i++)
    		dcd[i] = dcs[i].getDomain();
    	
    	String FilePath = mainDirectory + dataFilesDirectory + dataFileName;
    	
		FileWriter dataFile = new FileWriter(FilePath);
		BufferedWriter writer = new BufferedWriter(dataFile);
        writer.write("@relation " + datasetName + "\n");
        
        // write every attribute's name type and domain, extracted by the respective DataColumnSpec
        for(int i = 0; i < numColumns; i++)
        	writer.write("@attribute " + dcs[i].getName().replace(" ", "") + 
        			" " + getType(dcs[i]) + getDomain(dcs[i]) + "\n");
        
        String tmp = "@inputs ";

        //write the input column excluding the prediction one
        for(int i = 0; i < numColumns; i++)
        	if (!dcs[i].getName().equals(classColumnName)) 
        		tmp += dcs[i].getName().replace(" ", "") + ", ";
        
        //remove final comma and space
        tmp=tmp.substring(0, tmp.length() - 2);
        writer.write(tmp + "\n");
        
        //write the prediction column
        tmp = "@outputs " + classColumnName.replace(" ", "");
        writer.write(tmp + "\n");
        
        writer.write("@data\n");
        
        //write the dataset contained in the input Buffered Data Table
        for (DataRow r: inData) {
            tmp = "";
            
        	for(int i = 0; i < numColumns; i++)
        		tmp += r.getCell(i).toString() + ", ";
        	
        	while(tmp.endsWith(", "))
            	tmp=tmp.substring(0, tmp.length() - 2);
        	
        	writer.write(tmp + "\n");
        }
        writer.close();
        dataFile.close();
	}

	private String getDomain(DataColumnSpec dcs) {
		if(getType(dcs).equals("nominal"))
			return " " + dcs.getDomain().getValues().toString().replace('[', '{').replace(']', '}');
		else
			return " [" + dcs.getDomain().getLowerBound().toString() + ", " + dcs.getDomain().getUpperBound().toString() + "]";
	}

	private String getType(DataColumnSpec dcs) {
		String s = dcs.getType().toString();
		
		if(s.equals("Number (double)") || s.equals("Number (double precision)"))
			return "real";
		else if(s.equals("Number (integer)") || s.equals("Number (long)" ))
			return "integer";
		else if(s.equals("String"))
			return "nominal";
		else
			return "nominal";
	}
}
