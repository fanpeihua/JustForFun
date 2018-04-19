package fanpeihua.justforfun.one.detail.mvp;

import fanpeihua.justforfun.base.BasePresenter;
import fanpeihua.justforfun.base.BaseView;
import fanpeihua.justforfun.model.bean.CommentBean;
import fanpeihua.justforfun.model.bean.ContentListBean;
import fanpeihua.justforfun.model.bean.MovieDetailBean;
import fanpeihua.justforfun.model.bean.MoviePhotoBean;
import fanpeihua.justforfun.model.bean.MusicDetailBean;
import fanpeihua.justforfun.model.bean.QuestionDetailBean;
import fanpeihua.justforfun.model.bean.ReadDetailBean;

/**
 * Created by oneball on 2018/4/14.
 */

public class ReadDetailContract {
    public interface View extends BaseView {
        void showReadData(ReadDetailBean readDetailBean);

        void showMovieData(MovieDetailBean readDetailBean);

        void showMusicData(MusicDetailBean musicDetailBean);

        void showReadCommend(CommentBean commentBean);

        void showQuestionData(QuestionDetailBean questionDetailBean);

        void showMoviePhotos(MoviePhotoBean moviePhotoBean);
    }

    public interface Presenter extends BasePresenter<View> {
        void loadDetail(ContentListBean contentListBean);

        void loadReadDetail(String itemId);

        void loadMovieDetail(String itemId);

        void loadMusicDetail(String itemId);

        void loadQuestionDetail(String itemId);

        void loadReadComment(String itemId);

        void loadMoviePhoto(String itemId);
    }
}
