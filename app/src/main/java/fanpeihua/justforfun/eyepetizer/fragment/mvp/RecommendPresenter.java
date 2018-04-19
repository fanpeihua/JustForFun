package fanpeihua.justforfun.eyepetizer.fragment.mvp;

import java.util.HashMap;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.RxUtil;

/**
 * Created by oneball on 2018/4/13.
 */

public class RecommendPresenter extends RxPresenter<CommonContract.View> implements CommonContract.Presenter {

    private DataManagerModel mDataManagerModel;

    @Inject
    public RecommendPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void loadList() {
        loadData("0");
    }

    @Override
    public void loadMoreList(HashMap<String, String> stringHashMap) {
        loadData(stringHashMap.get(Constants.PAGE));
    }

    private void loadData(final String page) {
        addSubscribe(mDataManagerModel.getRecommendData(page)
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        if (page.equals("0")) {
                            view.refreshData(findBean);
                        } else {
                            view.showMoreData(findBean);
                        }

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideRefresh(page.equals("0"));
                    }
                }));
    }

}
