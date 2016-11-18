package lanou.carhometwo.bbs.select;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.LobbyistsBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/11/1.
 */
public class SelectFragment extends lanou.carhometwo.base.BaseFragment {

    private ListView listView;

    @Override
    protected void initData() {

        GsonRequest<LobbyistsBean> gsonRequest = new GsonRequest<LobbyistsBean>(LobbyistsBean.class, URLValues.URL_USECAR, new Response.Listener<LobbyistsBean>() {
            @Override
            public void onResponse(LobbyistsBean response) {
                SelectAdapter adapter = new SelectAdapter(getActivity());
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
        listView = bindView(R.id.lv_select);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.select_lv_head, null);
        listView.addHeaderView(headView);
    }

    @Override
    protected int getLayout() {
        return R.layout.select_fragment;
    }
}
