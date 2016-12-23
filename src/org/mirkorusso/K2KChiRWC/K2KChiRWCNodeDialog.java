package org.mirkorusso.K2KChiRWC;

import org.knime.core.data.StringValue;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * K2KChiRWCNodeDialog extends the NodeDialog class and creates the node 
 * configuration window in which will be inserted the configuration parameters 
 * required for the execution of the algorithm.
 * 
 * @author Mirko Russo
 */
public class K2KChiRWCNodeDialog extends DefaultNodeSettingsPane {

	/**
     * Constructor who creates the node configuration window.
     */
	@SuppressWarnings("unchecked")
	protected K2KChiRWCNodeDialog() {
        super();
  
        addDialogComponent(new DialogComponentNumber(new SettingsModelInteger
        		(KEELAlgorithmChiRWC.NUMBER_OF_LABELS, 3), "Number of Labels:", 1));
        addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString(KEELAlgorithmChiRWC.T_NORM, "Product"),
                "T-norm for the Computation of the Compatibility Degree:", "Product", "Minimum"));
        addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString(KEELAlgorithmChiRWC.RULE_WEIGHT, "Penalized_Certainty_Factor"),
                "Rule Weight:", "No_Weights", "Certainty_Factor", "Average_Penalized_Certainty_Factor", "Penalized_Certainty_Factor"));
        addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString(KEELAlgorithmChiRWC.FUZZY_REASONING_METHOD, "Winning_Rule"),
                "Fuzzy Reasoning Method:", "Winning_Rule", "Additive_Combination"));
        addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelString(K2KChiRWCNodeModel.CFGKEY_COLUMN_NAME, "columnName"), 
                "Class column:", K2KChiRWCNodeModel.IN_PORT, StringValue.class));   
        addDialogComponent(new DialogComponentString(new SettingsModelString
        		(K2KChiRWCNodeModel.PREDICTION_NAME, null), "Prediction column name:"));
    }
}

