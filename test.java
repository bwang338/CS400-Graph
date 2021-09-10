import java.util.List;
public class test {

	public static void main(String[] args) {
		CS400Graph<String> graph = new CS400Graph<>();
		try{
		FileReader.loadFile(graph, "SampleFlightNetwork.txt");
		} catch(Exception e) {
		}
		List<String> path = graph.shortestPath("Chicago", "Dane County");
	}
}
