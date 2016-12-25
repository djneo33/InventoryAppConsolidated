package com.example.omega.inventoryConsolidated;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Insert extends AppCompatActivity {
    public String paper;
    public ListView listview;
    public String m_Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        new GetInsert().execute();
        Bundle extra = getIntent().getExtras();
        paper = extra.getString("paper");

        Button button = (Button) findViewById(R.id.button);
        button.setText("Create new insert for " + paper);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Insert.this);


// Set up the input
                final EditText input = new EditText(Insert.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        Intent intent = new Intent(Insert.this, Date.class);
                       intent.putExtra("insert",m_Text);
                        intent.putExtra("paper",paper);
                        startActivity(intent);



                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        listview = (ListView) findViewById(R.id.listView2);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked = (String) listview.getItemAtPosition(position);

                Intent intent = new Intent(Insert.this, Date.class);
                intent.putExtra("insert",clicked);
                intent.putExtra("paper",paper);
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
                String query = "select distinct `insert` from `Inventory`."+"`"+paper+"`"+ "union select distinct `insert` from `InventoryBackLog`."+"`"+paper+"`";



                connection = MySql.getSqlConnection();
               Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    list.add(rs.getString("insert"));
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
