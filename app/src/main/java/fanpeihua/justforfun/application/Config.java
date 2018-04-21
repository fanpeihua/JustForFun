package fanpeihua.justforfun.application;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Config {

    @Retention(RetentionPolicy.SOURCE)
    public @interface BaseURL {

        String HUPU_FORUM_SERVER = "http://bbs.mobileapi.hupu.com/1/7.0.8/";
        String HUPU_GAMES_SERVER = "http://games.mobileapi.hupu.com/1/7.0.8/";
        String HUPU_LOGIN_SERVER = "http://passport.hupu.com/";
        String TECENT_VIDEO_SERVER = "http://vv.video.qq.com";
        String TECENT_VIDEO_SERVER_H5 = "http://h5vv.video.qq.com";
        String TECENT_SERVER = "http://sportsnba.qq.com";
    }


    @Retention(RetentionPolicy.SOURCE)
    public @interface NBANEWS {
        String BANNER = "banner";
        String NEWS = "news";
        String videos = "videos";
        String DEPTH = "depth";
        String HIGHLIGHT = "highlight";

        String TAB_INDEX = "TAB_INDEX";
        String TAB_TYPE = "TAB_TYPE";

        int ITEM_TYPE_ARTICLE = 0;
        int ITEM_TYPE_VIDEOS = 2;
    }
}
