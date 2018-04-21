package fanpeihua.justforfun.nba.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;

public class NbaContract {

    interface View extends BaseView {
        void showRefresh();

        void hideRefresh();

        void refreshData();
    }

    interface Presenter extends BasePresenter<View> {
        void loadNbaList(int page);
    }
}
