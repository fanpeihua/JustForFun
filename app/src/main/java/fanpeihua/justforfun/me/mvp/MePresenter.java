package fanpeihua.justforfun.me.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;

/**
 * Created by oneball on 2018/3/26.
 */

public class MePresenter extends RxPresenter<MeContract.View> implements MeContract.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public MePresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }
}
