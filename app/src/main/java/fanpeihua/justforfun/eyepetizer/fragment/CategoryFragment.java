package fanpeihua.justforfun.eyepetizer.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.HashMap;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.eyepetizer.fragment.mvp.CategoryPresenter;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.Utils;


public class CategoryFragment extends CommonBaseFragment<CategoryPresenter> implements
        OnItemVideoClickListener {
    private String mStart;
    private String mNum;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void init() {
        presenter.setPosition(FragmentPagerItem.getPosition(getArguments()));
        super.init();
    }

    @Override
    public void refreshData(FindBean findBean) {
        super.refreshData(findBean);
        if (isLoadMore) {
            mStart = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
            mNum = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[1].split(
                    "=")[1];
        }
    }

    @Override
    public void showMoreData(FindBean findBean) {
        super.showMoreData(findBean);
        if (isLoadMore) {
            mStart = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
            mNum = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[1].split(
                    "=")[1];
        }
    }


    @Override
    public void onLoadList() {
        presenter.loadList();
    }

    @Override
    public void onLoadMoreList() {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put(Constants.START, mStart);
        stringHashMap.put(Constants.NUM, mNum);
        presenter.loadMoreList(stringHashMap);
    }
}
