package com.example.blogreader;

import android.app.ListActivity;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainListActivity extends ListActivity {
	protected String[] mBlogPostTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
        setContentView(R.layout.activity_main_list);
        
        // Resources instance of our application package.
        // Class for accessing an application's resources
        Resources resources = getResources();
        // Retrieving list of android names
        mBlogPostTitle = resources.getStringArray(R.array.android_names);
        
        // ArrayAdapter is designed for binding to an array of strings of android names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mBlogPostTitle);
        
        // bind to our new adapter
        setListAdapter(adapter);
        Intent intent = new Intent();
        String action = intent.getAction();
        Toast.makeText(this, "action: " + action, Toast.LENGTH_LONG).show();
//        Toast.makeText(this, getString(R.string.no_items), Toast.LENGTH_LONG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_list, menu);
        return true;
    }
    
}
