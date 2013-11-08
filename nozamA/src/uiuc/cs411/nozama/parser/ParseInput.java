package uiuc.cs411.nozama.parser;

import java.util.List;

import android.os.Bundle;

public class ParseInput {

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
		
		return false;
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
