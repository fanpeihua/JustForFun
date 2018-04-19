package fanpeihua.justforfun.utils;

import android.content.Context;

/**
 * Created by oneball on 2018/3/14.
 */

public class AppManager {
    private static AppManager instance;
    private Context context;

    private AppManager(Context context) {
//        this.context = context;
        this.context = context.getApplicationContext();
    }

    public static AppManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppManager(context);
        }
        return instance;
    }

}
