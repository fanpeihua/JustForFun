package fanpeihua.justforfun.eyepetizer.detail.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;

/**
 * Created by oneball on 2018/4/13.
 */

public class WebDetailPresenter extends RxPresenter<WebDetailContract.View> implements WebDetailContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public WebDetailPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


}
