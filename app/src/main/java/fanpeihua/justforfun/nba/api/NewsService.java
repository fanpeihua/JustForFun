package fanpeihua.justforfun.nba.api;


import fanpeihua.justforfun.nba.bean.NewsIndex;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/news/index")
    Observable<NewsIndex> getNewsIndex(@Query("column") String column);

    @GET("/news/item")
    Observable<ResponseBody> getNewsItem(@Query("column") String column,
                                         @Query("articleIds") String articleIds);

    @GET("getinfo?platform=11001@charge=0&otype=json")
    Observable<ResponseBody> getVideosInfo(@Query("vids") String vids);

    @GET("/news/detail")
    Observable<ResponseBody> getNewsDetail(@Query("column") String column,
                                           @Query("articleId") String articleId);
}
