package subseq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class ReadInput {
	private String txtPath;
	private String PatternPath;
	private Scanner input;
	private int Pattern_Count;
	private String text;
	private ArrayList<String> pattern=new ArrayList<String>();
	public ReadInput(String txtPath,String PatternPath) {
		this.PatternPath=PatternPath;
		this.txtPath=txtPath;
	}
	public ArrayList<String> My_Pattern() {
		open_txt_File(PatternPath);
		read_pattern();
		close_text_File();
		return pattern;
	}
	public int Num_of_patterns() {
		return Pattern_Count;
	}
	private void open_txt_File(String path) {
		try {
			input=new Scanner(new File(path));
		}
		catch(FileNotFoundException e) {
			System.err.println("Error opening file");
			System.exit(1);
		}
	}
	private void open_Sentence_File(String path) {
		try {
			input=new Scanner(new File(path));
		}
		catch(FileNotFoundException e) {
			System.err.println("Error opening file");
			System.exit(1);
		}
	}
	public String Mytext() {
		open_Sentence_File(txtPath);
		read_text();
		close_text_File();
		text+='$';
		//System.out.println(text.length());
		return text;
	}
	
	private void read_text() {
			try {
			while(input.hasNextLine()) {
				text=(input.next());
			}
			
		}catch(NoSuchElementException e2) {
			System.err.println("file improperly formed");
			input.close();
			System.exit(1);
		}
		catch (IllegalStateException e1) {
			System.err.println("error reading file");
			System.exit(1);
		}
	}
	private void close_text_File() {
		if(input !=null)
			input.close();
	}
	private void read_pattern() {
		try {
		int count=0;
		Pattern_Count=Integer.parseInt(input.nextLine());
		while(count<Pattern_Count) {
			pattern.add((input.nextLine()));
			count++;
		}		
	}catch(NoSuchElementException e2) {
		System.err.println("file improperly formed");
		input.close();
		System.exit(1);
	}
	catch (IllegalStateException e1) {
		System.err.println("error reading file");
		System.exit(1);
	}
}
	
	
	
	
	
	
}
