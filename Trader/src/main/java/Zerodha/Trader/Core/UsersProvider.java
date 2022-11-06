package Zerodha.Trader.Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UsersProvider {

	private static String USERS_DETAILS_FILE = "C:\\Code\\Code\\sunny\\Trader\\UserDetails.json";

	public List<UserDetails> getAllUsers() throws FileNotFoundException, IOException, ParseException {
		List<UserDetails> users = new ArrayList<UserDetails>();	
		Gson gson = new Gson();	
		Type listType = new TypeToken<List<UserDetails>>(){}.getType();
		users = gson.fromJson(new FileReader(USERS_DETAILS_FILE), listType);
		return users;
	}

	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
		UsersProvider usersProvider = new UsersProvider();
		usersProvider.getAllUsers();
	}
}
