package com.example.omega.inventoryConsolidated;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class finalScreen extends AppCompatActivity {
    public String paper;
    public String insert;
    public String date;
    public EditText quantity;
    public EditText location;
    public EditText comments;
    public String quantityText;
    public String locationText;
    public String commentsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);
        Bundle bundle = getIntent().getExtras();
        paper = bundle.getString("paper");
        insert = bundle.getString("insert");
        date = bundle.getString("date");
        quantity = (EditText) findViewById(R.id.quantity);
        location = (EditText) findViewById(R.id.location);
        comments = (EditText) findViewById(R.id.comments);

        Button button = (Button) findViewById(R.id.finalButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityText = String.valueOf(quantity.getText());
                locationText = String.valueOf(location.getText());
                commentsText = String.valueOf(comments.getText());
                new FinalQuery().execute();
                Intent intent = new Intent(finalScreen.this,HomeScreen.class);
               intent.putExtra("paper",paper);
                intent.putExtra("insert",insert);
                intent.putExtra("date",date);

                intent.putExtra("quantity",quantityText);
                intent.putExtra("location",locationText);
                intent.putExtra("comments",commentsText);
                startActivity(intent);


            }
        });


        TextView textView = (TextView) findViewById(R.id.finalTextTop);
        TextView textView1 = (TextView) findViewById(R.id.finalTextBottom);
        textView.setText(paper + " " + insert);
        textView1.setText(date);
    }

    private class FinalQuery extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Connection connection = MySql.getSqlConnection();

            try {


                String curdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String query = "INSERT INTO `Inventory`.`" + paper + "` (`quantity`,`date created`,`insert`,`run date`,`location`,`comments`)  VALUES  ('"+quantityText+"','"+curdate+"','"+insert+"','"+date+"','"+locationText+"','"+commentsText+"'); ";
                System.out.println("Query: "+query);

                Statement stmt = connection.createStatement();
                stmt.execute(query);

                connection.close();

            } catch (Exception e) {
            }

            return null;
        }
    }

}
