package fanpeihua.justforfun.eyepetizer.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.OnClick;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.cutomview.DCTextView;
import fanpeihua.justforfun.cutomview.FZTextView;
import fanpeihua.justforfun.eyepetizer.detail.fragment.AuthorDetailIndexFragment;
import fanpeihua.justforfun.eyepetizer.detail.mvp.AuthorDetailPresenter;
import fanpeihua.justforfun.eyepetizer.detail.mvp.DetailBaseContract;
import fanpeihua.justforfun.model.bean.AuthorDetailBean;
import fanpeihua.justforfun.utils.ImageLoader;

public class AuthorDetailActivity extends DetailBaseActivity<AuthorDetailPresenter>
        implements DetailBaseContract.View {
    public static final String TAG = AuthorDetailActivity.class.getSimpleName();
    @BindView(R.id.tv_focus_num)
    FZTextView tvFocusNum;
    @BindView(R.id.tv_video_num)
    DCTextView tvVideoNum;
    @BindView(R.id.tv_like_num)
    DCTextView tvLikeNum;
    @BindView(R.id.tv_share_num)
    DCTextView tvShareNum;
    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    public int getLayout() {
        return R.layout.activity_author_detail;
    }

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public FragmentPagerItems getPages() {
        String[] tabs = getResources().getStringArray(R.array.author_tabs);
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of(tabs[0], AuthorDetailIndexFragment.class));
        pages.add(FragmentPagerItem.of(tabs[1], AuthorDetailIndexFragment.class));
        pages.add(FragmentPagerItem.of(tabs[2], AuthorDetailIndexFragment.class));
        return pages;
    }

    @Override
    public void loadCategoriesDetail(String id) {
        presenter.loadDetailIndex(id);
    }

    @Override
    public void refreshAuthorData(AuthorDetailBean.PgcInfoBean pgcInfoBean) {
        super.refreshAuthorData(pgcInfoBean);
        tvName.setText(pgcInfoBean.getName());
        tvBoldTitle.setText(pgcInfoBean.getName());
        tvFocusNum.setText(String.format(getString(R.string.by_focus_num), String.valueOf(
                pgcInfoBean.getFollowCount())));
        tvDescription.setText(pgcInfoBean.getDescription());
        tvVideoNum.setText(String.valueOf(pgcInfoBean.getVideoCount()));
        tvLikeNum.setText(String.valueOf(pgcInfoBean.getCollectCount()));
        tvShareNum.setText(String.valueOf(pgcInfoBean.getShareCount()));
        ImageLoader.displayImage(this, pgcInfoBean.getCover(), ivCoverBg, false,
                R.mipmap.cover_default);
        ImageLoader.displayImage(this, pgcInfoBean.getIcon(), ivHead, true, true);
    }
}
