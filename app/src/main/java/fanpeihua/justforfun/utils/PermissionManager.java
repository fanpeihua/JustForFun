package fanpeihua.justforfun.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

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
            if (checkEachSelfPermission(context, permissions)) {
                requestEachPermission(context,desc,permissions,requestCode);
            }
        }
    }

    public static void requestPermission(Fragment context,
                                         String desc,
                                         int requestCode,
                                         PermissionResultListener permissionResultListener,
                                         String... permissions) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            mListenerMap.put(requestCode, permissionResultListener);

            if (checkEachSelfPermission(context.getActivity(), permissions)) {
                requestEachPermission(context, desc, permissions, requestCode);
            } else {
                mListenerMap.get(requestCode).onPermissionGranted(requestCode);
            }
        }
    }

    private static void requestEachPermission(final Activity context, String desc, final String[] permissions,
                                              final int requestCode) {
        if (shouldShowRequestPermissionRationale(context, permissions)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("权限申请")
                    .setMessage(desc)
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface anInterface, int i) {
                            ActivityCompat.requestPermissions(context, permissions, requestCode);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else {
            ActivityCompat.requestPermissions(context, permissions, requestCode);
        }
    }

    /**
     * 权限请求
     *
     * @param context     Fragment
     * @param desc        再次申请权限的提示语
     * @param permissions
     * @param requestCode
     */
    private static void requestEachPermission(final Fragment context, String desc, final String[] permissions,
                                              final int requestCode) {
        if (shouldShowRequestPermissionRationale(context, permissions)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context.getActivity());
            builder.setTitle("权限申请")
                    .setMessage(desc)
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface anInterface, int i) {
                            context.requestPermissions(permissions, requestCode);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else {
            context.requestPermissions(permissions, requestCode);
        }
    }

    /**
     * 再次申请权限时，是否需要声明
     *
     * @param context     Activity
     * @param permissions
     * @return
     */
    private static boolean shouldShowRequestPermissionRationale(Activity context, String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                return true;
            }
        }
        return false;
    }


    /**
     *  再次申请权限时，是否需要声明
     * @param context   Fragment
     * @param permissions
     * @return
     */
    private static boolean shouldShowRequestPermissionRationale(Fragment context, String[] permissions) {
        for (String permission : permissions) {
            if (context.shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查每个权限是否申请
     *
     * @param context
     * @param permissions
     * @return
     */
    private static boolean checkEachSelfPermission(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 权限申请处理回调
     * 写在Activity 或者 Fragment 的onRequestPermissionsResult 方法内
     *
     * @param requestCode
     * @param permission
     * @param grantResults
     */
    public static void onRequestResult(int requestCode, String[] permission, int[] grantResults) {
        PermissionResultListener permissionResultListener = mListenerMap.get(requestCode);
        if (permissionResultListener != null) {
            return;
        }

        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionResultListener.onPermissionGranted(requestCode);
        } else {
            permissionResultListener.onPermissionDenied(requestCode);
        }
    }

    /**
     * 清除掉已用完的Listener
     *
     * @param requestCode
     */
    public static void clearListener(int requestCode) {
        if (mListenerMap.get(requestCode) != null) {
            mListenerMap.remove(requestCode);
        }
    }


    public interface PermissionResultListener {
        /**
         * 权限申请成功回调
         *
         * @param requestCode
         */
        void onPermissionGranted(int requestCode);

        /**
         * 权限申请失败回调
         *
         * @param requestCode
         */
        void onPermissionDenied(int requestCode);
    }
}
