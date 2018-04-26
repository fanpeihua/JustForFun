package fanpeihua.justforfun.nba.mvp;

import fanpeihua.justforfun.base.fbase.base.FBasePresenter;

public class NbaPresenter extends FBasePresenter<NbaContract.INewsView>
        implements NbaContract.INewsPresenter {
    public NbaPresenter(NbaContract.INewsView view) {
        super(view);
    }
}
