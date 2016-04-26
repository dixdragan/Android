package com.example.dragan.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView resultView;
    TextView timeView;
    EditText input;
    private static long time;

    private class MyTask extends AsyncTask <Integer,Integer,BigInteger>{ //params,progress,results
        protected BigInteger doInBackground(Integer[] number){  //BACKGROUND
            int value = number[0];
            BigInteger result = BigInteger.ONE;
            for (int i = 1; i <= value; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;   //Will be passed to onPostExecute
        }
        protected void onPostExecute(BigInteger result){   //UI
            progressBar.setVisibility(View.INVISIBLE);
            time=System.currentTimeMillis()-time;
            resultView.setText(result.toString());
            timeView.setText("\nFinished in: "+time+"ms");

        }
        protected void onProgressUpdate(Integer... progress){   //UI

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        resultView = (TextView) findViewById(R.id.result);
        timeView = (TextView) findViewById(R.id.timeView);
        input = (EditText) findViewById(R.id.editText);
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void onClick(View v){
        progressBar.setVisibility(View.VISIBLE);
        String in =input.getText().toString();
        if(in.equals("")){
            Toast.makeText(MainActivity.this, "Insert something", Toast.LENGTH_SHORT).show();
        }
        else{
            Integer calculate = Integer.parseInt(input.getText().toString());
            if (calculate < 1) {
                Toast.makeText(MainActivity.this, "FTW NO!", Toast.LENGTH_SHORT).show();
            } else {
                time = System.currentTimeMillis();
                new MyTask().execute(calculate);// STARTING
            }
        }
    }
}
