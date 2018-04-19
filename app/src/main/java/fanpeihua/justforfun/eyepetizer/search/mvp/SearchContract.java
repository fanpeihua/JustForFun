package fanpeihua.justforfun.eyepetizer.search.mvp;

import java.util.HashMap;
import java.util.List;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.FindBean;

/**
 * Created by oneball on 2018/3/29.
 */

public interface SearchContract {
    interface View extends BaseView {
        void hideRefresh(boolean isRefresh);

        void showTags(List<String> stringList);

        void refreshData(FindBean findBean);

        void showMoreDate(FindBean findBean);
    }

    interface Presenter extends BasePresenter<View> {
        void loadTagsList();

        void loadQueryList(String tag);

        void loadMoreQueryList(HashMap<String, String> stringHashMap);
    }
}
