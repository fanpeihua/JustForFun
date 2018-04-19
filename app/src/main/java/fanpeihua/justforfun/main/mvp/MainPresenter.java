package fanpeihua.justforfun.main.mvp;

import javax.inject.Inject;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.RxPresenter;

/**
 * Created by oneball on 2018/3/22.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    
    @Inject
    public MainPresenter() {

    }

    @Override
    public void switchNavView(int id) {
        switch (id) {
            case R.id.rb_one:
                view.switchOneView();
                break;
            case R.id.rb_all:
                view.switchAllView();
                break;
            case R.id.rb_me:
                view.switchMeView();
                break;

        }
    }
}
