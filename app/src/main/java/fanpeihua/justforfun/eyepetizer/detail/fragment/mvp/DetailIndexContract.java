package fanpeihua.justforfun.eyepetizer.detail.fragment.mvp;

import java.util.HashMap;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.FindBean;

/**
 * Created by oneball on 2018/4/13.
 */

public interface DetailIndexContract {
    interface View extends BaseView {
        void refreshData(FindBean findBean);

        void showMoreData(FindBean findBean);

        void hideRefresh(boolean isRefresh);
    }

    interface Presenter extends BasePresenter<View> {
        void loadList(int position);

        void loadMoreList(int position, HashMap<String, String> stringHashMap);
    }
}
