package lanou.carhometwo.internet;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    public VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyApp.getContext());
        imageLoader = new ImageLoader(requestQueue, new MemoryCache());
    }

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


    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        requestQueue.add(request);
    }

    //请求图片
    public void getImage(String url, ImageView imageView) {
        imageLoader.get(url, imageLoader.getImageListener(imageView, 0, 0));
    }
}