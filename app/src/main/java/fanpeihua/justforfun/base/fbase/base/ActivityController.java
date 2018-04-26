package fanpeihua.justforfun.base.fbase.base;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivityController {
    public static List<Activity> activities = new ArrayList<>();

    public static WeakReference<List<Activity>> listWeakReference = new WeakReference<>(activities);

    public static void addActivity(Activity activity) {
        listWeakReference.get().add(activity);
    }

    public static void finishActivity(Activity activity) {
        listWeakReference.get().remove(activity);
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    public static void finishAll() {
        for (Activity activity : listWeakReference.get()) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
