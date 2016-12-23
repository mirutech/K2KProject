package org.mirkorusso.K2KChiRWC.K2K;

import java.io.IOException;

import org.knime.core.node.BufferedDataTable;

/**
 * KNIME2KEEL was designed to convert data from the format of the KNIME KEEL format.
 * It extracts the dataset and associated metadata from a BufferedDataTable and encodes them in the KEEL input file format.
 */
public interface KNIME2KEEL {
	
	/**
	 * Procedure that creates the configuration file for KEEL.
	 * 
	 * @param algorithmName Name of the algorithm.
	 * @param resultFilesDirectory Directory path where the KEEL algorithm will create result file.
	 * @param KEELSettings Vector containing the algorithm settings.
	 * 
	 * @throws IOException If the config file cannot be created.
	 */
	void configFileCreator(String algorithmName, String resultFilesDirectory, String[] KEELSettings) throws IOException;

	/**
	 * Procedure that creates the file in .dat format for KEEL containing the training dataset or the testing dataset.
	 * 
	 * @param dataFileName Name of the the dat file that must be created .
	 * @param inData BufferedDataTable from which will be extracted the dataset and metadata to generate the .dat file.
	 * 
	 * @throws IOException If the data file cannot be created.
	 */
	void dataFileCreator(String dataFileName, BufferedDataTable inData) throws IOException;

}