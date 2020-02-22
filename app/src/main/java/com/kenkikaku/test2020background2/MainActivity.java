package com.kenkikaku.test2020background2;


//AndroidX
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.kenkikaku.test2020background2.HashMap.get;
import static com.kenkikaku.test2020background2.HashMap.post;


public class MainActivity extends AppCompatActivity {

    private TestTask testTask;
    private TextView textView;
    private HttpURLConnection httpURLConnection;
 //   private HashMap hashMap;
    private String resultStr = "";
    private String postResult = "";
    private String url1 = "https://drive.google.com/open?id=1BylPdU_hQl6PS6ct3Y6F_pi7NK91b06pyUcwDHX8KDI";
    private String url2 = "https://drive.google.com/file/d/1ywUptrqDbrigc_R4cmKr_sQWFeY3wCmc/view?usp=sharing";
    private String urlandroid = "http：//www.android.com/";
//      HttpResponsAsync
    public String httpStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // com.kenkikaku.test2020background2.HashMap.execute(0);


        Log.e("Main","onCreate Start");
        textView = findViewById(R.id.text_view);

        Button  buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Main","ボタンをタップして非同期処理を設定");
                // ボタンをタップして非同期処理を設定
                testTask = new TestTask();
                Log.e("Main","Listenerを設定");
                // Listenerを設定
                testTask.setListener(createListener());
                Log.e("Main"," 非同期処理を開始");
                // 非同期処理を開始
                testTask.execute(0);
            }
        });

        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Main","button_clear");
                textView.setText(String.valueOf(0));
            }
        });

        Button buttonBackG = findViewById(R.id.button_backgraund);
        buttonBackG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Main"," button_backgraund");

                httpURLConnection = new HttpURLConnection(null) {
                    @Override
                    public void disconnect() {

                    }

                    @Override
                    public boolean usingProxy() {
                        httpURLConnection.setRequestMethod();
                        return false;
                    }

                    @Override
                    public void connect() throws IOException {

                    }
                };
            }
        });

 /*
    試験
  */

 //  HttpResponsAsync  2020.2.17
        Log.e("Main","HttpResponsAsync.execute()");
   //     HttpResponsAsync.execute(0);


//HttpUrlConnection(ライブラリ使わずJava標準のURLConnectionを使う方法)

 //    getHM();     // GET呼び出し方
       Log.e("Main","GET  resultStr: " + resultStr);
       textView.setText(resultStr);

 //   postHM();
        Log.e("Main","POST  postResult: "  + postResult);


/*    // 2020.2.16
        試験
 */





    }

    private void getHM() {
//HttpUrlConnection(ライブラリ使わずJava標準のURLConnectionを使う方法)
        // GET呼び出し方
        //HTTPヘッダ(指定したければ)
        Log.e("Main","GET 呼び出し方");
        Map<String,String> headers=new HashMap<String,String>();
        headers.put("X-Example-Header","Example-Value");
        try {
            Log.e("Main","GET try");
            //  resultStr = get("http://hogehoge/foofoo", "UTF-8", headers);
            resultStr = get(urlandroid, "UTF-8", null);
        } catch (IOException e) {
            Log.e("Main","GET  try ERR");
            e.printStackTrace();
        }
    }
    private void postHM() {
        //POST呼び出し方
           Map<String, String> headers = new HashMap<String, String>(); // HTTPヘッダ(指定したければ)
           headers.put("X-Example-Header", "Example-Value");

        //POST呼び出し方
        String postJson = "[{\"message\":{\"number\":\"1\",\"value\":\"Hello\"}}]";
        // String postResult;
        Log.e("Main","POST  呼び出し方");
        try {
            Log.e("Main","POST  try");
            postResult = post(url2, "UTF-8", headers, postJson);
        } catch (IOException e) {
            Log.e("Main","POST  try Err");
            e.printStackTrace();
        }
    }
  /*
     試験　END
  */

    @Override
    protected void onDestroy() {
        testTask.setListener(null);
        Log.e("Main","onDestroy()");
        super.onDestroy();
    }

    /*
    TestTaskの interface Listener
     */
    private TestTask.Listener createListener() {
        Log.e("Main","createListener()");
        return new TestTask.Listener() {
            @Override
            //   public void onSuccess(int count)
            public void onSuccess(int count) {
                Log.e("Main","onSuccess()count: " +
                        "" + count);
                // textView.setText(String.valueOf(count));
                textView.setText(String.valueOf(count));
            }
        };
    }

    /*
    HttpResponsAsync
     */
    public void getString(String s) {
        textView.setText(s);

        Log.e("Main","getString(String s)");
    }
}