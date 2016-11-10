package lanou.carhometwo.choosecar.newcar;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.carhometwo.R;
import lanou.carhometwo.bean.NewCarBean;
import lanou.carhometwo.bean.NewCarHotBean;
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
    private View view;
    private RecyclerView recyclerView;
    private View viewHead;

    @Override
    protected void initData() {

        GsonRequest<NewCarHotBean> gsonRequestHot = new GsonRequest<NewCarHotBean>(NewCarHotBean.class, URLValues.HOTBRAND_URL, new Response.Listener<NewCarHotBean>() {
            @Override
            public void onResponse(NewCarHotBean response) {
                NewCarHotAdapter newCarHotAdapter = new NewCarHotAdapter();
                newCarHotAdapter.setNewCarHotBean(response);
                recyclerView.setAdapter(newCarHotAdapter);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);
                recyclerView.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleton.getInstance().addRequest(gsonRequestHot);

        GsonRequest<NewCarBean> gsonRequest = new GsonRequest<NewCarBean>(NewCarBean.class, URLValues.NEWCAR_BRAND_URL, new Response.Listener<NewCarBean>() {
            @Override
            public void onResponse(NewCarBean response) {

                NewCarAdapter adapter = new NewCarAdapter(getActivity());
                NewCarBodyAdapter bodyAdapter = new NewCarBodyAdapter(getActivity());
                adapter.setNewCarBean(response);

                bodyAdapter.setNewCarBean(response);
                stickyListHeadersListView.setAdapter(bodyAdapter);
                listView.setAdapter(adapter);
                listView.addHeaderView(viewHead);
                stickyListHeadersListView.addHeaderView(view);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stickyListHeadersListView.setSelection(i);
            }
        });

        stickyListHeadersListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                listView.smoothScrollToPositionFromTop(i, 0);
            }
        });
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_new_car);
        stickyListHeadersListView = bindView(R.id.sk_new_car);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.new_car_head_view, null);
        recyclerView = bindView(view, R.id.rv_new_car);
        viewHead = LayoutInflater.from(getActivity()).inflate(R.layout.new_car_head_view_null, null);
    }

    @Override
    protected int getLayout() {
        return R.layout.new_car_fragment;
    }
}
