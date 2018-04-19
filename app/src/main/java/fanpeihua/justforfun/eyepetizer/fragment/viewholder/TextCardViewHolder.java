package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.FZTextView;

/**
 * Created by oneball on 2018/4/13.
 */

public class TextCardViewHolder extends RecyclerView.ViewHolder {
    public FZTextView tvHeader;
    public FZTextView tvFooter;

    public TextCardViewHolder(View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.tv_header);
        tvFooter = itemView.findViewById(R.id.tv_footer);
    }

}
