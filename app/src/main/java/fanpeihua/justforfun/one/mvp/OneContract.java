package fanpeihua.justforfun.one.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.OneListBean;

/**
 * Created by oneball on 2018/3/27.
 */

public interface OneContract {
    interface View extends BaseView {
        void showRefresh();

        void hideRefresh();

        void refreshData(OneListBean oneListBean);
    }

    interface Presenter extends BasePresenter<View> {
        void loadOneList(int page);
    }
}
