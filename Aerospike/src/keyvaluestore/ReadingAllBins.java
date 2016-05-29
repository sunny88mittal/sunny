package keyvaluestore;

import java.util.Map.Entry;

import base.ConnectionUtil;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;

public class ReadingAllBins {
	
	public static void main (String args[]) {
		AerospikeClient client = ConnectionUtil.getClient();
	    Key key = new Key("test", "employee", 1);
	    Record record = client.get(null, key);
	    for (Entry<String, Object> entry : record.bins.entrySet()){
	    	System.out.println(entry.getKey() + "," +  entry.getValue());
	    }
		client.close();
	}
}
