package com.kenkikaku.test2020background2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/*
    いつまでこの方法がサポートされるか微妙ですが、ご参考ください。

public class HttpResponsAsync extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] object) {
        return null;
    }
}
*/
//  public class HttpResponsAsync extends AsyncTask<Void, Void, String> {
public class HttpResponsAsync extends AsyncTask<URL, Void, String> {

    public static void execute(MainActivity mainActivity) {
    }

    @Override
    protected String doInBackground(URL... url) {
        Log.e("HttpReAs","doInBackground");
        HttpURLConnection con = null;
        URL urlX =  url[0];
     //   String urlSt = url2;


        // URL url = null;
        // String urlSt = "http://sample.jp";
        String urlSt = "https://drive.google.com/file/d/1ywUptrqDbrigc_R4cmKr_sQWFeY3wCmc/view?usp=sharing";

        try {
            // URLの作成
            Log.e("HttpReAs","doInBackground");
            try {
                url = new URL(urlX);
                Log.e("HttpReAs","url: " + url);
            } catch (MalformedURLException e) {
                Log.e("HttpReAs","MalformedURLException e");
                e.printStackTrace();
            }
            // 接続用HttpURLConnectionオブジェクト作成
            con = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            con.setRequestMethod("POST");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            con.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
            con.setDoOutput(true);

            // 接続
            Log.e("HttpReAs","con.connect()");
            con.connect(); // ①
            // 接続を開始しています。このメソッドが実行された時点で、
            // サーバーからレスポンスが返ってきます。

            // 本文の取得  <-  con.setDoInput(true);
            Log.e("HttpReAs","InputStream in");
            InputStream in = con.getInputStream();
            byte bodyByte[] = new byte[1024];   //"bodyByte"が本文のbody部分です
            in.read(bodyByte);
            in.close();

            //2020.2.16
            String testBody = "";
            testBody.valueOf(bodyByte);
            Log.e("HttpReAs"," testBody: " +  testBody);
            //       MainActivity.httpString = testBody;
            //     MainActivity.getString(testBody);
            // end


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // doInBackground後処理


    }

}