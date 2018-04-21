package fanpeihua.justforfun.nba.bean;

import java.util.List;

public class NewsIndex {
    private int code;
    private String version;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String type;
        private String id;
        private String column;
        private String needUpdate;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getNeedUpdate() {
            return needUpdate;
        }

        public void setNeedUpdate(String needUpdate) {
            this.needUpdate = needUpdate;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "type='" + type + '\'' +
                    ", id='" + id + '\'' +
                    ", column='" + column + '\'' +
                    ", needUpdate='" + needUpdate + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsIndex{" +
                "code=" + code +
                ", version='" + version + '\'' +
                ", data=" + data +
                '}';
    }
}
