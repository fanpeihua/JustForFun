package fanpeihua.justforfun.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.TimeZone;

import fanpeihua.justforfun.application.MyApplication;
import fanpeihua.justforfun.R;

/**
 * Created by oneball on 2018/3/22.
 */

public class Utils {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getsInstance().
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }


    public static String showDate(Context context, String date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        String year = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String month = formatData(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String day = formatData(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (date.contains(year + "-" + month + "-" + day)) {
            return context.getString(R.string.today);
        } else {
            date = date.substring(0, 10);
            String[] dates = date.split("-");
            return dates[1] + context.getString(R.string.month) + dates[2] + context.getString(R.string.
                    day);
        }
    }

    public static String formatData(int data) {
        return data < 10 ? "0" + data : data + "";
    }


    public static int getCurrentViewIndex(LinearLayoutManager layoutManager) {
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        int currentIndex = firstVisibleItem;
        int lastHeight = 0;
        for (int i = firstVisibleItem; i <= lastVisibleItem; i++) {
            View view = layoutManager.getChildAt(i - firstVisibleItem);
            if (null == view) {
                continue;
            }

            int[] location = new int[2];
            view.getLocationOnScreen(location);

            Rect localRect = new Rect();
            view.getLocalVisibleRect(localRect);

            int showHeight = localRect.bottom - localRect.top;
            if (showHeight > lastHeight) {
                currentIndex = i;
                lastHeight = showHeight;
            }
        }

        if (currentIndex < 0) {
            currentIndex = 0;
        }

        return currentIndex;
    }

    public static String formatDate(String date) {
        return date.split(" ")[0].replace("-",
                "<font color='#878787'> / </font>");
    }

    public static String formatUrl(String url) {
        if (url.contains("date=")) {
            return url.split("date=")[1];
        } else if (url.contains("start=")) {
            return url.split("start=")[1];
        } else if (url.contains("title=")) {
            return url.split("title=")[1].split("&")[0];
        } else {
            return url.split("page=")[1];
        }
    }

    public static String durationFormat(Long duration) {
        long minute = duration / (long) 60;
        long second = duration % (long) 60;
        return minute <= (long) 9 ?
                (second <= (long) 9 ? "" + '0' + minute + "' 0" + second + "''" : "" + '0' + minute + "' " + second + "''")
                : (second <= (long) 9 ? "" + minute + "' 0" + second + "''" : "" + minute + "' " + second + "''");
    }

    public static String durationFormat(int duration) {
        return durationFormat((long) duration).substring(0, 6).replace("' ", ":");
    }

    public static void startAnimation(Context context, ImageView view) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_alpha);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public static String getCreateTime(Context context, long olderTime) {
        long currentTime = System.currentTimeMillis();
        long hour = (currentTime - olderTime) / 1000 / 3600;
        if (hour < 24) {
            return String.format(context.getString(R.string.create_date_hour), String.valueOf(hour));
        } else if (hour < (24 * 30)) {
            return String.format(context.getString(R.string.create_date_day), String.valueOf(hour / 24));
        } else {
            return String.format(context.getString(R.string.create_date_month), String.valueOf(hour / (24 * 30)));
        }

    }

    public static void setSoftInputActive(Context context, View view, boolean isShow) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        if (isShow) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);

    }
}

