package week4;

import java.util.ArrayList;
import java.util.List;

public class AdjListNode {
	
	public int vertexNo = 0;
	
	public boolean isExplored = false;
	
	public List<AdjListNode> endPoints = new ArrayList<AdjListNode>();
	
    public AdjListNode(int vertexNo) {
    	this.vertexNo = vertexNo;
    }
}
