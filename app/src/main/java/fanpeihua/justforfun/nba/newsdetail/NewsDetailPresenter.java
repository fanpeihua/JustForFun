package fanpeihua.justforfun.nba.newsdetail;

import android.graphics.Bitmap;

import java.io.File;

import fanpeihua.justforfun.application.Config;
import fanpeihua.justforfun.application.MyApplication;
import fanpeihua.justforfun.base.fbase.base.FBasePresenter;
import fanpeihua.justforfun.base.fbase.utils.base.ImageUtils;
import fanpeihua.justforfun.nba.api.NbaNewsApiFactory;
import fanpeihua.justforfun.nba.bean.NewsDetail;
import fanpeihua.justforfun.utils.JsonParser;
import fanpeihua.justforfun.utils.RxSchedulers;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class NewsDetailPresenter extends FBasePresenter<NewsDetailContract.INewsDetailView>
        implements NewsDetailContract.INewsDetailPresenter {
    public NewsDetailPresenter(NewsDetailContract.INewsDetailView view) {
        super(view);
    }

    @Override
    public void getNewsDetail(String column, String articleId) {
        addSubscription(NbaNewsApiFactory.getNewsDetail(column, articleId)
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody newsDetail) throws Exception {
                        String string = newsDetail.string();
                        NewsDetail detail = JsonParser.parseWithGson(NewsDetail.class, string);
                        mView.getNewsDetail(detail);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showViewError(throwable);
                    }
                }));
    }

    /**
     * 保存图片本地，并通知图库刷新
     *
     * @param bitmap
     */
    @Override
    public void saveBitmap(final Bitmap bitmap) {
        Disposable saveBit = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(Config.DIR.PIC_DIR, fileName);
                if (ImageUtils.save(bitmap, file, Bitmap.CompressFormat.JPEG)) {
                    if (ImageUtils.insertImage(MyApplication.getsInstance(), file.getAbsolutePath(), fileName, null)) {
                        e.onNext(true);
                    } else {
                        e.onNext(false);
                    }
                } else {
                    e.onNext(false);
                }
            }
        }).compose(RxSchedulers.<Boolean>ioMain()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean b) throws Exception {
                mView.savePicDone(b);
            }
        });
        addSubscription(saveBit);
    }
}
