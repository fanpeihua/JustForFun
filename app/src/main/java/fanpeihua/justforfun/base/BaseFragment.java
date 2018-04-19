package fanpeihua.justforfun.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.application.MyApplication;

/**
 * Created by oneball on 2018/3/14.
 */

public abstract class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();
    public ProgressDialog mProgressDialog;
    public Snackbar snackbar;

    public abstract View initView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    public abstract void init();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void showProgressDialog(int id) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }
        mProgressDialog.setMessage(getString(id));
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
//        MyApplication.getsRefWatcher().watch(this);
    }

    public void showSnackBar(int strId) {
        showSnackBar(getString(strId));
    }

    public void showSnackBar(String str) {
        snackbar = Snackbar.make(getActivity().findViewById(R.id.layout_main_content),
                str, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.radio_cover_color));
        snackbar.show();
    }

    @Subscribe
    public void onEventMainThread(String flags) {
        Log.d(TAG, flags);
    }
}
