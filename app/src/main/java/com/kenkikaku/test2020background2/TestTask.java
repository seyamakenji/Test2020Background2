package com.kenkikaku.test2020background2;

import android.os.AsyncTask;
import android.util.Log;

/*
このクラスの非同期を実行させるためにはexecute(…)をメインスレッドから呼び出します
 */
public class TestTask extends AsyncTask<Integer, Integer, Integer> {

    private Listener listener;

    /*  doInBackground(Integer... params)
    バックグラウンドでの非同期処理、何か重い処理を記述する
    ここに渡されるパラメータの型は1つめのParams
     */
    // 非同期処理
    @Override
    protected Integer doInBackground(Integer... params) {
        Log.e("TestTask","doInBackground()");

        // 10秒数える処理
        do{
            try {
                Log.e("TestTask","try ");
                //　1sec sleep
                //   Thread.sleep(1000);
                Thread.sleep(1000);


            } catch (InterruptedException e) {
                Log.e("TestTask","catch (InterruptedException e) ");
                Thread.currentThread().interrupt();
            }

            Log.d("TestTaskdebug",""+params[0]);
            params[0]++;
            Log.e("TestTask","params[0]++: " + params[0]);

            // publishProgress（）途中経過を返す
            //  onProgressUpdate(Integer... progress)を呼ぶ
            //　非同期でonProgressUpdate()がListnerを使い　Dataを返す？
            publishProgress(params[0]);
            Log.e("TestTask","publishProgress()途中経過を返す");
        }while(params[0]<10);
        Log.e("TestTask","return");
        return params[0] ;
    }

    /*  onProgressUpdate(Integer... progress)
    途中経過をメインスレッドで表示する場合
    doInBackground内でpublishProgressを設定するとメインスレッドから呼ばれ、
    パラメータの型は2つめのProgress
     */
    // 途中経過をメインスレッドに返す
    @Override
    protected void onProgressUpdate(Integer... progress) {
        Log.e("TestTask","up(0)onProgressUpdate()");
        if (listener != null) {
            listener.onSuccess(progress[0]);
            Log.e("TestTask","up(1)progress[0]     : " + progress[0]);
            Log.e("TestTask","up(2)listener != null: " + listener);
        }
    }

    /*  onPostExecute(Integer result)
    バックグラウンドでの非同期処理が終了後メインスレッドに結果を返す
    パラメータは3つめのResult
     */
    // 非同期処理が終了後、結果をメインスレッドに返す
    @Override
    //   protected void onPostExecute(Integer result)
    protected void onPostExecute(Integer result) {
        Log.e("TestTask","onPostExecute()　END");
        if (listener != null) {
            listener.onSuccess(result);
            Log.e("TestTask","result   : " + result);
            Log.e("TestTask","listener != null");
        }
    }


    void setListener(Listener listener) {
        Log.e("TestTask","setListener()");
        this.listener = listener;
    }

    interface Listener {
        //   void onSuccess(int count);
        void onSuccess(int count);
    }
}