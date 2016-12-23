package org.mirkorusso.K2KC45;

import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.mirkorusso.K2KC45.K2K.KEELAlgorithm;

import keel.Algorithms.Decision_Trees.C45.C45;
import keel.Dataset.Attributes;
import org.knime.core.node.defaultnodesettings.SettingsModel;

class KEELAlgorithmC45 implements KEELAlgorithm {
	static final String ALGORITHM_NAME = "C4.5 Decision Tree";
	static final String PRUNED = "pruned";
	static final String INSTANCES_PER_LEAF = "instancesPerLeaf";
	static final String CONFIDENCE = "confidence";
	static final String CONFIG_FILE_NAME = "config.txt";
	static final String DATA_FILES_DIRECTORY = "Datasets/";
	static final String RESULTS_FILES_DIRECTORY = "Results/";
	static final String RESULTS_TRAIN_FILE_NAME = "result.tra";
	static final String RESULTS_TEST_FILE_NAME = "result.tst";
	
	SettingsModelBoolean m_pruned = new SettingsModelBoolean(PRUNED, true);
	SettingsModelInteger m_instancesPerLeaf = new SettingsModelInteger(INSTANCES_PER_LEAF, 2);
	SettingsModelDouble m_confidence = new SettingsModelDouble(CONFIDENCE, .25);

	    
	    @Override
	    public void runKEELAlgorithm(String configFilePath) {
	    	synchronized (C45.class) {
	    		String[] args = {configFilePath};
	    		C45.main(args);
	    		
	    		//clear all static attributes of the class Attributes
	    		Attributes.clearAll();
	    	}
		}


	    @Override
	    public String[] getKEELSettings() {
			String[] settingsNames = new String[3];
			settingsNames[0] = PRUNED + " = " + String.valueOf(m_pruned.getBooleanValue()).toUpperCase();
			settingsNames[1] = INSTANCES_PER_LEAF + " = " + m_instancesPerLeaf.getIntValue();
			settingsNames[2] = CONFIDENCE + " = " + m_confidence.getDoubleValue();
			return settingsNames;	
		}
		
		
	    @Override
	    public SettingsModel[] getNodeSettings() {
			SettingsModel[] settingsValues = new SettingsModel[3];
			settingsValues [0] = m_pruned;
		    settingsValues [1] = m_instancesPerLeaf;
		    settingsValues [2] = m_confidence;
			return settingsValues;
		}
}
