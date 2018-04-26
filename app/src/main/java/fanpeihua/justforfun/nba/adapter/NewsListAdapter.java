package fanpeihua.justforfun.nba.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.application.Config;
import fanpeihua.justforfun.application.GlideApp;
import fanpeihua.justforfun.nba.api.NbaNewsApiFactory;
import fanpeihua.justforfun.nba.bean.NewsItem;
import fanpeihua.justforfun.nba.bean.VideoInfo;
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.JsonParser;
import fanpeihua.justforfun.utils.StringUtils;
import fanpeihua.justforfun.video.JCVideoPlayer;
import fanpeihua.justforfun.video.JCVideoPlayerStandard;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class NewsListAdapter extends BaseQuickAdapter<NewsItem.DataBean.ItemInfo, BaseViewHolder> {
    public NewsListAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<NewsItem.DataBean.ItemInfo>() {
            @Override
            protected int getItemType(NewsItem.DataBean.ItemInfo itemInfo) {
                if (StringUtils.isNotEmpty(itemInfo.getAtype())) {
                    int i = Integer.parseInt(itemInfo.getAtype());
                    switch (i) {
                        case 0:
                        case 1:
                            return Config.NEWS.ITEM_TYPE_ARTICLE;
                        case 2:
                            return Config.NEWS.ITEM_TYPE_VIDEOS;
                        default:
                            return Config.NEWS.ITEM_TYPE_ARTICLE;
                    }
                } else {
                    return Config.NEWS.ITEM_TYPE_ARTICLE;
                }
            }
        });
        getMultiTypeDelegate()
                .registerItemType(Config.NEWS.ITEM_TYPE_ARTICLE, R.layout.item_news_list_article)
                .registerItemType(Config.NEWS.ITEM_TYPE_VIDEOS, R.layout.item_news_list_videos);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final NewsItem.DataBean.ItemInfo item) {
        switch (helper.getItemViewType()) {
            case Config.NEWS.ITEM_TYPE_ARTICLE:
                helper.setText(R.id.tv_news_item_title, item.getTitle())
                        .setText(R.id.tv_news_item_date, item.getPub_time_detail());

                ImageLoader.displayImage(mContext, item.getImgurl2(), ((ImageView) helper.getView(R.id.iv_news_item_cover)));

                helper.addOnClickListener(R.id.ll_news_item);
                break;
            case Config.NEWS.ITEM_TYPE_VIDEOS:
                helper.setText(R.id.tv_video_title, item.getTitle())
                        .setText(R.id.tv_video_date, item.getPub_time());
                helper.addOnClickListener(R.id.iv_goto_web);
                final JCVideoPlayerStandard newsPlayer = (JCVideoPlayerStandard) helper.getView(R.id.jc_news_list_player);

//                if (StringUtils.isEmpty(item.getVideoUrl())) {
                NbaNewsApiFactory.getVideoInfo(item.getVid())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                                VideoInfo videoInfo = JsonParser.parseVideoInfo(responseBody.string());

                                if (videoInfo.vl.vi != null && videoInfo.vl.vi.size() > 0) {
                                    String vid = videoInfo.vl.vi.get(0).vid;
                                    String vkey = videoInfo.vl.vi.get(0).fvkey;
                                    String url = videoInfo.vl.vi.get(0).ul.ui.get(0).url + vid + ".mp4?vkey=" + vkey;

                                    item.setVideoUrl(url);
                                    newsPlayer.setUp(item.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST, item.getTitle());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                newsPlayer.setUp(item.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST, item.getTitle());
                            }
                        });
//                }

                newsPlayer.setPlayerClick(new JCVideoPlayerStandard.playerClickListenr() {
                    @Override
                    public void onClick(View v) {
                        helper.setVisible(R.id.tv_video_date, false)
                                .setVisible(R.id.iv_goto_web, false);
                    }
                });

                ImageLoader.displayImage(mContext, item.getImgurl(), newsPlayer.thumbImageView);

                break;

            default:
                break;
        }
    }
}
