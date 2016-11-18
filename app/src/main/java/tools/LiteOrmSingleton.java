package tools;

import android.os.Handler;
import android.os.Looper;

import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lanou.carhometwo.base.MyApp;
import lanou.carhometwo.bean.MoreBean;
import lanou.carhometwo.search.SearchCarNameBean;

/**
 * Created by dllo on 16/11/8.
 */
public class LiteOrmSingleton {

    private static LiteOrmSingleton base = new LiteOrmSingleton();
    private LiteOrm mLiteOrm;
    private Handler mHandler;
    private Executor mExecutorPool;

    private LiteOrmSingleton() {
        mLiteOrm = LiteOrm.newCascadeInstance(MyApp.getContext(), "carHome.db");
        mHandler = new Handler(Looper.getMainLooper());
        int coreNum = Runtime.getRuntime().availableProcessors();
        mExecutorPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    public static LiteOrmSingleton getIntstance() {
        return base;
    }

    public <T> void insertData(final List<T> t) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(t);
            }
        }).start();
    }

    public void deleteAllData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(SearchCarNameBean.class);

            }
        }).start();
    }

    public void deleteMoreData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(MoreBean.class);

            }
        }).start();
    }


    public void upDate(ArrayList<MoreBean> arrayList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.update(MoreBean.class);
            }
        }).start();
    }


    public <T> void queryAllData(OnQueryListenerAll<T> monQueryListener, Class<T> tClass) {
        mExecutorPool.execute(new QueryRunnable(monQueryListener, tClass));
    }

    class CallBackRunnable<T> implements Runnable {

        OnQueryListenerAll<T> mOnQueryListener;
        List<T> tList;

        public CallBackRunnable(OnQueryListenerAll<T> mOnQueryListener, List<T> tList) {
            this.mOnQueryListener = mOnQueryListener;
            this.tList = tList;
        }

        @Override
        public void run() {
            mOnQueryListener.onQuery(tList);
        }
    }

    class QueryRunnable<T> implements Runnable {
        private OnQueryListenerAll<T> mOnQueryListener;
        private Class<T> tClass;

        public QueryRunnable(OnQueryListenerAll<T> mOnQueryListener, Class<T> tClass) {
            this.mOnQueryListener = mOnQueryListener;
            this.tClass = tClass;
        }

        @Override
        public void run() {
            ArrayList<T> query = mLiteOrm.query(tClass);
            mHandler.post(new CallBackRunnable(mOnQueryListener, query));
        }
    }

    public interface OnQueryListenerAll<T> {
        void onQuery(List<T> tList);
    }
}
