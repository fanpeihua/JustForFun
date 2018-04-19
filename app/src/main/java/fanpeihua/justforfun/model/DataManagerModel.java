package fanpeihua.justforfun.model;

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
import fanpeihua.justforfun.model.prefs.PreferencesHelper;
import fanpeihua.justforfun.model.http.HttpHelper;
import io.reactivex.Flowable;

/**
 * Created by oneball on 2018/3/22.
 */

public class DataManagerModel implements HttpHelper, PreferencesHelper {
    private HttpHelper mHttpHelper;
    private PreferencesHelper mPreferencesHelper;

    public DataManagerModel(HttpHelper mHttpHelper, PreferencesHelper mPreferencesHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public Flowable<OneIdBean> fetchOneId() {
        return mHttpHelper.fetchOneId();
    }

    @Override
    public Flowable<MyHttpResponse<OneListBean>> getOneList(String id) {
        return mHttpHelper.getOneList(id);
    }

    @Override
    public Flowable<MyHttpResponse<ReadDetailBean>> getReadDetail(String itemId) {
        return mHttpHelper.getReadDetail(itemId);
    }

    @Override
    public Flowable<MyHttpResponse<CommentBean>> geReadCommentDetail(String itemId) {
        return mHttpHelper.geReadCommentDetail(itemId);
    }

    @Override
    public Flowable<MyHttpResponse<MovieDetailBean>> getMovieDetail(String itemId) {
        return mHttpHelper.getMovieDetail(itemId);
    }

    @Override
    public Flowable<MyHttpResponse<MusicDetailBean>> getMusicDetail(String itemId) {
        return mHttpHelper.getMusicDetail(itemId);
    }

    @Override
    public Flowable<MyHttpResponse<MoviePhotoBean>> getMoviePhoto(String itemId) {
        return mHttpHelper.getMoviePhoto(itemId);
    }

    @Override
    public Flowable<MyHttpResponse<QuestionDetailBean>> getQuestionDetail(String itemId) {
        return mHttpHelper.getQuestionDetail(itemId);
    }

    @Override
    public Flowable<VideoBean> getAllVideo() {
        return mHttpHelper.getAllVideo();
    }

    @Override
    public Flowable<VideoBean> getAllVideo(String date, String num, String page) {
        return mHttpHelper.getAllVideo(date, num, page);
    }

    @Override
    public Flowable<FindBean> getFindData() {
        return mHttpHelper.getFindData();
    }

    @Override
    public Flowable<FindBean> getFindMoreData(String start) {
        return mHttpHelper.getFindMoreData(start);
    }

    @Override
    public Flowable<FindBean> getRecommendData(String page) {
        return mHttpHelper.getRecommendData(page);
    }

    @Override
    public Flowable<FindBean> getDetailRecommendData(String id) {
        return mHttpHelper.getDetailRecommendData(id);
    }

    @Override
    public Flowable<FindBean> getDailyData() {
        return mHttpHelper.getDailyData();
    }

    @Override
    public Flowable<FindBean> getDailyMoreData(String date, String num) {
        return mHttpHelper.getDailyMoreData(date, num);
    }

    @Override
    public Flowable<FindBean> getCategoryData(int position) {
        return mHttpHelper.getCategoryData(position);
    }

    @Override
    public Flowable<FindBean> getCategoryMoreData(int position, String start, String num) {
        return mHttpHelper.getCategoryMoreData(position, start, num);
    }

    @Override
    public Flowable<FindBean> getAllCategoriesData() {
        return mHttpHelper.getAllCategoriesData();
    }

    @Override
    public Flowable<CategoryDetailBean> getCategoriesDetailIndexData(String id) {
        return mHttpHelper.getCategoriesDetailIndexData(id);
    }

    @Override
    public Flowable<FindBean> getCDetailData(int position, String id) {
        return mHttpHelper.getCDetailData(position, id);
    }


    @Override
    public Flowable<FindBean> getCDetailMoreData(int position, String id, HashMap<String, String>
            stringHashMap) {
        return mHttpHelper.getCDetailMoreData(position, id, stringHashMap);
    }

    @Override
    public Flowable<DataBean> getVideoDetailData(String id) {
        return mHttpHelper.getVideoDetailData(id);
    }

    @Override
    public Flowable<List<String>> getQueriesHotData() {
        return mHttpHelper.getQueriesHotData();
    }

    @Override
    public Flowable<FindBean> getQueryData(String query) {
        return mHttpHelper.getQueryData(query);
    }

    @Override
    public Flowable<TagsDetailBean> getTagDetailIndexData(String id) {
        return mHttpHelper.getTagDetailIndexData(id);
    }

    @Override
    public Flowable<FindBean> getTagDetailData(int position, String id) {
        return mHttpHelper.getTagDetailData(position, id);
    }

    @Override
    public Flowable<FindBean> getTagDetailMoreData(int position, String id, HashMap<String, String>
            stringHashMap) {
        return mHttpHelper.getTagDetailMoreData(position, id, stringHashMap);
    }

    @Override
    public Flowable<AuthorDetailBean> getAuthorDetailIndexData(String id) {
        return mHttpHelper.getAuthorDetailIndexData(id);
    }

    @Override
    public Flowable<FindBean> getMoreQueryData(String query, String start, String num) {
        return mHttpHelper.getMoreQueryData(query, start, num);
    }

    @Override
    public Flowable<FindBean> getAuthorDetailData(int position, String id) {
        return mHttpHelper.getAuthorDetailData(position, id);
    }

    @Override
    public Flowable<FindBean> getAuthorDetailMoreData(int position, String id, HashMap<String, String> stringHashMap) {
        return mHttpHelper.getAuthorDetailMoreData(position, id, stringHashMap);
    }

    @Override
    public String getCategoriesId() {
        return mPreferencesHelper.getCategoriesId();
    }

    @Override
    public void setCategoriesId(String id) {
        mPreferencesHelper.setCategoriesId(id);
    }

    @Override
    public String getTagsId() {
        return mPreferencesHelper.getTagsId();
    }

    @Override
    public void setTagsId(String id) {
        mPreferencesHelper.setTagsId(id);
    }

    @Override
    public String getAuthorId() {
        return mPreferencesHelper.getAuthorId();
    }

    @Override
    public void setAuthorId(String id) {
        mPreferencesHelper.setAuthorId(id);
    }


}
