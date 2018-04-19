package fanpeihua.justforfun.eyepetizer.detail.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.AuthorDetailBean;
import fanpeihua.justforfun.model.bean.CategoryDetailBean;
import fanpeihua.justforfun.model.bean.TagsDetailBean;

/**
 * Created by oneball on 2018/4/13.
 */

public interface DetailBaseContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void refreshTagsData(TagsDetailBean.TagInfoBean tagInfoBean);

        void refreshCategoriesData(CategoryDetailBean.CategoryInfoBean categoryInfoBean);

        void refreshAuthorData(AuthorDetailBean.PgcInfoBean pgcInfoBean);

    }

    interface Presenter extends BasePresenter<View> {
        void loadDetailIndex(String id);
    }
}
