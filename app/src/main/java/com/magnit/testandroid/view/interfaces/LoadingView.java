package com.magnit.testandroid.view.interfaces;


import com.magnit.testandroid.model.Values;

import java.util.ArrayList;

interface LoadingView {
    void showList(ArrayList<Values> values);

    void showMessage(String message);
}
