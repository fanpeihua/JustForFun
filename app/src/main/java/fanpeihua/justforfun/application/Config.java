package fanpeihua.justforfun.application;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import fanpeihua.justforfun.base.fbase.utils.base.FileUtils;

public class Config {

    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String NULL = "";

    @Retention(RetentionPolicy.SOURCE)
    public @interface BaseURL {

        String HUPU_FORUM_SERVER = "http://bbs.mobileapi.hupu.com/1/7.0.8/";
        String HUPU_GAMES_SERVER = "http://games.mobileapi..hupucom/1/7.0.8/";
        String HUPU_LOGIN_SERVER = "http://passport.hupu.com/";
        String TECENT_VIDEO_SERVER = "http://vv.video.qq.com";
        String TECENT_VIDEO_SERVER_H5 = "http://h5vv.video.qq.com";
        String TECENT_SERVER = "http://sportsnba.qq.com";
    }

    public interface DIR {
        String PIC_DIR = FileUtils.getRootFilePath() + "JustForFun/images";
        String CRASH = FileUtils.getRootFilePath() + "JustForFun/crashLog";
        String SONIC = FileUtils.getRootFilePath() + "JustForFun/sonic";
        String FILE = FileUtils.getRootFilePath() + "JustForFun/file";
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

    /**
     * 主题属性常量保存类
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ATTRS {
        String COLOR_PRIMARY = "COLOR_PRIMARY";
        String COLOR_PRIMARY_DARK = "COLOR_PRIMARY_DARK";
        String COLOR_ACCENT = "COLOR_ACCENT";
        String COLOR_TEXT_LIGHT = "COLOR_TEXT_LIGHT";
        String COLOR_TEXT_DARK = "COLOR_TEXT_DARK";
        String COLOR_BG = "COLOR_BG";
        String COLOR_BG_DARK = "COLOR_BG_DARK";
    }

    /**
     * 新闻模块常量
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface NEWS {
        String BANNER = "banner";
        String NEWS = "news";
        String VIDEOS = "videos";
        String DEPTH = "depth";
        String HIGHLIGHT = "highlight";

        String TAB_INDEX = "TAB_INDEX";
        String TAB_TYPE = "TAB_TYPE";

        int ITEM_TYPE_ARTICLE = 0;
        int ITEM_TYPE_VIDEOS = 2;
    }

    /**
     * SharePreferences常量保存类
     */
    public interface SP {
        String THEME = "THEME";
        String IS_LOGIN = "IS_LOGIN";
        String TAG_MINE_SELECTED = "TAG_MINE_SELECTED";
        String CURRENT_USER = "CURRENT_USER";
        String HUPU_TOKEN = "TOKEN";
        String HUPU_UID = "uid";
        String HUPU_NICKNAME = "HUPU_NICKNAME";

    }

    /**
     * 列表数据状态
     */
    public interface STATUS {
        int INIT = 1001;
        int REFRESH = 1002;
        int LOAD_MORE = 1003;
        int NULL = 1000;
    }


    public interface Utils {
        //广告过滤字符集
        String[] AD = {"/d/js/js/"
                , "u.xcy8.com"
                , "http://nba.tmiaoo.com/body.html"
                , "http://nba.tmiaoo.com/gg.html"
                , "http://img.ychtjd88.com"
                , "http://hm.baidu.com"
                , "http://img.jgchq.com"
                , "http://img1.pxpbj.com"
                , "http://img1.pxpbj.com"};

    }
}
