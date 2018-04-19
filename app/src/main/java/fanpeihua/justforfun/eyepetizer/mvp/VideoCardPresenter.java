package fanpeihua.justforfun.eyepetizer.mvp;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.DataBean;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by oneball on 2018/3/22.
 */

public class VideoCardPresenter extends RxPresenter<VideoCardContract.View> implements
        VideoCardContract.Presenter {
    private DataManagerModel mDataManagerModel;
    private boolean mIsShowHeadUi = true;

    @Inject
    public VideoCardPresenter(DataManagerModel mDataManagerModer) {
        this.mDataManagerModel = mDataManagerModer;
    }

    @Override
    public void loadVideoData(String id) {
        addSubscribe(mDataManagerModel.getVideoDetailData(id)
                .compose(RxUtil.<DataBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<DataBean>(view) {

                    @Override
                    public void onNext(DataBean bean) {
                        view.showVideoData(bean);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                }));
    }

    @Override
    public void loadRecommend(int id) {
        addSubscribe(mDataManagerModel.getDetailRecommendData(String.valueOf(id))
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        view.refreshData(findBean);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                })

        );
    }

    @Override
    public void showHeadUI() {
        addSubscribe(Flowable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (mIsShowHeadUi) {
                            view.showHeadUI();
                        }
                    }
                })
        );
    }

    @Override
    public void requestPermissions(Activity activity, String[] permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            view.permissionGranted();
                        }
                    }
                });
    }

    @Override
    public void setIsShowHeadUi(boolean isShowHeadUi) {
        this.mIsShowHeadUi = mIsShowHeadUi;
    }
}
