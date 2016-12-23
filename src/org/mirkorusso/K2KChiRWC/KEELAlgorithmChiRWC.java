package org.mirkorusso.K2KChiRWC;

import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.mirkorusso.K2KChiRWC.K2K.KEELAlgorithm;
import keel.Algorithms.Fuzzy_Rule_Learning.AdHoc.Chi_RW.*;
import keel.Dataset.Attributes;

import org.knime.core.node.defaultnodesettings.SettingsModel;

class KEELAlgorithmChiRWC implements KEELAlgorithm {
	static final String ALGORITHM_NAME = "Fuzzy Rule Learning Model by the Chi et al. approach with rule weights";
	public final static String NUMBER_OF_LABELS = "Number of Labels";
	public final static String T_NORM = "T-norm for the Computation of the Compatibility Degree";
	public final static String RULE_WEIGHT = "Rule Weight";
	public final static String FUZZY_REASONING_METHOD = "Fuzzy Reasoning Method";
	static final String CONFIG_FILE_NAME = "config.txt";
	static final String DATA_FILES_DIRECTORY = "Datasets/";
	static final String RESULTS_FILES_DIRECTORY = "Results/";
	static final String RESULTS_TRAIN_FILE_NAME = "result.tra";
	static final String RESULTS_TEST_FILE_NAME = "result.tst";


	
	SettingsModelInteger m_numberOfOLabels = new SettingsModelInteger(NUMBER_OF_LABELS, 3);
	SettingsModelString m_tNorm = new SettingsModelString(T_NORM, "Product");
	SettingsModelString m_ruleWeight = new SettingsModelString(RULE_WEIGHT, "Penalized_Certainty_Factor");
	SettingsModelString m_fuzzyReasoningMethod = new SettingsModelString(FUZZY_REASONING_METHOD, "Winning_Rule");
	

	    
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
			String[] settingsNames = new String[4];
			settingsNames[0] = NUMBER_OF_LABELS + " = " + m_numberOfOLabels.getIntValue();
			settingsNames[1] = T_NORM + " = " + m_tNorm.getStringValue();
			settingsNames[2] = RULE_WEIGHT + " = " + m_ruleWeight.getStringValue();
			settingsNames[3] = FUZZY_REASONING_METHOD + " = " + m_fuzzyReasoningMethod.getStringValue();
			return settingsNames;	
		}
		
		
	    @Override
	    public SettingsModel[] getNodeSettings() {
			SettingsModel[] settingsValues = new SettingsModel[4];
			settingsValues [0] = m_numberOfOLabels;
		    settingsValues [1] = m_tNorm;
		    settingsValues [2] = m_ruleWeight;
		    settingsValues [3] = m_fuzzyReasoningMethod;
			return settingsValues;
		}
}
