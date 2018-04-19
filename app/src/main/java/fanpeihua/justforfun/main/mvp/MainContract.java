package fanpeihua.justforfun.main.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;

/**
 * Created by oneball on 2018/3/22.
 */

public interface MainContract {
    interface View extends BaseView {
        void switchOneView();

        void switchAllView();

        void switchMeView();

        void switchNBAView();
    }

    interface Presenter extends BasePresenter<View> {
        void switchNavView(int id);
    }
}
