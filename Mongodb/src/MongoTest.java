import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class MongoTest {

	private static final String DB_NAME = "mydb";
	
	private static final String COLLECTION_NAME = "user";
	
	private static final String DB_HOST = "127.0.0.1";
	
	private static final String FILE_DIRECTORY = "/home/sunny"; 
	
	public static void main (String args[]) throws UnknownHostException, MongoException, FileNotFoundException {
		MongoClient mongoClient = new MongoClient(DB_HOST , 27017 );
		System.out.println("Connection Created");
		
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection dbCollection = db.getCollection(COLLECTION_NAME);
		
		String fileName = FILE_DIRECTORY + "/" + "ExUserCodes" + System.currentTimeMillis() + ".csv";
		PrintWriter pwCSV = new PrintWriter(new File(fileName));
		
		for (String regex : args) {
			BasicDBObject query = new BasicDBObject("email", new BasicDBObject("$regex", regex));
		    DBCursor cursor = dbCollection.find(query);
		    while (cursor.hasNext()) {
		    	DBObject object = cursor.next();
		    	pwCSV.write(object.get("name") + "," + object.get("email") + "\n");
		    }
		}
		if(pwCSV != null) {
			pwCSV.flush();
			pwCSV.close();
		}
		mongoClient.close();
		System.out.println("Connection Closed");
	}
}
