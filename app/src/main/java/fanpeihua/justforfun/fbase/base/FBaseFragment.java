package fanpeihua.justforfun.fbase.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.ArrayMap;
import android.view.View;

import java.util.concurrent.ConcurrentHashMap;

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
    protected FBaseActivity mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mContext = (FBaseActivity) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
