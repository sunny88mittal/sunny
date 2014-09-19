package week3;

import java.util.ArrayList;
import java.util.List;

public class AdjListNode {
	
	public short start;
	
	public List<EndPointWithCount> endPoints = new ArrayList<EndPointWithCount>();
	
    public AdjListNode(short start) {
    	this.start = start;
    }
}
