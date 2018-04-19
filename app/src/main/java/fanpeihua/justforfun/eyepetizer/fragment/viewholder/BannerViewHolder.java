package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.view.View;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.DCTextView;
import fanpeihua.justforfun.cutomview.FZTextView;

/**
 * Created by oneball on 2018/4/13.
 */

public class BannerViewHolder extends BaseViewHolder {
    public FZTextView tvHeader;
    public DCTextView tvSubtitle;
    public View viewLine;

    public BannerViewHolder(View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.tv_header);
        tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
        viewLine = itemView.findViewById(R.id.view_line);
    }
}
