package uiuc.cs411.nozama.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import uiuc.cs411.nozama.ui.SearchPostFragment;

import android.os.AsyncTask;
import android.util.Log;

public class DatabaseTask extends AsyncTask<String, Void, JSONArray> {

	public static final int CREATE_POST = 0;
	public static final int SEARCH_QUERY = 1;

	private static final String DATABASE_SITE = "http://web.engr.illinois.edu/~mgathma2/noZama/noZamaDB.php";

	@Override
	protected JSONArray doInBackground(String... params) {
		Log.d("TEST", "testing execute");
		int type = Integer.parseInt(params[0]);
		List<NameValuePair> nameValuePairs;
		JSONArray response = null;
		switch (type) {
		case CREATE_POST:
			nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("title", params[1]));
			nameValuePairs.add(new BasicNameValuePair("tag", "new post"));
			nameValuePairs.add(new BasicNameValuePair("body", params[2]));
			response = sendHttpPost(CREATE_POST, nameValuePairs);
			break;
		case SEARCH_QUERY:
			nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("keyword", params[1]));
			nameValuePairs.add(new BasicNameValuePair("tag", "search posts"));
			response = sendHttpPost(SEARCH_QUERY, nameValuePairs);
			break;
		}
		return response;
	}

	private JSONArray sendHttpPost(int postType,
			List<NameValuePair> nameValuePairs) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(DATABASE_SITE);
		JSONArray ja = null;

		try {
			Log.d("HTTP", "Sending HTTP POST");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			Log.d("HTTP", "Finished sending");
	        String result = EntityUtils.toString(response.getEntity());
	        Log.d("MYJSON", result);
	        JSONObject jo = new JSONObject(result); 
	        ja = new JSONArray();
	        ja.put(jo);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ja;
	}

	protected void onPostExecute(JSONArray result) {
		SearchPostFragment.result = result;

	}

}
