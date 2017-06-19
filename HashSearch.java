import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class HashSearch {
	int fileNum; //number of files 
	static LinkedHashMap<String, List<SearchObject>> myMap; //map
	
	public HashSearch(){ //constructor
		fileNum = 0;
		myMap = new LinkedHashMap<String, List<SearchObject>>();
	}
	
	public void runProgram(){ //runs the program by starting with getFiles
		getFiles();
	}//end runProgram
	
	public void getFiles(){ // gets a file from a user and loads search terms into map
		Scanner fromKb = new Scanner(System.in); //create Scanner object
		boolean ended = false; 
		while(ended == false){ //while user wishes to continue, load files
			System.out.print("Enter the name of a file to load: "); //prompt for user
			String filename = fromKb.next(); //get file name from user
			FileObject newFile = new FileObject(filename); //create new file object
			fileNum++; //increment number of files
			SearchObject[] searchObjects = newFile.getSearchObjects();
			for(int j = 0; j<searchObjects.length; j++){
				if(searchObjects[j] != null && !myMap.containsKey(searchObjects[j].getSearchterm().toLowerCase())){ 
					//if map doesn't contain the search term yet, make a new entry
					myMap.put(searchObjects[j].getSearchterm().toLowerCase(), new ArrayList<SearchObject>());
				}//end if
				//Add a new "searchTerm object" to that LinkedList
				if(searchObjects[j] != null)
					myMap.get(searchObjects[j].getSearchterm().toLowerCase()).add(searchObjects[j]); 
			}//end for 
			
			//Ask if the user wishes to continue entering files
			System.out.println("\nContinue entering files? Y/N");
			String choice = fromKb.next();
			if(choice.equalsIgnoreCase("N"))
				ended = true; //end process and proceed to search if user enters N
			}//end while
			performSearch();
	}//end getFiles
	
	public void priorityResults(String input, List<SearchObject> myList){
		Iterator<SearchObject> it = myList.iterator();
		while(it.hasNext()){
			SearchObject current = it.next();
			if(current.getContext().toLowerCase().contains(input.toLowerCase())){
				//exact match has been found: Print priority result to screen
				System.out.println("PRIORITY RESULT: "+input+" found on line "+current.getLine()+" in file "+current.getFilename()+
						"    Context in file: "+current.getContext());
			}
		}
	}
	
	public void performSearch(){ //gets a search term from the user and performs a search
		boolean continueSearch = true;
		while(continueSearch == true){ //continue searching while user wishes to continue
			Scanner fromKb = new Scanner(System.in); //create Scanner object
			System.out.print("Enter search term: ");
			String inputsearchTerm = fromKb.nextLine().toLowerCase(); //get searchTerm from user using Scanner
			String searchTerm = inputsearchTerm.replaceAll("[!?,']", ""); //remove punctuation from search term
			String[] searchTerms = searchTerm.split(" "); //split user's entry by spaces
			
			//Process Priority Results first. Check if entry contains a space and look for exact match
			if(inputsearchTerm.contains(" ")){
				String[] splitEntry = inputsearchTerm.split(" ");
				if(myMap.containsKey(splitEntry[0]))
					priorityResults(inputsearchTerm, myMap.get(splitEntry[0]));
			}//end if
			
			for(int i = 0; i<searchTerms.length; i++){ //look through all words (split from space) in the search query
				if(myMap.containsKey(searchTerms[i].toLowerCase())){
					
					//Initialize iterator to look the List associated with each matched Key
					Iterator<SearchObject> it = myMap.get(searchTerms[i].toLowerCase()).iterator();
					while(it.hasNext()){
						System.out.println(it.next().messageForScreen()); //print all results
					}//end while
				}//end if
			}//end for
			
			//Terminate program, search again, or return to entering files if necessary
			System.out.print("Continue searching? Y/N ");
			String otherchoice=fromKb.next();
			if(otherchoice.equalsIgnoreCase("N")){
				continueSearch = false;
				System.out.print("Continue entering files? Y/N ");
				String choice = fromKb.next();
				if(choice.equalsIgnoreCase("N")){
					System.out.println("\nHave a great day!"); //print nice message upon exit
					System.exit(0);
				}//end if
				else if(choice.equalsIgnoreCase("Y"))
					getFiles();
			}
		}
	}//end performSearch
			
	public static void main(String[] args){
		HashSearch mySearchProject = new HashSearch();
		mySearchProject.runProgram();
	}//end main
	

}//end class

