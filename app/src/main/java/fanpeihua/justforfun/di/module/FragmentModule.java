package fanpeihua.justforfun.di.module;


import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import fanpeihua.justforfun.di.scope.FragmentScope;

/**
 * Created by oneball on 2018/4/1.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity() {
        return fragment.getActivity();
    }
}
