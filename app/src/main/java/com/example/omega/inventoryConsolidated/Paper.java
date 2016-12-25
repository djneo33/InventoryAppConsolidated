package com.example.omega.inventoryConsolidated;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.TextView;


import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.ResultSet;

import java.util.ArrayList;

public class Paper extends AppCompatActivity {
    public ListView listView;

    public String screen = "main";
    public String paper = "";
    public TextView browserbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);//


        new GetTables().execute();
        listView = (ListView) findViewById(R.id.listView);

        browserbar = (TextView) findViewById(R.id.browserBar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listView.getItemAtPosition(position);

                paper = item;
                screen = "insert";
                Intent intent = new Intent(Paper.this, Insert.class);
                intent.putExtra("paper", item);
                startActivity(intent);


            }
        });

    }


    private class GetTables extends AsyncTask<Void, String[], String> {
        ArrayList tablenames = new ArrayList(1);

        @Override
        protected String doInBackground(Void... params) {


            Connection connection = MySql.getSqlConnection();
            try {


                DatabaseMetaData m = connection.getMetaData();
                ResultSet rs = m.getTables("Inventory", null, "%", null);

                while (rs.next()) {
                    tablenames.add(rs.getString(3));
                    System.out.println("im there");

                }


                connection.close();
            } catch (Exception e) {
            }

            return null;


        }

        @Override
        protected void onPostExecute(String s) {


            ArrayAdapter adapter = new ArrayAdapter<String>(Paper.this, R.layout.listitem, R.id.textview1, tablenames);
            listView.setAdapter(adapter);


        }
    }

}
