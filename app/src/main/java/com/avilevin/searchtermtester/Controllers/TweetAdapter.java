package com.avilevin.searchtermtester.Controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avilevin.searchtermtester.views.R;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by avilevinshtein on 22/04/2017.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.MyViewHolder> {

    private List<Tweet> tweetsList;

    public TweetAdapter(List<Tweet> tweetsList) {
        this.tweetsList = tweetsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tweetText;

        public MyViewHolder(View view) {
            super(view);
            tweetText = (TextView) view.findViewById(R.id.tweetname);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_list_raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tweet tweet = tweetsList.get(position);
        holder.tweetText.setText(tweet.text);
    }

    @Override
    public int getItemCount() {
        return tweetsList.size();
    }

}


