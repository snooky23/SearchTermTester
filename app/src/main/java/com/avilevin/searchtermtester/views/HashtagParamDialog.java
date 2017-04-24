package com.avilevin.searchtermtester.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.avilevin.searchterm.api.SearchTerm;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;


/**
 * Created by avilevin on 21/04/2017.
 */
public class HashtagParamDialog extends DialogFragment {

    EditText mEtHashtagValue;
    OnHashtagDialogResult mDialogResult;

    public HashtagParamDialog() {
        super();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle = getArguments();
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_hashtagsearch, null);
        initLayoutObjects(view);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        SearchTerm.searchTweetByName(mEtHashtagValue.getText().toString(), new Callback<Search>() {
                            @Override
                            public void success(Result<Search> result) {
                                List<Tweet> tweets = result.data.tweets;
                                mDialogResult.finish(tweets);
                            }

                            @Override
                            public void failure(TwitterException exception) {

                            }
                        });

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    private void initLayoutObjects(View view) {
        mEtHashtagValue = (EditText) view.findViewById(R.id.etCustomHashtag);
    }

    public void setDialogResult(OnHashtagDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    protected interface OnHashtagDialogResult {
        void finish(List<Tweet> tweets);
    }
}
