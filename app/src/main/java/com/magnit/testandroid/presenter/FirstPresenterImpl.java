package com.magnit.testandroid.presenter;


import android.content.Context;
import android.os.AsyncTask;

import com.magnit.testandroid.model.InfoDb;
import com.magnit.testandroid.model.ManagerDb;
import com.magnit.testandroid.model.ManagerDbImpl;
import com.magnit.testandroid.model.Values;
import com.magnit.testandroid.presenter.interfaces.FirstPresenter;
import com.magnit.testandroid.view.interfaces.FirstView;

import java.util.ArrayList;

public class FirstPresenterImpl implements FirstPresenter {
    private ManagerDb mManagerDb;
    private FirstView mFirstView;

    public FirstPresenterImpl(FirstView firstView) {
        this.mFirstView = firstView;
        mManagerDb = new ManagerDbImpl((Context) firstView);
    }

    private void writeData() {
        try {
            //добавляем записи в бд в цикле
            float r = 0.0f;
            for (int i = 0; i < 100; i++) {
                mManagerDb.addValue(new Values(i, r), InfoDb.DbEntries.TABLE_NAME, new String[] {InfoDb.DbEntries.COLUMN_NAME_VALUE, InfoDb.DbEntries.COLUMN_NAME_RATIO});
            }
        } catch (Exception e) {
            mFirstView.showMessage(e.getMessage());
        }
    }

    @Override
    public void showSelection(int position) {
        try {
            //получаем объект по позиции выбранной пользователей.
            // position + 1 потому, что определение нужных значений идет по первичному ключу в бд, а ListView дает position на единицу меньше
            Values value = mManagerDb.getValueByPosition(position + 1);
            //передаем нужные значения и открываем SecondActivity
            mFirstView.openSelectionScreen(value.getValue(), value.getRatio());
        } catch (Exception e) {
            mFirstView.showMessage(e.getMessage());
        }
    }

    private ArrayList<Values> getData() {
        try {
            if (!mManagerDb.existsRecords(InfoDb.DbEntries.TABLE_NAME)) {
                //если в базе нет записей, то заносим их
                writeData();
            }
            //возвращем список значений ArrayList со значениями
            return mManagerDb.getListValues(InfoDb.DbEntries.TABLE_NAME);
        } catch (Exception e) {
            mFirstView.showMessage(e.getMessage());
        }
        return null;
    }

    @Override
    public void loadingData() {
        try {
            //запускаем фоновый поток
            new BackgroundTask().execute();
        } catch (Exception e) {
            mFirstView.showMessage(e.getMessage());
        }
    }

    private class BackgroundTask extends AsyncTask<Void, Void, ArrayList<Values>> {

        @Override
        protected ArrayList<Values> doInBackground(Void... voids) {
            //в фоновом потоке готовим ArrayList со значениями
            return getData();
        }

        @Override
        protected void onPostExecute(ArrayList<Values> values) {
            //в главном потоке добавляем их на ListView
            mFirstView.showList(values);
        }
    }
}
