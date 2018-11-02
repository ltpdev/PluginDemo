package wis.gdcp.com.plugindemo;

import android.content.Intent;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
    }

    public void loadPluin(View view){
        String path= Environment.getExternalStorageDirectory().getAbsolutePath();
        File file=new File(path,"plugin.apk");
        PluginManager.getInstance().loadPath(file.getAbsolutePath());
    }

    public void skinPluin(View view){
        //找到全类名
        String className=PluginManager.getInstance().getPackageInfo().activities[0].name;
        Intent intent=new Intent(this,PluginActivity.class);
        intent.putExtra("className",className);
        startActivity(intent);
    }
}
