package com.example.omega.inventoryConsolidated;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreen extends AppCompatActivity {
    public String paper,insert,rundate,quantity,location,comments;
    public  TextView connectionView,textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        textView = (TextView) findViewById(R.id.homeScreenConnection);
        connectionView = (TextView) findViewById(R.id.connectionView);
        new network().execute();


        if (getIntent().hasExtra("paper")) {
            Bundle bundle = getIntent().getExtras();
            paper = bundle.getString("paper");
            insert = bundle.getString("insert");
           rundate = bundle.getString("Date");
            quantity = bundle.getString("quantity");
            location = bundle.getString("location");
            comments = bundle.getString("comments");
        }


        if(paper == null){
            textView.setText("Last entry: N/A");
        }
        else {
            textView.setText("-Last entry- "+'\n'+"Paper: "+paper+'\n'+"Insert: "+insert+'\n'+"Run Date: "+rundate+'\n'+"Amount: "+quantity+'\n'+"Location: "+location);
        }
        Button button2 = (Button)findViewById(R.id.buttonview);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,ViewRecords.class);
                startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.createRecord);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Paper.class);
                startActivity(intent);
            }
        });
    }
private class network extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
         java.sql.Connection connection = MySql.getSqlConnection();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (MySql.isConnected == false){
            connectionView.setText("Not Connected");

        }
    else {
            connectionView.setText("Connected"+ "\n"+MySql.getIp());
        }
    }
}
}
