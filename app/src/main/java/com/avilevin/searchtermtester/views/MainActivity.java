package com.avilevin.searchtermtester.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.avilevin.searchterm.api.SearchTerm;
import com.avilevin.searchtermtester.Controllers.TweetAdapter;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TweetAdapter mAdapter;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ao3ROjqMd4eH0OxvyifqAbyo8";
    private static final String TWITTER_SECRET = "fLUNkiCwQkn0HzwU0vE1SNnUfsrpTb7zRxsIx8bTEqDK8wqIRo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SearchTerm.init(this,TWITTER_KEY,TWITTER_SECRET);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashtagParamDialog hashtagParamDialog = new HashtagParamDialog();
                hashtagParamDialog.setDialogResult(new HashtagParamDialog.OnHashtagDialogResult() {
                    @Override
                    public void finish(List<Tweet> tweetList) {
                        mAdapter = new TweetAdapter(tweetList);
                        recyclerView.setAdapter(mAdapter);
                    }
                });
                hashtagParamDialog.show(getSupportFragmentManager(), "search_for_hashtag");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
