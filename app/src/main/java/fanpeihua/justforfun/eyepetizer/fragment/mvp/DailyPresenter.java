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

public class DailyPresenter extends RxPresenter<CommonContract.View> implements CommonContract.Presenter {

    private DataManagerModel mDataManagerModel;

    @Inject
    public DailyPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void loadList() {
        addSubscribe(mDataManagerModel.getDailyData()
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        view.refreshData(findBean);

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideRefresh(true);
                    }
                }));
    }

    @Override
    public void loadMoreList(HashMap<String, String> stringHashMap) {
        addSubscribe(mDataManagerModel.getDailyMoreData(stringHashMap.get(Constants.DATE),
                stringHashMap.get(Constants.NUM))
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        view.showMoreData(findBean);

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideRefresh(false);
                    }
                }));
    }
}
