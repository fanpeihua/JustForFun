package fanpeihua.justforfun.base.fbase.base;

import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatDelegate;
import android.util.ArrayMap;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.application.Config;

public abstract class BaseFragment<T extends FBasePresenter, V extends ViewDataBinding>
        extends FBaseFragment<T, V> {

    static {
        //设置VectorDrawable 兼容支持，否则会闪退
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void initThemeAttrs() {
        mThemeColorMap = new ArrayMap<>();
        TypedArray array = getActivity().getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.colorPrimary,
                android.R.attr.colorPrimaryDark,
                android.R.attr.colorAccent,
                R.attr.colorTextLight,
                R.attr.colorTextDark,
                R.attr.colorBg,
                R.attr.colorBgDark
        });

        int colorPrimary = array.getColor(0, 0xC01E2F);
        int colorPrimaryDark = array.getColor(1, 0xA82828);
        int colorAccent = array.getColor(2, 0xF65663);
        int colorTextLight = array.getColor(3, 0xB6B6BE);
        int colorTextDark = array.getColor(4, 0x444242);
        int colorBg = array.getColor(5, 0xFFFFFF);
        int colorBgDark = array.getColor(6, 0xF6F5F4);
        array.recycle();

        mThemeColorMap.put(Config.ATTRS.COLOR_PRIMARY, colorPrimary);
        mThemeColorMap.put(Config.ATTRS.COLOR_PRIMARY_DARK, colorPrimaryDark);
        mThemeColorMap.put(Config.ATTRS.COLOR_ACCENT, colorAccent);
        mThemeColorMap.put(Config.ATTRS.COLOR_TEXT_LIGHT, colorTextLight);
        mThemeColorMap.put(Config.ATTRS.COLOR_TEXT_DARK, colorTextDark);
        mThemeColorMap.put(Config.ATTRS.COLOR_BG, colorBg);
        mThemeColorMap.put(Config.ATTRS.COLOR_BG_DARK, colorBgDark);
    }
}
