package com.magnit.testandroid.presenter;


import android.content.Context;
import android.os.AsyncTask;

import com.magnit.testandroid.R;
import com.magnit.testandroid.model.InfoDb;
import com.magnit.testandroid.model.ManagerDbImpl;
import com.magnit.testandroid.model.Values;
import com.magnit.testandroid.presenter.interfaces.SettingsPresenter;
import com.magnit.testandroid.view.interfaces.SettingsView;

import java.util.ArrayList;

public class SettingsPresenterImpl implements SettingsPresenter {
    private ManagerDbImpl mManagerDbImpl;
    private SettingsView mSettingsView;
    private ArrayList<Values> mValues;

    public SettingsPresenterImpl(SettingsView settingsView) {
        this.mSettingsView = settingsView;
        this.mManagerDbImpl = new ManagerDbImpl((Context) settingsView);
    }

    @Override
    public void makeChanges(String row, String ratio) {
        try {
            if (row.isEmpty()) {
                        mSettingsView.showMessageErrorNumberRow(R.string.absent_line_number);
            }
            if (ratio.isEmpty()) {
                mSettingsView.showMessageErrorRatio(R.string.absent_value);
            }
            if (!row.isEmpty() && !ratio.isEmpty()) {
                int numberRow = Integer.parseInt(row);
                float ratioRow = Float.parseFloat(ratio);
                if (numberRow >= 0 && numberRow <= 99) {
                    if (ratioRow >= 0.0 && ratioRow <= 1.0) {
                        Values value = new Values(numberRow, ratioRow);
                        mManagerDbImpl.updateValue(value);
                        mManagerDbImpl.addValue(new Values(numberRow, ratioRow), InfoDb.DbHistory.TABLE_NAME, new String[]{InfoDb.DbHistory.COLUMN_NAME_ID_MAIN, InfoDb.DbHistory.COLUMN_NAME_RATIO});
                        mSettingsView.clearFocusInputFields();
                        mValues.add(value);
                        //Чтобы лишний раз не обращаться в бд, обновляю список истории локально. Потому что после нажатия кнопки остаюсь на этой activity
                        mSettingsView.updateListLocally();
                    } else {
                        mSettingsView.showMessageErrorRatio(R.string.value_outside_range);
                    }
                } else {
                    mSettingsView.showMessageErrorNumberRow(R.string.invalid_line_number);
                }
            }
        } catch (Exception e) {
            mSettingsView.showMessage(e.getMessage());
        }
    }

    private ArrayList<Values> getData() {
        return mManagerDbImpl.getListValues(InfoDb.DbHistory.TABLE_NAME);
    }

    @Override
    public void loadingData() {
        try {
            new BackgroundTask().execute();
        } catch (Exception e) {
            mSettingsView.showMessage(e.getMessage());
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
            mValues = values;
            //в главном потоке добавляем их на ListView
            mSettingsView.showList(mValues);
        }
    }
}
