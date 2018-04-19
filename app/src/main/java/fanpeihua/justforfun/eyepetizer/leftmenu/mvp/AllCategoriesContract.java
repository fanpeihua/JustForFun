package fanpeihua.justforfun.eyepetizer.leftmenu.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.FindBean;

/**
 * Created by oneball on 2018/3/27.
 */

public interface AllCategoriesContract {
    interface View extends BaseView {
        void refreshData(FindBean findBean);
    }

    interface Presenter extends BasePresenter<View> {
        void loadCategories();
    }
}
