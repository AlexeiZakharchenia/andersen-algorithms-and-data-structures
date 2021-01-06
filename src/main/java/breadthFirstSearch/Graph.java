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
        GraphNode s = new GraphNode(9, new ArrayList<>());
        b.setLinks(Arrays.asList(a, c));
        f.setLinks(Arrays.asList(g, h));
        d.setLinks(Arrays.asList(b, e, f));
        e.setLinks(Arrays.asList(c, f));
        g.setLinks(Collections.singletonList(s));
        Graph graph = new Graph(Arrays.asList(a, b, c, d, e, f, g, h, s));
        System.out.println(breadthFirstSearch(graph, 4, 9));
    }

    public static List<Integer> breadthFirstSearch(Graph graph, int startId, int endId) {
        GraphNode start = graph.getGraphNodes().stream()
                .filter(x -> x.getId() == startId).findFirst().orElse(null);
        GraphNode end = graph.getGraphNodes().stream()
                .filter(x -> x.getId() == endId).findFirst().orElse(null);

        if (start == null || end == null) {
            return new LinkedList<>();
        }

        List<GraphNode> checked = new LinkedList<>();
        LinkedList<GraphNode> queue = new LinkedList<>(start.getLinks());

        Map<GraphNode, GraphNode> parents = new HashMap<>();
        start.getLinks().forEach(x -> parents.put(x, start));

        while (queue.size() != 0) {
            GraphNode tmp = queue.pollFirst();
            if (!checked.contains(tmp)) {
                checked.add(tmp);
                if (tmp.getLinks().contains(end)) {
                    parents.put(end, tmp);
                    break;
                } else {
                    checked.add(tmp);
                    queue.addAll(tmp.getLinks());
                    tmp.getLinks().stream().filter(x->!parents.containsKey(x)).forEach(x -> parents.put(x, tmp));
                }
            }
        }
        return getResultFromParents(parents, end);
    }

    public static List<Integer> getResultFromParents(Map<GraphNode, GraphNode> parents, GraphNode end){
        LinkedList<Integer> results = new LinkedList<>();
        GraphNode tmp = parents.get(end);

        while (tmp != null) {
            results.addFirst(tmp.getId());
            tmp = parents.get(tmp);
        }
        if (results.size() > 0) {
            results.addLast(end.getId());
        }
        return results;
    }
}
