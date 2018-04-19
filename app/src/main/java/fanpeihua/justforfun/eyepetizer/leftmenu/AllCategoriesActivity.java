package fanpeihua.justforfun.eyepetizer.leftmenu;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import butterknife.BindView;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.MvpBaseActivity;
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.eyepetizer.detail.CategoriesDetailActivity;
import fanpeihua.justforfun.eyepetizer.fragment.adapter.BriefAdapter;
import fanpeihua.justforfun.eyepetizer.leftmenu.mvp.AllCategoriesContract;
import fanpeihua.justforfun.eyepetizer.leftmenu.mvp.AllCategoriesPresenter;
import fanpeihua.justforfun.eyepetizer.listener.OnItemCategoryClickListener;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.utils.Constants;

public class AllCategoriesActivity extends MvpBaseActivity<AllCategoriesPresenter> implements
        AllCategoriesContract.View, OnItemCategoryClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_bold_title)
    FZTextView tvBoldTitle;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private BriefAdapter mBriefAdapter;

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_all_categories;
    }

    @Override
    public void init() {
        isBack = true;
        initToolbar();
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        tvBoldTitle.setVisibility(View.VISIBLE);
        tvBoldTitle.setText(R.string.all_categories);

        refreshLayout.startRefresh();
        initRefreshLayout();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBriefAdapter = new BriefAdapter(this);
        recyclerView.setAdapter(mBriefAdapter);
        mBriefAdapter.setOnItemClickListener(this);
    }

    public void initRefreshLayout() {
        ProgressLayout headerView = new ProgressLayout(this);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setEnableLoadmore(false);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                presenter.loadCategories();
            }

        });

    }

    @Override
    public void refreshData(FindBean findBean) {
        refreshLayout.finishRefreshing();
        refreshLayout.setEnableRefresh(false);
        mBriefAdapter.setAllCategoriesData(findBean.getItemList());
    }

    @Override
    public void showErrorMsg(String msg) {

    }


    @Override
    public void onCategoryItemClick(ItemListBean itemListBeanX) {
        Intent intent = new Intent(this, CategoriesDetailActivity.class);
        intent.putExtra(Constants.ID, String.valueOf(itemListBeanX.getData().getId()));
        startActivity(intent);
    }
}
