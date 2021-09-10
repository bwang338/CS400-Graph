import java.util.List;

//--== CS400 File Header Information ==--
//Name: Ryan Szymanski
//Email: rpszymanski@wisc.edu
//Team:BA
//Role: Back End Developer 1 
//TA:Brianna
//Lecturer: Florian 
//Notes to Grader: <optional extra notes>
public class ShortestPathToString {

interface shortestPath {
 String findShortestPath(String flightMap, String prevDes);
}

/**
* 
* @param graph Map graph being used
* @param start Starting address
* @param end   Destination address
* @return String representation of the shortest path from the start to end addresses, separated
*         by lines. Distances for each leg and the total trip are also included.
*/
public static String findShortestPath(CS400Graph<String> graph, String start, String end) {

 List<String> flightPath = graph.shortestPath(start, end);

 if (flightPath.size() == 2) {
   int time = graph.getTime(start, end);
   String direct = "This is a direct flight from: " + flightPath.get(0) + " " + flightPath.get(1)
       + " leaving at " + time + ", with a total of " + graph.getWeight(start, end) + " miles.";
   return direct;
 }

 List<String> path = graph.shortestPath(start, end);
 //int time = graph.getTime(start, end);
 String flightMap = path.get(0);
 String prevDes = path.get(0);
 path.remove(0);
 int[] times=new int[10];
 shortestPath returnPath = (String x, String y) -> {

  int i=0;
   for (String currDes : path) {
   times[i]=graph.getTime(y,currDes);
     x += " to " + currDes + " (" + graph.getWeight(y, currDes) + " miles) at "+times[i]+ "\n";
     y = currDes;
     i++;
   }

   x += "Total distance of your flight: " + graph.getPathCost(start, end)
       + " miles). Leaving at "+times[0];

   return x;
 };

 return returnPath.findShortestPath(flightMap, prevDes);
}
}