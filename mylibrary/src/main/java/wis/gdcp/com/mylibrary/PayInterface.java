package wis.gdcp.com.mylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public interface PayInterface {
    //传递上下文
    public void attach(Activity activity);
    //传递生命周期
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onStop();
    public void onPause();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();

}
