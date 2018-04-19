package fanpeihua.justforfun.eyepetizer.fragment.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import fanpeihua.justforfun.R;

/**
 * Created by oneball on 2018/4/13.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView rvItem;

    BaseViewHolder(View itemView) {
        super(itemView);
        rvItem = itemView.findViewById(R.id.rv_item);
    }
}
