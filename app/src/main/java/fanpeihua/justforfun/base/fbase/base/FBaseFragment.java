package fanpeihua.justforfun.base.fbase.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import fanpeihua.justforfun.main.MainActivity;
import fanpeihua.justforfun.utils.PermissionManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class FBaseFragment<T extends FBasePresenter, V extends ViewDataBinding> extends
        Fragment {
    protected View mView;
    protected Bundle savedInstanceState;
    private boolean isPrepare;
    private boolean isVisible;
    protected T mPresenter;
    protected CompositeDisposable compositeDisposable;
    protected ArrayMap<String, Disposable> disposableMap;
    protected ConcurrentHashMap<String, String> mValueMap = new ConcurrentHashMap<>();
    protected V mBinding;
    protected ArrayMap<String, Integer> mThemeColorMap;
    private boolean isFirstInit;
//    protected FBaseActivity mContext;
    protected MainActivity mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mContext = (MainActivity) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        if (mBinding == null) {
            mView = inflater.inflate(setFragmentLayoutRes(), null, false);
            mBinding = DataBindingUtil.inflate(inflater, setFragmentLayoutRes(), container, false);
            mPresenter = getPresenter();
            isPrepare = true;
            isVisible = true;
            isFirstInit = true;
            onVisible();
        }
        return mBinding.getRoot();
    }

    /**
     * 在这里实现Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.onRequestResult(requestCode, permissions, grantResults);
    }

    /**
     * 懒加载，触发调用数据封装层
     */
    private void onVisible() {
        if (!isPrepare || !isVisible) {
            return;
        }

        if (isFirstInit) {
            initValueFromPrePage();
            initThemeAttrs();
            initView(savedInstanceState);
            isFirstInit = false;
        } else {
            onRepeatVisible();
        }
    }

    /**
     * 重复可见调用方法
     */
    private void onRepeatVisible() {

    }

    /**
     * 不可见时
     */
    private void onInvisible() {

    }

    /**
     * 设置当前Fragment的布局文件资源
     *
     * @return
     */
    protected abstract int setFragmentLayoutRes();

    public abstract void initView(Bundle savedInstanceState);

    protected abstract T getPresenter();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onUnsubscribe();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取当前主题中的颜色
     */
    protected void initThemeAttrs() {

    }

    /**
     * 添加事件监听处理到 事件处理类
     *
     * @param disposable 上流事件
     */
    protected void addSubscription(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 添加事件监听处理到 事件管理类
     *
     * @param tag
     * @param disposable
     */
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
    protected void onUnsubscribe() {
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
     * @param tags 标识符
     */
    protected void removeDisposableByTag(String... tags) {
        if (disposableMap != null && disposableMap.size() > 0) {
            for (String tag : tags) {
                if (disposableMap.containsKey(tag)) {
                    compositeDisposable.remove(disposableMap.get(tag));
                }
            }
        }
    }

    /**
     * 从上个页面取得传递参数的值
     *
     * @param name
     * @return
     */
    public String getValueFromPrePage(String name) {
        return mValueMap.get(name);
    }

    public void initValueFromPrePage() {
        Bundle extBundle = getArguments() != null ? getArguments() : null;
        if (extBundle != null) {
            Iterator<String> it = extBundle.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = extBundle.getString(key);
                mValueMap.put(key, value);
            }
        }
    }
}
