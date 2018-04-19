package fanpeihua.justforfun.eyepetizer.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.DCTextView;
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.Utils;

/**
 * Created by oneball on 2018/4/13.
 */
public class BannerVideoBriefAdapter extends RecyclerView.Adapter<BannerVideoBriefAdapter.
        BannerVideoBriefViewHolder> {

    private Context mContext;
    private List<ItemListBean> mItemListBeans;
    private OnItemVideoClickListener mOnItemVideoClickListener;

    BannerVideoBriefAdapter(Context mContext, List<ItemListBean> itemList) {
        this.mContext = mContext;
        this.mItemListBeans = itemList;
    }

    public void setOnItemClickListener(OnItemVideoClickListener mOnItemVideoClickListener) {
        this.mOnItemVideoClickListener = mOnItemVideoClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Constants.FIRST_VIEW_TAPE;
        } else {
            return Constants.ALL_VIEW_TAPE;
        }
    }

    @Override
    public BannerVideoBriefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view;
        if (viewType == Constants.FIRST_VIEW_TAPE) {
            view = layoutInflater.inflate(R.layout.item_video_brief_card_divider, parent,
                    false);
        } else {
            view = layoutInflater.inflate(R.layout.item_video_brief_card, parent,
                    false);
        }
        return new BannerVideoBriefViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerVideoBriefViewHolder holder, int position) {
        ItemListBean itemListBean = mItemListBeans.get(position);
        ImageLoader.displayImage(mContext, itemListBean.getData().getCover().getFeed(), holder.ivCard);
        holder.tvTime.setText(Utils.durationFormat(itemListBean.getData().getDuration()));
        holder.tvTitle.setText(itemListBean.getData().getTitle());
        holder.tvDescription.setText(String.format(mContext.getString(R.string.category1),
                itemListBean.getData().getCategory()));

        Utils.startAnimation(mContext, holder.ivCard);
    }

    @Override
    public int getItemCount() {
        return mItemListBeans == null ? 0 : mItemListBeans.size();
    }

    class BannerVideoBriefViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_card)
        ImageView ivCard;
        @BindView(R.id.tv_time)
        DCTextView tvTime;
        @BindView(R.id.tv_title)
        FZTextView tvTitle;
        @BindView(R.id.tv_description)
        FZTextView tvDescription;

        BannerVideoBriefViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemVideoClickListener != null) {
                mOnItemVideoClickListener.onItemVideoClick(mItemListBeans.get(getAdapterPosition()));
            }
        }
    }
}

