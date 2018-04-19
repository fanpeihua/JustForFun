package fanpeihua.justforfun.splash;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import fanpeihua.justforfun.main.MainActivity;
import fanpeihua.justforfun.R;
import fanpeihua.justforfun.utils.Constants;

public class WelcomeActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.author_bg);
        configSplash.setAnimCircularRevealDuration(500);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        configSplash.setPathSplash(Constants.DROID_LOGO);
        configSplash.setOriginalHeight(400);
        configSplash.setOriginalWidth(400);
        configSplash.setAnimPathStrokeDrawingDuration(250);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(250);
        configSplash.setPathSplashFillColor(R.color.colorAccent); //path object filling color


        configSplash.setTitleSplash("JustForFun");
        configSplash.setTitleTextColor(R.color.colorAccent);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("fonts/hp_title.ttf");
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
