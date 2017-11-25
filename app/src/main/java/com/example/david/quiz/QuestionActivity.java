package com.example.david.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Question> questionList = new ArrayList<Question>();
    int score;
    int questionId;
    String url;
    TextView textViewQuestion;
    Button buttonAnswer1;
    Button buttonAnswer2;
    Button buttonAnswer3;
    Button buttonAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Bundle bundle = getIntent().getExtras();
        // Making a request to url and getting response
        url = bundle.getString("url");
        Log.e("DEBUG", "Calling url: " + url);
        try{
            new GetQuestions().execute().get();
        } catch (Exception e){
            Log.e("DEBUG", "Exception: " + e.toString());
        }
        textViewQuestion = (TextView)findViewById(R.id.textViewQuestion);
        buttonAnswer1 = (Button)findViewById(R.id.buttonAnswerOne);
        buttonAnswer2 = (Button)findViewById(R.id.buttonAnswerTwo);
        buttonAnswer3 = (Button)findViewById(R.id.buttonAnswerThree);
        buttonAnswer4 = (Button)findViewById(R.id.buttonAnswerFour);
        buttonAnswer1.setOnClickListener(this);
        buttonAnswer2.setOnClickListener(this);
        buttonAnswer3.setOnClickListener(this);
        buttonAnswer4.setOnClickListener(this);
        questionId = 0;
        score = 0;
        setNewQuestionView(questionId);
    }

    private void setNewQuestionView(int id){
        textViewQuestion.setText(questionList.get(id).getQuestion());
        int random = new Random().nextInt(4);
        if(random == 0){
            buttonAnswer1.setText(questionList.get(id).getCorrectAnswer());
            buttonAnswer2.setText(questionList.get(id).getIncorrectAnswers().get(0));
            buttonAnswer3.setText(questionList.get(id).getIncorrectAnswers().get(1));
            buttonAnswer4.setText(questionList.get(id).getIncorrectAnswers().get(2));
        } else if(random == 1){
            buttonAnswer2.setText(questionList.get(id).getCorrectAnswer());
            buttonAnswer3.setText(questionList.get(id).getIncorrectAnswers().get(0));
            buttonAnswer4.setText(questionList.get(id).getIncorrectAnswers().get(1));
            buttonAnswer1.setText(questionList.get(id).getIncorrectAnswers().get(2));
        } else if(random == 2){
            buttonAnswer3.setText(questionList.get(id).getCorrectAnswer());
            buttonAnswer4.setText(questionList.get(id).getIncorrectAnswers().get(0));
            buttonAnswer2.setText(questionList.get(id).getIncorrectAnswers().get(1));
            buttonAnswer1.setText(questionList.get(id).getIncorrectAnswers().get(2));
        } else if(random == 3){
            buttonAnswer4.setText(questionList.get(id).getCorrectAnswer());
            buttonAnswer1.setText(questionList.get(id).getIncorrectAnswers().get(0));
            buttonAnswer2.setText(questionList.get(id).getIncorrectAnswers().get(1));
            buttonAnswer3.setText(questionList.get(id).getIncorrectAnswers().get(2));
        }

    }

    @Override
    public void onClick(View v) {
        if (((Button)v).getText().equals(questionList.get(questionId).getCorrectAnswer()))
        {
            score++;
        }
        if (questionList.size() == questionId+1)
        {
            Intent mainActivity = new Intent(QuestionActivity.this, MainActivity.class);
            mainActivity.putExtra("score", Integer.toString(score));
            startActivity(mainActivity);
        } else {
            questionId++;
            setNewQuestionView(questionId);
        }
    }

    private class GetQuestions extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(QuestionActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e("AsyncTask", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray questions = jsonObj.getJSONArray("results");

                    // looping through All Contacts
                    for (int i = 0; i < questions.length(); i++) {
                        JSONObject c = questions.getJSONObject(i);
                        String category = c.getString("category");
                        String type = c.getString("type");
                        String difficulty = c.getString("difficulty");
                        String question = c.getString("question");
                        String correctAnswer = c.getString("correct_answer");

                        JSONArray incorrectAnswersJSONArray = c.getJSONArray("incorrect_answers");
                        ArrayList<String> incorrectAnswers = new ArrayList<String>();
                        for (int j = 0; j < incorrectAnswersJSONArray.length(); j++)
                        {
                            incorrectAnswers.add(incorrectAnswersJSONArray.getString(j));
                        }

                        Question singleQuestion = new Question(
                                category,
                                type,
                                difficulty,
                                question,
                                correctAnswer,
                                incorrectAnswers
                        );

                        questionList.add(singleQuestion);
                    }
                } catch (final JSONException e) {
                    Log.e("AsyncTask", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e("AsyncTask", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
