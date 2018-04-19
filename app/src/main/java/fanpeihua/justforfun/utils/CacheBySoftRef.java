package fanpeihua.justforfun.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oneball on 2018/3/14.
 */

public class CacheBySoftRef {
    private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

    public void addBitmapToCache(String path) {
        //强引用的Bitmap 对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //软引用的Bitmap 对象
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
        //添加该对象到Map中️使其缓存
        imageCache.put(path, softBitmap);
    }

    //获取的时候，可以通过SoftReference 的get()方法得到Bitmap对象
    public Bitmap getBitmapByPath(String path) {
        //从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        //判断是否存在软引用
        if (softBitmap == null) {
            return null;
        }
        //通过软引用取出Bitmap 对象，如果由于内存不足Bitmap 被回收，将取得空，如果未被回收，则可重复使用，提高速度。
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }


}
