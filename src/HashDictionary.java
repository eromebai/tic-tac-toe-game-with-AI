//BY Evan Rome-Bailey eromebai@uwo.ca Student #250867976
public class HashDictionary implements DictionaryADT {
	private int tablesize;
	private LinkNode[] table;
	
	// Constructor which creates a Node table of the given length and initializes all entries to null
	public HashDictionary(int length){
		tablesize = length;
		table = new LinkNode[tablesize];
		
		for(int i = 0; i<tablesize; i++){
			table[i]=null;
		}
	}
	
	// The hash function used by this class to turn string keys into integer locations in the stored table
	private int hashfunction(String config){
		int length = config.length();
		int hashcode = 7;
		for(int i = 0; i<length; i++){
			hashcode = (hashcode * ((int)config.charAt(i)) + 37);
			hashcode = hashcode%tablesize;
		}
		return hashcode;
	}
	
	//The put function which uses linear search to check if the entry already exists
	public int put(Configuration data) throws DictionaryException{
 		int code = hashfunction(data.getStringConfiguration());
		int checker;
		
		if(table[code] == null){
			checker = 0;
			table[code] = new LinkNode(data);
		}
		else{
			LinkNode location = table[code];
			while(data.getStringConfiguration()!= location.GetData().getStringConfiguration() && location.GetNext() != null){
				location = location.GetNext();
			}
			if(data.getStringConfiguration()  == location.GetData().getStringConfiguration()){
				throw new DictionaryException("");
			}
			else{
				location.SetNext(new LinkNode(data));
				checker = 1;
			}
		}
		return checker;
		
	}
	
	//The remove function which throws an exception if the entry does not exist in the dictionary
	public void remove(String config) throws DictionaryException{
		int code = hashfunction(config);
		
		if(table[code] == null){
			throw new DictionaryException("");
		}
		
		LinkNode location = table[code];
			while(location.GetNext()!= null && location.GetNext().GetData().getStringConfiguration() != config){
			location = location.GetNext();
		}
		
		if(location.GetData().getStringConfiguration() == config){
			table[code] = null;
		}
		else if(location.GetNext() == null){
			throw new DictionaryException("");
		}
		
		else if(location.GetNext().GetData().getStringConfiguration() == config){
			location.SetNext(location.GetNext().GetNext());
		}
		
		else{
			throw new DictionaryException("");
		}
	}
	
	// This function returns the score data which is stored in a Node data type
	public int getScore(String config){
		int code = hashfunction(config);
		
		if(table[code] == null){
			return -1;
		}
		
		else{
			LinkNode location = table[code];
			while(location.GetNext() != null && location.GetData().getStringConfiguration() != config){
				location = location.GetNext();
			}
			
			if (location.GetData().getStringConfiguration() == config){
				return location.GetData().getScore();
			}
			else{
				return -1;
			}
		}
	}

}
