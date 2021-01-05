package breadthFirstSearch;

import java.util.List;

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
}
