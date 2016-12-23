package org.mirkorusso.K2KChiRWC.K2K;

import java.io.IOException;
import java.util.Hashtable;

import org.knime.core.node.BufferedDataTable;


/**
 * KEEL2KNIME was designed to convert data from the KEEL format to the KNIME format. 
 * It extracts the results from KEEL output file and create a BufferedDataTable composed of 
 * extracts results and to the data contained in the original BufferedDataTable.
 */
public interface KEEL2KNIME {

	/**
	 * Function that extracts the results of an KEEL output file and encodes them into an hash table.
	 * 
	 * @param resultsFileName Name of the KEEL output file.
	 * @return Hash Table containing results.
	 * 
	 * @throws IOException If the results file cannot be read.
	 */
	Hashtable<Integer, String> resultsExtractor(String resultsFileName) throws IOException;
	
	/**
	 * Function that integrates the results of executing an algorithm KEEL with the initial BufferedDataTable 
	 * to create a new BufferedDataTable representing the final result.
	 * 
	 * @param predictions Hash Table containing KEEL Algorithm's results.
	 * @param inData Initial dataset.
	 * 
	 * @return BufferedDataTable containing initial dataset and KEEL Algorithm's results.
	 */
	BufferedDataTable outDataCreator(BufferedDataTable inData, Hashtable<Integer, String> predictions);

}