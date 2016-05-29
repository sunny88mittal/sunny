package query;

import base.ConnectionUtil;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.query.IndexType;
import com.aerospike.client.task.IndexTask;

public class CreateSecondaryIndex {

	public static void main(String args[]) {
		AerospikeClient client = ConnectionUtil.getClient();
		IndexTask task = client.createIndex(null, "test", "cricketer",
				"idx_player_name", "name", IndexType.STRING);
		task.waitTillComplete();
		client.close();
	}
}
