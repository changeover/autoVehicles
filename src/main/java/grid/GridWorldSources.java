package grid;


/**
 * Interface for a Source in this Project. 
 * This should be the necessary classes to add and Update a source
 * 
 * @author Joel Zimmerli, Kevin Streiter, Cesar De Carmo
 *
 * @param <T>
 */
public interface GridWorldSources<T> extends GridWorld<T> {
	
	/**
	 * Sets a source at the given coordinates, which can probably float around
	 * @param koordinates
	 */
	void addSource(int[] koordinates);
	
	/**
	 * Updates the source, if the layer just has one.
	 * @param koordinates
	 */
	void updateSource(int[] koordinates);
	

	
	T getMinValue();
	T getMaxValue();
	/**
	 * Deletes all Sources in the list
	 */
	void deleteSources();
}
