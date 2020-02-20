//BY Evan Rome-Bailey eromebai@uwo.ca Student #250867976

// A class used to store configurations so that they can be easily made into a linked list
public class LinkNode {
	Configuration data;
	LinkNode next = null;
	
	// Makes a new node containing the given configuration
	public LinkNode(Configuration config){
		data = config;
	}
	
	// Stores the address of another node, the next in the linked list
	public void SetNext(LinkNode nxt){
		next = nxt;
	}
	
	// Gets the address for the next node in the list
	public LinkNode GetNext(){
		return next;
	}
	
	// Returns the configuration data
	public Configuration GetData(){
		return data;
	}

}
