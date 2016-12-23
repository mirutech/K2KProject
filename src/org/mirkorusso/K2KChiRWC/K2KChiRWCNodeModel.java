package org.mirkorusso.K2KChiRWC;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DoubleValue;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.mirkorusso.K2KChiRWC.K2K.KEEL2KNIME;
import org.mirkorusso.K2KChiRWC.K2K.KEELAlgorithm;
import org.mirkorusso.K2KChiRWC.K2K.KNIME2KEEL;


/**
 * The class K2KChiRWCNodeModel extends the NodeModel class and allows the execution of the node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KChiRWCNodeModel extends NodeModel{  
	
	public static final int IN_PORT = 0;
	public static final String CFGKEY_COLUMN_NAME = "columnName";
	public static final String PREDICTION_NAME = "Prediction";
	private String mainDirectory = "/K2KNodeTempFiles";
	private String trainingFileName ="-tra.dat";
	private String testFileName ="-tst.dat";
	private String datasetName;
	private String predictionColumn;
    private KEELAlgorithm k = new KEELAlgorithmChiRWC();
	
	private SettingsModelString m_column = new SettingsModelString(
			   CFGKEY_COLUMN_NAME, null);
	private SettingsModelString m_predictionName = new SettingsModelString(
			PREDICTION_NAME, null);

    /**
     * Constructor that sets the number of input and output node ports and the main directory of the KEEL files.
     */
    protected K2KChiRWCNodeModel() {
        super(2, 2);
        mainDirectory = System.getProperty("java.io.tmpdir").replace("\\", "/") + mainDirectory;
    }
    
    
    /**
     * @throws IOException If the KEEL files cannot be read/written.
     */
    @Override
	protected BufferedDataTable[] execute(final BufferedDataTable[] inData, final ExecutionContext exec) throws IOException {

    	DataTableSpec inputTableSpec = inData[0].getDataTableSpec();
    	
    	//Dataset file name without its extension
    	datasetName = inputTableSpec.getName().substring(0, inputTableSpec.getName().length() - 4);
    	
    	// Complete data files paths
    	trainingFileName = datasetName + trainingFileName;
    	testFileName = datasetName + testFileName;
    	
    	//unique directory name
    	mainDirectory += UUID.randomUUID().toString() + "/";
    			
    	//folders creations
    	(new File(mainDirectory)).mkdir();
    	(new File(mainDirectory + KEELAlgorithmChiRWC.DATA_FILES_DIRECTORY)).mkdir();
    	(new File(mainDirectory + KEELAlgorithmChiRWC.RESULTS_FILES_DIRECTORY)).mkdir();

    	KNIME2KEEL knimeToKeel = new KNIME2KEELChiRWC(datasetName, mainDirectory, KEELAlgorithmChiRWC.DATA_FILES_DIRECTORY, m_column.getStringValue());
    	knimeToKeel.configFileCreator(KEELAlgorithmChiRWC.ALGORITHM_NAME, KEELAlgorithmChiRWC.RESULTS_FILES_DIRECTORY, k.getKEELSettings());
    	knimeToKeel.dataFileCreator(trainingFileName, inData[0]);
    	knimeToKeel.dataFileCreator(testFileName, inData[1]);
		
    	k.runKEELAlgorithm(mainDirectory + KEELAlgorithmChiRWC.CONFIG_FILE_NAME);
    	
    	BufferedDataTable[] outData = new BufferedDataTable[2];
    	KEEL2KNIME keelToKnime = new KEEL2KNIMEChiRWC(mainDirectory, KEELAlgorithmChiRWC.RESULTS_FILES_DIRECTORY, predictionColumn, exec);
    	outData[0] = keelToKnime.outDataCreator(inData[0], keelToKnime.resultsExtractor(KEELAlgorithmChiRWC.RESULTS_TRAIN_FILE_NAME));
    	outData[1] = keelToKnime.outDataCreator(inData[1], keelToKnime.resultsExtractor(KEELAlgorithmChiRWC.RESULTS_TEST_FILE_NAME));
    	
    	// files deletion
    	System.gc();
    	while((new File(mainDirectory).exists())){
    		try{
    			FileUtils.deleteDirectory(new File(mainDirectory));
    		} catch (IOException e){
    			
    		}
    	}

    	return outData;
	}
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
    	mainDirectory = System.getProperty("java.io.tmpdir").replace("\\", "/") + "/K2KNodeTempFiles";
    	trainingFileName = "-tra.dat";
    	testFileName = "-tst.dat";
    	datasetName = null;
    }


    /**
     * The function configure checks the parameters of the node input and reconfigures the specifications of the DataTable as input in the DataTable in output.
     * 
     * @throws InvalidSettingsException if the KEEL files cannot be read/written.
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs) throws InvalidSettingsException {            
        boolean hasNumericColumn = false;
        boolean containsName = false;
        
        for (int i = 0; i < inSpecs[IN_PORT].getNumColumns(); i++) {
            DataColumnSpec columnSpec = inSpecs[IN_PORT].getColumnSpec(i);
            
            if (columnSpec.getType().isCompatible(DoubleValue.class))
                hasNumericColumn = true;

            if (m_column != null && columnSpec.getName().equals(m_column.getStringValue()))
                containsName = true;     
        }
        if (!hasNumericColumn) 
            throw new InvalidSettingsException("Input table must contain at least one numeric column");
        
        if (!containsName) 
            throw new InvalidSettingsException("Please (re-)configure the Class column setting.");
        // set the Prediction Column name
        if(m_predictionName.getStringValue().equals(""))
        	predictionColumn = "Prediction(" + m_column.getStringValue() + ")";
    	else
    		predictionColumn = m_predictionName.getStringValue();
        
        return new DataTableSpec[]{null, null};
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {
    	SettingsModel[] s = k.getNodeSettings();
    	
    	for (int i = 0; i < s.length; i++)
        		s[i].saveSettingsTo(settings);
    	
    	m_column.saveSettingsTo(settings);
    	m_predictionName.saveSettingsTo(settings);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
    	SettingsModel[] s = k.getNodeSettings();
    	
    	for (int i = 0; i < s.length; i++)
        			s[i].loadSettingsFrom(settings);
    	
    	m_column.loadSettingsFrom(settings);
    	m_predictionName.loadSettingsFrom(settings);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
    	SettingsModel[] s = k.getNodeSettings();
    	
    	for (int i = 0; i < s.length; i++)
        		s[i].validateSettings(settings);

    	m_column.validateSettings(settings);
    	m_predictionName.validateSettings(settings);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
    }
}