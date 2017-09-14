package com.magnit.testandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.magnit.testandroid.R;
import com.magnit.testandroid.model.Values;
import com.magnit.testandroid.presenter.FirstPresenterImpl;
import com.magnit.testandroid.presenter.interfaces.FirstPresenter;
import com.magnit.testandroid.view.custom.Adapter;
import com.magnit.testandroid.view.interfaces.FirstView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements FirstView, AdapterView.OnItemClickListener {

    private FirstPresenter mFirstPresenter;
    private ListView mMainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mFirstPresenter = new FirstPresenterImpl(this);
        mMainListView = (ListView) findViewById(R.id.mainListView);
        mMainListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //перед тем, как UI станет доступен пользователю загружаем список с историей
        mFirstPresenter.loadingData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem = item.getItemId();
        if (idItem == R.id.itemSettings) {
            Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void openSelectionScreen(int value, float ratio) {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra("v", String.valueOf(value));
        intent.putExtra("r", ratio);
        startActivity(intent);
    }

    @Override
    public void showList(ArrayList<Values> values) {
        Adapter adapter = new Adapter(this, values);
        mMainListView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mFirstPresenter.showSelection(i);
    }
}
