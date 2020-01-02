package me.luocaca.ytfservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置界面
 */
public class SettingActivity extends AppCompatActivity {


    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.host)
    EditText host;
    @BindView(R.id.save)
    Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        host.setText(getHost(this));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "" + host.getText(), Toast.LENGTH_SHORT).show();

                saveHost(SettingActivity.this, host.getText().toString());
                finish();

            }
        });




    }


    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }


    public static void saveHost(Context mContext, String host) {

        SPUtil.put(mContext, "host", host);
    }


    public static String getHost(Context mContext) {

        return SPUtil.get(mContext, "host", "http://192.168.1.6:7022").toString();

    }

    public static void saveCount(Context mContext, String host) {

        SPUtil.put(mContext, "count", host);
    }


    public static String getCount(Context mContext) {

        return SPUtil.get(mContext, "count", "").toString();

    }

    public static void savePwd(Context mContext, String host) {

        SPUtil.put(mContext, "pwd", host);
    }


    public static String getPwd(Context mContext) {

        return SPUtil.get(mContext, "pwd", "").toString();

    }


    public static void saveToken(Context mContext, String host) {

        SPUtil.put(mContext, "token", host);
    }


    public static String getToken(Context mContext) {

        return SPUtil.get(mContext, "token", "").toString();

    }

}
