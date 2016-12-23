package org.mirkorusso.K2KC45;

import org.knime.core.data.StringValue;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * K2KC45NodeDialog extends the NodeDialog class and creates the node 
 * configuration window in which will be inserted the configuration parameters 
 * required for the execution of the algorithm.
 * 
 * @author Mirko Russo
 */
public class K2KC45NodeDialog extends DefaultNodeSettingsPane {

	/**
     * Constructor who creates the node configuration window.
     */
	@SuppressWarnings("unchecked")
	protected K2KC45NodeDialog() {
        super();

        addDialogComponent(new DialogComponentBoolean(new SettingsModelBoolean
        		(KEELAlgorithmC45.PRUNED, true), "Pruned"));
        addDialogComponent(new DialogComponentNumber(new SettingsModelDouble
        		(KEELAlgorithmC45.CONFIDENCE, .25),"Confidence:", 0.01));
        addDialogComponent(new DialogComponentNumber(new SettingsModelInteger
        		(KEELAlgorithmC45.INSTANCES_PER_LEAF, 2), "Instances per leafe:", 1));
        addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelString(K2KC45NodeModel.CFGKEY_COLUMN_NAME, "columnName"), 
                "Class column:", K2KC45NodeModel.IN_PORT, StringValue.class));   
        addDialogComponent(new DialogComponentString(new SettingsModelString
        		(K2KC45NodeModel.PREDICTION_NAME, null), "Prediction column name:"));
    }
}

