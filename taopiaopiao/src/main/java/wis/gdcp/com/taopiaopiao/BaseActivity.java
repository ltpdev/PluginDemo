package wis.gdcp.com.taopiaopiao;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import wis.gdcp.com.mylibrary.PayInterface;

public class BaseActivity extends Activity implements PayInterface{
    protected Activity that;
    @Override
    public void attach(Activity activity) {
      that=activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }


    @Override
    public void setContentView(View view) {
        if (that!=null){
            //作为插件来使用
            that.setContentView(view);
        }else {
            //独立安装
            super.setContentView(view);
        }

    }


    @Override
    public void startActivity(Intent intent) {
        Intent m=new Intent();
        m.putExtra("className",intent.getComponent().getClassName());
        m.putExtra("oldIntent",intent);
        that.startActivity(m);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (that!=null){
            //作为插件来使用
            that.setContentView(layoutResID);
        }else {
            //独立安装
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }



    @Override
    public void onDestroy() {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

    }


    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }
}
