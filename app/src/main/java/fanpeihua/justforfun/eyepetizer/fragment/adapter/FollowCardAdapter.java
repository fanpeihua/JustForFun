package fanpeihua.justforfun.eyepetizer.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.DCTextView;
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.eyepetizer.listener.OnItemAuthorClickListener;
import fanpeihua.justforfun.model.bean.DataBean;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.splash.listener.OnItemVideoClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.Utils;

/**
 * Created by oneball on 2018/4/13.
 */

public class FollowCardAdapter extends RecyclerView.Adapter<FollowCardAdapter.FollowCardViewHolder> {
    private Context mContext;
    private List<ItemListBean> mItemList;
    private OnItemVideoClickListener mOnItemVideoClickListener;
    private OnItemAuthorClickListener mOnItemAuthorClickListener;
    private boolean isOpenAnim;
    private boolean isSelect;

    public FollowCardAdapter(Context mContext, List<ItemListBean> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }

    public FollowCardAdapter(Context mContext, List<ItemListBean> itemListBeanX, boolean isOpenAnim) {
        this.mContext = mContext;
        this.mItemList = itemListBeanX;
        this.isOpenAnim = isOpenAnim;
    }

    public void setOnItemClickListener(OnItemVideoClickListener mOnItemVideoClickListener) {
        this.mOnItemVideoClickListener = mOnItemVideoClickListener;
    }

    public void setOnItemAuthorClickListener(OnItemAuthorClickListener mOnItemAuthorClickListener) {
        this.mOnItemAuthorClickListener = mOnItemAuthorClickListener;
    }

    public void setSelect(boolean select) {
        isSelect = select;
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
    public FollowCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view;
        if (viewType == Constants.FIRST_VIEW_TAPE) {
            view = layoutInflater.inflate(R.layout.item_follow_card_divider, parent,
                    false);
        } else {
            view = layoutInflater.inflate(R.layout.item_follow_card, parent,
                    false);
        }
        return new FollowCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowCardViewHolder holder, int position) {
        ItemListBean itemListBean = mItemList.get(position);
        if (itemListBean.getType().equals(Constants.BANNER3)) {
            holder.tvTime.setVisibility(View.GONE);
            holder.tvAd.setVisibility(View.VISIBLE);
            holder.ivSelect.setVisibility(View.GONE);
            holder.tvDescription.setText(itemListBean.getData().getDescription());
            ImageLoader.displayImage(mContext, itemListBean.getData().getImage(), holder.ivCardCover);
        } else {
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvAd.setVisibility(View.GONE);
            DataBean dataBean = itemListBean.getData().getContent().getData();
            ImageLoader.displayImage(mContext, dataBean.getCover().
                    getFeed(), holder.ivCardCover);
            holder.tvTime.setText(Utils.durationFormat(dataBean.
                    getDuration()));
            if (dataBean.getAuthor() != null) {
                holder.tvDescription.setText(String.format(mContext.getString(R.string.
                                follow_description), dataBean.getAuthor().getName(),
                        dataBean.getCategory()));
                if (dataBean.getAuthor().getName().contains(mContext.getString(R.string.select)) ||
                        isSelect) {
                    holder.ivSelect.setVisibility(View.VISIBLE);
                } else {
                    holder.ivSelect.setVisibility(View.GONE);
                }
            } else {
                holder.tvDescription.setText(dataBean.getSlogan());
            }
        }
        holder.tvTitle.setText(itemListBean.getData().getHeader().getTitle());
        ImageLoader.displayImage(mContext, itemListBean.getData().getHeader().getIcon(), holder.ivCover,
                true);
        if (isOpenAnim) {
            Utils.startAnimation(mContext, holder.ivCardCover);
            Utils.startAnimation(mContext, holder.ivCover);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    class FollowCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_card_cover)
        ImageView ivCardCover;
        @BindView(R.id.tv_time)
        DCTextView tvTime;
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.tv_title)
        FZTextView tvTitle;
        @BindView(R.id.tv_description)
        FZTextView tvDescription;
        @BindView(R.id.tv_ad)
        FZTextView tvAd;
        @BindView(R.id.layout_author)
        RelativeLayout layoutAuthor;
        @BindView(R.id.card_view)
        CardView cardView;

        public FollowCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this);
            layoutAuthor.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layout_author:
                    if (mOnItemAuthorClickListener != null) {
                        DataBean.AuthorBean authorBean = mItemList.get(getAdapterPosition()).
                                getData().getContent().getData().getAuthor();
                        if (authorBean != null) {
                            mOnItemAuthorClickListener.onItemAuthorClick(authorBean.getId());
                        }

                    }
                    break;
                case R.id.card_view:
                    if (mOnItemVideoClickListener != null) {
                        mOnItemVideoClickListener.onItemVideoClick(mItemList.get(getAdapterPosition()));
                    }
                    break;
            }

        }
    }
}
