package com.example.omega.inventoryConsolidated;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class ViewRecordsMain extends AppCompatActivity {
    public String paper;
    public TextView textView;
    public ListView listView;
    public ListView listView2;
    public ListView listView3;
    public ListView listView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_records_main);
        new GetTables().execute();
        textView = (TextView) findViewById(R.id.textviewonrecordmain);
        listView = (ListView) findViewById(R.id.listView4);
        listView2 = (ListView)findViewById(R.id.listView5);
        listView3 = (ListView) findViewById(R.id.listView6);
        listView4 = (ListView)findViewById(R.id.listView7);
        Bundle bundle = getIntent().getExtras();
        paper = bundle.getString("paper");
        textView.setText("Viewing active records for " + paper);


    }


    private class GetTables extends AsyncTask<Void, String[], String> {
        ArrayList quantity = new ArrayList(1);
        ArrayList insert = new ArrayList(1);
        ArrayList rundate = new ArrayList(1);
        ArrayList location = new ArrayList(1);

        @Override
        protected String doInBackground(Void... params) {


            Connection connection = MySql.getSqlConnection();
            try {
                String query = "select * from `Inventory`.`" + paper + "` limit 38";
                Statement statement = connection.createStatement();
                com.mysql.jdbc.ResultSet resultSet = (com.mysql.jdbc.ResultSet) statement.executeQuery(query);
                quantity.add("Amount");
                insert.add("Insert");
                rundate.add("Run Date");
                location.add("Location");


                while (resultSet.next()) {
                    quantity.add(resultSet.getString("quantity"));
                    insert.add(resultSet.getString("insert"));
                    rundate.add(resultSet.getString("run date"));
                    location.add(resultSet.getString("location"));
                }


                connection.close();
            } catch (Exception e) {
            }

            return null;


        }

        @Override
        protected void onPostExecute(String s) {


            ArrayAdapter adapter = new ArrayAdapter<String>(ViewRecordsMain.this, R.layout.listemitem2, R.id.textview1, insert);
            listView.setAdapter(adapter);
            ArrayAdapter adapter2 = new ArrayAdapter<String>(ViewRecordsMain.this, R.layout.listemitem2, R.id.textview1, rundate);
            listView2.setAdapter(adapter2);
            ArrayAdapter adapter3 = new ArrayAdapter<String>(ViewRecordsMain.this, R.layout.listemitem2, R.id.textview1, quantity);
            listView3.setAdapter(adapter3);
            ArrayAdapter adapter4 = new ArrayAdapter<String>(ViewRecordsMain.this, R.layout.listemitem2, R.id.textview1, location);
            listView4.setAdapter(adapter4);


        }
    }


}
