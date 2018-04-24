package fanpeihua.justforfun.nba.mvp;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

import fanpeihua.justforfun.databinding.FragmentNewsBinding;
import fanpeihua.justforfun.fbase.base.BaseFragment;


public class NbaFragment extends BaseFragment<NbaPresenter,FragmentNewsBinding>
        implements NbaContract.INewsView, TabLayout.OnTabSelectedListener {

    private List<String> tabTitles = new ArrayList<>();
    private ArrayList<BaseFragment> mFragmentList;
    private VPNews



}
