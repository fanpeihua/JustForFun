package fanpeihua.justforfun.di.component;

import javax.inject.Singleton;

import dagger.Component;
import fanpeihua.justforfun.application.MyApplication;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.prefs.PreferencesHelper;
import fanpeihua.justforfun.di.module.AppModule;
import fanpeihua.justforfun.di.module.HttpModule;
import fanpeihua.justforfun.model.http.HttpHelper;

/**
 * Created by oneball on 2018/3/22.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    MyApplication getContext();

    DataManagerModel getDataManagerModel();

    HttpHelper getHttpHelper();

    PreferencesHelper getPreferencesHelper();
}
