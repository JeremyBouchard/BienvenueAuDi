package read;


/** The abstract class for reading the file '.csv'. 
 * @author GAUCHER_François, LI_Yuanyuan.
 *
 */
public abstract class ReadCSV {
	/**
	 * The filePath of the file '.csv'.
	 */
	private String path;

	/**
	 * @return the filePath of the file '.csv'. 
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path : the filePath of the file '.csv'. 
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Read the file '.csv' and convert the content to different specific types.
	 */
	public abstract void convertCSV();
}
