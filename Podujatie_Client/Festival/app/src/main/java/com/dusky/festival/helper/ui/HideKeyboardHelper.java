package com.dusky.festival.helper.ui;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by dusanmatejka on 6/12/16.
 */
public class HideKeyboardHelper {

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void setupUI(View view, final Activity activity) {
        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }

            });
        }else{
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                boolean isFirstTimeGetFocused = true;

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus && isFirstTimeGetFocused) {
                        ((EditText) v).getText().clear();
                        isFirstTimeGetFocused = false;
                    } else if (!hasFocus) {
                        isFirstTimeGetFocused = true;
                    }
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView, activity);
            }
        }
    }


}
