package com.example.david.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.buttonQuiz);
        textView = (TextView)findViewById(R.id.textView);
        try{
            Bundle bundle = getIntent().getExtras();
            textView.setText("Twój wynik to: " + System.lineSeparator() + bundle.getString("score"));
        } catch (Exception e){
            Log.e("MainActivity", e.toString());
        }


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizActivity = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(quizActivity);
            }
        });
    }


}
