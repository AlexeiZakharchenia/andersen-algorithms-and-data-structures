package dijkstra;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DijkstraNode {
    private Integer id;
    Map <DijkstraNode, Integer> adjacents = new HashMap<>();

    public DijkstraNode(Integer id) {
        this.id = id;
    }

    public void addAdjacent(DijkstraNode node, Integer distance){
        adjacents.put(node,distance);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<DijkstraNode, Integer> getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(Map<DijkstraNode, Integer> adjacents) {
        this.adjacents = adjacents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DijkstraNode that = (DijkstraNode) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
