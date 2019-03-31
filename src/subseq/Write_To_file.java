package subseq;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Map;
import java.util.NoSuchElementException;
public class Write_To_file {
	private Formatter output;
	ArrayList<Integer> locations;
	String File_name;
	public Write_To_file(String s,ArrayList<Integer> locations) {
		this.locations=locations;
		File_name=s;
	}
	private void openfile() {
		try {
			output=new Formatter(File_name+".txt");
		}
		catch (SecurityException e) {
			System.err.println("access denied");
			System.exit(1);
		}catch (FileNotFoundException e1) {
			System.err.println("File not found");
			System.exit(1);
		}
			}
	private void Write() {
		try {
			for(int i=0;i<locations.size();i++) {
				output.format("%d \n", locations.get(i));
			}
			}catch (FormatterClosedException e) {
				System.err.println("errot writing to file");
			}
			catch (NoSuchElementException e1) {
				System.err.println("invalid input");
			}
	}
	public void wtire_To_File() {
		
	}
	private void closefile() {
		if(output!=null)
			output.close();
	}
	public void start() {
		openfile();
		Write();
		closefile();
	}
	}
