package fanpeihua.justforfun.one.detail.mvp;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import fanpeihua.justforfun.base.RxPresenter;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.bean.CommentBean;
import fanpeihua.justforfun.model.bean.ContentListBean;
import fanpeihua.justforfun.model.bean.MovieDetailBean;
import fanpeihua.justforfun.model.bean.MoviePhotoBean;
import fanpeihua.justforfun.model.bean.MusicDetailBean;
import fanpeihua.justforfun.model.bean.MyHttpResponse;
import fanpeihua.justforfun.model.bean.QuestionDetailBean;
import fanpeihua.justforfun.model.bean.ReadDetailBean;
import fanpeihua.justforfun.model.http.CommonSubscriber;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by oneball on 2018/4/14.
 */

public class ReadDetailPresenter extends RxPresenter<ReadDetailContract.View> implements
        ReadDetailContract.Presenter {
    public static final String TAG = ReadDetailPresenter.class.getSimpleName();
    private DataManagerModel mDataManagerModel;

    @Inject
    public ReadDetailPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void loadDetail(ContentListBean contentListBean) {
        String itemId = contentListBean.getItemId();
        switch (Integer.parseInt(contentListBean.getCategory())) {
            case Constants.CATEGORY_MUSIC:
                loadMusicDetail(itemId);
                break;
            case Constants.CATEGORY_MOVIE:
                loadMovieDetail(itemId);
                break;
            default:
                if (contentListBean.getAnswerer() == null) {
                    loadReadDetail(itemId);
                } else {
                    loadQuestionDetail(itemId);
                }
                break;
        }
        loadReadComment(itemId);
    }

    @Override
    public void loadReadDetail(String itemId) {
        addSubscribe(mDataManagerModel.getReadDetail(itemId)
                .compose(RxUtil.<MyHttpResponse<ReadDetailBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<ReadDetailBean>, Publisher<ReadDetailBean>>() {
                    @Override
                    public Publisher<ReadDetailBean> apply(@NonNull MyHttpResponse<ReadDetailBean>
                                                                   httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                }).subscribeWith(new CommonSubscriber<ReadDetailBean>(view) {
                    @Override
                    public void onNext(ReadDetailBean readDetailBean) {
                        view.showReadData(readDetailBean);
                    }
                }));
    }


    @Override
    public void loadMovieDetail(String itemId) {
        addSubscribe(mDataManagerModel.getMovieDetail(itemId)
                .compose(RxUtil.<MyHttpResponse<MovieDetailBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<MovieDetailBean>, Publisher<MovieDetailBean>>() {
                    @Override
                    public Publisher<MovieDetailBean> apply(@NonNull MyHttpResponse<MovieDetailBean>
                                                                    httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                }).subscribeWith(new CommonSubscriber<MovieDetailBean>(view) {
                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        view.showMovieData(movieDetailBean);
                    }
                }));
    }

    @Override
    public void loadMusicDetail(String itemId) {
        addSubscribe(mDataManagerModel.getMusicDetail(itemId)
                .compose(RxUtil.<MyHttpResponse<MusicDetailBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<MusicDetailBean>, Publisher<MusicDetailBean>>() {
                    @Override
                    public Publisher<MusicDetailBean> apply(@NonNull MyHttpResponse<MusicDetailBean>
                                                                    httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                }).subscribeWith(new CommonSubscriber<MusicDetailBean>(view) {
                    @Override
                    public void onNext(MusicDetailBean musicDetailBean) {
                        view.showMusicData(musicDetailBean);
                    }
                }));
    }

    @Override
    public void loadQuestionDetail(String itemId) {
        addSubscribe(mDataManagerModel.getQuestionDetail(itemId)
                .compose(RxUtil.<MyHttpResponse<QuestionDetailBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<QuestionDetailBean>, Publisher<QuestionDetailBean>>() {
                    @Override
                    public Publisher<QuestionDetailBean> apply(@NonNull MyHttpResponse<QuestionDetailBean>
                                                                       httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                }).subscribeWith(new CommonSubscriber<QuestionDetailBean>(view) {
                    @Override
                    public void onNext(QuestionDetailBean questionDetailBean) {
                        view.showQuestionData(questionDetailBean);
                    }
                }));
    }

    @Override
    public void loadReadComment(String itemId) {
        addSubscribe(mDataManagerModel.geReadCommentDetail(itemId)
                .compose(RxUtil.<MyHttpResponse<CommentBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<CommentBean>, Publisher<CommentBean>>() {
                    @Override
                    public Publisher<CommentBean> apply(@NonNull MyHttpResponse<CommentBean>
                                                                httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                }).subscribeWith(new CommonSubscriber<CommentBean>(view) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        view.showReadCommend(commentBean);
                    }
                }));
    }

    @Override
    public void loadMoviePhoto(String itemId) {
        addSubscribe(mDataManagerModel.getMoviePhoto(itemId)
                .compose(RxUtil.<MyHttpResponse<MoviePhotoBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<MoviePhotoBean>, Publisher<MoviePhotoBean>>() {
                    @Override
                    public Publisher<MoviePhotoBean> apply(@NonNull MyHttpResponse<MoviePhotoBean>
                                                                   httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                }).subscribeWith(new CommonSubscriber<MoviePhotoBean>(view) {
                    @Override
                    public void onNext(MoviePhotoBean moviePhotoBean) {
                        view.showMoviePhotos(moviePhotoBean);
                    }
                }));
    }
}
