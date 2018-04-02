package com.study.lesson14;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bStart) Button bStart;
    @BindView(R.id.etString) EditText etString;
    @BindView(R.id.tvString) TextView tvString;

    private MyAsyncTask myAsyncTask;
    public String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bStart)
    public void onClick(){
        pass = etString.getText().toString();
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(pass);
    }

    class MyAsyncTask extends AsyncTask<String, String, Void> {
        int count;

        String cKString = "";

        @Override protected void onPreExecute() {
            super.onPreExecute();
            count = pass.length();
        }

        @Override protected Void doInBackground(String... voids) {
            for (int i = 0; i < count; i++) {
                for (char j = '\u0000'; j < 'z'; j++) {
                    if (pass.charAt(i) == j){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cKString = cKString + j;
                    }
                }
                publishProgress(cKString);
            }
            return null;
        }
        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tvString.setText(cKString);
        }

        @Override protected void onCancelled() {
            super.onCancelled();
        }
    }

}


