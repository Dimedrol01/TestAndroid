package com.magnit.testandroid.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.magnit.testandroid.R;
import com.magnit.testandroid.presenter.interfaces.SecondPresenter;
import com.magnit.testandroid.presenter.SecondPresenterImpl;
import com.magnit.testandroid.view.custom.Button;
import com.magnit.testandroid.view.interfaces.SecondView;


public class SecondActivity extends AppCompatActivity implements SecondView {
    private TextView mTextSelectedItem;
    private Button mButtonSelectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextSelectedItem = (TextView) findViewById(R.id.textSelectedItem);
        mButtonSelectedItem = (Button) findViewById(R.id.buttonSelectedItem);
        SecondPresenter secondPresenter = new SecondPresenterImpl(this);
        secondPresenter.loadSelection();
    }

    @Override
    public void showResult() {
        String numberRow = getIntent().getStringExtra("v");
        setTitle("Row №" + numberRow);
        mTextSelectedItem.setText(numberRow);
        mButtonSelectedItem.setRatio(getIntent().getFloatExtra("r", 0.0f));
    }
}
