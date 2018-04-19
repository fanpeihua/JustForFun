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

public class CategoryPresenter extends RxPresenter<CommonContract.View> implements CommonContract.Presenter {

    private DataManagerModel mDataManagerModel;
    private int position;

    @Inject
    public CategoryPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void loadList() {
        addSubscribe(mDataManagerModel.getCategoryData(position)
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
                })
        );
    }

    @Override
    public void loadMoreList(HashMap<String, String> stringHashMap) {
        addSubscribe(mDataManagerModel.getCategoryMoreData(position, stringHashMap.get(Constants.START),
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
