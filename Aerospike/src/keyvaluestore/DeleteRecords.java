package keyvaluestore;

import base.ConnectionUtil;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;

public class DeleteRecords {

	public static void main (String args[]) {
		AerospikeClient client = ConnectionUtil.getClient();
		Key key = new Key("test", "employee", 1);
		client.delete(null, key);
	}
}
