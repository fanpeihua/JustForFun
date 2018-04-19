package fanpeihua.justforfun.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import fanpeihua.justforfun.main.MainActivity;

/**
 * Created by oneball on 2018/3/14.
 */

public class MyHandler {

    public static class WeakHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public WeakHandler(MainActivity mainActivity) {
            this.mActivity = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {

            }
        }
    }
}
