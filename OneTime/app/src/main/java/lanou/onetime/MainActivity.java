package lanou.onetime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {
    private boolean first;
    private PopupWindow popupWindow;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            } else {

            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        first = sp.getBoolean("1", true);
        if (!first) {
            handler.sendEmptyMessageDelayed(1, 3000);
        }else {
            popupWindow = creatPop();
            handler.sendEmptyMessageDelayed(2,3000);
        }
first = false;
        SharedPreferences.Editor d = sp.edit();
        d.putBoolean("1",false);


    }

    private PopupWindow creatPop() {
        popupWindow = new PopupWindow();
        View v = LayoutInflater.from(this).inflate(R.layout.item,null);

        popupWindow.setHeight(300);
        popupWindow.setWidth(300);
        popupWindow.setContentView(v);
        popupWindow.setFocusable(true);


        return popupWindow;


    }
}
