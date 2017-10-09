package com.headless;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */

    private static MainActivity inst;
    public static boolean active = false;
    
    public static MainActivity instance() {
      return inst;
    }


    @Override
    public void onStart() {
      super.onStart();
      active = true;
      inst = this;
    }

    @Override
    public void onStop() {
      super.onStop();
      active = false;
    }

    @Override
    protected String getMainComponentName() {
        return "headless";
    }
}
