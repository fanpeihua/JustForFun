package fanpeihua.justforfun.nba.newslist;

import java.util.List;

import fanpeihua.justforfun.base.fbase.base.IFBaseView;
import fanpeihua.justforfun.nba.bean.NewsIndex;
import fanpeihua.justforfun.nba.bean.NewsItem;

public class NewsListContract {
    public interface INewsListView extends IFBaseView {
        void getNewsIndex(NewsIndex newsIndex);

        void getNewsItem(List<NewsItem.DataBean.ItemInfo> data, int status);

        void getNewsError(Throwable t, int status);
    }

    public interface INewsListPresenter {
        void getNewsIndex(String column);

        void getNewsItem(String column, String articleIds, int status);
    }
}
