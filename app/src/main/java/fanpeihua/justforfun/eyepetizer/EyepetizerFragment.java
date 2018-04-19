package fanpeihua.justforfun.eyepetizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.MvpBaseFragment;
import fanpeihua.justforfun.eyepetizer.fragment.CategoryFragment;
import fanpeihua.justforfun.eyepetizer.fragment.DailyFragment;
import fanpeihua.justforfun.eyepetizer.fragment.FindFragment;
import fanpeihua.justforfun.eyepetizer.fragment.RecommendFragment;
import fanpeihua.justforfun.eyepetizer.leftmenu.AllCategoriesActivity;
import fanpeihua.justforfun.eyepetizer.leftmenu.mvp.AllCategoriesContract;
import fanpeihua.justforfun.eyepetizer.mvp.EyeContact;
import fanpeihua.justforfun.eyepetizer.mvp.EyePresenter;
import fanpeihua.justforfun.eyepetizer.search.SearchActivity;
import fanpeihua.justforfun.model.bean.ScrollYEvent;

/**
 * Created by oneball on 2018/4/1.
 */

public class EyepetizerFragment extends MvpBaseFragment<EyePresenter> implements EyeContact.View {
    private static final String TAG = EyepetizerFragment.class.getSimpleName();
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_view_pager)
    SmartTabLayout tabViewPager;
    @BindView(R.id.iv_all_category)
    ImageView ivAllCategory;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eye, null);
    }

    @Override
    public void init() {
        initTab();
    }

    private void initTab() {
        String[] tabs = getResources().getStringArray(R.array.tabs);
        FragmentPagerItems pages = new FragmentPagerItems(getActivity());
        pages.add(FragmentPagerItem.of(tabs[0], FindFragment.class));
        pages.add(FragmentPagerItem.of(tabs[1], RecommendFragment.class));
        pages.add(FragmentPagerItem.of(tabs[2], DailyFragment.class));
        for (int i = 3; i < tabs.length; i++) {
            pages.add(FragmentPagerItem.of(tabs[i], CategoryFragment.class));
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        tabViewPager.setViewPager(viewPager);
    }


    @Override
    public void showErrorMsg(String msg) {

    }

    public void scrollToTop() {
        EventBus.getDefault().post(new ScrollYEvent(viewPager.getCurrentItem()));
    }


    @OnClick({R.id.iv_all_category, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_all_category:
                startActivity(new Intent(getActivity(), AllCategoriesActivity.class));
                break;
            case R.id.iv_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.screen_top_in, R.anim.screen_null);
                break;
        }

    }

}
