package fanpeihua.justforfun.eyepetizer.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.Utils;

/**
 * Created by oneball on 2018/4/1.
 */

public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardAdapter.FindSmallCardViewHolder> {
    private Context mContext;
    private List<ItemListBean> mItemList;
    private OnItemVideoClickListener mOnItemVideoClickListener;
    private boolean mIsDetail;


    public SmallCardAdapter(Context context, OnItemVideoClickListener mOnItemClickListener) {
        this.mContext = context;
        this.mOnItemVideoClickListener = mOnItemClickListener;
    }

    public void setIsDetail(boolean isDetail) {
        this.mIsDetail = isDetail;
    }

    public void setSmallCardData(List<ItemListBean> mItemList) {
        mItemList.remove(0);
        this.mItemList = mItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @NonNull
    @Override
    public FindSmallCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_small_card, parent, false);
        return new FindSmallCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindSmallCardViewHolder holder, int position) {
        ItemListBean itemListBean = mItemList.get(position);
        ImageLoader.displayImage(mContext, itemListBean.getData().getCover().getFeed(),
                holder.ivCover, false);
        holder.tvTime.setText(Utils.durationFormat(itemListBean.getData().getDuration()));
        holder.tvTitle.setText(itemListBean.getData().getTitle());
        if (mIsDetail) {
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvDescription.setTextColor(mContext.getResources().getColor(R.color.line_color));
            holder.tvDescription.setText(String.format(mContext.getString(R.string.category1),
                    itemListBean.getData().getCategory()));
        } else {
            holder.tvDescription.setText(String.format(mContext.getString(R.string.small_description),
                    itemListBean.getData().getCategory(), itemListBean.getData().getAuthor().getName()));
        }

        Utils.startAnimation(mContext, holder.ivCover);
    }

    class FindSmallCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_time)
        DCTextView tvTime;
        @BindView(R.id.tv_title)
        FZTextView tvTitle;
        @BindView(R.id.tv_description)
        FZTextView tvDescription;

        public FindSmallCardViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemVideoClickListener != null) {
                mOnItemVideoClickListener.onItemVideoClick(mItemList.get(getAdapterPosition()));
            }
        }
    }
}
