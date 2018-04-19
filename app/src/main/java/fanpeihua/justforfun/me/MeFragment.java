package fanpeihua.justforfun.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.MvpBaseFragment;
import fanpeihua.justforfun.cutomview.BallPulseView;
import fanpeihua.justforfun.cutomview.HpTextView;
import fanpeihua.justforfun.cutomview.X5WebView;
import fanpeihua.justforfun.eyepetizer.EyepetizerFragment;
import fanpeihua.justforfun.me.mvp.MeContract;
import fanpeihua.justforfun.me.mvp.MePresenter;
import fanpeihua.justforfun.utils.Constants;

/**
 * Created by oneball on 2018/4/1.
 */

public class MeFragment extends MvpBaseFragment<MePresenter> implements MeContract.View {
    private static final String TAG = EyepetizerFragment.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_hp_title)
    HpTextView tvHpTitle;
    @BindView(R.id.web_view)
    X5WebView webView;
    @BindView(R.id.ball_pulse_view)
    BallPulseView ballPulseView;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    public void init() {
        tvHpTitle.setVisibility(View.VISIBLE);
        tvHpTitle.setText(R.string.me);
        ballPulseView.startAnim();
        webView.loadUrl(Constants.GIT_HUB);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                ballPulseView.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void showErrorMsg(String msg) {
        Log.d(TAG, msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isOnWebViewBack() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return false;
        }
    }

}