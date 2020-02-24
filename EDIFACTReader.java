import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EDIFACTReader {

	public static void main(String[] args) {
		
		List<String> parsedEDIFACT = new ArrayList<String>();
	
		try {
			
			//creating file and file reader objects so as to take in the .txt file with the EDIFACT information
			//the buffered reader object is to improve reader performance
			
			File employeeFile = new File("edifact.txt");
			FileReader fileReader = new FileReader(employeeFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			
			while ((line = reader.readLine()) !=null) {
				
				//reading the text file line by line and printing it to console while line is not null
				//System.out.println(line);
				
				//searching for LOC segments, removing the ' at the end of each statement
				String processedLine = line.substring(0, line.length()-1);
				
				//assigning + as the delimiter. + is a reserved character, so I enter \\+ into the method
				String [] elements =  processedLine.split("\\+");
				
				if (elements[0].equals("LOC")) {
					//System.out.println("Found LOC");
						
						for (int i=1; i <elements.length; i++) {
							//System.out.println(elements[i]);
							
							//if it is the second or third element
							if (i == 1 || i == 2) {
								parsedEDIFACT.add(elements[i]);
							}
						
					}
				}			
			}
			//end while loop
			
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		//end try catch
		
		//printing the captured elements
		for (String word : parsedEDIFACT) {
			System.out.println(word);
		}
		
	}

}
