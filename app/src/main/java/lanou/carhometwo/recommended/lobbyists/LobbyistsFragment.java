package lanou.carhometwo.recommended.lobbyists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;
import lanou.carhometwo.bean.LobbyistsBean;
import lanou.carhometwo.internet.GsonRequest;
import lanou.carhometwo.internet.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class LobbyistsFragment extends BaseFragment {

    private ListView lv;
    private static final String key = "lobby";
    private static final String Id = "lobbyist";


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_lobbyists);
    }

    @Override
    protected int getLayout() {
        return R.layout.lobbyists_fragment;
    }


    public static LobbyistsFragment getInstance(int pos, ArrayList<String> arrayList) {
        LobbyistsFragment hotRepeatFragment = new LobbyistsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(key, pos);
        bundle.putStringArrayList(Id, arrayList);
        hotRepeatFragment.setArguments(bundle);
        return hotRepeatFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String urlStr = null;
        int pos = bundle.getInt(key);
        Log.d("LobbyistsFragment", "pos:" + pos);
        ArrayList<String> arrayListId = new ArrayList<>();
        arrayListId = bundle.getStringArrayList(Id);
        urlStr = arrayListId.get(pos);
        GsonRequest<LobbyistsBean> gsonRequest = new GsonRequest<LobbyistsBean>(LobbyistsBean.class, urlStr, new Response.Listener<LobbyistsBean>() {
            @Override
            public void onResponse(LobbyistsBean response) {
                LobbyistsAdapter adapter = new LobbyistsAdapter();
                adapter.setLobbyistsBean(response);
                Log.d("100", "response:" + response);
                lv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
