package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.DCTextView;
import fanpeihua.justforfun.cutomview.FZTextView;

/**
 * Created by oneball on 2018/4/13.
 */

public class VideoSmallHolder extends RecyclerView.ViewHolder {
    public DCTextView tvTime;
    public ImageView ivCover;
    public FZTextView tvTitle;
    public FZTextView tvDescription;

    public VideoSmallHolder(View itemView) {
        super(itemView);
        tvTime = itemView.findViewById(R.id.tv_time);
        ivCover = itemView.findViewById(R.id.iv_cover);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDescription = itemView.findViewById(R.id.tv_description);
    }
}
