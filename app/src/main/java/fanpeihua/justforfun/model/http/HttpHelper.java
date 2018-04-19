package fanpeihua.justforfun.model.http;

import java.util.HashMap;
import java.util.List;

import fanpeihua.justforfun.model.bean.AuthorDetailBean;
import fanpeihua.justforfun.model.bean.CategoryDetailBean;
import fanpeihua.justforfun.model.bean.CommentBean;
import fanpeihua.justforfun.model.bean.DataBean;
import fanpeihua.justforfun.model.bean.FindBean;
import fanpeihua.justforfun.model.bean.MovieDetailBean;
import fanpeihua.justforfun.model.bean.MoviePhotoBean;
import fanpeihua.justforfun.model.bean.MusicDetailBean;
import fanpeihua.justforfun.model.bean.MyHttpResponse;
import fanpeihua.justforfun.model.bean.OneIdBean;
import fanpeihua.justforfun.model.bean.OneListBean;
import fanpeihua.justforfun.model.bean.QuestionDetailBean;
import fanpeihua.justforfun.model.bean.ReadDetailBean;
import fanpeihua.justforfun.model.bean.TagsDetailBean;
import fanpeihua.justforfun.model.bean.VideoBean;
import io.reactivex.Flowable;

/**
 * Created by oneball on 2018/3/22.
 */

public interface HttpHelper {
    Flowable<OneIdBean> fetchOneId();

    Flowable<MyHttpResponse<OneListBean>> getOneList(String id);

    Flowable<MyHttpResponse<ReadDetailBean>> getReadDetail(String detailId);

    Flowable<MyHttpResponse<CommentBean>> geReadCommentDetail(String itemId);

    Flowable<MyHttpResponse<MovieDetailBean>> getMovieDetail(String itemId);

    Flowable<MyHttpResponse<MusicDetailBean>> getMusicDetail(String itemId);

    Flowable<MyHttpResponse<MoviePhotoBean>> getMoviePhoto(String itemId);

    Flowable<MyHttpResponse<QuestionDetailBean>> getQuestionDetail(String itemId);

    Flowable<VideoBean> getAllVideo();

    Flowable<VideoBean> getAllVideo(String date, String num, String page);

    Flowable<FindBean> getFindData();

    Flowable<FindBean> getFindMoreData(String start);

    Flowable<FindBean> getRecommendData(String page);

    Flowable<FindBean> getDetailRecommendData(String id);

    Flowable<FindBean> getDailyData();

    Flowable<FindBean> getDailyMoreData(String date, String num);

    Flowable<FindBean> getCategoryData(int position);

    Flowable<FindBean> getCategoryMoreData(int position, String start, String num);

    Flowable<FindBean> getAllCategoriesData();

    Flowable<CategoryDetailBean> getCategoriesDetailIndexData(String id);

    Flowable<FindBean> getCDetailData(int position, String id);

    Flowable<FindBean> getCDetailMoreData(int position, String id, HashMap<String, String> stringHashMap);

    Flowable<DataBean> getVideoDetailData(String id);

    Flowable<List<String>> getQueriesHotData();

    Flowable<FindBean> getQueryData(String query);

    Flowable<FindBean> getMoreQueryData(String query, String start, String num);


    Flowable<TagsDetailBean> getTagDetailIndexData(String id);

    Flowable<FindBean> getTagDetailData(int position, String id);

    Flowable<FindBean> getTagDetailMoreData(int position, String id, HashMap<String, String> stringHashMap);

    Flowable<AuthorDetailBean> getAuthorDetailIndexData(String id);

    Flowable<FindBean> getAuthorDetailData(int position, String id);

    Flowable<FindBean> getAuthorDetailMoreData(int position, String id, HashMap<String, String> stringHashMap);


}
