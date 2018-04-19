package fanpeihua.justforfun.model.http.response;

/**
 * Created by oneball on 2018/4/13.
 */

public class MyHttpResponse<T> {
    private int res;
    private T data;

    public MyHttpResponse(int res, T data) {
        this.res = res;
        this.data = data;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
