package Project;

public class SearchObject{

	private String filename, searchterm, context;
	private int line;

	//Constructor requires file name, search term, its context, and the line number
	public SearchObject(String filename, String searchterm, String context, int line) {
		super();
		this.filename = filename;
		this.searchterm = searchterm;
		this.context = context;
		this.line = line;
	}//end constructor

	//Getters and Setters
	public String getFilename() {
		return filename;
	}//end getFilename
	
	public int getLine(){
		return line;
	}//end getLine

	public void setFilename(String filename) {
		this.filename = filename;
	}//end setFileName

	public String getSearchterm() {
		return searchterm;
	}//end getSearchterm

	public void setSearchterm(String searchterm) {
		this.searchterm = searchterm;
	}//end setSearchTerm

	public String getContext() {
		return context;
	}//end getContext

	public void setContext(String context) {
		this.context = context;
	}//end setContext
	
	//Constructs the "found" message for the screen
	public String messageForScreen(){
		return searchterm+" found on line "+line+" in file "+filename+"    Context in file: "+"\""+context+"\"";
	}//end messageForScreen
	
}
