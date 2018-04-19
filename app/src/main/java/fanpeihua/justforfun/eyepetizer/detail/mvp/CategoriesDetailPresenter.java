package fanpeihua.justforfun.eyepetizer.detail.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.CategoryDetailBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;

/**
 * Created by oneball on 2018/4/13.
 */

public class CategoriesDetailPresenter extends RxPresenter<DetailBaseContract.View> implements
        DetailBaseContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public CategoriesDetailPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


    @Override
    public void loadDetailIndex(String id) {
        view.showProgress();
        mDataManagerModel.setCategoriesId(id);
        addSubscribe(mDataManagerModel.getCategoriesDetailIndexData(id)
                .compose(RxUtil.<CategoryDetailBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<CategoryDetailBean>(view) {
                    @Override
                    public void onNext(CategoryDetailBean categoryDetailBean) {
                        view.refreshCategoriesData(categoryDetailBean.getCategoryInfo());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideProgress();
                    }
                }));
    }
}
