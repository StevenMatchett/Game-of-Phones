package core.gameOfPhones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetFriends {


		public String printFriends() throws IOException { 
		String name = "https://api.foursquare.com/v2/users/self/friends";
		return connect(name);
		
		}
	
		public String connect(String name) throws IOException
		{
			BufferedReader reader = null;
			URL url = new URL(name);
			try {
			    reader = new BufferedReader(new InputStreamReader(url.openStream()));

			    for (String line; (line = reader.readLine()) != null;) {
			        return line;
			    }
			} finally {
			    if (reader != null) try { reader.close(); } catch (IOException ignore) {}
			}
			return "nothing";
		}
	}


