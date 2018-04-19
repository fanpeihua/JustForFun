package fanpeihua.justforfun.model.http.apis;

import fanpeihua.justforfun.model.bean.CommentBean;
import fanpeihua.justforfun.model.bean.MovieDetailBean;
import fanpeihua.justforfun.model.bean.MoviePhotoBean;
import fanpeihua.justforfun.model.bean.MusicDetailBean;
import fanpeihua.justforfun.model.bean.MyHttpResponse;
import fanpeihua.justforfun.model.bean.OneIdBean;
import fanpeihua.justforfun.model.bean.OneListBean;
import fanpeihua.justforfun.model.bean.QuestionDetailBean;
import fanpeihua.justforfun.model.bean.ReadDetailBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by oneball on 2018/3/22.
 */

public interface OneApis {
    String HOST = "http://v3.wufazhuce.com:8000/api/";
    String END = "?cchannel=wdj&version=4.3.4&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";

    //获取最新 idlist
    @GET("onelist/idlist/" + END)
    Flowable<OneIdBean> getOneId();

    //获取 onelist
    @GET("onelist/{id}/0" + END)
    Flowable<MyHttpResponse<OneListBean>> getOneList(@Path("id") String id);

    //获取文章详细信息
    @GET("essay/{item_id}" + END)
    Flowable<MyHttpResponse<ReadDetailBean>> getReadDetail(@Path("item_id") String itemId);

    //获取文章的评论信息
    @GET("comment/praiseandtime/essay/{item_id}/0" + END)
    Flowable<MyHttpResponse<CommentBean>> getReadCommendDetail(@Path("item_id") String itemId);

    //获取影视详细信息
    @GET("movie/{item_id}/story/1/0" + END)
    Flowable<MyHttpResponse<MovieDetailBean>> geMovieDetail(@Path("item_id") String itemId);

    //获取音乐详细信息
    @GET("music/detail/{item_id}" + END)
    Flowable<MyHttpResponse<MusicDetailBean>> geMusicDetail(@Path("item_id") String itemId);

    //获取问答的详细信息
    @GET("question/{item_id}" + END)
    Flowable<MyHttpResponse<QuestionDetailBean>> getQuestionDetail(@Path("item_id") String itemId);

    //获取影视的photo
    @GET("movie/detail/{item_id}" + END)
    Flowable<MyHttpResponse<MoviePhotoBean>> geMoviePhoto(@Path("item_id") String itemId);
}