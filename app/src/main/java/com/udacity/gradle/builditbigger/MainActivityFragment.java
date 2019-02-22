package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeButton = root.findViewById(R.id.buttonJokes);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndpointsAsyncTask stuff = new EndpointsAsyncTask();
                stuff.response = new EndpointsAsyncTask.TaskResponse() {
                    @Override
                    public void process(String out) {
                        Log.i("MainActivityFragment", out);
                    }
                };
                stuff.execute(new Pair<Context, String>(getActivity(), "Manfred"));
            }
        });
        return root;
    }
}
