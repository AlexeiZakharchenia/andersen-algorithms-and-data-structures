package breadthFirstSearch;

import java.util.*;

public class Graph {
    private List<GraphNode> graphNodes;

    public Graph(List<GraphNode> graphNodes) {
        this.graphNodes = graphNodes;
    }

    public List<GraphNode> getGraphNodes() {
        return graphNodes;
    }

    public void setGraphNodes(List<GraphNode> graphNodes) {
        this.graphNodes = graphNodes;
    }

    public static void main(String[] args) {
        GraphNode a = new GraphNode(1, new ArrayList<>());
        GraphNode b = new GraphNode(2, new ArrayList<>());
        GraphNode c = new GraphNode(3, new ArrayList<>());
        GraphNode d = new GraphNode(4, new ArrayList<>());
        GraphNode e = new GraphNode(5, new ArrayList<>());
        GraphNode f = new GraphNode(6, new ArrayList<>());
        GraphNode g = new GraphNode(7, new ArrayList<>());
        GraphNode h = new GraphNode(8, new ArrayList<>());
        b.setLinks(Arrays.asList(a, c));
        f.setLinks(Arrays.asList(g, h));
        d.setLinks(Arrays.asList(b, e, f));
        e.setLinks(Arrays.asList(c, f));
        Graph graph = new Graph(Arrays.asList(a, b, c, d, e, f, g, h));
        System.out.println(breadthFirstSearch(graph, 5, 7));
    }

    public static boolean breadthFirstSearch(Graph graph, int startId, int endId) {
        GraphNode start = graph.getGraphNodes().stream()
                            .filter(x -> x.getId() == startId).findFirst().orElse(null);
        GraphNode end = graph.getGraphNodes().stream()
                            .filter(x -> x.getId() == startId).findFirst().orElse(null);
        if (start == null || end == null) {
            return false;
        }

        List<GraphNode> checked = new LinkedList<>();
        LinkedList<GraphNode> queue = new LinkedList<>(start.getLinks());

        while (queue.size() != 0) {
            GraphNode tmp = queue.pollFirst();
            System.out.println(tmp.getId() + " ");
            if (!checked.contains(tmp)) {
                checked.add(tmp);
                if (tmp.getLinks().contains(end)) {
                    return true;
                } else {
                    checked.add(tmp);
                    queue.addAll(tmp.getLinks());
                }
            }
        }
        return false;
    }


}
