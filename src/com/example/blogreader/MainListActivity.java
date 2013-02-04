package com.example.blogreader;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

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
        
        try {
			URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/getrecentsummary/?count="+NUMBER_OF_POSTS);
		} catch (MalformedURLException e) {
			Log.e(TAG, "Exception caught: ", e);
		}
        
//        Toast.makeText(this, getString(R.string.no_items), Toast.LENGTH_LONG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_list, menu);
        return true;
    }
    
}
