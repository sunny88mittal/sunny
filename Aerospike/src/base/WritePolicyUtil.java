package base;

import com.aerospike.client.policy.WritePolicy;

public class WritePolicyUtil {

	public static WritePolicy getWritePolicyWithTimeout(int timeout) {
		// Initialize policy.
		WritePolicy policy = new WritePolicy();
		policy.timeout = timeout; // 50 millisecond timeout.
		return policy;
	}
}
