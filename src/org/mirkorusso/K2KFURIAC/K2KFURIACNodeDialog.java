package org.mirkorusso.K2KFURIAC;

import org.knime.core.data.StringValue;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * K2KFURIACNodeDialog extends the NodeDialog class and creates the node 
 * configuration window in which will be inserted the configuration parameters 
 * required for the execution of the algorithm.
 * 
 * @author Mirko Russo
 */
public class K2KFURIACNodeDialog extends DefaultNodeSettingsPane {

	/**
     * Constructor who creates the node configuration window.
     */
	@SuppressWarnings("unchecked")
	protected K2KFURIACNodeDialog() {
        super();
  
        addDialogComponent(new DialogComponentNumber(new SettingsModelInteger
        		(KEELAlgorithmFURIAC.NUMBER_OF_OPTIMIZATIONS, 2), "Number of optimizations:", 1));
        addDialogComponent(new DialogComponentNumber(new SettingsModelInteger
        		(KEELAlgorithmFURIAC.NUMBER_OF_FOLDS, 3), "Number of folds:", 1));
        addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelString(K2KFURIACNodeModel.CFGKEY_COLUMN_NAME, "columnName"), 
                "Class column:", K2KFURIACNodeModel.IN_PORT, StringValue.class));   
        addDialogComponent(new DialogComponentString(new SettingsModelString
        		(K2KFURIACNodeModel.PREDICTION_NAME, null), "Prediction column name:"));
    }
}

