import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileObject {

	//Instance variables
	private String filename;
	private File textfile;
	private static SearchObject[] searchObjects;
	private int numOfSearchObjects;
	
	//Constructor
	public FileObject(String filename) {
		this.filename = filename;
		searchObjects = new SearchObject[400];
		getFile();
		numOfSearchObjects = 0;
	}//end constructor
	
	public String getName(){
		return filename;
	}//end getName
	
	public SearchObject[] getSearchObjects(){
		return searchObjects;
	}//end getSearchObjects
	
	public static void resize(){
		SearchObject[] newArray = new SearchObject[searchObjects.length*2];
		for(int i = 0; i<searchObjects.length; i++){
			newArray[i] = searchObjects[i];
		}
		searchObjects = newArray;
	}
	
	//Function gets the file from the filename and puts it in a File object
	public void getFile(){
		textfile = new File(filename);
		try{
			System.out.println(filename+" loaded!");
			Scanner myScanner = new Scanner(textfile);
			int lineNumber = 1;
			while(myScanner.hasNextLine()){
				if(lineNumber > searchObjects.length)
					resize();
				String lineOfText = myScanner.nextLine();
				String lineOfText2 = lineOfText.replaceAll("[!?,']", "");
				String[] words = lineOfText2.split(" ");
				for(int i = 0; i<words.length; i++){
					SearchObject searchTerm = new SearchObject(filename, words[i], lineOfText, lineNumber);
					searchObjects[numOfSearchObjects] = searchTerm;
					numOfSearchObjects++;
				}
				lineNumber++;
			}//end while
			myScanner.close();
		} catch (FileNotFoundException exception) {
			System.out.println("File not found.");
		}//end catch
	}//end getFile
	
}
