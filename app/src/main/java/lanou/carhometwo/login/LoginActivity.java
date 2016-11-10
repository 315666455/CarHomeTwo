package lanou.carhometwo.login;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;
import lanou.carhometwo.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        TextView tvRegister = bindView(R.id.tv_register);
        tvRegister.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);


                break;

        }
    }
}
