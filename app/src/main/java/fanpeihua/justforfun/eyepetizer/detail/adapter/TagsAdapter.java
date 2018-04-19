package fanpeihua.justforfun.eyepetizer.detail.adapter;

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
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.model.bean.DataBean;
import fanpeihua.justforfun.splash.listener.OnItemTagsClickListener;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.ImageLoader;

/**
 * Created by oneball on 2018/3/29.
 */

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    private Context mContext;
    private List<DataBean.TagsBean> mItemList;
    private OnItemTagsClickListener mOnItemTagsClickListener;

    public TagsAdapter(Context mContext, OnItemTagsClickListener mOnITemTagsClickListener) {
        this.mContext = mContext;
        this.mOnItemTagsClickListener = mOnITemTagsClickListener;
    }

    public void setRvData(List<DataBean.TagsBean> mItemList) {
        this.mItemList = mItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return Constants.ALL_VIEW_TAPE;
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tags_card,
                parent, false);
        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder holder, int position) {
        DataBean.TagsBean tagsBean = mItemList.get(position);
        ImageLoader.displayImage(mContext, tagsBean.getBgPicture(), holder.ivCard);
        holder.tvTitle.setText(String.format(mContext.getString(R.string.tags),
                tagsBean.getName()));
    }

    class TagsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_card)
        ImageView ivCard;
        @BindView(R.id.tv_title)
        FZTextView tvTitle;


        public TagsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnItemTagsClickListener.onItemClick(mItemList.get(getAdapterPosition()).getId());
        }
    }
}
