//BY Evan Rome-Bailey eromebai@uwo.ca Student #250867976

// A storage object for board configurations and their given scores
public class Configuration {
	
	private String conf;
	private int scor;
	
	public Configuration(String config, int score) {
		conf = config;
		scor = score;
	}
	
	public String getStringConfiguration(){
		return conf;
	}
	
	public int getScore(){
		return scor;
	}

}
