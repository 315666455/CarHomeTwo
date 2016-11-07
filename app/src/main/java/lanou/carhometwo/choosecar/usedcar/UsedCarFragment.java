package lanou.carhometwo.choosecar.usedcar;

import android.webkit.WebView;

import lanou.carhometwo.R;
import lanou.carhometwo.internet.URLValues;

/**
 * Created by dllo on 16/11/5.
 */
public class UsedCarFragment extends lanou.carhometwo.base.BaseFragment {

    private WebView webView;

    @Override
    protected void initData() {
        webView.loadUrl(URLValues.OLDCAR_DL_URL);
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.wv_used_car);
    }

    @Override
    protected int getLayout() {
        return R.layout.used_car;
    }
}
