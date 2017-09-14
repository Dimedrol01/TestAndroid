package com.magnit.testandroid.presenter;


import com.magnit.testandroid.presenter.interfaces.SecondPresenter;
import com.magnit.testandroid.view.interfaces.SecondView;

public class SecondPresenterImpl implements SecondPresenter {

    private SecondView mSecondView;

    public SecondPresenterImpl(SecondView secondView) {
        this.mSecondView = secondView;
    }

    @Override
    public void loadSelection() {
        //показываем результат выбора
        mSecondView.showResult();
    }
}
