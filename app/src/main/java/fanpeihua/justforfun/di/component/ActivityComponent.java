package fanpeihua.justforfun.di.component;

import android.app.Activity;

import dagger.Component;
import fanpeihua.justforfun.eyepetizer.detail.VideoCardActivity;
import fanpeihua.justforfun.main.MainActivity;
import fanpeihua.justforfun.di.module.ActivityModule;
import fanpeihua.justforfun.di.scope.ActivityScope;
import fanpeihua.justforfun.eyepetizer.detail.AuthorDetailActivity;
import fanpeihua.justforfun.eyepetizer.detail.CategoriesDetailActivity;
import fanpeihua.justforfun.eyepetizer.detail.ShareActivity;
import fanpeihua.justforfun.eyepetizer.detail.TagsDetailActivity;
import fanpeihua.justforfun.eyepetizer.detail.WebDetailActivity;
import fanpeihua.justforfun.eyepetizer.leftmenu.AllCategoriesActivity;
import fanpeihua.justforfun.eyepetizer.search.SearchActivity;
import fanpeihua.justforfun.one.detail.ReadDetailActivity;

/**
 * Created by oneball on 2018/3/22.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(ReadDetailActivity readDetailActivity);

    void inject(VideoCardActivity videoCardActivity);

    void inject(AllCategoriesActivity allCategoriesActivity);

    void inject(CategoriesDetailActivity categoriesDetailActivity);

    void inject(SearchActivity searchActivity);

    void inject(TagsDetailActivity tagsDetailActivity);

    void inject(AuthorDetailActivity authorDetailActivity);

    void inject(WebDetailActivity webDetailActivity);

    void inject(ShareActivity shareActivity);
}
