package dijkstra;

import breadthFirstSearch.GraphNode;

import java.util.*;

public class DijkstraGraph {
    private List<DijkstraNode> nodes = new ArrayList<>();

    public void addNode(DijkstraNode node) {
        nodes.add(node);
    }

    public List<DijkstraNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<DijkstraNode> nodes) {
        this.nodes = nodes;
    }

    public static List<Integer> dijkstraAlgorithm(DijkstraGraph graph, Integer startId, Integer endId) {
        DijkstraNode start = graph.getNodes().stream()
                            .filter(x-> x.getId().equals(startId)).findFirst().orElse(null);
        DijkstraNode end = graph.getNodes().stream()
                            .filter(x-> x.getId().equals(endId)).findFirst().orElse(null);

        if (start == null || end == null) {
            return new LinkedList<>();
        }

        List<DijkstraNode> processed = new LinkedList<>();
        processed.add(start);

        Map<DijkstraNode, Integer> costs = new HashMap<>();
        graph.getNodes().forEach(x -> costs.put(x, Integer.MAX_VALUE));
        costs.putAll(start.getAdjacents());

        Map<DijkstraNode, DijkstraNode> parents = new HashMap<>();
        start.getAdjacents().forEach((key, value) -> parents.put(key, start));
        Map.Entry<DijkstraNode, Integer> entry = findMinDistanceEntry(costs, processed);

        while (entry != null) {
            DijkstraNode node = entry.getKey();
            Integer cost = costs.get(node);
            Map<DijkstraNode, Integer> neighbors = node.getAdjacents();
            for (DijkstraNode tmp : neighbors.keySet()) {
                Integer newCost = cost + neighbors.get(tmp);
                if (costs.get(tmp) > newCost) {
                    costs.put(tmp, newCost);
                    parents.put(tmp, node);
                }
            }
            processed.add(node);
            entry = findMinDistanceEntry(costs, processed);
        }

        return getResultFromParents(parents, end);
    }

    public static Map.Entry<DijkstraNode, Integer> findMinDistanceEntry(Map<DijkstraNode, Integer> map,
                                                                        List<DijkstraNode> processed) {
        Map.Entry<DijkstraNode, Integer> minDistanceEntry = null;
        for (Map.Entry<DijkstraNode, Integer> entry : map.entrySet()) {
            if ((minDistanceEntry == null || (minDistanceEntry.getValue() < entry.getValue())) && !processed.contains(entry.getKey())) {
                minDistanceEntry = entry;
            }
        }

        return minDistanceEntry;
    }

    public static List<Integer> getResultFromParents(Map<DijkstraNode, DijkstraNode> parents, DijkstraNode end){
        LinkedList<Integer> results = new LinkedList<>();
        DijkstraNode tmp = end;

        while ((tmp = parents.get(tmp)) != null) {
            results.addFirst(tmp.getId());
        }
        if (results.size() > 0) {
            results.addLast(end.getId());
        }
        return results;
    }

    public static void main(String[] args) {
        DijkstraNode a = new DijkstraNode(1);
        DijkstraNode b = new DijkstraNode(2);
        DijkstraNode c = new DijkstraNode(3);
        DijkstraNode d = new DijkstraNode(4);
        DijkstraNode e = new DijkstraNode(5);
        DijkstraNode f = new DijkstraNode(6);
        a.addAdjacent(b, 1);
        a.addAdjacent(c, 2);

        b.addAdjacent(e, 4);
        b.addAdjacent(d, 1);

        c.addAdjacent(d, 2);

        d.addAdjacent(f, 7);

        e.addAdjacent(f, 8);

        DijkstraGraph graph = new DijkstraGraph();
        graph.setNodes(Arrays.asList(a, b, c, d, e, f));
        System.out.println(dijkstraAlgorithm(graph, 1, 6));
    }
}
