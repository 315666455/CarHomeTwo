package lanou.carhometwo.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;
import lanou.carhometwo.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private RelativeLayout relativeLayoutLogin;
    private TextView tvRegister;
    private EditText editTextTelephone;
    private EditText editTextPassword;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        relativeLayoutLogin = (RelativeLayout) findViewById(R.id.rv_login);
        editTextTelephone = (EditText) findViewById(R.id.et_login_telephone);
        editTextPassword = (EditText) findViewById(R.id.et_login_password);
        tvRegister = bindView(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        relativeLayoutLogin.setOnClickListener(this);
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
            case R.id.rv_login:
                LoginUser loginUser = new LoginUser();
                loginUser.setUsername(editTextTelephone.getText().toString());
                loginUser.setPassword(editTextPassword.getText().toString());
                loginUser.login(new SaveListener<LoginUser>() {
                    @Override
                    public void done(LoginUser myUser, BmobException e) {
                        if (e == null) {
                            String s = editTextTelephone.getText().toString();
//                            loginSuccessChangeListerner.setText(s);
                            TestBean testBean = new TestBean();
                            testBean.setText(s);
                            EventBus.getDefault().post(testBean);

                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", "登录成功");
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", e.getMessage());
                            Log.d("MainActivity", "登录失败");
                        }
                    }
                });
                break;
        }
    }
}
