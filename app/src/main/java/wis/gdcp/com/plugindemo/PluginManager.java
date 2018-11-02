package wis.gdcp.com.plugindemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private DexClassLoader dexClassLoader;
    private Resources resources;
    private Context context;
    private PackageInfo packageInfo;

    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {

    }


    public void setContext(Context context) {
        this.context = context;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }


    public Resources getResources() {
        return resources;
    }

    public void loadPath(String path){
        //这里path是apk的全路径
        File outDexFile=context.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader=new DexClassLoader(path,outDexFile.getAbsolutePath(),null,context.getClassLoader());
        //获取全部ACTIVITIE的信息
        packageInfo=context.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        try {
            AssetManager assetManager=AssetManager.class.newInstance();
            //反射设置属性
            Method method=AssetManager.class.getMethod("addAssetPath",String.class);
            method.invoke(assetManager,path);
            resources=new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }
}
