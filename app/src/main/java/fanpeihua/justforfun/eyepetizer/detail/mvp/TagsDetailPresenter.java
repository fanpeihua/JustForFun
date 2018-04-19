package fanpeihua.justforfun.eyepetizer.detail.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.TagsDetailBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.RxUtil;

/**
 * Created by oneball on 2018/4/13.
 */

public class TagsDetailPresenter extends RxPresenter<DetailBaseContract.View> implements
        DetailBaseContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public TagsDetailPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


    @Override
    public void loadDetailIndex(String id) {
        view.showProgress();
        mDataManagerModel.setTagsId(id);
        addSubscribe(mDataManagerModel.getTagDetailIndexData(id)
                .compose(RxUtil.<TagsDetailBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<TagsDetailBean>(view) {
                    @Override
                    public void onNext(TagsDetailBean tagsDetailBean) {
                        view.refreshTagsData(tagsDetailBean.getTagInfo());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideProgress();
                    }
                }));
    }
}
