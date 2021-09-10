//--== CS400 File Header Information ==--
//Name: Neel Murthy
//Email: nmurthy@wisc.edu
//Team: BA
//TA: Brianna Cochran
//Lecturer: Gary Dahl
//Notes to Grader:
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.InputMismatchException;
class TestClass2 {
	
	/**
	 * checks the file reader loadFile test to make sure it works. 
	 * Sees if the correct vertices and edges are returned when 
	 * SampleFlightNetwork is loaded in
	 */
	@Test
	void fileReaderTest()
	{
		CS400Graph flightMap=new CS400Graph();
		try {
		FileReader.loadFile(flightMap, "SampleFlightNetwork.txt");
		}
		catch(IOException e1)
		{
			fail("Error");
		}
		assertTrue(flightMap.containsVertex("JFK")&&flightMap.containsVertex("Chicago"));
		assertTrue(flightMap.containsEdge("JFK", "Chicago") && flightMap.containsEdge("Chicago", "Dane County"));
	}
	
	/**
	 * checks the ShortestPathToString class to make sure output is correctly
	 * formatted and the correct output is returned
	 * Test based on case that the path is a direct flight
	 * and tests based on another case that the path has a connection
	 */
	@Test
	void backEnd1Test() {
		CS400Graph<String> flightMap=new CS400Graph<String>();
		flightMap.insertVertex("Chicago");
		flightMap.insertVertex("Dallas");
		flightMap.insertVertex("New York");
		flightMap.insertEdge("Chicago", "Dallas", 300, 800);
		flightMap.insertEdge("Dallas", "New York", 300, 1300);
		if(!ShortestPathToString.findShortestPath(flightMap, "Chicago", "Dallas")
				.equals("This is a direct flight from: Chicago to Dallas leaving at 08:00, with a total of 300 miles."))
		{
			fail("Not the right message");
		}
		if(!ShortestPathToString.findShortestPath(flightMap, "Chicago", "New York")
				.equals("Chicago to Dallas (300 miles) at 08:00\n"
						+ " to New York (300 miles) at 13:00\n"
						+ "Total distance of your flight: 600 miles). Leaving at 08:00"))
		{
			fail("Not the right message");
		}
	}
	/**
	 * checks the BackEnd class to make sure output is correctly
	 * formatted and the correct output is returned
	 * Test based on case that the path is a direct flight
	 */
	@Test
	void backEnd2Test() {
		CS400Graph<String> flightMap=new CS400Graph<String>();
		flightMap.insertVertex("Chicago");
		flightMap.insertVertex("Dallas");
		flightMap.insertEdge("Chicago", "Dallas", 300, 400);
		if(!BackEnd.shortestPathToString("Chicago", "Dallas", flightMap)
				.equals("Start city: \n" + 
						"ChicagoEnd city: \n" + 
						"DallasDistance: 300Start city: \n" + 
						"ChicagoEnd city: \n" + 
						"DallasDistance: 300"))
		{
			fail("Not the right message");
		}
	}

	
	
}
