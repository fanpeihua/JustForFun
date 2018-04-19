package fanpeihua.justforfun.di.module;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fanpeihua.justforfun.BuildConfig;
import fanpeihua.justforfun.di.qualifier.EyepetizerUrl;
import fanpeihua.justforfun.di.qualifier.OneUrl;
import fanpeihua.justforfun.model.http.CacheInterceptor;
import fanpeihua.justforfun.model.http.apis.EyepetizerApis;
import fanpeihua.justforfun.model.http.apis.OneApis;
import fanpeihua.justforfun.utils.Constants;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oneball on 2018/3/22.
 */
@Module
public class HttpModule {
    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        Cache cache = new Cache(new File(Constants.PATH_CACHE), Constants.CACHE_SIZE);
        //设置缓存
        builder.addInterceptor(new CacheInterceptor());
        builder.addNetworkInterceptor(new CacheInterceptor());
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    @Provides
    @Singleton
    @OneUrl
    Retrofit provideOneRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, OneApis.HOST);
    }

    @Provides
    @Singleton
    OneApis provideOneService(@OneUrl Retrofit retrofit) {
        return retrofit.create(OneApis.class);
    }

    @Provides
    @Singleton
    @EyepetizerUrl
    Retrofit provideEyepetizerRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, EyepetizerApis.HOST);
    }

    @Provides
    @Singleton
    EyepetizerApis provideEyepetizerService(@EyepetizerUrl Retrofit retrofit) {
        return retrofit.create(EyepetizerApis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
