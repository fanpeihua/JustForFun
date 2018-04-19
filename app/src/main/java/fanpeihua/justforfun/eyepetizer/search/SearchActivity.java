package fanpeihua.justforfun.eyepetizer.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.MvpBaseActivity;
import fanpeihua.justforfun.cutomview.BallPulseView;
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.eyepetizer.detail.AuthorDetailActivity;
import fanpeihua.justforfun.eyepetizer.detail.TagsDetailActivity;
import fanpeihua.justforfun.eyepetizer.fragment.adapter.CommonAdapter;
import fanpeihua.justforfun.eyepetizer.listener.OnItemAuthorClickListener;
import fanpeihua.justforfun.eyepetizer.listener.OnItemCategoryClickListener;
import fanpeihua.justforfun.eyepetizer.search.mvp.SearchContract;
import fanpeihua.justforfun.eyepetizer.search.mvp.SearchPresenter;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.model.bean.event.IntentEvent;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.Utils;

public class SearchActivity extends MvpBaseActivity<SearchPresenter> implements SearchContract.
        View, OnItemVideoClickListener, OnItemCategoryClickListener
        , OnItemAuthorClickListener {

    @BindView(R.id.tv_cancel)
    FZTextView tvCancel;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.layout_tag)
    TagContainerLayout tagContainerLayout;
    @BindView(R.id.layout_search)
    RelativeLayout layoutSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.card_view)
    CardView cardView;
    private CommonAdapter mCommonAdapter;
    private String mCurrentSearchText;
    private boolean mIsLoadMore;
    private HashMap<String, String> mStringHashMap;

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void init() {
        mStringHashMap = new HashMap<>();
        presenter.loadTagsList();
        initRefreshLayout();
        initRecyclerView();
        initListener();
    }

    public void initRefreshLayout() {
        ProgressLayout headerView = new ProgressLayout(this);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setEnableLoadmore(false);
        BallPulseView loadingView = new BallPulseView(this);
        refreshLayout.setBottomView(loadingView);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                presenter.loadQueryList(mCurrentSearchText);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                presenter.loadMoreQueryList(mStringHashMap);
            }
        });

    }

    public void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mCommonAdapter = new CommonAdapter(this);
        recyclerView.setAdapter(mCommonAdapter);
        mCommonAdapter.setOnItemVideoClickListener(this);
        mCommonAdapter.setOnItemCategoryClickListener(this);
        mCommonAdapter.setOnItemAuthorClickListener(this);
    }

    private void initListener() {
        tagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                etSearch.setText(text);
                etSearch.setSelection(text.length());

                startSearch(text);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    startSearch(etSearch.getText().toString());
                }

                return false;
            }
        });
    }

    private void startSearch(String text) {
        mCurrentSearchText = text;
        layoutSearch.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
        Utils.setSoftInputActive(SearchActivity.this, etSearch, false);
        refreshLayout.startRefresh();
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
    public void showTags(List<String> stringList) {
        tagContainerLayout.setTags(stringList);
    }

    @Override
    public void refreshData(FindBean findBean) {
        mCommonAdapter.refreshList(findBean.getItemList());
        setStringHashMap(findBean.getNextPageUrl());
    }

    @Override
    protected void onPause() {
        super.onPause();
        etSearch.clearFocus();
        etSearch.setFocusable(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etSearch.setFocusable(true);
        etSearch.setFocusableInTouchMode(true);
    }

    @Override
    public void showMoreDate(FindBean findBean) {
        mCommonAdapter.addItemListBeanXES(findBean.getItemList());
        setStringHashMap(findBean.getNextPageUrl());
    }

    public void setStringHashMap(String url) {
        mIsLoadMore = !TextUtils.isEmpty(url);
        refreshLayout.setEnableLoadmore(mIsLoadMore);
        if (mIsLoadMore) {
            String start = Utils.formatUrl(url).split("&")[0];
            String num = Utils.formatUrl(url).split("&")[1].split("=")[1];
            mStringHashMap.put(Constants.START, start);
            mStringHashMap.put(Constants.NUM, num);
            mStringHashMap.put(Constants.QUERY, mCurrentSearchText);
        }
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @OnClick({R.id.tv_cancel, R.id.card_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.card_view:
                Utils.showSoftInputFromWindow(this, etSearch);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.setSoftInputActive(SearchActivity.this, etSearch, false);
        overridePendingTransition(R.anim.screen_null, R.anim.screen_top_out);
    }


    @Override
    public void onItemVideoClick(ItemListBean itemListBeanX) {
        EventBus.getDefault().post(new IntentEvent(this, Constants.TO_VIDEO_CARD_ACTIVITY,
                itemListBeanX));
    }

    @Override
    public void onCategoryItemClick(ItemListBean itemListBeanX) {
        Intent intent = new Intent(this, TagsDetailActivity.class);
        intent.putExtra(Constants.ID, String.valueOf(itemListBeanX.getData().getId()));
        startActivity(intent);
    }


    @Override
    public void onItemAuthorClick(int id) {
        Intent intent = new Intent(this, AuthorDetailActivity.class);
        intent.putExtra(Constants.ID, String.valueOf(id));
        startActivity(intent);
    }
}
