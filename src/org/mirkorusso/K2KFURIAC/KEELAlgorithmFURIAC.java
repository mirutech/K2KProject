package org.mirkorusso.K2KFURIAC;

import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.mirkorusso.K2KFURIAC.K2K.KEELAlgorithm;

import keel.Algorithms.Fuzzy_Rule_Learning.Hybrid.FURIA.Main;
import keel.Dataset.Attributes;
import org.knime.core.node.defaultnodesettings.SettingsModel;

class KEELAlgorithmFURIAC implements KEELAlgorithm {
	static final String ALGORITHM_NAME = "FURIA";
	static final String NUMBER_OF_OPTIMIZATIONS = "Number of Optimizations";
	static final String NUMBER_OF_FOLDS = "Number of folds";
	static final String CONFIG_FILE_NAME = "config.txt";
	static final String DATA_FILES_DIRECTORY = "Datasets/";
	static final String RESULTS_FILES_DIRECTORY = "Results/";
	static final String RESULTS_TRAIN_FILE_NAME = "result.tra";
	static final String RESULTS_TEST_FILE_NAME = "result.tst";
	
	SettingsModelInteger m_numberOfOptimizations = new SettingsModelInteger(NUMBER_OF_OPTIMIZATIONS, 2);
	SettingsModelInteger m_numberOfFolds = new SettingsModelInteger(NUMBER_OF_FOLDS, 3);

	    
	    @Override
	    public void runKEELAlgorithm(String configFilePath) {
	    	synchronized (Main.class) {
	    		String[] args = {configFilePath};
	    		Main.main(args);
	    		
	    		//clear all static attributes of the class Attributes
	    		Attributes.clearAll();
	    	}
		}


	    @Override
	    public String[] getKEELSettings() {
			String[] settingsNames = new String[2];
			settingsNames[0] = NUMBER_OF_OPTIMIZATIONS + " = " + m_numberOfOptimizations.getIntValue();
			settingsNames[1] = NUMBER_OF_FOLDS + " = " + m_numberOfFolds.getIntValue();
			return settingsNames;	
		}
		
		
	    @Override
	    public SettingsModel[] getNodeSettings() {
			SettingsModel[] settingsValues = new SettingsModel[2];
			settingsValues [0] = m_numberOfOptimizations;
		    settingsValues [1] = m_numberOfFolds;
			return settingsValues;
		}
}
