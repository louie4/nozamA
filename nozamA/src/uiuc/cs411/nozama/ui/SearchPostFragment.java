package uiuc.cs411.nozama.ui;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import uiuc.cs411.nozama.R;
import uiuc.cs411.nozama.content.Content;
import uiuc.cs411.nozama.parser.ParseInput;

/**
 * A fragment for creating a post. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class SearchPostFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Content.Item mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SearchPostFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = Content.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.search_post,
					container, false);
		EditText search = (EditText) rootView.findViewById(R.id.searchQuery);
		search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					String query = v.getText().toString();
					if(query.length() > 0) {
//						Context context = getActivity().getApplicationContext();
//						CharSequence text = "query:" + query;
//						int duration = Toast.LENGTH_SHORT;
//	
//						Toast toast = Toast.makeText(context, text, duration);
//						toast.show();
						
						List<Bundle> responses = ParseInput.createQuery(query);
						
						return true;
					}
				}
				return false;
			}
		});
		return rootView;
	}
}
