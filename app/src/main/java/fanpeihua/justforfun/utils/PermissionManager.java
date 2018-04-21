package fanpeihua.justforfun.utils;

import android.app.Activity;
import android.os.Build;

import java.util.concurrent.ConcurrentHashMap;

public class PermissionManager {
    public static ConcurrentHashMap<Integer, PermissionResultListener> mListenerMap = new ConcurrentHashMap<>();

    /**
     * 权限申请
     *
     * @param context Activity
     */

    public static void requestPermission(Activity context,
                                         String desc,
                                         int requestCode,
                                         PermissionResultListener permissionsListener,
                                         String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mListenerMap.put(requestCode, permissionsListener);
        }
    }

    public interface PermissionResultListener {
        void onPermissionGranted(int requestCode);

        void onPermissionDenied(int requestCode);
    }
}
