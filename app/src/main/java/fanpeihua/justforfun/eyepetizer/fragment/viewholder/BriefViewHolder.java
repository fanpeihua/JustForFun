package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.FZTextView;

/**
 * Created by oneball on 2018/4/13.
 */

public class BriefViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivCover;
    public ImageView ivRight;
    public FZTextView tvTitle;
    public FZTextView tvDescription;
    public Button btnFocus;

    public BriefViewHolder(View itemView) {
        super(itemView);
        ivCover = itemView.findViewById(R.id.iv_cover);
        ivRight = itemView.findViewById(R.id.iv_right);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDescription = itemView.findViewById(R.id.tv_description);
        btnFocus = itemView.findViewById(R.id.btn_focus);
    }
}