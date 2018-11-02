package wis.gdcp.com.plugindemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Constructor;

import wis.gdcp.com.mylibrary.PayInterface;

public class PluginActivity extends Activity {
  private String className;
  private PayInterface payInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_plugin);
        className=getIntent().getStringExtra("className");
        //加载插件的activity
        try {
            Class clazzActivity=getClassLoader().loadClass(className);
            //获取要加载activity的对象
            Constructor constructor=clazzActivity.getConstructor(new Class[]{});
            Object intance=constructor.newInstance(new Object[]{});
            payInterface= (PayInterface) intance;
            payInterface.attach(this);
            payInterface.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        payInterface.onCreate(savedInstanceState);
    }


    @Override
    public void startActivity(Intent intent) {
        String className=intent.getStringExtra("className");
        Intent m=new Intent(this,PluginActivity.class);
        m.putExtra("className",className);
        super.startActivity(m);
    }

    @Override
    protected void onStart() {
        payInterface.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        payInterface.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        payInterface.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        payInterface.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        payInterface.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        payInterface.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return  PluginManager.getInstance().getResources();
    }
}
