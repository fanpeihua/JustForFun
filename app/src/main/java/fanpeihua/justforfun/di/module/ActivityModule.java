package fanpeihua.justforfun.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import fanpeihua.justforfun.di.scope.ActivityScope;

/**
 * Created by oneball on 2018/3/22.
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }
}
