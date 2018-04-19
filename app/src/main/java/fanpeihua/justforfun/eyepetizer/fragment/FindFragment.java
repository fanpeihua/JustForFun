package fanpeihua.justforfun.eyepetizer.fragment;

import android.content.Intent;

import java.util.HashMap;

import fanpeihua.justforfun.eyepetizer.detail.CategoriesDetailActivity;
import fanpeihua.justforfun.eyepetizer.fragment.mvp.FindPresenter;
import fanpeihua.justforfun.eyepetizer.listener.OnItemCategoryClickListener;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.Utils;

/**
 *
 */
public class FindFragment extends CommonBaseFragment<FindPresenter> implements
        OnItemVideoClickListener, OnItemCategoryClickListener {
    private String mStart;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initListener() {
        super.initListener();
        commonAdapter.setOnItemCategoryClickListener(this);

    }

    @Override
    public void onLoadList() {
        presenter.loadList();
    }

    @Override
    public void onLoadMoreList() {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put(Constants.START, mStart);
        presenter.loadMoreList(stringHashMap);
    }

    @Override
    public void refreshData(FindBean findBean) {
        super.refreshData(findBean);
        if (isLoadMore) {
            mStart = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
        }
    }

    @Override
    public void showMoreData(FindBean findBean) {
        super.showMoreData(findBean);
        if (isLoadMore) {
            mStart = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
        }
    }

    @Override
    public void onCategoryItemClick(ItemListBean itemListBeanX) {
        Intent intent = new Intent(getActivity(), CategoriesDetailActivity.class);
        intent.putExtra(Constants.ID, String.valueOf(itemListBeanX.getData().getId()));
        startActivity(intent);
    }


}
