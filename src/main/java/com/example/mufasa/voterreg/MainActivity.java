package com.example.mufasa.voterreg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText idNo;
    EditText station;
    Button register;
    Button view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.txtName);
        idNo=(EditText)findViewById(R.id.txtId);
        station=(EditText)findViewById(R.id.txtStation);

        register=(Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncHttpClient sync = new AsyncHttpClient();

                //used since the user is required to enter data
                RequestParams request = new RequestParams();


                //the keys uname uid and uward have to be like as entered for tht specific url bellow.
                request.put("uname", name.getText());
                request.put("uid", idNo.getText().toString());
                request.put("uward", station.getText());

            //if the php returns a text then the TextHttpResponseHandler is used
                sync.post("http://ccpafrica.net/safari/register.php", request, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "Failed to Connect", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        if (responseString.contains("true")) {
                            Toast.makeText(MainActivity.this, "Data was successfully entered.", Toast.LENGTH_SHORT).show();

                            name.setText("");
                            idNo.setText("");
                            station.setText("");


                        }else if (responseString.contains("false")){
                            Toast.makeText(MainActivity.this, "Failed to Insert Data.", Toast.LENGTH_SHORT).show();

                        }else if (responseString.contains("Error")){
                            Toast.makeText(MainActivity.this, "ID already Exists.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


                view = (Button) findViewById(R.id.btnView);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent voter= new Intent(getApplicationContext(),voters.class);
                        startActivity(voter);

                    }
                });

            }
        }


