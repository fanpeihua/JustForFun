package fanpeihua.justforfun.nba.newsdetail;

import android.graphics.Bitmap;

import fanpeihua.justforfun.base.fbase.base.IFBaseView;
import fanpeihua.justforfun.nba.bean.NewsDetail;

public class NewsDetailContract {

    public interface INewsDetailView extends IFBaseView {
        void getNewsDetail(NewsDetail newsDetail);

        void savePicDone(Boolean b);
    }

    public interface INewsDetailPresenter {
        void getNewsDetail(String column, String articleId);

        void saveBitmap(Bitmap bitmap);
    }
}
