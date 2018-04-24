package fanpeihua.justforfun.fbase.base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.utils.PermissionManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class FBaseActivity<T extends FBasePresenter, V extends ViewDataBinding>
        extends AppCompatActivity {

    protected Activity mContext;
    protected T mPresenter;
    protected CompositeDisposable compositeDisposable;
    protected ArrayMap<String, Disposable> disposableMap;
    protected static ConcurrentHashMap<String, String> paramMap = new ConcurrentHashMap<>();
    protected HashMap<String, String> valueMap = new HashMap<>();

    protected V mBinding;

    protected FragmentManager fm;
    protected FBaseFragment currentFragment;
    protected ArrayMap<String, Integer> mThemeColorMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityController.addActivity(this);
        setRequestedOrientation(getOrientation());
        super.onCreate(savedInstanceState);
        mContext = this;
        initTheme();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initWindowTransition(getWindowTransition());
        }
        int layoutID = getLayoutID();
        if (layoutID != 0) {
            setContentView(layoutID);
            mBinding = DataBindingUtil.setContentView(mContext, layoutID);
        }
        initThemeAttrs();
//        setStatusColor();
        mPresenter = getPresenter();
        initValueFromPrePage();
        initEventAndData(savedInstanceState);
    }

    /**
     * 横竖屏，默认是竖屏
     *
     * @return
     */
    protected int getOrientation() {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    protected abstract void initEventAndData(Bundle savedInstanceState);

    protected abstract T getPresenter();

    protected abstract int getLayoutID();

    protected abstract int setFragmentContainerResId();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        onUnSubscribe();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.onRequestResult(requestCode, permissions, grantResults);
    }

    /**
     * 初始化主题颜色
     */
    protected void initTheme() {

    }

    /**
     * 初始化获取当前主题中的颜色
     */
    protected void initThemeAttrs() {

    }

    /**
     * 设置过渡动画
     * 默认是淡入淡出，可重写
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected Transition getWindowTransition() {
        return new Fade();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initWindowTransition(Transition transition) {
        getWindow().setReturnTransition(transition);
        getWindow().setExitTransition(transition);
        getWindow().setEnterTransition(transition);
        getWindow().setReenterTransition(transition);
    }

    protected void showFragment(FBaseFragment fragment, int position) {
        if (fm == null) {
            fm = getSupportFragmentManager();
        }
        FragmentTransaction transaction = fm.beginTransaction();
        //Fragment 添加
        if (!fragment.isAdded()) {
            transaction.add(setFragmentContainerResId(), fragment, position + "");
        }
        if (currentFragment == null) {
            currentFragment = fragment;
        }
        // 通过tag进行过渡动画滑动判断
        if (Integer.parseInt(currentFragment.getTag()) >= Integer.parseInt(fragment.getTag())) {
            transaction.setCustomAnimations(R.anim.fragment_push_left_in, R.anim.fragment_push_right_out);
        } else {
            transaction.setCustomAnimations(R.anim.fragment_push_right_in, R.anim.fragment_push_left_out);
        }

        transaction.hide(currentFragment).show(fragment);
        transaction.commit();
        currentFragment = fragment;
    }

    /**
     * 添加事件监听处理到 事件管理类
     *
     * @param disposable
     */
    protected void addSubscription(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected void addSubscription(String tag, Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        if (disposableMap == null) {
            disposableMap = new ArrayMap<>();
        }
        disposableMap.put(tag, disposable);
        compositeDisposable.add(disposable);
    }

    /**
     * RxJava 取消注册，避免内存泄漏
     * 取消以后就只能重新新建一个了
     */
    private void onUnSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        if (disposableMap != null && disposableMap.size() > 0) {
            disposableMap.clear();
        }
    }

    /**
     * 根据标识符移除Disposable
     *
     * @param tags
     */
    protected void removeDisposableByTag(String... tags) {
        if (disposableMap != null && disposableMap.size() > 0) {
            for (String tag : tags) {
                if (disposableMap.containsKey(tag)) {
                    compositeDisposable.remove(disposableMap.get(tag));
                    disposableMap.remove(tag);
                }
            }
        }
    }

    /**
     * 将参数值传递到下个页面
     *
     * @param name
     * @param value
     */
    public static void putParmToNextPage(String name, String value) {
        paramMap.put(name, value);
    }

    /**
     * 从上个页面取得传递参数的值
     *
     * @param name
     * @return
     */
    public String getValueFromPrePage(String name) {
        return valueMap.get(name);
    }

    public void initValueFromPrePage() {
        if (getIntent() == null) {
            return;
        }

        if (getIntent().getExtras() == null) {
            return;
        }

        Bundle extBundle = getIntent().getExtras();
        if (extBundle != null) {
            Iterator<String> it = extBundle.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if (key == null) {
                    continue;
                }
                String value = extBundle.getString(key);
                valueMap.put(key, value);
            }
        }
    }


    public static void toNextActivity(Context context, Class<? extends Activity> clazz, Activity preActivity) {
        Intent intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        Iterator it = paramMap.keySet().iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj != null) {
                String key = String.valueOf(obj);
                String value = paramMap.get(key);
                bundle.putString(key, value);
            }
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(preActivity).toBundle());
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    public void finish() {
        super.finish();
        ActivityController.finishActivity(this);
    }
}
