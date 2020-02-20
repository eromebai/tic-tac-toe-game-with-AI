//BY Evan Rome-Bailey eromebai@uwo.ca Student #250867976
public class BoardGame {
	
	private int size;
	private int empty;
	private int levels;
	private char[][] gameBoard;
	
	// Constructor that creates the game board using a 2D character array and initializes it
	public BoardGame(int board_size, int empty_positions, int max_levels){
		size = board_size;
		empty = empty_positions;
		levels = max_levels;
		char[][] emptyBoard = new char[size][size];
		gameBoard = initialize(emptyBoard);
	}
	
	// Sets all elements of the array to the character 'g' representing an empty space
	private char[][] initialize(char[][] board){
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = 'g';
			}
		}
		return board;
	}
	
	//Creates a hashdictionary object
	public HashDictionary makeDictionary(){
		HashDictionary dict = new HashDictionary(8191);
		
		return dict;
	}
	
	// Creates a string version of the game board to be used by other methods
	private String getboardRep(){
		String board = new String();
				
		for(int i = 0; i < size; i++){
			for(int j = 0; i < size; i++){
				board = board + gameBoard[j][i];
			}
		}
		return board;
	}
	
	// Checks if a configuration is already in the dictionary
	public int isRepeatedConfig(HashDictionary dict){
		
		return dict.getScore(getboardRep());		
	}
	
	// Puts the current game configuration in the dictionary
	public void putConfig(HashDictionary dict, int score){
		
		try{
			dict.put(new Configuration(getboardRep(), score));
		}
		catch(DictionaryException e){
		}
	}
	
	// Saves a play that was made by the human or the computer
	public void savePlay(int row, int col, char symbol){
		
		gameBoard[row][col] = symbol;
	}
	
	// Checks if a given position contains the character 'g' representing an empty space
	public boolean positionIsEmpty(int row, int col){
		
		if(gameBoard[row][col] == 'g'){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	// Checks if a given position contains the character 'o' representing a computer-owned tile
	public boolean tileOfComputer(int row, int col){
		
		if(gameBoard[row][col] == 'o'){
			return true;
		}
		
		else{
			return false;
		}
		
	}
	
	// Checks if a given position contains the character 'b' representing a human-owned tile
	public boolean tileOfHuman(int row, int col){
		
		if(gameBoard[row][col] == 'b'){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	// Checks if either human or computer wins using private functions
	public boolean wins(char symbol){
		return(checkRows(symbol)||checkCols(symbol)||checkDiags(symbol));
	}
	
	// Checks all rows of the game board for n human or computer tiles in a row
	private boolean checkRows(char symbol){
		boolean won;
		
		for(int i = 0; i < size; i++){
			won = true;
			for(int j = 0; j < size; j++){
				if(gameBoard[i][j] != symbol){
					won = false;
				}
			}
			if(won == true){
				return true;
			}
		}
		return false;
	}
	
	// Checks all columns of the game board for n human or computer tiles in a row
	private boolean checkCols(char symbol){
		boolean won;
		
		for(int i = 0; i < size; i++){
			won = true;
			for(int j = 0; j < size; j++){
				if(gameBoard[j][i] != symbol){
					won = false;
				}
			}
			if(won == true){
				return true;
			}
		}
		return false;
	}
	
	// Checks both diagonals of the game board for n human or computer tiles in a row
	private boolean checkDiags(char symbol){
		
		boolean won = true;
		for(int i = 0; i < size; i++){
			if(gameBoard[i][i] != symbol){
				won = false;
			}
		}
		
		if(won == true){
			return true;
		}
		
		won = true;
		for(int i = 0; i < size; i++){
			if(gameBoard[i][(size - (i+1))] != symbol){
				won = false;
			}
		}
		if(won == true){
			return true;
		}
		return false;
	}
	
	// Private function used to check the spaces around remaining empty tiles for possible available moves for the given symbol
	private boolean checkdraw(char symbol){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(gameBoard[i][j] == 'g'){
					for(int k = -1; k < 3; k++){
						for(int h = -1; h < 3; h++){
							if((!((i+k)<0))&&(!((i+k)>size-1))&&(!((j+h)<0))&&(!((j+h)>size-1))&&(!((k==0))&&((h==0)))){
								if(gameBoard[i+k][j+h]==symbol){
									return false; 
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	// Private function that checks how many empty spaces are left
	private int gcount(){
		int count = 0;
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(gameBoard[i][j] == 'g'){
					count+=1;
				}
			}
		}
		return count;
	}
	
	// Checks conditions for a draw and returns true if one has occurred
	public boolean isDraw(char symbol, int empty_positions){
		
		if(wins('o') || wins('b')){
			return false;
		}
						
		else if(gcount() == empty_positions){
			return(checkdraw(symbol));
		}
		
		else{
			return false;
		}
		
	}
	
	// Gives the board state a score: 3 for computer wins, 0 for human wins, 2 for draw, 1 if undecided 
	public int evalBoard(char symbol, int empty_positions){
		
		if(wins('o')){
			return 3;
		}
		
		else if(wins('b')){
			return 0;
		}
		
		else if(isDraw(symbol, empty_positions)){
			return 2;
		}
		
		else{
			return 1;
		}
	}

}
