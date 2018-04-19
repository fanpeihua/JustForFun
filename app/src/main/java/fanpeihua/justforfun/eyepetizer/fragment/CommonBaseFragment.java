package fanpeihua.justforfun.eyepetizer.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import fanpeihua.justforfun.cutomview.listener.HidingScrollBottomListener;
import fanpeihua.justforfun.main.MainActivity;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.MvpBaseFragment;
import fanpeihua.justforfun.cutomview.BallPulseView;
import fanpeihua.justforfun.eyepetizer.detail.AuthorDetailActivity;
import fanpeihua.justforfun.eyepetizer.detail.WebDetailActivity;
import fanpeihua.justforfun.eyepetizer.fragment.adapter.CommonAdapter;
import fanpeihua.justforfun.eyepetizer.fragment.mvp.CommonContract;
import fanpeihua.justforfun.eyepetizer.listener.OnItemAuthorClickListener;
import fanpeihua.justforfun.eyepetizer.listener.OnItemBannerClickListener;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.model.bean.ScrollYEvent;
import fanpeihua.justforfun.model.bean.event.IntentEvent;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;

/**
 *
 *
 * */
public abstract class CommonBaseFragment<T extends BasePresenter> extends MvpBaseFragment<T>
        implements CommonContract.View, OnItemVideoClickListener, OnItemAuthorClickListener, OnItemBannerClickListener {
    public RecyclerView recyclerView;
    public CommonAdapter commonAdapter;
    public TwinklingRefreshLayout refreshLayout;
    public LinearLayoutManager linearLayoutManager;
    private View mParentView;
    public boolean isInit = true;
    public boolean isLoadMore;

    public abstract void onLoadList();

    public abstract void onLoadMoreList();

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mParentView == null) {
            mParentView = inflater.inflate(R.layout.fragment_find, container, false);
            recyclerView = mParentView.findViewById(R.id.recycler_view);
            refreshLayout = mParentView.findViewById(R.id.refresh_layout);
        }
        return mParentView;
    }

    @Override
    public void init() {
        if (isInit) {
            refreshLayout.startRefresh();
            initRefreshLayout();
            initRecyclerView();
            initListener();
        }
    }

    public void initRefreshLayout() {
        ProgressLayout headerView = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setEnableLoadmore(false);
        BallPulseView loadingView = new BallPulseView(getActivity());
        refreshLayout.setBottomView(loadingView);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                onLoadList();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                onLoadMoreList();
            }
        });

    }

    public void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        commonAdapter = new CommonAdapter(getActivity());
        recyclerView.setAdapter(commonAdapter);
    }

    public void initListener() {
        recyclerView.addOnScrollListener(new HidingScrollBottomListener(getActivity()) {
            @Override
            public void onHide() {
                ((MainActivity) getActivity()).changeRadioBtnState(false);
            }

            @Override
            public void onShow() {
                ((MainActivity) getActivity()).changeRadioBtnState(true);
            }

            @Override
            public void onUpdateDate() {

            }

        });
        commonAdapter.setOnItemVideoClickListener(this);
        commonAdapter.setOnItemAuthorClickListener(this);
        commonAdapter.setOnItemBannerClickListener(this);
    }

    @Override
    public void refreshData(FindBean findBean) {
        isLoadMore = !TextUtils.isEmpty(findBean.getNextPageUrl());
        refreshLayout.setEnableLoadmore(isLoadMore);
        commonAdapter.refreshList(findBean.getItemList());
    }

    @Override
    public void showMoreData(FindBean findBean) {
        isLoadMore = !TextUtils.isEmpty(findBean.getNextPageUrl());
        refreshLayout.setEnableLoadmore(isLoadMore);
        commonAdapter.addItemListBeanXES(findBean.getItemList());
    }


    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh(boolean isRefresh) {
        if (isRefresh) {
            refreshLayout.finishRefreshing();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        hideRefresh(false);
    }

    @Override
    protected void setInject() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mParentView) {
            ((ViewGroup) mParentView.getParent()).removeView(mParentView);
            isInit = false;
        }
    }

    @Subscribe
    public void onEventMainThread(ScrollYEvent scrollYEvent) {
        if (scrollYEvent.getFlag() == FragmentPagerItem.getPosition(getArguments())) {
            linearLayoutManager.scrollToPositionWithOffset(0, 0);
            linearLayoutManager.setStackFromEnd(true);
        }
    }

    @Override
    public void onItemVideoClick(ItemListBean itemListBeanX) {
        EventBus.getDefault().post(new IntentEvent(getActivity(), Constants.TO_VIDEO_CARD_ACTIVITY,
                itemListBeanX));
    }

    @Override
    public void onItemAuthorClick(int id) {
        Intent intent = new Intent(getActivity(), AuthorDetailActivity.class);
        intent.putExtra(Constants.ID, String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void onItemBannerClick(String url) {
        if (url.contains(Constants.EYEPETIZER)) {
            Intent intent = new Intent(getActivity(), WebDetailActivity.class);
            intent.putExtra(Constants.URL, url);
            startActivity(intent);
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            startActivity(intent);
        }

    }
}
