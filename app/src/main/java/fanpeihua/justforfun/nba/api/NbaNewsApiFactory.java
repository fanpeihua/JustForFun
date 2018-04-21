package fanpeihua.justforfun.nba.api;


import fanpeihua.justforfun.application.Config;
import fanpeihua.justforfun.nba.bean.NewsIndex;
import fanpeihua.justforfun.utils.RxSchedulers;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class NbaNewsApiFactory {
    public static Observable<NewsIndex> getNewsIndex(String column) {
        return ApiClient.get(Config.BaseURL.TECENT_SERVER)
                .create(NewsService.class)
                .getNewsIndex(column)
                .compose(RxSchedulers.<NewsIndex>ioMain());
    }

    public static Observable<ResponseBody> getNewsItemJson(String column, String articleIds) {
        return ApiClient.get(Config.BaseURL.TECENT_SERVER)
                .create(NewsService.class)
                .getNewsItem(column, articleIds)
                .compose(RxSchedulers.<ResponseBody>ioMain());
    }

    public static Observable<ResponseBody> getVideoInfo(String vids) {
        return ApiClient.get(Config.BaseURL.TECENT_VIDEO_SERVER_H5)
                .create(NewsService.class)
                .getVideosInfo(vids)
                .compose(RxSchedulers.<ResponseBody>ioMain());
    }

    public static Observable<ResponseBody> getNewsDetail(String column, String articleId) {
        return ApiClient.get(Config.BaseURL.TECENT_SERVER)
                .create(NewsService.class)
                .getNewsDetail(column, articleId)
                .compose(RxSchedulers.<ResponseBody>ioMain());
    }
}
