package com.example.david.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_results);
        buttonClear = (Button)findViewById(R.id.buttonClear);
        init();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(ResultsActivity.this);
                db.clearResults();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    public void init() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.onCreate(db.getWritableDatabase());
        results = db.getAllResults();
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        tbrow0.setPadding(0,20,0,30);
        tbrow0.setBackgroundColor(Color.LTGRAY);
        TextView tv0 = new TextView(this);
        tv0.setText(" Kategoria ");
        tv0.setTextColor(Color.BLACK);
        tv0.setGravity(Gravity.CENTER);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Trudność ");
        tv1.setTextColor(Color.BLACK);
        tv1.setGravity(Gravity.CENTER);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Liczba pytań ");
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Wynik ");
        tv3.setTextColor(Color.BLACK);
        tv3.setGravity(Gravity.CENTER);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i = 0; i < results.size(); i++) {
            TableRow tbrow = new TableRow(this);
            tbrow.setPadding(10,10,10,10);
            TextView t1v = new TextView(this);
            t1v.setText(results.get(i).getCategory());
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            t1v.setPadding(10,10,10,10);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(results.get(i).getDifficulty());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            t2v.setPadding(10,10,10,10);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(String.valueOf(results.get(i).getNumber()));
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            t3v.setPadding(10,10,10,10);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText(String.valueOf(results.get(i).getScore()));
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            t4v.setPadding(10,10,10,10);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }
}
