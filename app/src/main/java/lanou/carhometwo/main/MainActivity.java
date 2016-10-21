package lanou.carhometwo.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import base.BaseActivity;
import lanou.carhometwo.R;
import lanou.carhometwo.bbs.BbsFragment;
import lanou.carhometwo.choosecar.ChooseCarFragment;
import lanou.carhometwo.found.FoundFragment;
import lanou.carhometwo.personal.PersonalFragment;
import lanou.carhometwo.recommended.RecommendedFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    FrameLayout fl_main;
    ImageView imgRecommended;
    ImageView imgBbs;
    ImageView imgChooseCar;
    ImageView imgFound;
    ImageView imgPersonal;
    private FragmentTransaction transaction;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        imgRecommended = (ImageView) findViewById(R.id.img_main_recommended);
        imgBbs = (ImageView) findViewById(R.id.img_main_bbs);
        imgChooseCar = (ImageView) findViewById(R.id.img_main_choosecar);
        imgFound = (ImageView) findViewById(R.id.img_main_found);
        imgPersonal = (ImageView) findViewById(R.id.img_main_personal);
        setClick(this, imgRecommended);
        setClick(this, imgBbs);
        setClick(this, imgChooseCar);
        setClick(this, imgFound);
        setClick(this, imgPersonal);
    }

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, new RecommendedFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_main_recommended:
                transaction.replace(R.id.fl_main, new RecommendedFragment());
                break;
            case R.id.img_main_bbs:
                transaction.replace(R.id.fl_main, new BbsFragment());
                break;
            case R.id.img_main_choosecar:
                transaction.replace(R.id.fl_main, new ChooseCarFragment());
                break;
            case R.id.img_main_found:
                transaction.replace(R.id.fl_main, new FoundFragment());
                break;
            case R.id.img_main_personal:
                transaction.replace(R.id.fl_main, new PersonalFragment());
                break;
        }
    }
}
