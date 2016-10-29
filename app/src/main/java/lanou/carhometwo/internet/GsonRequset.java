package lanou.carhometwo.internet;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/10/24.
 */

public class GsonRequset<T> extends Request<T> {

    private final Response.Listener<T> mListener;
    private Gson gson;
    Class<T> tClass;

    public GsonRequset(int method, Class<T> tClass, String url, Response.Listener<T> mListener, Response.ErrorListener listener) {
        super(method, url, listener);

        this.mListener = mListener;
        gson = new Gson();
        this.tClass = tClass;
    }

    public GsonRequset(Class<T> tClass, String url, Response.Listener<T> mListener, Response.ErrorListener listener) {
        this(Method.GET, tClass, url, mListener, listener);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        T t = gson.fromJson(parsed, tClass);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
