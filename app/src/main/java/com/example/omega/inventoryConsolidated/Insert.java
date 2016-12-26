package com.example.omega.inventoryConsolidated;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Insert extends AppCompatActivity {
    public String paper;
    public ListView listview;
   public EditText createnewinsert;
    public Boolean newPaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        new GetInsert().execute();
        Bundle extra = getIntent().getExtras();
        paper = extra.getString("paper");
        newPaper = extra.getBoolean("createPaper");
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        createnewinsert = (EditText)findViewById(R.id.createNewInsert);
        createnewinsert.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Intent intent = new Intent(Insert.this, date.class);
                intent.putExtra("insert",textView.getText().toString());
                intent.putExtra("paper",paper);
                intent.putExtra("createPaper",newPaper);

                startActivity(intent);
                return true;
            }
        });







        listview = (ListView) findViewById(R.id.listViewInsert);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked = (String) listview.getItemAtPosition(position);

                Intent intent = new Intent(Insert.this, date.class);
                intent.putExtra("insert",clicked);
                intent.putExtra("paper",paper);
                intent.putExtra("createPaper",newPaper);

                startActivity(intent);
            }
        });

    }


    private class GetInsert extends AsyncTask<Void, Void, String> {
        ArrayList list = new ArrayList(1);
       Connection connection;
        @Override
        protected String doInBackground(Void... params) {



            try {
                String query = "select * from `Inventory`.`InsertNames`";



                connection = MySql.getSqlConnection();
               Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    list.add(rs.getString("Insert"));
                    System.out.println("im here");

                }
                connection.close();
            } catch (Exception e) {
            }


            return null;


        }

        @Override
        protected void onPostExecute(String s) {

            ArrayAdapter adapter = new ArrayAdapter<String>(Insert.this, R.layout.listitem, R.id.textview1, list);
            listview.setAdapter(adapter);
        }
    }




}
