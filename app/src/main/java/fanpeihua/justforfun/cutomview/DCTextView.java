package fanpeihua.justforfun.cutomview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by oneball on 2018/3/28.
 */
@SuppressLint("AppCompatCustomView")
public class DCTextView extends TextView {
    public DCTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DCTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DCTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (isInEditMode()) return;

        String fontName = "dc_bold.ttf";

        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName),
                defStyleAttr);
    }
}
