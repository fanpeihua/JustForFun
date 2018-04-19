package fanpeihua.justforfun.cutomview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.eyepetizer.detail.VideoCardActivity;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by oneball on 2018/3/27.
 */
@SuppressLint("AppCompatCustomView")
public class FZTextView extends TextView {
    public static final int UPDATE_DELAY = 10;
    private int mIndex;
    private boolean isSetColor;

    public FZTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public FZTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public FZTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        boolean isBold = false;
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().
                    obtainStyledAttributes(attrs, R.styleable.FZTVStay, defStyleAttr, 0);
            int n = typedArray.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.FZTVStay_isBold) {
                    isBold = typedArray.getBoolean(attr, false);
                }
            }
            typedArray.recycle();
        }

        String fontName = isBold ? "fz_bold.ttf" : "fz_light.ttf";
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "fonts/" + fontName), defStyleAttr);

    }

    public void startTypeWriter(VideoCardActivity activity, final String text) {
        startTypeWriter(activity, text, true);
    }

    public void startTypeWriter(VideoCardActivity activity, final String text, final boolean isSingleLine) {
        final int length = text.length();
        if (!isSingleLine) {
            //测量TextView高度，并在打字机开始前赋值
            ViewTreeObserver observer = getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //避免重复监听
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    //获文本高度
                    getLayoutParams().height = getMeasuredHeight();
                }
            });
        }

        mIndex = length / 2;
        int speed = 1500 / length;

        Flowable.interval(UPDATE_DELAY, speed, TimeUnit.MILLISECONDS)
                .filter(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return mIndex <= length;
                    }
                })
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        if (mIndex <= length) {
                            return text.substring(0, mIndex);
                        } else {
                            return "";
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.<String>bindToLifecycle())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        isSetColor = false;
                        if (!isSingleLine) {
                            //测量TextView高度，并在打字机开始前赋值
                            ViewTreeObserver observer = getViewTreeObserver();
                            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    //避免重复监听
                                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                    //获文本高度
                                    getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                                }
                            });
                        }
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mIndex++;
                        setText(s);
                        if (!isSetColor) {
                            setTextColor(isSingleLine ? getResources().getColor(R.color.white) :
                                    getResources().getColor(R.color.line_color));
                            isSetColor = true;
                        }
                    }

                });

    }
}
