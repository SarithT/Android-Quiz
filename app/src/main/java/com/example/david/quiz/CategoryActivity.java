package com.example.david.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    Spinner spinnerDificulty;
    Spinner spinnerNumber;
    Button buttonStart;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    private static final String[]numbers = {"5", "10", "15", "20"};
    private static final String[]categories = {"Historia", "Polityka", "Filmy", "Muzyka", "Matematyka"};
    private static final String[]difficulties = {"Łatwy", "Średni", "Trudny"};
    private static final Map<String, String>categoriesMap = new HashMap<String, String>(){{
        put("Historia","23");
        put("Polityka","24");
        put("Filmy","11");
        put("Muzyka","12");
        put("Matematyka","19");
    }};

    private static final Map<String, String>difficultiesMap = new HashMap<String, String>(){{
        put("Łatwy","easy");
        put("Średni","medium");
        put("Trudny","hard");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        spinnerCategory = (Spinner)findViewById(R.id.spinnerCategories);
        spinnerDificulty = (Spinner)findViewById(R.id.spinnerDifficulties);
        spinnerNumber = (Spinner)findViewById(R.id.spinnerNumer);
        buttonStart = (Button)findViewById(R.id.buttonStart);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView2.setTextColor(Color.WHITE);
        textView3.setTextColor(Color.WHITE);
        textView4.setTextColor(Color.WHITE);
        ArrayAdapter<String> adapterNumbers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  numbers);
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  categories);
        ArrayAdapter<String> adapterDifficulties = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  difficulties);
        spinnerNumber.setAdapter(adapterNumbers);
        spinnerCategory.setAdapter(adapterCategories);
        spinnerDificulty.setAdapter(adapterDifficulties);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pickedNumber = spinnerNumber.getSelectedItem().toString();
                String pickedCategory = spinnerCategory.getSelectedItem().toString();
                String pickedDifficulty = spinnerDificulty.getSelectedItem().toString();
                String url = buildUrl(pickedNumber, pickedCategory, pickedDifficulty);
                Log.e("DEBUG", url);
                Intent questionActivity = new Intent(CategoryActivity.this, QuestionActivity.class);
                questionActivity.putExtra("url", url);
                startActivity(questionActivity);
            }
        });
    }

    protected String buildUrl(String pickedNumber, String pickedCategory, String pickedDifficulty) {
        String url =
                "https://opentdb.com/api.php?"
                        +"amount=" + pickedNumber
                        + "&category=" + categoriesMap.get(pickedCategory)
                        + "&difficulty=" + difficultiesMap.get(pickedDifficulty)
                        + "&type=multiple";
        return url;
    }
}
