package fanpeihua.justforfun.eyepetizer.detail.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;

/**
 * Created by oneball on 2018/4/13.
 */

public interface ShareContract {
    interface View extends BaseView {
        void bottomShow();

        void bottomHide();

        void onBack();
    }

    interface Presenter extends BasePresenter<View> {
        void bottomShow();

        void bottomHide();
    }
}
