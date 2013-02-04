package com.example.blogreader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainListActivity extends ListActivity {
	protected String[] mBlogPostTitle;
	public static final int NUMBER_OF_POSTS = 20;
	public static final String TAG = MainListActivity.class.getSimpleName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
        setContentView(R.layout.activity_main_list);
        if (isNetworkAvailable()) {
			GetBlogPostsTask getBlogPostsTask = new GetBlogPostsTask();
			getBlogPostsTask.execute();
		}
        else {
			Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
		}
        
//        Toast.makeText(this, getString(R.string.no_items), Toast.LENGTH_LONG).show();
    }

    private boolean isNetworkAvailable() {
    	ConnectivityManager connectivyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connectivyManager.getActiveNetworkInfo();
    	boolean isAvalable = false;
    	if (networkInfo != null && networkInfo.isConnected()) {
    		isAvalable = true;
		}
    	return isAvalable;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_list, menu);
        return true;
    }
    
    private class GetBlogPostsTask extends AsyncTask<Object, Void, String> {

		@Override
		protected String doInBackground(Object... params) {
			int responseCode = -1;
			
	        try {
				URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count="+NUMBER_OF_POSTS);
				HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
				connection.connect();
				
				responseCode = connection.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					InputStream inputStream = connection.getInputStream();
					Reader reader = new InputStreamReader(inputStream);
					int connectionLength = connection.getContentLength();
					char[] charArray = new char[connectionLength];
					reader.read(charArray);
					String responseData = new String(charArray);
					Log.v(TAG,responseData);
				} else {
					Log.i(TAG, "Unsuccessful HTTP Response Code: " + responseCode);
				}
			} 
	        catch (MalformedURLException e) {
				Log.e(TAG, "Exception caught: ", e);
			}
	        catch (IOException e) {
				Log.e(TAG, "Exception caught: ", e);
			}
	        catch (Exception e) {
				Log.e(TAG, "Exception caught: ", e);
			}
			return "Code: "+responseCode;
		}
    	
    }
}
