package fanpeihua.justforfun.me.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;

/**
 * Created by oneball on 2018/3/26.
 */

public interface MeContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
