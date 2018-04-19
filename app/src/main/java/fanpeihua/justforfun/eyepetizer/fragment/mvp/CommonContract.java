package fanpeihua.justforfun.eyepetizer.fragment.mvp;

import com.google.android.gms.common.api.internal.BasePendingResult;

import java.util.HashMap;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.FindBean;

/**
 * Created by oneball on 2018/4/13.
 */

public interface CommonContract {

    interface View extends BaseView {
        void showRefresh();

        void hideRefresh(boolean isRefresh);

        void refreshData(FindBean findBean);

        void showMoreData(FindBean findBean);
    }

    interface Presenter extends BasePresenter<View> {
        void loadList();

        void loadMoreList(HashMap<String, String> stringHashMap);
    }
}
