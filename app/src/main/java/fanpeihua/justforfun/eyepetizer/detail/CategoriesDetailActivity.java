package fanpeihua.justforfun.eyepetizer.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.eyepetizer.detail.fragment.CategoriesDetailIndexFragment;
import fanpeihua.justforfun.eyepetizer.detail.mvp.CategoriesDetailPresenter;
import fanpeihua.justforfun.eyepetizer.detail.mvp.DetailBaseContract;
import fanpeihua.justforfun.model.bean.CategoryDetailBean;

public class CategoriesDetailActivity extends DetailBaseActivity<CategoriesDetailPresenter>
        implements DetailBaseContract.View {
    public static final String TAG = CategoriesDetailActivity.class.getSimpleName();

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public FragmentPagerItems getPages() {
        String[] tabs = getResources().getStringArray(R.array.categories_tabs);
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of(tabs[0], CategoriesDetailIndexFragment.class));
        pages.add(FragmentPagerItem.of(tabs[1], CategoriesDetailIndexFragment.class));
        pages.add(FragmentPagerItem.of(tabs[2], CategoriesDetailIndexFragment.class));
        pages.add(FragmentPagerItem.of(tabs[3], CategoriesDetailIndexFragment.class));
        return pages;
    }

    @Override
    public void loadCategoriesDetail(String id) {
        presenter.loadDetailIndex(id);
    }

    @Override
    public void refreshCategoriesData(CategoryDetailBean.CategoryInfoBean categoryInfoBean) {
        super.refreshCategoriesData(categoryInfoBean);
        initUi(false, categoryInfoBean.getName(), categoryInfoBean.getHeaderImage(),
                categoryInfoBean.getDescription());
    }
}
