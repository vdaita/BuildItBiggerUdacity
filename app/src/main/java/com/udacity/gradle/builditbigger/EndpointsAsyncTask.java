package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by vijay on 2/17/19.
 */

// from the linked tutorial
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String>{
    private static MyApi myApiService = null;
    // inspired by  https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
    public TaskResponse response = null;
    private Context context;

    @Override
    protected void onPostExecute(String s) {
        response.process(s);
        Intent intent = null;
        try {
            intent = new Intent(context, Class.forName("com.daita.jokedisplayer.JokeDisplayActivity"));
            intent.putExtra("joke", s);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = params[0].first;
        try{
            return myApiService.joke().execute().getData();
        } catch (IOException e ){
            return null;
        }
    }

    public interface TaskResponse{
        void process(String out);
    }
}