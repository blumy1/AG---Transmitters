import net.sourceforge.gxl.*;

import java.util.ArrayList;
import java.util.List;

public class GraphManager {

    public static GXLNode createNode(float x, float y, float range, String id) {
        GXLNode node = new GXLNode(id);
        node.setAttr("x", new GXLFloat(x));
        node.setAttr("y", new GXLFloat(y));
        node.setAttr("radius", new GXLFloat(range));
        return node;
    }

    public static List<GXLNode> createRandomNodes(int numberOfPoints, float cityRadius, float transmitterRadius, GXLDocument gxlDocument) {
        List<GXLNode> nodes = new ArrayList<>();
        GXLIDGenerator gxlidGenerator = new GXLIDGenerator(gxlDocument);

        for (int i=0; i<numberOfPoints; i++) {
            float x = MathUtils.randomizeNumber(cityRadius);
            float y = MathUtils.randomizeNumber(cityRadius);
            nodes.add(createNode(x, y, transmitterRadius, gxlidGenerator.generateNodeID()));
        }

        return nodes;
    }

    public static List<GXLEdge> createEdges(List<GXLNode> nodes) {
        if (nodes.size() < 2) throw new IllegalArgumentException("Graph must contain at least 2 nodes.");

        List<GXLEdge> edges = new ArrayList<>();

        for (int i=0; i<nodes.size(); i++) {
            float x1 = getNodeAttrValue(nodes.get(i), "x");
            float y1 = getNodeAttrValue(nodes.get(i), "y");
            float r1 = getNodeAttrValue(nodes.get(i), "radius");

            for (int j=i+1; j<nodes.size(); j++) {
                float x2 = getNodeAttrValue(nodes.get(j), "x");
                float y2 = getNodeAttrValue(nodes.get(j), "y");
                float r2 = getNodeAttrValue(nodes.get(j), "radius");

                double dist = Math.hypot(x1 - x2, y1 - y2) - r1 - r2;

                if (dist < 0) {
                    GXLEdge edge = new GXLEdge(nodes.get(i), nodes.get(j));
                    edges.add(edge);
                }
            }
        }

        return edges;
    }

    public static float getNodeAttrValue(GXLNode node, String attrName) {
        GXLFloat gxlFloat1 = (GXLFloat) node.getAttr(attrName).getValue();
        return gxlFloat1.getFloatValue();
    }

}
