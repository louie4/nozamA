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

import uiuc.cs411.nozama.R;
import uiuc.cs411.nozama.content.Content;
import uiuc.cs411.nozama.content.Content.Item;
import uiuc.cs411.nozama.ui.SearchPostFragment;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class DatabaseTask extends AsyncTask<String, Void, JSONObject> {

	public static final int CREATE_POST = 0;
	public static final int SEARCH_QUERY = 1;

	private static final String DATABASE_SITE = "http://web.engr.illinois.edu/~mgathma2/noZama/noZamaDB.php";

	@Override
	protected JSONObject doInBackground(String... params) {
		Log.d("TEST", "testing execute");
		int type = Integer.parseInt(params[0]);
		List<NameValuePair> nameValuePairs;
		JSONObject response = null;
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

	private JSONObject sendHttpPost(int postType,
			List<NameValuePair> nameValuePairs) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(DATABASE_SITE);
		JSONObject jo = null;

		try {
			Log.d("HTTP", "Sending HTTP POST");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			Log.d("HTTP", "Finished sending");
	        String result = EntityUtils.toString(response.getEntity());
	        Log.d("MYJSON", result);
	        jo = new JSONObject(result); 
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo;
	}

	protected void onPostExecute(JSONObject result) {
		try {
			if(result.getString("tag").equals("search posts")) {
				JSONArray posts = result.getJSONArray("posts");
				Log.d("post", posts.toString());
				JSONObject object = posts.getJSONObject(0);
				Log.d("object", object.toString());
				Log.d("title", object.getString("title"));
				SearchPostFragment.emptyLists();
				for(int i = 0; i < posts.length(); i++) {
					Log.d("i",""+i);
					String title = posts.getJSONObject(i).getString("title");
					SearchPostFragment.titles.add(new Content.Item("title",posts.getJSONObject(i).getString("title")));
					SearchPostFragment.bodies.add(new Content.Item("body",title + ": " +posts.getJSONObject(i).getString("body")));;
				}
				
				SearchPostFragment.adapter.notifyDataSetChanged();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
