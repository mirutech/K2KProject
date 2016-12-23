package org.mirkorusso.K2KChiRWC.K2K;

import org.knime.core.node.defaultnodesettings.SettingsModel;

/**
 * KEELAlgorithm was designed to allow the proper execution of the algorithm of KEEL.
 * It executes the KEEL algorithm and store the configuration parameters of the algorithm and the input file.
 */
public interface KEELAlgorithm {

	/**
	 * Procedure that executes the KEEL algorithm.
	 * 
	 * @param configFilePath Path to the configuration file.
	 */
	void runKEELAlgorithm(String configFilePath);

	/**
	 * @return Vector containing the KEEL algorithm settings .
	 */
	String[] getKEELSettings();

	/**
	 * @return Vector containing the KEEL algorithm settings values, contained in SettingsModel items.
	 */
	SettingsModel[] getNodeSettings();

}