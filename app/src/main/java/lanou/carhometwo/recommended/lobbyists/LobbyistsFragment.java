package lanou.carhometwo.recommended.lobbyists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class LobbyistsFragment extends BaseFragment {

    private static final String Key = "lobby";
    private TextView tv;
    private static final String Poss = "pos";
    // TODO: 16/10/31  


    public static LobbyistsFragment getInstance(int pos , ArrayList<Integer> arrayListPos) {
        LobbyistsFragment lobbyistsFragment = new LobbyistsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Key, pos);
        bundle.putIntegerArrayList(Poss,arrayListPos);
        lobbyistsFragment.setArguments(bundle);
        return lobbyistsFragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tv = bindView(R.id.tv);
    }

    @Override
    protected int getLayout() {
        return R.layout.lobbyists_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int ni = bundle.getInt(Key);
        tv.setText(String.valueOf(ni));
    }
}
