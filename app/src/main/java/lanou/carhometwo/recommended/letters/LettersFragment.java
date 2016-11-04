package lanou.carhometwo.recommended.letters;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.LobbyistsBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class LettersFragment extends BaseFragment {

    private ListView listView;

    @Override
    protected void initData() {

        GsonRequest<LobbyistsBean> gsonRequest = new GsonRequest<LobbyistsBean>(LobbyistsBean.class, URLValues.URL_QUICK, new Response.Listener<LobbyistsBean>() {
            @Override
            public void onResponse(LobbyistsBean response) {

                LettersAdapter adapter = new LettersAdapter(getContext());
                adapter.setLobbyistsBean(response);
                listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest);

    }

    @Override
    protected void initView() {

        listView = bindView(R.id.lv_letters);

    }

    @Override
    protected int getLayout() {
        return R.layout.letters_fragment;
    }
}
