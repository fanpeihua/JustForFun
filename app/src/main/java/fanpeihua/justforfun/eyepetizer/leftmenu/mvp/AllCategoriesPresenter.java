package fanpeihua.justforfun.eyepetizer.leftmenu.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;

/**
 * Created by oneball on 2018/4/13.
 */

public class AllCategoriesPresenter extends RxPresenter<AllCategoriesContract.View> implements
        AllCategoriesContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public AllCategoriesPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


    @Override
    public void loadCategories() {
        addSubscribe(mDataManagerModel.getAllCategoriesData()
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
                }));
    }
}
