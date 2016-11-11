package lanou.carhometwo.personal;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.login.LoginActivity;
import lanou.carhometwo.login.TestBean;

/**
 * Created by dllo on 16/10/21.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout linearLayoutThirdParty;
    private TextView tvTelephoneLogin;
    private ImageView ivTelephoneLogin;

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        linearLayoutThirdParty = bindView(R.id.ll_third_party_login);
        LinearLayout linearLayoutLogin = bindView(R.id.ll_login);
        linearLayoutLogin.setOnClickListener(this);
        tvTelephoneLogin = bindView(R.id.tv_telephone_login);
        ivTelephoneLogin = bindView(R.id.iv_telephone_login);
    }

    @Override
    protected int getLayout() {
        return R.layout.personal_fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Subscribe
    public void getTextEvent(TestBean event) {
        String s = event.getText();
        Log.d("PersonalFragment", s);
        tvTelephoneLogin.setText(s);
        ivTelephoneLogin.setImageResource(R.mipmap.didala_meitu_2);
        linearLayoutThirdParty.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().unregister(LoginActivity.class);

    }
}
