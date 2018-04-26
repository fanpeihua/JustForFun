package fanpeihua.justforfun.application;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

import fanpeihua.justforfun.base.fbase.utils.useful.SPManager;
import fanpeihua.justforfun.di.component.AppComponent;
import fanpeihua.justforfun.di.component.DaggerAppComponent;
import fanpeihua.justforfun.di.module.AppModule;
import fanpeihua.justforfun.utils.GlobalVariable;

/**
 * Created by oneball on 2018/3/22.
 */

public class MyApplication extends Application {
    private static MyApplication sInstance;
    private static AppComponent mAppComponent;
//    private static RefWatcher sRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        if (sInstance == null) {
            sInstance = this;
        }

//        sRefWatcher = LeakCanary.install(this);
        initTBSWebView();

    }

    private void initTBSWebView() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //true 代表成功 load
                Log.d(GlobalVariable.TAG, " onViewInitFinished is " + b);
            }
        };
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

//    public static RefWatcher getsRefWatcher() {
//        return sRefWatcher;
//    }

    public static MyApplication getsInstance() {
        if (sInstance == null) {
            return new MyApplication();
        } else {
            return sInstance;
        }
    }

    public static AppComponent getmAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .build();
        }
        return mAppComponent;
    }

    public static boolean isNightTheme() {
        String isNight = SPManager.get().getStringValue(Config.SP.THEME, Config.FALSE);
        if (Config.FALSE.equals(isNight)) {
            return false;
        } else {
            return true;
        }
    }

}
