package com.kenkikaku.test2020background2;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

public class TestTaskLoader extends AsyncTaskLoader<TestTaskLoader.Result> {
    public class Result {
        public int    status;
        public String message;
    }

    private Result  mResult;
    private boolean mIsStarted;



    public TestTaskLoader(Context context) {
        super(context);
        mResult    = null;
        mIsStarted = false;
    }

    @Override
    public TestTaskLoader.Result loadInBackground() {
        TestTaskLoader.Result result = new TestTaskLoader.Result();

        //
        // ここで非同期の処理をする
        //

        return result;
    }

    @Override
    public void deliverResult(Result result) {
        // 非同期処理の結果を保存しておく
        mResult = result;
        super.deliverResult(result);
    }

    @Override
    protected void onStartLoading() {
        // 呼び出し元のActivityが回転などで再作成されるとinitLoaderを再度呼ばなければならない
        // initLoaderがよばれるとココに来るが再度forceLoadしてしまうと
        // 実行中のloadInBackgroundは破棄されもう一度loadInBackgroundが開始されてしまう
        // 実行中のloadInBackgroundが無い時だけ実行を開始し終了している時は直ちに結果を返す
        if (null != mResult) {
            deliverResult(mResult);
            return;
        }
        if ((! mIsStarted) || takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mIsStarted = true;
    }
}