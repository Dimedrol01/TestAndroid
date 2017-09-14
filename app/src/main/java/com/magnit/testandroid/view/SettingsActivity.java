package com.magnit.testandroid.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.magnit.testandroid.R;
import com.magnit.testandroid.model.Values;
import com.magnit.testandroid.presenter.interfaces.SettingsPresenter;
import com.magnit.testandroid.presenter.SettingsPresenterImpl;
import com.magnit.testandroid.view.custom.Adapter;
import com.magnit.testandroid.view.interfaces.SettingsView;

import java.util.ArrayList;


public class SettingsActivity extends AppCompatActivity implements SettingsView, View.OnClickListener {
    private EditText mNumberRow;
    private EditText mRatio;
    private SettingsPresenter mSettingsPresenter;
    private ListView mListHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button addButton = (Button) findViewById(R.id.addButton);
        mNumberRow = (EditText) findViewById(R.id.numberRow);
        mRatio = (EditText) findViewById(R.id.ratio);
        mListHistory = (ListView) findViewById(R.id.listHistory);
        addButton.setOnClickListener(this);
        mSettingsPresenter = new SettingsPresenterImpl(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSettingsPresenter.loadingData();
    }

    @Override
    public void showMessageErrorNumberRow(int codeString) {
        mNumberRow.setError(getString(codeString));
    }

    @Override
    public void showMessageErrorRatio(int codeString) {
        mRatio.setError(getString(codeString));
    }

    @Override
    public void cleanInputFields() {
        mNumberRow.clearFocus();
        mNumberRow.setText("");
        mRatio.clearFocus();
        mRatio.setText("");
    }

    @Override
    public void showList(ArrayList<Values> values) {
        Adapter adapter = new Adapter(this, values);
        mListHistory.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }

    @Override
    public void onClick(View view) {
        mSettingsPresenter.makeChanges(mNumberRow.getText().toString(), mRatio.getText().toString());
        mSettingsPresenter.loadingData();
    }
}
