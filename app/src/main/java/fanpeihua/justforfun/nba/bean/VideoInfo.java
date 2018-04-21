package fanpeihua.justforfun.nba.bean;

import java.util.List;

public class VideoInfo {
    public int dltype;
    public int exem;

    public FlBean fl;
    public int hs;
    public int ls;
    public int preview;
    public String s;


    public SflBean sfl;
    public int tm;


    public VlBean vl;

    public static class FlBean {
        public int cnt;

        public List<FiBean> fi;
    }

    public static class FiBean {
        public int br;
        public String cname;
        public int fs;
        public int id;
        public int lmt;
        public String name;
        public int sb;
        public int sl;
    }

    public static class SflBean {
        public int cnt;
    }

    public static class VlBean {
        public int cnt;


        public List<ViBean> vi;
    }

    public static class ViBean {
        public int br;
        public int ch;


        public ClBean cl;
        public int ct;
        public int drm;
        public int dsb;
        public int fclip;
        public String fmd5;
        public String fn;
        public int fs;
        public int fst;
        public String fvkey;
        public int hevc;
        public int iflag;
        public int level;
        public String lnk;
        public int logo;
        public int share;
        public int sp;
        public int st;
        public String td;
        public String ti;
        public int type;
        public UlBean ul;
        public int vh;
        public String vid;
        public int videotype;
        public int vst;
        public int vw;


        public List<PlBean> pl;
    }

    public static class ClBean {
        public int fc;


        public List<CiBean> ci;
    }

    public static class UlBean {


        public List<UiBean> ui;
    }

    public static class PlBean {
        public int cnt;

        public List<PdBean> pd;
    }

    public static class CiBean {
        public String cd;
        public String cmd5;
        public int cs;
        public int idx;
        public String keyid;
    }

    public static class UiBean {
        public int dt;
        public int dtc;
        public String url;
        public int vt;
    }

    public static class PdBean {
        public int c;
        public int cd;
        public int fmt;
        public String fn;
        public int h;
        public int r;
        public String url;
        public int w;
    }


}
