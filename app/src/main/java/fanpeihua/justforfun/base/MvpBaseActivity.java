package fanpeihua.justforfun.base;

import javax.inject.Inject;

import fanpeihua.justforfun.application.MyApplication;
import fanpeihua.justforfun.di.component.ActivityComponent;
import fanpeihua.justforfun.di.component.DaggerActivityComponent;
import fanpeihua.justforfun.di.module.ActivityModule;

public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseActivity
        implements BaseView {

    @Inject
    public T presenter;

    public abstract void setInject();

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getmAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        setInject();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

}
