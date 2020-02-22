package com.kenkikaku.test2020background2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static java.net.HttpURLConnection.*;

//
//  HttpUrlConnection(ライブラリ使わずJava標準のURLConnectionを使う方法)
//

public class HashMap extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] object) {
    //protected Object doInBackground(HttpURLConnection[] httpURLConnection) {

/*
        //HTTPヘッダ(指定したければ)
        Map<String,String> headers=new java.util.HashMap<String,String>();
        String put = headers.put("X-Example-Header", "Example-Value");
        String resultStr = get("http://hogehoge/foofoo", "UTF-8", headers);

    //POST呼び出し方
        String postJson = "[{\"message\":{\"number\":\"1\",\"value\":\"Hello\"}}]";
        Map<String, String> headers = new java.util.HashMap<String, String>(); // HTTPヘッダ(指定したければ)
        headers.put("X-Example-Header", "Example-Value");
        String postResult = post("http://hogehoge/foofoo", "UTF-8", headers, postJson);
*/

        Log.e("HashMap", "doInBackground()");

        return null;
    }

    //  HttpUrlConnectionでGETのサンプルコード
    public static String get(String endpoint, String encoding, Map<String, String> headers) throws IOException {
        Log.e("HashMap", "Get()");

        final int TIMEOUT_MILLIS = 0;// タイムアウトミリ秒：0は無限
        final StringBuffer sb = new StringBuffer("");

        HttpURLConnection httpConn = null;
        BufferedReader br = null;
        InputStream is = null;
        InputStreamReader isr = null;

        try {
            Log.e("HashMap", "Get() try");
            URL url = new URL(endpoint);
            Log.e("HashMap", "Get() url: " + url);
            httpConn = (HttpURLConnection) url.openConnection();

 //           Log.e("Task", "Get() httpConn: " + httpConn);
            httpConn.setConnectTimeout(TIMEOUT_MILLIS);// 接続にかかる時間
            httpConn.setReadTimeout(TIMEOUT_MILLIS);// データの読み込みにかかる時間
            httpConn.setRequestMethod("GET");// HTTPメソッド
            httpConn.setUseCaches(false);// キャッシュ利用
            httpConn.setDoOutput(false);// リクエストのボディの送信を許可(GETのときはfalse,POSTのときはtrueにする)
            httpConn.setDoInput(true);// レスポンスのボディの受信を許可

            Log.e("HashMap", "Get()headers: " + headers);
            // HTTPヘッダをセット
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpConn.setRequestProperty(key, headers.get(key));// HTTPヘッダをセット
                }
            }
            Log.e("HashMap", "Get() connect() url: " + url);

               httpConn.connect();       //2020.2.15







            final int responseCode = httpConn.getResponseCode();
            Log.e("HashMap", "Get() responseCode : " + responseCode);

            if (responseCode == HTTP_OK) {
                Log.e("HashMap", "Get()  OK");
                is = httpConn.getInputStream();
                isr = new InputStreamReader(is, encoding);
                br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                Log.e("HashMap", "Get() Err");
                // If responseCode is not HTTP_OK
            }

        } catch (IOException e) {
            throw e;
        } finally {
            // fortify safeかつJava1.6 compliantなclose処理
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return sb.toString();
    }
    // 2020.2.15 deve
    private static void readStream(InputStream in) {

    }



    //   HttpUrlConnectionでPOST(JSON文字列をPOSTする例)
    public static String post(String endpoint, String encoding, Map<String, String> headers, String jsonString) throws IOException {
        Log.e("HashMap", "Post()");
        final int TIMEOUT_MILLIS = 0;// タイムアウトミリ秒：0は無限

        final StringBuffer sb = new StringBuffer("");

        HttpURLConnection httpConn = null;
        BufferedReader br = null;
        InputStream is = null;
        InputStreamReader isr = null;

        try {
            Log.e("HashMap", "Post() try");
            URL url = new URL(endpoint);
            Log.e("HashMap", "Post() url: " + url);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setConnectTimeout(TIMEOUT_MILLIS);// 接続にかかる時間
            httpConn.setReadTimeout(TIMEOUT_MILLIS);// データの読み込みにかかる時間
            httpConn.setRequestMethod("POST");// HTTPメソッド
            httpConn.setUseCaches(false);// キャッシュ利用
            httpConn.setDoOutput(true);// リクエストのボディの送信を許可(GETのときはfalse,POSTのときはtrueにする)
            httpConn.setDoInput(true);// レスポンスのボディの受信を許可

            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpConn.setRequestProperty(key, headers.get(key));// HTTPヘッダをセット
                }
            }

            OutputStream os = httpConn.getOutputStream();
            final boolean autoFlash = true;
            PrintStream ps = new PrintStream(os, autoFlash, encoding);
            ps.print(jsonString);
            ps.close();

            final int responseCode = httpConn.getResponseCode();

            if (responseCode == HTTP_OK) {
                Log.e("HashMap", "Post()  OK");
                is = httpConn.getInputStream();
                isr = new InputStreamReader(is, encoding);
                br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                // If responseCode is not HTTP_OK
                Log.e("HashMap", "Post() err");
            }

        } catch (IOException e) {
            throw e;
        } finally {
            // fortify safeかつJava1.6 compliantなclose処理
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return sb.toString();
    }

}
