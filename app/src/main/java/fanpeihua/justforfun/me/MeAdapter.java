package fanpeihua.justforfun.me;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.model.bean.VideoBean;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.Utils;

/**
 * Created by oneball on 2018/3/26.
 */

public class MeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<VideoBean.ItemListBeanX> mItemList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(VideoBean.ItemListBeanX position);
    }

    private enum ITEM_TYPE {
        CLIENT,
        OTHER
    }

    public MeAdapter(Context mContext, OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public void addVideoListData(List<VideoBean.ItemListBeanX> itemList, boolean isFirst) {
        int index = getItemCount();
        if (isFirst) {
            this.mItemList = itemList;
        } else {
            this.mItemList.addAll(itemList);
        }
//        notifyDataSetChanged();
        //局部刷新
        for (int i = index; i < this.mItemList.size(); i++) {
            notifyItemInserted(i);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = mItemList.get(position).getType();
        String dataType = mItemList.get(position).getData().getDataType();
        if (type.equals(Constants.VIDEO_TYPE) && dataType.equals(Constants.VIDEO_DATA_TYPE)) {
            return ITEM_TYPE.CLIENT.ordinal();
        } else {
            return ITEM_TYPE.OTHER.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (viewType == ITEM_TYPE.CLIENT.ordinal()) {
            return new MeViewHolder(layoutInflater.inflate(R.layout.item_all_video,
                    parent, false));
        } else {
            return new MeViewHolder(layoutInflater.inflate(R.layout.item_all_video,
                    parent, false));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoBean.ItemListBeanX listBeanX = mItemList.get(position);
        if (getItemViewType(position) == ITEM_TYPE.CLIENT.ordinal()) {
            MeViewHolder viewHolder = ((MeViewHolder) holder);
            ImageLoader.displayImage(mContext, listBeanX.getData().getCover().getFeed(),
                    viewHolder.ivCover);
            if (listBeanX.getData().getAuthor() != null) {
                ImageLoader.displayImage(mContext, listBeanX.getData().getAuthor().getIcon(),
                        viewHolder.ivAuthor, true);
                viewHolder.tvAuthor.setText(listBeanX.getData().getAuthor().getName() + " / " +
                        Utils.durationFormat((long) listBeanX.getData().getDuration()));
            }
            viewHolder.tvTitle.setText(listBeanX.getData().getTitle());

            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_alpha);
            animation.setFillAfter(true);
            viewHolder.ivCover.startAnimation(animation);
            viewHolder.tvAuthor.startAnimation(animation);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();

    }

    class MeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.iv_author)
        ImageView ivAuthor;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;

        public MeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mItemList.get(getAdapterPosition()));
            }
        }
    }
}