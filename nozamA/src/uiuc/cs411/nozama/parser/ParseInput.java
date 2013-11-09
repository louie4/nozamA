package uiuc.cs411.nozama.parser;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import android.os.Bundle;

public class ParseInput {
	
	public static void main(String... args) {
        
	    boolean check = createPost("Buying Girlfriend", "Must be really hot", "not yet", "aaronluo");
	    System.out.print("create post returns: " + check);
        
	}

	/**
	 * Creates a JSON data object representing a post
	 * @param title The title taken from user input
	 * @param description The description taken from user input
	 * @param pathToFile The path to the picture file chosen by user
	 * @return True on success, False on failure
	 */
	public static boolean createPost(String title, String description, String pathToFile, String username) {
		/* 
		 * TODO: 
		 * Basics -
		 * Generate ID of post
		 * Extract keywords from description (hashtags)
		 * Place data into JSON object
		 * Call function to send JSON object (or return JSON object and have send method call this method)
		 * Return whether or not post is successful
		 * 
		 * Advanced - Decide what to do with the image file
		 */
		
	    Data data = new Data();
	    Gson gson = new Gson();
	    
	    //set datafields
	    data.setTitle(title);
	    data.setBody(body);
	    
	    // Show it.
	    System.out.println(data);
	    
	    //make string
	    String json = gson.toJson(data);
	
	    //parse and make JSON
	    JsonParser parser = new JsonParser();
	    JsonObject object = (JsonObject)parser.parse(json);
	    
	    sendJSON(object);
	    
		System.out.println(json);
		if (json != "0" || json != null) 
			return true;
		else return false;
	}
	
	private static void sendJSON(JsonObject object) {
		// TODO send JSON obj somewhere
		
	}   
}
	
	/**
	 * Creates a JSON data object representing a query
	 * @param query The query taken from user input
	 * @return List of bundles which contain the response tuple data
	 */
	public static List<Bundle> createQuery(String query) {
		/*
		 * TODO: Create JSON query from given string
		 * Call function to send JSON object (or return JSON object and have send method call this method)
		 * Wait for response
		 * Parse response into Bundle
		 */
				
//		Bundle b = new Bundle();
//		b.putString("title", value);
//		b.putString("body", value);
//		b.putString("price", value);
//		b.putString("picture", value);
//		b.putString("responseID", value);
		
		return null;
	}
}

class Data {
    private String title;
    private String tag;
    private String keyword;
    private String body;
    private String msg;
    private String user;
    private boolean success;


    public String getTitle() { return title; }
    public String getTag() { return tag; }
    public String getKeyword() { return keyword; }
    public String getBody() { return body; }
    public String getMsg() { return msg; }
    public String getUser() { return user; }
    public boolean getSuccess() { return success; }

    public void setTitle(String title) { this.title = title; }
    public void setTag(String tag) { this.tag = tag; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public void setBody(String body) { this.body = body; }
    public void setMsg(String msg) { this.msg = msg; }
    public void setSuccess(boolean success) { this.success = success; }

    
    public String toString() {
        return String.format("title: %s, body: %s", title, body);
    }
}
