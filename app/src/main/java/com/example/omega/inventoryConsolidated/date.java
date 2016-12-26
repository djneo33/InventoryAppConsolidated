package com.example.omega.inventoryConsolidated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class date extends AppCompatActivity {
    public String paper;
    public String insert;
    public int day;
    public int month;
    public int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Bundle bundle = getIntent().getExtras();
        paper = bundle.getString("paper");
        insert = bundle.getString("insert");
        TextView textView = (TextView) findViewById(R.id.dateText);
        textView.setText("Choose run Date for " + paper + " " + insert);
        Button button = (Button) findViewById(R.id.button2);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth();
                year = datePicker.getYear();
                String date = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day);


                System.out.println(date);
                Intent intent = new Intent(com.example.omega.inventoryConsolidated.date.this, finalScreen.class);
                intent.putExtra("paper", paper);
                intent.putExtra("insert", insert);
                intent.putExtra("Date", date);
                startActivity(intent);
            }
        });
    }
}
