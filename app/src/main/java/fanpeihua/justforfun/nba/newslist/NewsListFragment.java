package fanpeihua.justforfun.nba.newslist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.application.Config;
import fanpeihua.justforfun.cutomview.DividerItemDecoration;
import fanpeihua.justforfun.databinding.FragmentNewslistBinding;
import fanpeihua.justforfun.base.fbase.base.BaseFragment;
import fanpeihua.justforfun.nba.adapter.NewsListAdapter;
import fanpeihua.justforfun.nba.bean.NewsIndex;
import fanpeihua.justforfun.nba.bean.NewsItem;
import fanpeihua.justforfun.nba.newsdetail.MyWebActivity;
import fanpeihua.justforfun.nba.newsdetail.NewsDetailActivity;
import fanpeihua.justforfun.video.JCVideoPlayer;

public class NewsListFragment extends BaseFragment<NewsListPresenter, FragmentNewslistBinding>
        implements NewsListContract.INewsListView, BaseQuickAdapter.RequestLoadMoreListener
        , SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener
        , BaseQuickAdapter.OnItemChildClickListener {

    //Fragment在Tab中位置
    private String mTabIndex;
    //Fragment类型
    private String mTabType;
    private List<String> indexList = new ArrayList<>();
    //数据查询起始位置
    private int start = 0;
    //每次加载条目数
    private int pageNum = 10;
    private NewsListAdapter mNewsListAdapter;

    @Override
    protected int setFragmentLayoutRes() {
        return R.layout.fragment_newslist;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTabIndex = getValueFromPrePage(Config.NEWS.TAB_INDEX);
        mTabType = getValueFromPrePage(Config.NEWS.TAB_TYPE);

        initRv();
        initProgressLayout();

        mPresenter.getNewsIndex(mTabType);
    }


    @Override
    protected NewsListPresenter getPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //释放资源
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void getNewsIndex(NewsIndex newsIndex) {
//        mBinding.tvNewsListTest.setText(newsIndex.toString());
        List<NewsIndex.DataBean> data = newsIndex.getData();
        indexList.clear();
        for (NewsIndex.DataBean indexInfo : data) {
            indexList.add(indexInfo.getId());
        }
        start = 0;
        mPresenter.getNewsItem(mTabType, parseIndexs(indexList), Config.STATUS.INIT);
    }

    @Override
    public void getNewsItem(List<NewsItem.DataBean.ItemInfo> data, int status) {
        switch (status) {
            case Config.STATUS.INIT:
            case Config.STATUS.REFRESH:
                mNewsListAdapter.setNewData(data);
                if (mBinding.srlNewsList.isRefreshing()) {
                    mBinding.srlNewsList.setRefreshing(false);
                }
                break;
            case Config.STATUS.LOAD_MORE:
                mNewsListAdapter.addData(data);
                mNewsListAdapter.loadMoreComplete();
                break;
            default:
                break;
        }

        mBinding.progressLayout.showContent(mBinding.srlNewsList);
//        showContent(mBinding.srlNewsList, mBinding.pl);
//        RLog.e("data=" + data.toString());
    }

    @Override
    public void getNewsError(Throwable t, int status) {
        switch (status) {
            case Config.STATUS.INIT:
            case Config.STATUS.REFRESH:
                if (mBinding.srlNewsList.isRefreshing()) {
                    mBinding.srlNewsList.setRefreshing(false);
                }
                break;
            case Config.STATUS.LOAD_MORE:
                mNewsListAdapter.loadMoreFail();
                break;
            default:
                break;
        }
        mBinding.progressLayout.showError(mBinding.srlNewsList);
//        showError(mBinding.srlNewsList, mBinding.pl);
    }

    @Override
    public void showViewLoading() {
        mBinding.progressLayout.showLoading(mBinding.srlNewsList);
//        showLoading(mBinding.srlNewsList, mBinding.pl);
    }

    @Override
    public void showViewError(Throwable t) {


    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getNewsItem(mTabType, parseIndexs(indexList), Config.STATUS.LOAD_MORE);
    }

    @Override
    public void onRefresh() {
        mPresenter.getNewsIndex(mTabType);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        List<NewsItem.DataBean.ItemInfo> data = adapter.getData();
//        ToastUtils.showShort(data.get(position).getNewsId());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        List<NewsItem.DataBean.ItemInfo> data = adapter.getData();
        NewsItem.DataBean.ItemInfo item = data.get(position);
        switch (view.getId()) {
            case R.id.iv_goto_web:
                MyWebActivity.start(getActivity(), getActivity()
                        , item.getUrl(), item.getTitle(), false, true);
                break;
            case R.id.ll_news_item:
                NewsDetailActivity.start(getActivity(), getActivity(), mTabType, item.getNewsId());
                break;
            default:
                break;
        }
    }


    /**
     * 初始化条目列表
     */
    private void initRv() {
        mBinding.rvNewsList.setLayoutManager(new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false));
        mNewsListAdapter = new NewsListAdapter();
        mBinding.rvNewsList.setAdapter(mNewsListAdapter);
        mBinding.rvNewsList.addItemDecoration(
                new DividerItemDecoration(getActivity()
                        , DividerItemDecoration.HORIZONTAL_LIST
                        , 3
                        , mThemeColorMap.get(Config.ATTRS.COLOR_BG_DARK)));
        mNewsListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mNewsListAdapter.isFirstOnly(true);
        mNewsListAdapter.setOnLoadMoreListener(this, mBinding.rvNewsList);
        mNewsListAdapter.setOnItemClickListener(this);
        mNewsListAdapter.setOnItemChildClickListener(this);
        mBinding.srlNewsList.setOnRefreshListener(this);
        mBinding.srlNewsList.setColorSchemeColors(mThemeColorMap.get(Config.ATTRS.COLOR_PRIMARY));
    }

    /**
     * 初始化ProgressLayout
     */
    private void initProgressLayout() {
        mBinding.progressLayout.setColor(mThemeColorMap.get(Config.ATTRS.COLOR_TEXT_LIGHT)
                , mThemeColorMap.get(Config.ATTRS.COLOR_PRIMARY));
        mBinding.progressLayout.setRefreshClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewLoading();
                mPresenter.getNewsIndex(mTabType);
            }
        });
    }


    /**
     * 获取当前位置后10条数据
     *
     * @param indexs 新闻索引集合
     * @return id组合字符串   example:1234,234,5665
     */
    private String parseIndexs(List<String> indexs) {
        int size = indexs.size();
        String articleIds = "";
        for (int i = start, j = 0; i < size && j < pageNum; i++, j++, start++) {
            articleIds += indexs.get(i) + ",";
        }
        if (!TextUtils.isEmpty(articleIds)) {
            articleIds = articleIds.substring(0, articleIds.length() - 1);
        }
        return articleIds;
    }
}
