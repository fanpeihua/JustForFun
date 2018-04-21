package fanpeihua.justforfun.fbase.base;

import android.util.ArrayMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class FBasePresenter<T extends IFBaseView> implements IFBasePresenter {
    protected T mView;
    protected CompositeDisposable compositeDisposable;
    protected ArrayMap<String, Disposable> disposableMap;


    public FBasePresenter(T view) {
        mView = view;
    }

    @Override
    public void onDestroy() {
        onUnsubscribe();
    }

    @Override
    public void onStop() {

    }

    protected void addSubscription(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected void addSubscription(String tag, Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        if (disposableMap == null) {
            disposableMap = new ArrayMap<>();
        }
        disposableMap.put(tag, disposable);
        compositeDisposable.add(disposable);
    }

    protected void onUnsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        if (disposableMap != null && disposableMap.size() > 0) {
            disposableMap.clear();
        }
    }

    protected void removeDisposableByTag(String... tags) {
        if (disposableMap != null && disposableMap.size() > 0) {
            for (String tag :
                    tags) {
                if (disposableMap.containsKey(tag)) {
                    compositeDisposable.remove(disposableMap.get(tag));
                    disposableMap.remove(tag);
                }
            }
        }
    }
}
