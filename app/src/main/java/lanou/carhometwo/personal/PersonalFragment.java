package lanou.carhometwo.personal;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.login.LoginActivity;

/**
 * Created by dllo on 16/10/21.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        LinearLayout linearLayoutLogin = bindView(R.id.ll_login);
        linearLayoutLogin.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.personal_fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
