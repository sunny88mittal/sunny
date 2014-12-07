package keyvaluestore;

import base.ConnectionUtil;
import base.WritePolicyUtil;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.policy.WritePolicy;

public class WritingRecords {

	public static void main (String args[]) {
		AerospikeClient client = ConnectionUtil.getClient();
		
		// Initialize policy.
		WritePolicy policy = WritePolicyUtil.getWritePolicyWithTimeout(50);
		
		//To select from aql we need to give PK = 1
		Key key = new Key("test", "employee", 2); 
		Bin name = new Bin("name", "aashish-mann");
		Bin team = new Bin("team", "tech");
		
		client.put(policy, key, name, team);
		client.close();
	}
}