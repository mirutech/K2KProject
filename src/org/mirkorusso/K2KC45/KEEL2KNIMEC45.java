package org.mirkorusso.K2KC45;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.mirkorusso.K2KC45.K2K.KEEL2KNIME;


class KEEL2KNIMEC45 implements KEEL2KNIME {
	private String mainDirectory;
	private String resultsFileDirectory;
	private String predictionName;
	private ExecutionContext exec;

	
	/**
	 * Constructor that initializes the instance variables with their input parameters.
	 * 
	 * @param mainDirectory Path to the root directory.
	 * @param resultFilesDirectory Directory path where methods read the KEEL output files.
	 * @param predictionName Name of the column containing the results.
	 * @param exec Execution context of the node.
	 */
	KEEL2KNIMEC45(String mainDirectory, String resultsFileDirectory, String predictionName, ExecutionContext exec) {
		this.mainDirectory = mainDirectory;
		this.resultsFileDirectory = resultsFileDirectory;
		this.predictionName = predictionName;
		this.exec = exec;
	}

	
	@Override
	public BufferedDataTable outDataCreator(BufferedDataTable inData, Hashtable<Integer, String> predictions) {
		int j = 0;
	    DataTableSpec inputTableSpec = inData.getDataTableSpec();
	    
	    //New num columns = old num columns + 1 (prediction column)
    	DataColumnSpec[] outputColumnSpecs = new DataColumnSpec[inputTableSpec.getNumColumns() + 1];
    	
    	for (int i = 0; i < inputTableSpec.getNumColumns(); i++)
    	   		outputColumnSpecs[i] = inputTableSpec.getColumnSpec(i);
    	
    	outputColumnSpecs[inputTableSpec.getNumColumns()] = 
    			new DataColumnSpecCreator(predictionName, StringCell.TYPE).createSpec();
    	
    	//New BufferedDataTable creation
    	DataTableSpec outputTableSpec = new DataTableSpec(outputColumnSpecs);
    	BufferedDataContainer container = exec.createDataContainer(outputTableSpec);
    	CloseableRowIterator it = inData.iterator();
    	DataRow r = null;
    	
    	while(it.hasNext()) {
    		r = it.next();
    		DataCell[] cells = new DataCell[outputColumnSpecs.length];
    		
    		for (int i = 0; i < inputTableSpec.getNumColumns(); i++)
    			cells[i] = r.getCell(i);
    		
    		cells[inputTableSpec.getNumColumns()] = new StringCell(predictions.get(j++));
    		DataRow row = new DefaultRow(r.getKey(), cells);
    		container.addRowToTable(row);
    	}
    	container.close();
		return container.getTable();
	}
	

	@Override
	public Hashtable<Integer, String> resultsExtractor(String resultsFileName) 
			throws IOException {
		String resultPath = mainDirectory + resultsFileDirectory + resultsFileName;
		FileReader filereader = new FileReader(resultPath);
	    BufferedReader reader = new BufferedReader(filereader);
	    Hashtable<Integer, String> predictions = new Hashtable<>();
	    String s = reader.readLine();
	    
	    //find the beginning of the data results
	    while(!reader.readLine().contentEquals("@data")){}	    
	    s = reader.readLine();
	    
	    //data results will be stored in the hash table (metadata + dataset)
	    for(int i = 0; s != null; i++) {
	    	predictions.put(i, s);
	    	s = reader.readLine();
	    }
	    
	    //delete the original prediction, store the value predicted by the KEEL Algorithm
	    for(int j = 0;j < predictions.size(); j++) {
	    	String ClPlusPr = predictions.get(j);
	    	ClPlusPr = (String) ClPlusPr.subSequence(ClPlusPr.indexOf(" ") + 1, ClPlusPr.length());
			predictions.put(j,ClPlusPr);
	    }
	    
    	reader.close();
	    return predictions;
	}	
}
