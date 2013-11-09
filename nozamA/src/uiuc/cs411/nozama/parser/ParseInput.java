package uiuc.cs411.nozama.parser;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class ParseInput {
	
	public static ArrayList<PostData> dataList;
	
	public static void main(String... args) {
        
	    	/* checks createPost
	        boolean check = createPost("Buying Girlfriend", , "not yet", "aaronluo");
	        System.out.print("create post returns: " + check);
	        */
	
	    	/* checks parse
	        Data data = new Data();
	        Gson gson = new Gson();
	        
	        //set datafields
	        data.setTitle("Buying Girlfriend");
	        data.setBody("Must be really hot");
	        
	        // Show it.
	        System.out.println(data);
	        
	        //make string
	        String json = gson.toJson(data);
	
	        //parse and make JSON
	        JsonParser parser = new JsonParser();
	        JsonObject obj = (JsonObject)parser.parse(json); 
	        
	        
	        Data print = new Gson().fromJson(obj, Data.class);
	        System.out.println(print.getTitle()+ print.getBody()+ print.getUser());
	        */
        
	}

	/**
	 * Executes a HTTP POST Request to database for creating a post
	 * @param title The title taken from user input
	 * @param description The description taken from user input
	 * @param pathToFile The path to the picture file chosen by user
	 * @return True on success, False on failure
	 */
	public static boolean createPost(String title, String description, String pathToFile, String username) {
		/*
		 * TODO: Decide what to do with the image file
		 */
	    	
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://web.engr.illinois.edu/~mgathma2/noZama/webInterface/testNewPost.php");
	        HttpResponse response = null;
	        
	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("title", title));
	            nameValuePairs.add(new BasicNameValuePair("body", description));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	
	            // Execute HTTP Post Request
	            response = httpclient.execute(httppost);
	            
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	            return false;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true;
		    
	}
	
	/**
	 * Executes a HTTP Post Request to the database for doing a query
	 * @param query The query taken from user input
	 * @return Data Object
	 */
	public static ArrayList<PostData> createQuery(String query) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://web.engr.illinois.edu/~mgathma2/noZama/webInterface/testSearch.php");
        HttpResponse response = null;
        
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("keyword", query));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            response = httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        ArrayList<PostData> data = null;
        try {
			data = parse(response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return data;
	}
	
	/**
	 * Parses response from server and creates a Data Object.
	 * Stores the Data object in the Data list
	 * @param results The HttpResponse from the database
	 * @return The Data Object
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws UnsupportedEncodingException 
	 * @throws JSONException 
	 */
	public static ArrayList<PostData> parse(HttpResponse results) throws UnsupportedEncodingException, IllegalStateException, IOException, JSONException {
		HttpResponse response = results;
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		for (String line = null; (line = reader.readLine()) != null;) {
		    builder.append(line).append("\n");
		}
		JSONTokener tokener = new JSONTokener(builder.toString());
		JSONArray finalResult = new JSONArray(tokener);
		ArrayList<PostData> data = new ArrayList<PostData>();
		
		for(int i = 0; i < finalResult.length(); i++) {
			JSONObject result = finalResult.getJSONObject(i);
			String title = result.getString("title");
			String body = result.getString("body");
			PostData post = new PostData(title, body);
			data.add(post);
		}
		return data;
	}
}


