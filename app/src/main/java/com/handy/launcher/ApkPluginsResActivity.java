package com.handy.launcher;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;

/**
 * file name
 *
 * @author LiuJie https://github.com/Handy045
 * @description functional description.
 * @date Created in 2018/11/29 9:14 AM
 * @modified By liujie
 */
public class ApkPluginsResActivity extends AppCompatActivity {

    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apppluginsres);
        text = findViewById(R.id.text);
        image = findViewById(R.id.image);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<PluginBean> pluginBeans = findAllPlugin();
        if (!pluginBeans.isEmpty()) {
            for (PluginBean pluginBean : pluginBeans) {
                System.out.println(pluginBean.getString());
            }
            try {
                Context pluginContext = createPackageContext(pluginBeans.get(0).pkgName, CONTEXT_IGNORE_SECURITY | CONTEXT_INCLUDE_CODE);
                int resourceId = dynamicLoadApk(pluginBeans.get(0).pkgName, pluginContext);
                image.setImageDrawable(pluginContext.getResources().getDrawable(resourceId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查找手机内所有的插件
     *
     * @return 返回一个插件List
     */
    private List<PluginBean> findAllPlugin() {
        List<PluginBean> plugins = new ArrayList<>();
        PackageManager pm = getPackageManager();
        //通过包管理器查找所有已安装的apk文件
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo info : packageInfos) {
            //得到当前apk的包名
            String pkgName = info.packageName;
            //得到当前apk的sharedUserId
            String shareUesrId = info.sharedUserId;
            //判断这个apk是否是我们应用程序的插件
            if ("com.handy.launcher.test.userid".equals(shareUesrId) && !pkgName.equals(this.getPackageName())) {
                //得到插件apk的名称
                String label = pm.getApplicationLabel(info.applicationInfo).toString();
                PluginBean bean = new PluginBean(label, pkgName);
                plugins.add(bean);
            }
        }
        return plugins;
    }

    /**
     * 加载已安装的App资源
     *
     * @param packageName   应用的包名
     * @param pluginContext 插件app的上下文
     * @return 对应资源的id
     */
    private int dynamicLoadApk(String packageName, Context pluginContext) throws Exception {
        //第一个参数：包含dex的apk或者jar的路径，第二个参数为父加载器
        PathClassLoader pathClassLoader = new PathClassLoader(pluginContext.getPackageResourcePath(), ClassLoader.getSystemClassLoader());
        Class<?> clazz = Class.forName(packageName + ".R$mipmap", true, pathClassLoader);
        Field field = clazz.getDeclaredField("ic_launcher");
        return field.getInt(R.mipmap.class);
    }

    class PluginBean {
        String label;
        String pkgName;

        PluginBean(String label, String pkgName) {
            this.label = label;
            this.pkgName = pkgName;
        }

        public String getString() {
            return "Name:" + label + " pkgName:" + pkgName;
        }
    }
}
