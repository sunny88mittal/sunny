package base;

import com.aerospike.client.AerospikeClient;

public class ConnectionUtil {

	public static AerospikeClient getClient() {
		AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);
		return client;
	}
}
