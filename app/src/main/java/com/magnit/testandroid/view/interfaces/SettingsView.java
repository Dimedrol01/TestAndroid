package com.magnit.testandroid.view.interfaces;

public interface SettingsView extends LoadingView {
    void showMessageErrorNumberRow(int codeString);
    void showMessageErrorRatio(int codeString);
    void cleanInputFields();
}
