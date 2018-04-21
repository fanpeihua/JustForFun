package fanpeihua.justforfun.nba.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import fanpeihua.justforfun.application.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit mRetrofit;

    private static ConcurrentMap<String, Retrofit> retrofitFactory = new ConcurrentHashMap<>();

    private ApiClient() {

    }

    private ApiClient(OkHttpClient okHttpClient, String baseUrl) {
        for (Map.Entry<String, Retrofit> retrofitEntry : retrofitFactory.entrySet()) {
            if (retrofitEntry.getKey().equals(baseUrl)) {
                return;
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitFactory.putIfAbsent(baseUrl, retrofit);
    }

    public static void create(@Config.BaseURL String baseUrl, OkHttpClient okHttpClient) {
        new ApiClient(okHttpClient, baseUrl);
    }

    public static Retrofit get(String baseUrl) {
        return retrofitFactory.get(baseUrl);
    }
}
