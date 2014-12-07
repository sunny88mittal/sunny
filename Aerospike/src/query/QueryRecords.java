package query;

import base.ConnectionUtil;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Record;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;

public class QueryRecords {
	
	public static void main(String args[]) {
		AerospikeClient client = ConnectionUtil.getClient();
		
		//Create Statement
		Statement stmt = new Statement();
		stmt.setNamespace("test");
		stmt.setSetName("cricketer");
		
		//Create Filter
		stmt.setFilters( Filter.equal("name", "Sachin"));
		
		//Set the column names to be retrieved
		stmt.setBinNames("name", "centuries");
		
		//Execute the query
		RecordSet rs = client.query(null, stmt);
		try {
		    while (rs.next()) {
		        Record record = rs.getRecord();
		        System.out.println("Printing details for: " + record.bins.get("name"));
		        System.out.println("Centuries: " + record.bins.get("centuries"));
		    }
		}
		finally {
		    rs.close();
		}
		
		client.close();
	}
}
