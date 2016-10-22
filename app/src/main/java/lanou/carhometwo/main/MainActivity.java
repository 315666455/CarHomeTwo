package lanou.carhometwo.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;
import lanou.carhometwo.bbs.BbsFragment;
import lanou.carhometwo.choosecar.ChooseCarFragment;
import lanou.carhometwo.found.FoundFragment;
import lanou.carhometwo.personal.PersonalFragment;
import lanou.carhometwo.recommended.RecommendedFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    FrameLayout fl_main;
    RadioButton rbRecommended;
    RadioButton rbBbs;
    RadioButton rbChooseCar;
    RadioButton rbFound;
    RadioButton rbPersonal;
    private FragmentTransaction transaction;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        rbRecommended = (RadioButton) findViewById(R.id.rb_main_recommended);
        rbBbs = (RadioButton) findViewById(R.id.rb_main_bbs);
        rbChooseCar = (RadioButton) findViewById(R.id.rb_main_choose_car);
        rbFound = (RadioButton) findViewById(R.id.rb_main_found);
        rbPersonal = (RadioButton) findViewById(R.id.rb_main_personal);
        setClick(this, rbRecommended);
        setClick(this, rbBbs);
        setClick(this, rbChooseCar);
        setClick(this, rbFound);
        setClick(this, rbPersonal);

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, new RecommendedFragment());
        transaction.commit();

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.rb_main_recommended:
                transaction.replace(R.id.fl_main, new RecommendedFragment());
                break;
            case R.id.rb_main_bbs:
                transaction.replace(R.id.fl_main, new BbsFragment());
                break;
            case R.id.rb_main_choose_car:
                transaction.replace(R.id.fl_main, new ChooseCarFragment());
                break;
            case R.id.rb_main_found:
                transaction.replace(R.id.fl_main, new FoundFragment());
                break;
            case R.id.rb_main_personal:
                transaction.replace(R.id.fl_main, new PersonalFragment());
                break;
        }
        transaction.commit();
    }
}
