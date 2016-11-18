package lanou.carhometwo.main;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;

public class SecondActivity extends BaseActivity {

    private WebView detailsWeb;
    private ImageView returnImg;
    private String url;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {
        detailsWeb = bindView(R.id.activity_details_wab);
        returnImg = bindView(R.id.details_return);

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");

        switch (title) {
            case "论坛":
                url = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t" +
                        id +
                        "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
                break;
        }

        detailsWeb.loadUrl(url);
    }
}