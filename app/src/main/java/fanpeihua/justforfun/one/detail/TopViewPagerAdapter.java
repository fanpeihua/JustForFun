package fanpeihua.justforfun.one.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import fanpeihua.justforfun.R;
import fanpeihua.justforfun.utils.ImageLoader;

/**
 * Created by oneball on 2018/4/14.
 */

public class TopViewPagerAdapter extends PagerAdapter {
    private List<String> mList;
    private Context mContext;

    public TopViewPagerAdapter(Context context, List<String> mList) {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_top_pager, container,
                false);
        ImageView ivImage = view.findViewById(R.id.iv_top_image);
        ImageLoader.displayImage(mContext, mList.get(position % mList.size()), ivImage);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
