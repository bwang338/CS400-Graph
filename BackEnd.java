public class BackEnd {
	
	interface shortestPath
	{
		String shortestPathToString(String a, String b, CS400Graph<String> g);
	}
	
	public static String shortestPathToString(String start, String end, CS400Graph<String> g)
	{
		String output = "";
		for(int i = 0; i < g.dijkstrasShortestPath(start, end).dataSequence.size(); i++)
		{
			output += "Start city: \n" + start;
			output += "End city: \n" + end;
			output += "Distance: " + g.getPathCost(start, end);
		}
		return output;
		
	}
}