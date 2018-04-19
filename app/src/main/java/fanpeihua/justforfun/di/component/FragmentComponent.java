package fanpeihua.justforfun.di.component;

import android.app.Activity;

import dagger.Component;
import fanpeihua.justforfun.di.module.FragmentModule;
import fanpeihua.justforfun.di.scope.FragmentScope;
import fanpeihua.justforfun.eyepetizer.EyepetizerFragment;
import fanpeihua.justforfun.eyepetizer.detail.fragment.AuthorDetailIndexFragment;
import fanpeihua.justforfun.eyepetizer.detail.fragment.CategoriesDetailIndexFragment;
import fanpeihua.justforfun.eyepetizer.detail.fragment.TagsDetailIndexFragment;
import fanpeihua.justforfun.eyepetizer.fragment.CategoryFragment;
import fanpeihua.justforfun.eyepetizer.fragment.DailyFragment;
import fanpeihua.justforfun.eyepetizer.fragment.FindFragment;
import fanpeihua.justforfun.eyepetizer.fragment.RecommendFragment;
import fanpeihua.justforfun.me.MeFragment;
import fanpeihua.justforfun.one.OneFragment;

/**
 * Created by oneball on 2018/4/13.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(OneFragment oneFragment);

    void inject(EyepetizerFragment eyepetizerFragment);

    void inject(MeFragment meFragment);

    void inject(FindFragment findFragment);

    void inject(RecommendFragment recommendFragment);

    void inject(DailyFragment dailyFragment);

    void inject(CategoryFragment categoryFragment);

    void inject(CategoriesDetailIndexFragment categoriesDetailIndexFragment);

    void inject(TagsDetailIndexFragment tagsDetailIndexFragment);

    void inject(AuthorDetailIndexFragment authorDetailIndexFragment);
}
