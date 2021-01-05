package dijkstra;

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

    public static List<Integer> dijkstraAlgorithm(DijkstraGraph graph, DijkstraNode start, DijkstraNode end) {
        List<DijkstraNode> processed = new ArrayList<>();
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
        LinkedList<Integer> results = new LinkedList<>();
        DijkstraNode tmp = parents.get(end);
        while (tmp != null) {
            results.addFirst(tmp.getId());
            tmp = parents.get(tmp);
        }
        if (results.size() > 0) {
            results.addLast(end.getId());
        }
        return results;
    }


    public static Map.Entry<DijkstraNode, Integer> findMinDistanceEntry(Map<DijkstraNode, Integer> map,
                                                                        List<DijkstraNode> processed) {
        Map.Entry<DijkstraNode, Integer> min = null;
        for (Map.Entry<DijkstraNode, Integer> entry : map.entrySet()) {
            if ((min == null || (min.getValue() < entry.getValue())) && !processed.contains(entry.getKey())) {
                min = entry;
            }
        }

        return min;
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
        System.out.println(dijkstraAlgorithm(graph, a, e));
    }
}
