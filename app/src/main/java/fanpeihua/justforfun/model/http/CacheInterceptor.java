package fanpeihua.justforfun.model.http;

import java.io.IOException;

import fanpeihua.justforfun.utils.Utils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by oneball on 2018/3/22.
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (Utils.isNetworkConnected()) {
            Response response = chain.proceed(request);
            int maxAge = 10;
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        } else {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Response response = chain.proceed(request);
            int maxStale = 60 * 60 * 24 * 3;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale= " + maxStale)
                    .build();
        }
    }
}
