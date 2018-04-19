package fanpeihua.justforfun.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import fanpeihua.justforfun.application.MyApplication;
import fanpeihua.justforfun.di.component.DaggerFragmentComponent;
import fanpeihua.justforfun.di.component.FragmentComponent;
import fanpeihua.justforfun.di.module.FragmentModule;

/**
 * Created by oneball on 2018/3/27.
 */

public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    public T presenter;

    protected abstract void setInject();

    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(MyApplication.getmAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setInject();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
