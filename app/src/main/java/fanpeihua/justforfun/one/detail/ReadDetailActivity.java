package fanpeihua.justforfun.one.detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.application.GlideApp;
import fanpeihua.justforfun.base.MvpBaseActivity;
import fanpeihua.justforfun.cutomview.X5WebView;
import fanpeihua.justforfun.model.bean.AuthorBean;
import fanpeihua.justforfun.model.bean.CommentBean;
import fanpeihua.justforfun.model.bean.ContentListBean;
import fanpeihua.justforfun.model.bean.MovieDetailBean;
import fanpeihua.justforfun.model.bean.MoviePhotoBean;
import fanpeihua.justforfun.model.bean.MusicDetailBean;
import fanpeihua.justforfun.model.bean.QuestionDetailBean;
import fanpeihua.justforfun.model.bean.ReadDetailBean;
import fanpeihua.justforfun.one.detail.mvp.ReadDetailContract;
import fanpeihua.justforfun.one.detail.mvp.ReadDetailPresenter;
import fanpeihua.justforfun.utils.Constants;
import fanpeihua.justforfun.utils.HtmlUtil;
import fanpeihua.justforfun.utils.ImageLoader;
import fanpeihua.justforfun.utils.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import jp.wasabeef.blurry.Blurry;

public class ReadDetailActivity extends MvpBaseActivity<ReadDetailPresenter> implements
        ReadDetailContract.View {
    private static final String TAG = ReadDetailActivity.class.getSimpleName();
    @BindView(R.id.web_view)
    X5WebView webView;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_ask)
    TextView tv_userAsk;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.iv_author)
    ImageView ivAuthor;
    @BindView(R.id.iv_loading)
    ImageView ivLoading;
    @BindView(R.id.tv_hp_author)
    TextView tvHpAuthor;
    @BindView(R.id.tv_auth_it)
    TextView tvAuthIt;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.layout_bottom2)
    RelativeLayout layoutBottom2;
    @BindView(R.id.layout_app_bar)
    RelativeLayout layoutAppBar;
    @BindView(R.id.tv_like_num)
    TextView tvLikeNum;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.line_bar)
    View lineBar;
    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;
    @BindView(R.id.layout_view_pager)
    FrameLayout layoutViewPager;
    @BindView(R.id.layout_question)
    RelativeLayout layoutQuestion;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_user_question)
    TextView tvUserQuestion;
    @BindView(R.id.tv_question_content)
    TextView tvQuestionContent;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.iv_mini_cover)
    ImageView ivMiniCover;
    @BindView(R.id.tv_music_info)
    TextView tvMusicInfo;
    @BindView(R.id.layout_music)
    FrameLayout layoutMusic;
    private ContentListBean mContentListBean;
    private CommentAdapter mCommentAdapter;
    private AnimationDrawable mAnimationDrawable;
    private boolean mIsBottomShow = true;
    private String mTitle;
    private boolean mIsMovie;

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_read_detail;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void init() {
        mContentListBean = (ContentListBean) getIntent().getSerializableExtra(Constants.
                ONE_LIST_BEAN);
        mTitle = mContentListBean.getShareList().getWx().getTitle().split("\\|")[0].
                trim();
        initAnim();
        initToolbar();
        initTop();
        tvTitle.setText(mTitle);
        tvDetailTitle.setText(mContentListBean.getTitle());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (mContentListBean.getAnswerer() == null) {
            tvUserName.setText(mContentListBean.getShareList().getWx().getDesc().split(" ")
                    [0].trim());
        } else {
            layoutQuestion.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.GONE);
        }

        tvLikeNum.setText(mContentListBean.getLikeCount() + "");

        presenter.loadDetail(mContentListBean);

        initListener();
    }

    private void initTop() {
        switch (Integer.parseInt(mContentListBean.getCategory())) {
            case Constants.CATEGORY_MOVIE:
                layoutViewPager.setVisibility(View.VISIBLE);
                mIsMovie = true;
                presenter.loadMoviePhoto(mContentListBean.getItemId());
                setAppBarAlpha(0);
                break;
            case Constants.CATEGORY_MUSIC:
                mIsMovie = true;
                layoutMusic.setVisibility(View.VISIBLE);
                setAppBarAlpha(0);
                break;
            default:
                mIsMovie = false;
                break;
        }
    }

    private void initListener() {
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX,
                                       int oldScrollY) {
                if (scrollY - oldScrollY > 0 && mIsBottomShow) {  //隐藏
                    mIsBottomShow = false;
                    layoutBottom2.animate().translationY(layoutBottom2.getHeight());
                    layoutAppBar.animate().translationY(-layoutAppBar.getHeight());
                    tvTitle.setText(tvDetailTitle.getText().toString());

                } else if (scrollY - oldScrollY < 0 && !mIsBottomShow) {    //出现
                    mIsBottomShow = true;
                    layoutBottom2.animate().translationY(0);
                    layoutAppBar.animate().translationY(0);
                    setAppBarAlpha(255);
                }

                if (mIsBottomShow) {
                    if (scrollY <= 255) {
                        if (mIsMovie) {
                            setAppBarAlpha(scrollY);
                        }
                        if (scrollY == 0) {
                            tvTitle.setText(mTitle);
                        }
                    }
                }

            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (i == 100) {
                    stopAnim();
                }
            }
        });
    }

    private void setAppBarAlpha(int alpha) {
        layoutAppBar.getBackground().setAlpha(alpha);
        tvTitle.setAlpha(alpha);
        lineBar.getBackground().setAlpha(alpha);
        if (alpha < 30) {
            toolbar.setNavigationIcon(R.mipmap.back_white);
        } else {
            toolbar.setNavigationIcon(R.mipmap.return_image_gray);
        }
    }

    private void initAnim() {
        ivLoading.setImageResource(R.drawable.web_view_loading);
        mAnimationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        mAnimationDrawable.start();
    }


    @Override
    public void showErrorMsg(String msg) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showReadData(ReadDetailBean readDetailBean) {
        showContent(readDetailBean.getHp_content(), readDetailBean.getHp_author_introduce() +
                " " + readDetailBean.getEditor_email(), readDetailBean.getAuthor().get(0));
    }


    @Override
    public void showMovieData(MovieDetailBean movieDetailBean) {
        MovieDetailBean.DataBean dataBean = movieDetailBean.getData().get(0);
        tvDetailTitle.setText(dataBean.getTitle());
        showContent(dataBean.getContent(), dataBean.getChargeEdt() +
                " " + dataBean.getEditorEmail(), dataBean.getUser());
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void showMusicData(MusicDetailBean musicDetailBean) {
        ImageLoader.displayImage(this, mContentListBean.getCover(), ivMiniCover);
        tvMusicInfo.setText(String.format(getString(R.string.music_info), musicDetailBean.getTitle(),
                musicDetailBean.getAuthor().getUserName(), musicDetailBean.getAlbum()));
        showContent(musicDetailBean.getStory(), musicDetailBean.getChargeEdt() +
                " " + musicDetailBean.getEditorEmail(), musicDetailBean.getAuthorList().get(0));
        Flowable.just(mContentListBean.getImgUrl())
                .flatMap(new Function<String, Publisher<Bitmap>>() {
                    @Override
                    public Publisher<Bitmap> apply(String s) throws Exception {
                        Bitmap bitmap = GlideApp.with(ReadDetailActivity.this).asBitmap()
                                .load(s).into(300, 300).get();
                        return Flowable.just(bitmap);
                    }
                }).compose(RxUtil.<Bitmap>rxSchedulerHelper())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        Blurry.with(ReadDetailActivity.this).radius(10)
                                .sampling(8).from(bitmap).into(ivCover);
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showReadCommend(CommentBean commentBean) {
        mCommentAdapter = new CommentAdapter(this, commentBean);
        recyclerView.setAdapter(mCommentAdapter);
        tvCommentNum.setText(commentBean.getCount() + "");
    }

    @Override
    public void showQuestionData(QuestionDetailBean questionDetailBean) {
        tvUserQuestion.setText(String.format(getString(R.string.question), questionDetailBean.
                getAsker().getUserName()));
        tvQuestionContent.setText(questionDetailBean.getQuestionContent());
        tv_userAsk.setText(String.format(getString(R.string.answerer1), questionDetailBean.
                getAnswerer().getUserName()));
        showContent(questionDetailBean.getAnswerContent(), questionDetailBean.getChargeEdt() +
                " " + questionDetailBean.getChargeEmail(), questionDetailBean.getAnswerer());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showMoviePhotos(final MoviePhotoBean moviePhotoBean) {
        final List<String> photoList = new ArrayList<>();
        photoList.add(moviePhotoBean.getDetailcover());
        photoList.addAll(moviePhotoBean.getPhoto());
        viewPager.setAdapter(new TopViewPagerAdapter(this, photoList));
        tvMovieName.setText(String.format(getString(R.string.movie_title), moviePhotoBean.
                getTitle()));
        tvPosition.setText(1 + "/" + photoList.size());
        viewPager.setCurrentItem(Constants.MOVIE_VIEW_PAGE_SIZE * photoList.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onPageSelected(int position) {
                tvPosition.setText(position % photoList.size() + 1 + "/" + photoList.size());
            }

            @Override
            public void onPageScrollStateChanged(int status) {
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void showContent(String hpContent, String sIntroduce, AuthorBean authorBean) {
        List<String> list = new ArrayList<>();
        list.add(Constants.ONE_DETAIL_CSS);
        List<String> list1 = new ArrayList<>();
        list1.add(Constants.ONE_DETAIL_JS1);
        list1.add(Constants.ONE_DETAIL_JS2);
        list1.add(Constants.ONE_DETAIL_JS3);
        String htmlData = HtmlUtil.createHtmlData(hpContent,
                list, list1);
        webView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

        tvIntroduce.setText(sIntroduce);
        ImageLoader.displayImage(this, authorBean.getWebUrl(), ivAuthor, true);
        tvHpAuthor.setText(authorBean.getUserName() + " " + authorBean.getWbName());
        tvAuthIt.setText(authorBean.getDesc());
    }

    private void stopAnim() {
        mAnimationDrawable.stop();
        layoutBottom.setVisibility(View.VISIBLE);
        ivLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解决一个很奇葩的bug
        setAppBarAlpha(255);
        layoutAppBar.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (webView != null)
            webView.destroy();
        super.onDestroy();
    }
}
