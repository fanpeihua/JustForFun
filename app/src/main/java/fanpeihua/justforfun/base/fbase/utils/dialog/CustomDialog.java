package fanpeihua.justforfun.base.fbase.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.base.fbase.utils.base.ScreenSizeUtils;

public class CustomDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    public CustomDialog(Builder builder) {
        mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.MyDialogStyle);
        mDialogView = mBuilder.getView();
        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getItemHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getItemWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = builder.getDialogGravity();
        dialogWindow.setAttributes(lp);
        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public static class Builder {

        private View mView;
        private float itemHeight;
        private float itemWidth;
        private Context mContext;
        private boolean isTouchOutside;
        private int dialogGravity;

        public Builder(Context context) {
            mContext = context;
            itemHeight = 0.28f;
            itemWidth = 0.8f;
            isTouchOutside = true;
            dialogGravity = Gravity.CENTER;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
        }

        public View getView() {
            return mView;
        }

        public Builder setView(View view) {
            mView = view;
            return this;
        }

        public float getItemHeight() {
            return itemHeight;
        }

        public Builder setItemHeight(float itemHeight) {
            this.itemHeight = itemHeight;
            return this;
        }

        public float getItemWidth() {
            return itemWidth;
        }

        public Builder setItemWidth(float itemWidth) {
            this.itemWidth = itemWidth;
            return this;
        }

        public Context getContext() {
            return mContext;
        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }

        public int getDialogGravity() {
            return dialogGravity;
        }

        public Builder setDialogGravity(int gravity) {
            dialogGravity = gravity;
            return this;
        }

        public CustomDialog build() {
            return new CustomDialog(this);
        }
    }

}
