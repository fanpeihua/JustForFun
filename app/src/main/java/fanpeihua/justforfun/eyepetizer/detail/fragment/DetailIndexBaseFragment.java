package fanpeihua.justforfun.eyepetizer.detail.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.plus.PlusOneButton;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.MvpBaseFragment;
import fanpeihua.justforfun.eyepetizer.detail.AuthorDetailActivity;
import fanpeihua.justforfun.eyepetizer.detail.fragment.mvp.DetailIndexContract;
import fanpeihua.justforfun.eyepetizer.fragment.adapter.CommonAdapter;
import fanpeihua.justforfun.eyepetizer.listener.OnItemAuthorClickListener;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.model.bean.event.IntentEvent;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;


public abstract class DetailIndexBaseFragment<T extends BasePresenter> extends MvpBaseFragment<T>
        implements OnItemVideoClickListener, DetailIndexContract.View, OnItemAuthorClickListener {
    public RecyclerView recyclerView;
    private View mParentView;
    public boolean mIsInit = true;
    private LinearLayoutManager mLinearLayoutManager;
    private CommonAdapter mCommonAdapter;
    public boolean isLoadMore;
    public HashMap<String, String> stringHashMap;
    public int position;

    public abstract void loadList();

    public abstract void loadMoreList();

    public abstract void setStringHashMap(String nextUrl);

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mParentView == null) {
            mParentView = inflater.inflate(R.layout.recycler_view, container, false);
            recyclerView = mParentView.findViewById(R.id.recycler_view);
        }
        return mParentView;
    }

    @Override
    public void init() {
        if (mIsInit) {
            initRecyclerView();
            initListener();
            stringHashMap = new HashMap<>();
            position = FragmentPagerItem.getPosition(getArguments());
            loadList();
        }
    }

    public void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mCommonAdapter = new CommonAdapter(getActivity());
        recyclerView.setAdapter(mCommonAdapter);
    }

    private void initListener() {
        mCommonAdapter.setOnItemVideoClickListener(this);
        mCommonAdapter.setOnItemAuthorClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = mLinearLayoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= mLinearLayoutManager.getItemCount() - 2) {
                        if (isLoadMore) {
                            loadMoreList();
                        } else {
//                            showSnackBar(R.string.the_end);
                        }
                    }
                }
            }
        });
    }


    @Override
    public void refreshData(FindBean findBean) {
        mCommonAdapter.refreshList(findBean.getItemList());
        setStringHashMap(findBean.getNextPageUrl());
    }

    @Override
    public void showMoreData(FindBean findBean) {
        mCommonAdapter.addItemListBeanXES(findBean.getItemList());
        setStringHashMap(findBean.getNextPageUrl());
    }


    @Override
    public void hideRefresh(boolean isRefresh) {

    }


    @Override
    public void showErrorMsg(String msg) {
        showSnackBar(msg);
    }

    @Override
    protected void setInject() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mParentView) {
            ((ViewGroup) mParentView.getParent()).removeView(mParentView);
            mIsInit = false;
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
}
