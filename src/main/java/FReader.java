import java.io.*;
import java.util.*;
import javax.swing.*;

class WriteFile{
	String path="";
	boolean appendToFile=false;
	public WriteFile(String name){
		path = name;
	}
	
	public WriteFile(String name, boolean append){
		path = name;
		appendToFile=append;
	}
	
	public void writeToFile(String content) throws IOException{
		FileWriter write = new FileWriter(path, appendToFile);
		PrintWriter print_line = new PrintWriter(write);
		print_line.printf("%s"+"%n", content);
		print_line.close();
	}
}

public class FReader {
  ArrayList<String> content = new ArrayList<String>();;
  
  //filereader takes file, creates arrayList of strings
  public FReader(File path) throws IOException{
    BufferedReader bReader = new BufferedReader(new FileReader(path));
    String line;
    while ((line = bReader.readLine()) != null)
      content.add(line);
    bReader.close();
  }
  
  //returns arraylist of file-row strings
  public ArrayList<String> getContent(){
    return content;
  }
}