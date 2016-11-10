package lanou.carhometwo.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/10/24.
 */
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Bmob.initialize(this, "e5714d18cbe1a883a6630a402fa76f41");
    }

    public static Context getContext() {

        return context;
    }
}
