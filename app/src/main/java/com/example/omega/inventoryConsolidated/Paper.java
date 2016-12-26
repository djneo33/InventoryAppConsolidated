package com.example.omega.inventoryConsolidated;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;


import java.sql.Connection;
import java.sql.DatabaseMetaData;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

public class Paper extends AppCompatActivity {
    public ListView listView;

   public  Boolean newPaper;
    public String paper = "";
    public TextView browserbar;
    public EditText createnewpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        createnewpaper = (EditText)findViewById(R.id.createNewPaper);
        createnewpaper.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Intent intent = new Intent(Paper.this, Insert.class);
                newPaper = true;
                paper = v.getText().toString();
                intent.putExtra("paper",paper);
                intent.putExtra("createPaper",newPaper);
                startActivity(intent);

                return true;
            }
        });

        new getPaperNames().execute();
        listView = (ListView) findViewById(R.id.listView);

        browserbar = (TextView) findViewById(R.id.browserBar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listView.getItemAtPosition(position);

                paper = item;
                newPaper = false;

                Intent intent = new Intent(Paper.this, Insert.class);
                intent.putExtra("paper", item);
                intent.putExtra("createPaper",newPaper);
                startActivity(intent);


            }
        });

    }


    private class getPaperNames extends AsyncTask<Void, String[], String> {
        ArrayList paperNames = new ArrayList(1);

        @Override
        protected String doInBackground(Void... params) {


            Connection connection = MySql.getSqlConnection();
            try {


                Statement st = connection.createStatement();
                String sql = ("select * from `Inventory`.`PaperNames`");
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()){
                    paperNames.add(rs.getString("Paper"));
                }


                connection.close();
            } catch (Exception e) {
            }

            return null;


        }

        @Override
        protected void onPostExecute(String s) {


            ArrayAdapter adapter = new ArrayAdapter<String>(Paper.this, R.layout.listitem, R.id.textview1, paperNames);
            listView.setAdapter(adapter);


        }
    }

}
