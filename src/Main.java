import net.sourceforge.gxl.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        if (args.length < 4) {
            System.out.println("Provide number of transmitters, city radius, transmitter radius and file name (What file name to save).");
            return;
        }

        int numberOfPoints = Integer.valueOf(args[0]);
        float cityRadius = Integer.valueOf(args[1]);
        float transmitterRadius = Integer.valueOf(args[2]);
        String fileName = args[3];

        GXLDocument gxlDocument = new GXLDocument();
        GXLGraph graph = new GXLGraph("graph");
        graph.setEdgeMode(GXL.UNDIRECTED);

        List<GXLNode> nodes = GraphManager.createRandomNodes(numberOfPoints, cityRadius, transmitterRadius, gxlDocument);
        for (GXLNode node : nodes) {
            graph.add(node);
        }

        List<GXLEdge> edges = GraphManager.createEdges(nodes);
        for (GXLEdge edge : edges) {
            graph.add(edge);
        }

        gxlDocument.getDocumentElement().add(graph);

        GXLManager.saveGXLDocument(gxlDocument, fileName);
    }
}
