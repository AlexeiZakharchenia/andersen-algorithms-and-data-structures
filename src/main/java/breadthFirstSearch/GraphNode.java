package breadthFirstSearch;

import java.util.List;
import java.util.Objects;

public class GraphNode {
    private int id;
    private List<GraphNode> links;

    public GraphNode(int id, List<GraphNode> links) {
        this.id = id;
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GraphNode> getLinks() {
        return links;
    }

    public void setLinks(List<GraphNode> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return id == graphNode.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
