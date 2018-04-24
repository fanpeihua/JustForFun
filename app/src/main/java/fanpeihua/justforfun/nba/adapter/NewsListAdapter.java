package fanpeihua.justforfun.nba.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.application.Config;
import fanpeihua.justforfun.nba.bean.NewsItem;
import fanpeihua.justforfun.utils.StringUtils;

public class NewsListAdapter extends BaseQuickAdapter<NewsItem.DataBean.ItemInfo, BaseViewHolder> {
    public NewsListAdapter() {
        super(null);

        setMultiTypeDelegate(new MultiTypeDelegate<NewsItem.DataBean.ItemInfo>() {
            @Override
            protected int getItemType(NewsItem.DataBean.ItemInfo info) {
                if (StringUtils.isNotEmpty(info.getAtype())) {
                    int i = Integer.parseInt(info.getAtype());
                    switch (i) {
                        case 0:
                        case 1:
                            return Config.NBANEWS.ITEM_TYPE_ARTICLE;
                        case 2:
                            return Config.NBANEWS.ITEM_TYPE_VIDEOS;
                        default:
                            return Config.NBANEWS.ITEM_TYPE_ARTICLE;
                    }
                }
                return Config.NBANEWS.ITEM_TYPE_ARTICLE;
            }
        });

        getMultiTypeDelegate().registerItemType(Config.NBANEWS.ITEM_TYPE_ARTICLE, R.layout.item_news_list_article)
                .registerItemType(Config.NBANEWS.ITEM_TYPE_VIDEOS,R.layout.item_news_list_videos);
    }
}
