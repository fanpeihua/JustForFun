package fanpeihua.justforfun.eyepetizer.detail.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.eyepetizer.detail.fragment.mvp.TagsDetailIndexPresenter;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.Utils;


public class TagsDetailIndexFragment extends DetailIndexBaseFragment<TagsDetailIndexPresenter> {
    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void loadList() {
        presenter.loadList(position);
    }

    @Override
    public void loadMoreList() {
        presenter.loadMoreList(position, stringHashMap);
    }

    @Override
    public void setStringHashMap(String nextUrl) {
        isLoadMore = !TextUtils.isEmpty(nextUrl);
        if (isLoadMore) {
            String start = Utils.formatUrl(nextUrl).split("&")[0];
            String num = Utils.formatUrl(nextUrl).split("&")[1].split("=")[1];
            stringHashMap.put(Constants.START, start);
            stringHashMap.put(Constants.NUM, num);
        }
    }
}
