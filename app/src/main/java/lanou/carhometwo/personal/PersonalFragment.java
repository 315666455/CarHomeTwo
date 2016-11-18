package lanou.carhometwo.personal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.internet.VolleySingleton;
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
        LinearLayout ll = bindView(R.id.ll_qq);
        ll.setOnClickListener(this);
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

            case R.id.ll_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        String name = platformDb.getUserName();
                        String icon = platformDb.getUserIcon();

                        tvTelephoneLogin.setText(name);

                        VolleySingleton.getInstance().getImage(icon,ivTelephoneLogin);

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
        }
    }

    @Subscribe
    public void getTextEvent(TestBean event) {
        String s = event.getText();
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
