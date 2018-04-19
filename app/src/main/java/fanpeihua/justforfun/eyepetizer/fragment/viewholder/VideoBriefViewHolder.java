package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.FZTextView;

/**
 * Created by oneball on 2018/4/13.
 */

public class VideoBriefViewHolder extends BaseViewHolder {
    public ImageView ivCover;
    public FZTextView tvTitle;
    public FZTextView tvDescription;
    public Button btnFocus;
    public RelativeLayout layoutAuthor;

    public VideoBriefViewHolder(View itemView) {
        super(itemView);
        ivCover = itemView.findViewById(R.id.iv_cover);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDescription = itemView.findViewById(R.id.tv_description);
        btnFocus = itemView.findViewById(R.id.btn_focus);
        layoutAuthor = itemView.findViewById(R.id.layout_author);
    }
}