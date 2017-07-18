package com.example.mufasa.voterreg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;

public class voters extends AppCompatActivity {

    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voters);

        lstView= (ListView)findViewById(R.id.lstView);

        AsyncHttpClient client = new AsyncHttpClient();


        client.post("http://ccpafrica.net/safari/voters.php", new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                String[]array=responseString.split("#");

                ArrayAdapter<String>adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.support,array);

                lstView.setAdapter(adapter);

                
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Toast.makeText(voters.this, "Error in Connecting", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
