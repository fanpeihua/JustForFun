package fanpeihua.justforfun.eyepetizer.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;

/**
 * Created by oneball on 2018/4/1.
 */

public class EyePresenter extends RxPresenter<EyeContact.View> implements EyeContact.Presenter {
    private DataManagerModel mDataManagerModel;

    @Inject
    public EyePresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }


}