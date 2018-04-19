package fanpeihua.justforfun.base;

/**
 * Created by oneball on 2018/3/22.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
