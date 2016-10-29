package lanou.carhometwo.internet;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lanou.carhometwo.R;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private LiteOrm orm;
    private Executor executor;
    private Handler handler;

    //
    public VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyApp.getContext());
        imageLoader = new ImageLoader(requestQueue, new MemoryCache());
        orm = LiteOrm.newCascadeInstance(MyApp.getContext(), "carHome.db");
        int cpuMore = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(cpuMore + 1);
        handler = new Handler(Looper.getMainLooper());
    }

    //插入数据库
    public <T> void liteInsert(final List<T> T) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                orm.insert(T);
            }
        }).start();
    }

    //查询数据库
    public <T> void liteQueryDate(OnQueryListenerAll<T> onQueryListenerAll, Class<T> tClass) {
        executor.execute(new QueryRunnable<T>(onQueryListenerAll, tClass));
    }

    //将插入数据库操作转给子线程
    class QueryRunnable<T> implements Runnable {

        private OnQueryListenerAll<T> onQueryListenerAll;
        private Class<T> tClass;

        public QueryRunnable(OnQueryListenerAll<T> onQueryListenerAll, Class<T> tClass) {
            this.onQueryListenerAll = onQueryListenerAll;
            this.tClass = tClass;
        }

        @Override
        public void run() {
            ArrayList<T> query = orm.query(tClass);
            handler.post(new CallBackRunnable<>(onQueryListenerAll, query));
        }
    }

    //线程锁
    public static VolleySingleton getInstance() {
        if (volleySingleton == null) {
            synchronized (VolleySingleton.class) {
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    //将查询出来的数据创给子线程
    class CallBackRunnable<T> implements Runnable {
        private OnQueryListenerAll<T> onQueryListenerAll;
        List<T> tList;

        public CallBackRunnable(OnQueryListenerAll<T> onQueryListenerAll, List<T> tList) {
            this.onQueryListenerAll = onQueryListenerAll;
            this.tList = tList;
        }

        @Override
        public void run() {
            onQueryListenerAll.onQuery(tList);
        }
    }


    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        requestQueue.add(request);
    }

    //请求图片
    public void getImage(String url, ImageView imageView) {
        imageLoader.get(url, imageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }

    //创建接口
    public interface OnQueryListenerAll<T> {
        void onQuery(List<T> T);
    }
}

