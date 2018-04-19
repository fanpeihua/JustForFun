package fanpeihua.justforfun.eyepetizer.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.eyepetizer.listener.OnItemCategoryClickListener;
import fanpeihua.justforfun.model.bean.ItemListBean;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.Utils;

/**
 * Created by oneball on 2018/4/13.
 */

public class BriefAdapter extends RecyclerView.Adapter<BriefAdapter.FindHotSortViewHolder> {
    private Context mContext;
    private List<ItemListBean> mItemList;
    private OnItemCategoryClickListener mOnItemCategoryClickListener;

    public BriefAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BriefAdapter(Context mContext, List<ItemListBean> mItemList) {
        this.mContext = mContext;
        setHotSortData(mItemList);
    }

    public void setOnItemClickListener(OnItemCategoryClickListener mOnItemCategoryClickListener) {
        this.mOnItemCategoryClickListener = mOnItemCategoryClickListener;
    }

    public void setHotSortData(List<ItemListBean> mItemList) {
        this.mItemList = new ArrayList<>();
        for (ItemListBean itemListBeanX : mItemList) {
            if (itemListBeanX.getType().equals(Constants.BRIEF_CARD)) {
                this.mItemList.add(itemListBeanX);
            }
        }
    }

    public void setAllCategoriesData(List<ItemListBean> mItemList) {
        this.mItemList = new ArrayList<>();
        for (ItemListBean itemListBeanX : mItemList) {
            if (itemListBeanX.getType().equals(Constants.SQUARE_CARD) && !TextUtils.isEmpty(
                    itemListBeanX.getData().getTitle())) {
                this.mItemList.add(itemListBeanX);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public FindHotSortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_brief_card, parent,
                false);
        return new FindHotSortViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindHotSortViewHolder holder, int position) {
        ItemListBean itemListBean = mItemList.get(position);
        holder.tvTitle.setText(itemListBean.getData().getTitle());
        if (itemListBean.getType().equals(Constants.SQUARE_CARD)) {
            ImageLoader.displayImage(mContext, itemListBean.getData().getImage(), holder.ivCover);
            holder.ivRight.setImageResource(R.mipmap.drag_icon);
            String[] descriptions = mContext.getResources().getStringArray(R.array.
                    categories_descriptions);
            holder.tvDescription.setText(descriptions[position]);
            holder.tvDescription.setTextColor(mContext.getResources().getColor(R.color.
                    transparent));
        } else {
            ImageLoader.displayImage(mContext, itemListBean.getData().getIcon(), holder.ivCover);
            holder.tvDescription.setText(itemListBean.getData().getDescription());
            Utils.startAnimation(mContext, holder.ivCover);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    class FindHotSortViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        FZTextView tvTitle;
        @BindView(R.id.tv_description)
        FZTextView tvDescription;
        @BindView(R.id.iv_right)
        ImageView ivRight;

        public FindHotSortViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemCategoryClickListener != null) {
                mOnItemCategoryClickListener.onCategoryItemClick(mItemList.get(
                        getAdapterPosition()));
            }
        }
    }
}

