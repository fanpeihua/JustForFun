package fanpeihua.justforfun.eyepetizer.detail.mvp;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by oneball on 2018/4/13.
 */

public class SharePresenter extends RxPresenter<ShareContract.View> implements
        ShareContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public SharePresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void bottomShow() {
        Flowable.timer(50, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        view.bottomShow();
                    }
                });
    }

    @Override
    public void bottomHide() {
        view.bottomHide();
        Flowable.timer(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        view.onBack();
                    }
                });
    }
}
