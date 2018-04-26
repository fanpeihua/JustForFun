package fanpeihua.justforfun.nba.newslist;


import java.util.List;

import fanpeihua.justforfun.application.Config;
import fanpeihua.justforfun.base.fbase.base.FBasePresenter;
import fanpeihua.justforfun.nba.api.NbaNewsApiFactory;
import fanpeihua.justforfun.nba.bean.NewsIndex;
import fanpeihua.justforfun.nba.bean.NewsItem;
import fanpeihua.justforfun.utils.JsonParser;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class NewsListPresenter extends FBasePresenter<NewsListContract.INewsListView>
        implements NewsListContract.INewsListPresenter {
    public NewsListPresenter(NewsListContract.INewsListView view) {
        super(view);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void getNewsIndex(String column) {
        mView.showViewLoading();
        addSubscription(NbaNewsApiFactory.getNewsIndex(column)
                .subscribe(new Consumer<NewsIndex>() {
                    @Override
                    public void accept(@NonNull NewsIndex newsIndex) throws Exception {
                        mView.getNewsIndex(newsIndex);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.getNewsError(throwable, Config.STATUS.REFRESH);
                    }
                }));
    }

    @Override
    public void getNewsItem(String column, String articleIds, final int status) {
//        mView.showViewLoading();
        addSubscription(NbaNewsApiFactory.getNewsItemJson(column, articleIds)
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        List<NewsItem.DataBean.ItemInfo> dataBeen = JsonParser.parseNewsItem(json);
                        mView.getNewsItem(dataBeen, status);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.getNewsError(throwable, status);
                    }
                }));
    }
}
