package fanpeihua.justforfun.eyepetizer.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.eyepetizer.detail.fragment.TagsDetailIndexFragment;
import fanpeihua.justforfun.eyepetizer.detail.mvp.DetailBaseContract;
import fanpeihua.justforfun.eyepetizer.detail.mvp.TagsDetailPresenter;
import fanpeihua.justforfun.model.bean.TagsDetailBean;

public class TagsDetailActivity extends DetailBaseActivity<TagsDetailPresenter>
        implements DetailBaseContract.View {
    public static final String TAG = TagsDetailActivity.class.getSimpleName();

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public FragmentPagerItems getPages() {
        String[] tabs = getResources().getStringArray(R.array.tags_tabs);
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of(tabs[0], TagsDetailIndexFragment.class));
        pages.add(FragmentPagerItem.of(tabs[1], TagsDetailIndexFragment.class));
        return pages;
    }

    @Override
    public void loadCategoriesDetail(String id) {
        presenter.loadDetailIndex(id);
    }

    @Override
    public void refreshTagsData(TagsDetailBean.TagInfoBean tagInfoBean) {
        initUi(true, tagInfoBean.getName(), tagInfoBean.getHeaderImage(),
                "");
    }
}
