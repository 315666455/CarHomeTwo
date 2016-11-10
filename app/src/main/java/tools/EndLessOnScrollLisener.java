package tools;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by dllo on 16/10/31.
 */
public abstract class EndLessOnScrollLisener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;
    private int curentPage = 0;
    private int totalTtemCount;
    private int previousTotal;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading;

    public EndLessOnScrollLisener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalTtemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalTtemCount > previousTotal) {

                loading = false;
                previousTotal = totalTtemCount;
            }
        }
        if (!loading && totalTtemCount - visibleItemCount <= firstVisibleItem) {
            curentPage++;
            onLoadMore(curentPage);
            loading = true;
        }
    }

    protected abstract void onLoadMore(int curentPage);

}
