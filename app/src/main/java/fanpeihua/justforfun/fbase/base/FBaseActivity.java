package fanpeihua.justforfun.fbase.base;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

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
        setStatusColor();
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
        PermissionManager.
    }

    protected void initTheme() {

    }

    protected void initThemeAttrs() {

    }

    private void onUnSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        if (disposableMap != null && disposableMap.size() > 0) {
            disposableMap.clear();
        }
    }

}
