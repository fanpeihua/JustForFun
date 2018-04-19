package fanpeihua.justforfun.eyepetizer.search.mvp;

import java.util.HashMap;
import java.util.List;

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

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.
        Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public SearchPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


    @Override
    public void loadTagsList() {
        addSubscribe(mDataManagerModel.getQueriesHotData()
                .compose(RxUtil.<List<String>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<String>>(view) {
                    @Override
                    public void onNext(List<String> stringList) {
                        view.showTags(stringList);

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                }));
    }

    @Override
    public void loadQueryList(String tag) {
        addSubscribe(mDataManagerModel.getQueryData(tag)
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
    public void loadMoreQueryList(HashMap<String, String> stringHashMap) {
        addSubscribe(mDataManagerModel.getMoreQueryData(stringHashMap.get(Constants.QUERY),
                stringHashMap.get(Constants.START), stringHashMap.get(Constants.NUM))
                .compose(RxUtil.<FindBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FindBean>(view) {
                    @Override
                    public void onNext(FindBean findBean) {
                        view.showMoreDate(findBean);

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideRefresh(false);
                    }
                }));
    }
}