package lanou.carhometwo.more;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lanou.carhometwo.R;
import lanou.carhometwo.base.BaseActivity;
import lanou.carhometwo.bean.MoreBean;
import tools.LiteOrmSingleton;

public class MoreActivity extends BaseActivity {

    private RecyclerView recyclerView;
    ArrayList<MoreBean> arrayList;
    private MoreAdapter moreAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_more;
    }

    @Override
    protected void initViews() {
        recyclerView = bindView(R.id.rv_more);
    }

    @Override
    protected void initData() {
        moreAdapter = new MoreAdapter(this);

        LiteOrmSingleton.getIntstance().queryAllData(new LiteOrmSingleton.OnQueryListenerAll<MoreBean>() {
            @Override
            public void onQuery(List<MoreBean> moreBeen) {
                Log.d("popopo", "moreBeen:" + moreBeen);
                arrayList = (ArrayList<MoreBean>) moreBeen;
                moreAdapter.setArrayList(arrayList);
            }
        }, MoreBean.class);

        ItemTouchHelper.Callback callback = mCallback();
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(moreAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
    }

    private ItemTouchHelper.Callback mCallback() {
        return new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof GridLayoutManager) {
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0);
                } else {
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                moreAdapter.move(from, to);
                return false;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    View view = viewHolder.itemView;
                    view.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }
        };
    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        TestBean testBean = new TestBean();
////        EventBus.getDefault().post(testBean);
//        return super.onKeyDown(keyCode, event);
//    }
}
