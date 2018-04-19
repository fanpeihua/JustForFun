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
import fanpeihua.justforfun.eyepetizer.fragment.mvp.DailyPresenter;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.Utils;


public class DailyFragment extends CommonBaseFragment<DailyPresenter> implements OnItemVideoClickListener {
    private String mDate;
    private String mNum;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void refreshData(FindBean findBean) {
        super.refreshData(findBean);
        if (isLoadMore) {
            mDate = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
            mNum = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[1].split(
                    "=")[1];
        }
    }

    @Override
    public void showMoreData(FindBean findBean) {
        super.showMoreData(findBean);
        if (isLoadMore) {
            mDate = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
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
        stringHashMap.put(Constants.DATE, mDate);
        stringHashMap.put(Constants.NUM, mNum);
        presenter.loadMoreList(stringHashMap);
    }
}
