package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.FZTextView;

/**
 * Created by oneball on 2018/4/13.
 */

public class DynamicInfoViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivHead;
    public FZTextView tvUserName;
    public FZTextView tvMessage;
    public ImageView ivCover;
    public FZTextView tvTitle;
    public FZTextView tvDescription;
    public FZTextView tvLikeNum;
    public FZTextView tvTime;
    public FZTextView tvText;

    public DynamicInfoViewHolder(View itemView) {
        super(itemView);
        ivHead = itemView.findViewById(R.id.iv_head);
        tvUserName = itemView.findViewById(R.id.tv_user_name);
        tvMessage = itemView.findViewById(R.id.tv_message);
        ivCover = itemView.findViewById(R.id.iv_cover);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDescription = itemView.findViewById(R.id.tv_description);
        tvLikeNum = itemView.findViewById(R.id.tv_like_num);
        tvTime = itemView.findViewById(R.id.tv_time);
        tvText = itemView.findViewById(R.id.tv_text);
    }
}
