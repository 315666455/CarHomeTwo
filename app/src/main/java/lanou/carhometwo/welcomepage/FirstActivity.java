package lanou.carhometwo.welcomepage;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;
import lanou.carhometwo.bean.FirstBean;
import lanou.carhometwo.internet.GsonRequset;
import lanou.carhometwo.internet.VolleySingleton;
import lanou.carhometwo.main.MainActivity;

public class FirstActivity extends BaseActivity {

    String firstUrl = "http://url.cn/40IP3VW";
    private ImageView imgFirst;
    private TextView tvWelcomePage;
    private boolean is = true;

    @Override
    protected int getLayout() {
        return R.layout.activity_first;
    }

    @Override
    protected void initViews() {
        imgFirst = bindView(R.id.img_first);
        tvWelcomePage = bindView(R.id.tv_welcome_page);
        tvWelcomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                is = false;
                finish();
            }
        });
    }


    @Override
    protected void initData() {

        GsonRequset<FirstBean> gsonRequset = new GsonRequset<FirstBean>(FirstBean.class, firstUrl, new Response.Listener<FirstBean>() {
            @Override
            public void onResponse(FirstBean response) {

                String imgUrlFront = response.getResult().getAd().getImgad().getImgurl();

                VolleySingleton.getInstance().getImage(imgUrlFront, imgFirst);
                int showTime = response.getResult().getAd().getShowtime();

                ShowTimeAs showTimeAs = new ShowTimeAs();
                showTimeAs.execute(showTime);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FirstActivity.this, "网络不稳定", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequset);
    }

    class ShowTimeAs extends AsyncTask<Integer, String, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            int showTime = integers[0];
            try {
                Thread.sleep(showTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (is) {
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}