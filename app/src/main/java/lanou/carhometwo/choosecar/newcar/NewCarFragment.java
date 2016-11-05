package lanou.carhometwo.choosecar.newcar;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.URLValues;
import lanou.carhometwo.internet.VolleySingleton;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by dllo on 16/11/5.
 */
public class NewCarFragment extends lanou.carhometwo.base.BaseFragment {

    private ListView listView;
    private StickyListHeadersListView stickyListHeadersListView;

    @Override
    protected void initData() {

        GsonRequest<NewCarBean> gsonRequest = new GsonRequest<NewCarBean>(NewCarBean.class, URLValues.NEWCAR_BRAND_URL, new Response.Listener<NewCarBean>() {
            @Override
            public void onResponse(NewCarBean response) {
                NewCarAdapter adapter = new NewCarAdapter(getActivity());
                adapter.setNewCarBean(response);
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
        listView = bindView(R.id.lv_new_car);
        stickyListHeadersListView = bindView(R.id.sk_new_car);

    }

    @Override
    protected int getLayout() {
        return R.layout.new_car_fragment;
    }
}
