package fanpeihua.justforfun.eyepetizer.detail.fragment;

import android.text.TextUtils;

import fanpeihua.justforfun.eyepetizer.detail.fragment.mvp.AuthorDetailIndexPresenter;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.Utils;


public class AuthorDetailIndexFragment extends DetailIndexBaseFragment<AuthorDetailIndexPresenter> {

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
