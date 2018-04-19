package fanpeihua.justforfun.one.mvp;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.MyHttpResponse;
import fanpeihua.justforfun.model.bean.OneIdBean;
import fanpeihua.justforfun.model.bean.OneListBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by oneball on 2018/3/27.
 */

public class OnePresenter extends RxPresenter<OneContract.View> implements OneContract.Presenter {
    public static final String TAG = OnePresenter.class.getSimpleName();
    private DataManagerModel mDataManagerModel;

    @Inject
    public OnePresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


    @Override
    public void loadOneList(final int page) {
        view.showRefresh();
        addSubscribe(mDataManagerModel.fetchOneId()
                .compose(RxUtil.<OneIdBean>rxSchedulerHelper())
                .flatMap(new Function<OneIdBean, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull OneIdBean oneIdBean) throws Exception {
                        List<String> strings = oneIdBean.getData();
                        return Flowable.just(strings.get(page));
                    }
                }).subscribeWith(new CommonSubscriber<String>(view) {
                    @Override
                    public void onNext(String id) {
                        getOneListById(id);
                    }
                }));
    }


    private void getOneListById(final String id) {
        addSubscribe(mDataManagerModel.getOneList(id)
                .compose(RxUtil.<MyHttpResponse<OneListBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<OneListBean>, Publisher<OneListBean>>() {
                    @Override
                    public Publisher<OneListBean> apply(@NonNull MyHttpResponse<OneListBean> listBean)
                            throws Exception {
                        return Flowable.just(listBean.getData());
                    }
                }).subscribeWith(new CommonSubscriber<OneListBean>(view) {
                    @Override
                    public void onNext(OneListBean oneListBean) {
                        view.refreshData(oneListBean);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideRefresh();
                    }
                }));
    }
}
