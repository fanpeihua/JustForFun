package fanpeihua.justforfun.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fanpeihua.justforfun.application.MyApplication;
import fanpeihua.justforfun.model.DataManagerModel;
import fanpeihua.justforfun.model.prefs.PreferencesHelper;
import fanpeihua.justforfun.model.http.HttpHelper;
import fanpeihua.justforfun.model.http.HttpHelperImpl;
import fanpeihua.justforfun.model.prefs.PreferenceHelperImpl;

/**
 * Created by oneball on 2018/3/22.
 */
@Module
public class AppModule {
    private MyApplication mApplication;

    public AppModule(MyApplication myApplication) {
        this.mApplication = myApplication;
    }

    @Provides
    @Singleton
    MyApplication provideMyApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelper) {
        return httpHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferenceHelperImpl preferencesHelper) {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    DataManagerModel provideDataManagerModel(HttpHelperImpl httpHelper,
                                             PreferenceHelperImpl preferencesHelper) {
        return new DataManagerModel(httpHelper, preferencesHelper);
    }


}
