package com.example.david.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button resultButton;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.buttonQuiz);
        resultButton = (Button)findViewById(R.id.buttonResults);
        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView.setTextColor(Color.WHITE);
        try{
            Bundle bundle = getIntent().getExtras();
            textView.setText("Twój wynik to: " + System.lineSeparator() + bundle.getString("score"));
            int score = Integer.parseInt(bundle.getString("score"));
            int number = Integer.parseInt(bundle.getString("number"));
            if(score > number/2) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.smiley_face_thumbs_up));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.thumbdown));
            }

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

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultActivity = new Intent(MainActivity.this, ResultsActivity.class);
                startActivity(resultActivity);
            }
        });
    }


}
