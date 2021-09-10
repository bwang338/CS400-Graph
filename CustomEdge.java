// --== CS400 File Header Information ==--
// Name: Ian Koh
// Email: iskoh@wisc.edu
// Team: BA
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

public class CustomEdge extends CS400Graph<String>.Edge {

	public int time;
	
	public CustomEdge(CS400Graph<String> graph, CS400Graph<String>.Vertex target, int weight, int time) {
		graph.super(target, weight);
		this.time = time;
	}
	
	public static boolean insertCustomEdge(CS400Graph<String> graph, String source, String target, int weight, int time) {
		
        if(source == null || target == null) 
            throw new NullPointerException("Cannot add edge with null source or target");
        
        CS400Graph<String>.Vertex sourceVertex = graph.vertices.get(source);
        CS400Graph<String>.Vertex targetVertex = graph.vertices.get(target);
        if(sourceVertex == null || targetVertex == null) 
            throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
        
        if(weight < 0) 
            throw new IllegalArgumentException("Cannot add edge with negative weight");
        
        // handle cases where edge already exists between these vertices
        for(CS400Graph<String>.Edge edge : sourceVertex.edgesLeaving) {
        	CustomEdge e = (CustomEdge)edge;
            if(e.target == targetVertex && e.time == time) {
                if(e.weight == weight) return false; // edge already exists
                else e.weight = weight; // otherwise update weight of existing edge
                return true;
            }
        }
        
        // otherwise add new edge to sourceVertex
        sourceVertex.edgesLeaving.add(new CustomEdge(graph, targetVertex, weight, time));
        return true;
    }  
	
}
