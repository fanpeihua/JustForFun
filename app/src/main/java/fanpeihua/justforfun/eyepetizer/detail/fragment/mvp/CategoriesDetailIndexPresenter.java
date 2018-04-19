package fanpeihua.justforfun.eyepetizer.detail.fragment.mvp;

import java.util.HashMap;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;

/**
 * Created by oneball on 2018/4/14.
 */

public class CategoriesDetailIndexPresenter extends RxPresenter<DetailIndexContract.View> implements
        DetailIndexContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public CategoriesDetailIndexPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void loadList(int position) {
        addSubscribe(mDataManagerModel.getCDetailData(position, mDataManagerModel.
                getCategoriesId())
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        if (view != null) {
                            view.refreshData(findBean);
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                }));
    }

    @Override
    public void loadMoreList(int position, HashMap<String, String> stringHashMap) {
        addSubscribe(mDataManagerModel.getCDetailMoreData(position, mDataManagerModel.
                getCategoriesId(), stringHashMap)
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        if (view != null) {
                            view.showMoreData(findBean);
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                }));
    }
}
