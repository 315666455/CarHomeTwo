package lanou.carhometwo.register;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private EditText editTextPhone;
    private EditText editTextPassword;
    private RelativeLayout relativeLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        editTextPhone = (EditText) findViewById(R.id.et_register_telephone);
        editTextPassword = (EditText) findViewById(R.id.et_register_password);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_register);
        relativeLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_register:

                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(editTextPhone.getText().toString());
                bmobUser.setPassword(editTextPassword.getText().toString());
                bmobUser.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.d("444", e.getMessage());
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
