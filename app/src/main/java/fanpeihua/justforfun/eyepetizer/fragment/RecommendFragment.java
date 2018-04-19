package fanpeihua.justforfun.eyepetizer.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.eyepetizer.fragment.mvp.RecommendPresenter;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;


public class RecommendFragment extends CommonBaseFragment<RecommendPresenter> implements
        OnItemVideoClickListener {
    private String mPage;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void onLoadList() {
        presenter.loadList();
    }

    @Override
    public void onLoadMoreList() {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put(Constants.PAGE, mPage);
        presenter.loadMoreList(stringHashMap);
    }

    @Override
    public void refreshData(FindBean findBean) {
        super.refreshData(findBean);
        if (isLoadMore) {
            mPage = findBean.getNextPageUrl().split("=")[1];
        }
    }

    @Override
    public void showMoreData(FindBean findBean) {
        super.showMoreData(findBean);
        if (isLoadMore) {
            mPage = findBean.getNextPageUrl().split("=")[1];
        }
    }


}
