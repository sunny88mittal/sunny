package keyvaluestore;

import java.util.Map.Entry;

import base.ConnectionUtil;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;

public class BatchRead {
	
	public static void main(String args[]) {
		AerospikeClient client = ConnectionUtil.getClient();
		Key key1 = new Key("test", "employee", 1);
		Key key2 = new Key("test", "employee", 2);
		
		Record[] records = client.get(null, new Key[]{key1, key2});
		for (Record record : records) {
			for (Entry<String, Object> entry : record.bins.entrySet()) {
				System.out.println(entry.getKey() + "," + entry.getValue());
			}
			System.out.println();
		}
		client.close();
	}
}
