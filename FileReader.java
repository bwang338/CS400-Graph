// --== CS400 File Header Information ==--
// Name: Ian Koh
// Email: iskoh@wisc.edu
// Team: BA
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;

public class FileReader {

    public static void loadFile(CS400Graph<String> network, String filename) throws IOException, InputMismatchException {

	File file = new File(filename);

	Scanner sc;
	try {
	    sc = new Scanner(file);
	} catch (IOException e) {
	    throw new IOException("Unable to access file");
	}

	String line = "";
	String[] elems = null;
	while(sc.hasNextLine()) {

	    line = sc.nextLine().trim();
	    if (line.contains(",")) {
		elems = line.split(",");
		for (int i = 0; i < elems.length; i++)
		    elems[i] = elems[i].trim();

		if (elems.length < 4) throw new InputMismatchException("Invalid data format");
		
		network.insertVertex(elems[0]);
		network.insertVertex(elems[1]);

		for (int i = 3; i < elems.length; i++) {
		    CustomEdge.insertCustomEdge(network, elems[0], elems[1], Integer.parseInt(elems[2]), Integer.parseInt(elems[i]));
		}
	    } else {
		network.insertVertex(line);
	    }
	    
	}

    }
    
}
