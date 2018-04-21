package fanpeihua.justforfun.nba.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

public class NewsItem {
    private int code;
    private DataBean data;
    private String version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "code=" + code +
                ", data=" + data +
                ", version='" + version + '\'' +
                '}';
    }

    public static class DataBean {

        private ItemInfo mItemInfo;

        public ItemInfo getItemInfo() {
            return mItemInfo;
        }

        public void setItemInfo(ItemInfo itemInfo) {
            this.mItemInfo = itemInfo;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "mItemInfo=" + mItemInfo +
                    '}';
        }

        public static class ItemInfo implements MultiItemEntity {


            private String newsId;
            private String title;
            @SerializedName("abstract")
            private String abstractX;
            private String url;
            private String imgurl;
            private String imgurl1;
            private String imgurl2;
            private String pub_time;
            private String publishTime;
            private String currentTime;
            private String pub_time_new;
            private String pub_time_detail;
            private String atype;
            private String atypeName;
            private String newsAppId;
            private String source;
            private String duration;
            private String hasCopyRight;
            private String vid;
            private String commentsNum;
            private String commentId;
            private String targetId;
            private String isDisabled;
            private String tag_key;
            private String cmsColumn;
            private String cmsSite;
            private String shareUrl;
            private String commentNum;
            private String upNum;
            private String footer;
            private String isCollect;

            private String videoUrl;

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getNewsId() {
                return newsId;
            }

            public void setNewsId(String newsId) {
                this.newsId = newsId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAbstractX() {
                return abstractX;
            }

            public void setAbstractX(String abstractX) {
                this.abstractX = abstractX;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getImgurl1() {
                return imgurl1;
            }

            public void setImgurl1(String imgurl1) {
                this.imgurl1 = imgurl1;
            }

            public String getImgurl2() {
                return imgurl2;
            }

            public void setImgurl2(String imgurl2) {
                this.imgurl2 = imgurl2;
            }

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getCurrentTime() {
                return currentTime;
            }

            public void setCurrentTime(String currentTime) {
                this.currentTime = currentTime;
            }

            public String getPub_time_new() {
                return pub_time_new;
            }

            public void setPub_time_new(String pub_time_new) {
                this.pub_time_new = pub_time_new;
            }

            public String getPub_time_detail() {
                return pub_time_detail;
            }

            public void setPub_time_detail(String pub_time_detail) {
                this.pub_time_detail = pub_time_detail;
            }

            public String getAtype() {
                return atype;
            }

            public void setAtype(String atype) {
                this.atype = atype;
            }

            public String getAtypeName() {
                return atypeName;
            }

            public void setAtypeName(String atypeName) {
                this.atypeName = atypeName;
            }

            public String getNewsAppId() {
                return newsAppId;
            }

            public void setNewsAppId(String newsAppId) {
                this.newsAppId = newsAppId;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getHasCopyRight() {
                return hasCopyRight;
            }

            public void setHasCopyRight(String hasCopyRight) {
                this.hasCopyRight = hasCopyRight;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getCommentsNum() {
                return commentsNum;
            }

            public void setCommentsNum(String commentsNum) {
                this.commentsNum = commentsNum;
            }

            public String getCommentId() {
                return commentId;
            }

            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public String getIsDisabled() {
                return isDisabled;
            }

            public void setIsDisabled(String isDisabled) {
                this.isDisabled = isDisabled;
            }

            public String getTag_key() {
                return tag_key;
            }

            public void setTag_key(String tag_key) {
                this.tag_key = tag_key;
            }

            public String getCmsColumn() {
                return cmsColumn;
            }

            public void setCmsColumn(String cmsColumn) {
                this.cmsColumn = cmsColumn;
            }

            public String getCmsSite() {
                return cmsSite;
            }

            public void setCmsSite(String cmsSite) {
                this.cmsSite = cmsSite;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getUpNum() {
                return upNum;
            }

            public void setUpNum(String upNum) {
                this.upNum = upNum;
            }

            public String getFooter() {
                return footer;
            }

            public void setFooter(String footer) {
                this.footer = footer;
            }

            public String getIsCollect() {
                return isCollect;
            }

            public void setIsCollect(String isCollect) {
                this.isCollect = isCollect;
            }

            @Override
            public String toString() {
                return "ItemInfo{" +
                        "newsId='" + newsId + '\'' +
                        ", title='" + title + '\'' +
                        ", abstractX='" + abstractX + '\'' +
                        ", url='" + url + '\'' +
                        ", imgurl='" + imgurl + '\'' +
                        ", imgurl1='" + imgurl1 + '\'' +
                        ", imgurl2='" + imgurl2 + '\'' +
                        ", pub_time='" + pub_time + '\'' +
                        ", publishTime='" + publishTime + '\'' +
                        ", currentTime='" + currentTime + '\'' +
                        ", pub_time_new='" + pub_time_new + '\'' +
                        ", pub_time_detail='" + pub_time_detail + '\'' +
                        ", atype='" + atype + '\'' +
                        ", atypeName='" + atypeName + '\'' +
                        ", newsAppId='" + newsAppId + '\'' +
                        ", source='" + source + '\'' +
                        ", duration='" + duration + '\'' +
                        ", hasCopyRight='" + hasCopyRight + '\'' +
                        ", vid='" + vid + '\'' +
                        ", commentsNum='" + commentsNum + '\'' +
                        ", commentId='" + commentId + '\'' +
                        ", targetId='" + targetId + '\'' +
                        ", isDisabled='" + isDisabled + '\'' +
                        ", tag_key='" + tag_key + '\'' +
                        ", cmsColumn='" + cmsColumn + '\'' +
                        ", cmsSite='" + cmsSite + '\'' +
                        ", shareUrl='" + shareUrl + '\'' +
                        ", commentNum='" + commentNum + '\'' +
                        ", upNum='" + upNum + '\'' +
                        ", footer='" + footer + '\'' +
                        ", isCollect='" + isCollect + '\'' +
                        '}';
            }

            @Override
            public int getItemType() {
                return Integer.parseInt(atype);
            }
        }
    }
}
