package com.kakkun61.aclogpluginfortwicca;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowTweetActivity extends Activity {
    private TextView text;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_tweet);
        text = (TextView) findViewById(R.id.text);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    AndroidHttpClient httpClient = AndroidHttpClient.newInstance("");
                    HttpGet get = new HttpGet("http://aclog.koba789.com/api/tweets/show.json?id=" + getIntent().getStringExtra("id"));
                    HttpResponse response = httpClient.execute(get);
                    return new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                } catch (IOException e) {
                    Log.w("aclog", "", e);
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                Log.d("aclog", "before download");
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String string) {
                Log.d("aclog", "download done");
                super.onPostExecute(string);
                text.setText(string);
            }
        }.execute();
    }
}
