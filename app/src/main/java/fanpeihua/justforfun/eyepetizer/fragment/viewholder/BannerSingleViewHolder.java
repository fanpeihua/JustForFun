package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.view.View;
import android.widget.ImageView;

import fanpeihua.justforfun.R;

/**
 * Created by oneball on 2018/4/13.
 */

public class BannerSingleViewHolder extends BaseViewHolder {
    public ImageView ivCard;

    public BannerSingleViewHolder(View itemView) {
        super(itemView);
        ivCard = itemView.findViewById(R.id.iv_card);
    }
}
