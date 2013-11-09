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
	 * Executes a HTTP POST Request to database for creating a post
	 * @param title The title taken from user input
	 * @param description The description taken from user input
	 * @param pathToFile The path to the picture file chosen by user
	 * @return True on success, False on failure
	 */
	public static boolean createPost(String title, String description, String pathToFile, String username) {
		/* 
		 * TODO: Implement http://www.androidsnippets.com/executing-a-http-post-request-with-httpclient
		 * Replace BasicNameValuePairs with the ids used by web.engr.illinois.edu/~mgathma2/noZama/webInterface/testNewPost.php
		 * and the corresponding strings in the parameters
		 * Replace the url with web.engr.illinois.edu/~mgathma2/noZama/webInterface/testNewPost.php
		 * TODO: Decide what to do with the image file
		 */
		
	}
	
	/**
	 * Executes a HTTP Post Request to the database for doing a query
	 * @param query The query taken from user input
	 * @return Data Object
	 */
	public static List<Bundle> createQuery(String query) {
		/*
		 * TODO: Implement http://www.androidsnippets.com/executing-a-http-post-request-with-httpclient
		 * Replace BasicNameValuePairs with the ids used by web.engr.illinois.edu/~mgathma2/noZama/webInterface/testSearch.php
		 * and the corresponding strings in the parameters
		 * Replace the url with web.engr.illinois.edu/~mgathma2/noZama/webInterface/testSearch.php and get the JSON for the query
		 * results
		 * 
		 * TODO: call Aaron's method parse(JsonObject json) on the json returned from the server to get a Data object
		 */
		
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
