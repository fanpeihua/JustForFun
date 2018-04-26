package fanpeihua.justforfun.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import fanpeihua.justforfun.R;

public abstract class BaseActivity extends RxAppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();
    public Toolbar toolbar;
    public TextView tvTitle;
    public boolean isBack = true;
    private long mExitTime;
    public Snackbar snackbar;

    public abstract void init();

    public abstract int getLayout();

    static {
        //设置VectorDrawable兼容支持，否则会闪退
        AppCompatDelegate
                .setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        onCreateView();
        init();
    }

    public void onCreateView() {

    }

    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            tvTitle = findViewById(R.id.tv_title);
            setSupportActionBar(toolbar);
            setTitle("");

            toolbar.setOnMenuItemClickListener(onMenuItemClick);
            if (isBack) {
                toolbar.setNavigationIcon(R.mipmap.return_image_gray);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    public Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            OnMenuItemClick(item.getItemId());
            return true;
        }
    };

    public void OnMenuItemClick(int itemId) {

    }

    public boolean onExitAcitivity(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再次点击，将退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void showSnackBar(int strId) {
        showSnackBar(getString(strId));
    }

    public void showSnackBar(String str) {
        snackbar = Snackbar.make(findViewById(R.id.layout_main_content), str, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.radio_cover_color));
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEventMainThread(String flags) {
        Log.d(TAG, flags);
    }
}
