package fanpeihua.justforfun.eyepetizer.detail.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.AuthorDetailBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;

/**
 * Created by oneball on 2018/4/13.
 */

public class AuthorDetailPresenter extends RxPresenter<DetailBaseContract.View> implements
        DetailBaseContract.Presenter {

    private DataManagerModel mDataManagerModel;

    @Inject
    public AuthorDetailPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void loadDetailIndex(String id) {
        view.showProgress();
        mDataManagerModel.setAuthorId(id);
        addSubscribe(mDataManagerModel.getAuthorDetailIndexData(id)
                .compose(RxUtil.<AuthorDetailBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<AuthorDetailBean>(view) {
                    @Override
                    public void onNext(AuthorDetailBean authorDetailBean) {
                        view.refreshAuthorData(authorDetailBean.getPgcInfo());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideProgress();
                    }
                })
        );
    }


}
