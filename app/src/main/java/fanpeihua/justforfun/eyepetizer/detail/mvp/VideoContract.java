package fanpeihua.justforfun.eyepetizer.detail.mvp;

import android.app.Activity;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.DataBean;
import fanpeihua.justforfun.model.bean.FindBean;

/**
 * Created by oneball on 2018/4/13.
 */

public interface VideoContract {
    interface View extends BaseView {
        void showVideoData(DataBean dataBean);

        void refreshData(FindBean findBean);

        void permissionGranted();

        void showHeadUI();

        void hideProgress();
    }

    interface Presenter extends BasePresenter<View> {
        void loadVideoData(String id);

        void loadRecommend(int id);

        void showHeadUI();

        void setIsShowHeadUi(boolean isShowHeadUi);

        void requestPermissions(Activity activity, final String[] permissions);
    }
}


