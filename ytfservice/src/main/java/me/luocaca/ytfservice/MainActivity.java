package me.luocaca.ytfservice;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rebate.commom.util.GsonUtil;
import com.rebate.commom.util.mock.超牛模拟工具;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.luocaca.ytfservice.api.API;
import me.luocaca.ytfservice.entity.login.LoginVo;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 易通付
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.order_query)
    TextView orderQuery;
    @BindView(R.id.count)
    EditText count;
    @BindView(R.id.count1)
    EditText count1;
    @BindView(R.id.button2)
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.performClick();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                超牛模拟工具.执行模拟(new HashMap(),
                        "POST /merchant/admin/app/login HTTP/1.1\n" +
                                "Content-Type: application/x-www-form-urlencoded\n" +
                                "user-agent: HUAWEI MLA-AL10(Android/5.1.1) (com.lingyundata.mchytf/19.09.20) Weex/0.26.0 540x936\n" +
                                "Host: ytf.lingyundata.com\n" +
                                "Connection: Keep-Alive\n" +
                                "Accept-Encoding: gzip\n" +
                                "Content-Length: 38\n" +
                                "\n" +
                                "username=" + count.getText().toString() + "&password=" + count1.getText().toString() + "",
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("hello", "" + response);
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();


                                LoginVo loginVo = GsonUtil.getGson().fromJson(response, LoginVo.class);


//                                上传数据到后台(loginVo);


                            }
                        }
                );
            }
        });


        orderQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                超牛模拟工具.执行模拟(new HashMap(),
                        "GET /order/app/query_order?pageNumber=1&pageSize=10&start_payTime=&end_payTime=&payWay=&status=&storeId=&orderNumber= HTTP/1.1\n" +
                                "Content-Type: application/json\n" +
                                "user-agent: HUAWEI MLA-AL10(Android/5.1.1) (com.lingyundata.mchytf/19.09.20) Weex/0.26.0 540x936\n" +
                                "authorized: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTg0ODA3NTM1MDEzOTgyMjA4Iiwic2VydmljZVByb3ZpZGVySWQiOiIxMTc0MTQwNDQxMzE3MzkyMzg0IiwibWVyY2hhbnRJZCI6IjExODQ4MDc1MzQ2MTU1MjMzMjgiLCJpc3MiOiJtZXJjaGFudCIsImV4cCI6MTU3MzI2NjQ3OX0.5xbYasev6bisPhrCyu6CbPdaMJTQNmzsKmZ-IL7F4UESjjThfkoQhzsqVbZYmal1yFSYXWl9P7dOH5M2x2MUow\n" +
                                "Host: ytf.lingyundata.com\n" +
                                "Connection: Keep-Alive\n" +
                                "Accept-Encoding: gzip",
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                Log.i("hello", "" + response);
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                orderQuery.setText(response);
                            }
                        }
                );
            }
        });
    }

    /**
     * @param loginVo
     */
    private void 上传数据到后台(LoginVo loginVo) {

        /**
         * Account：用户Id,
         * Phone: merchantId
         *
         *
         *  merchantId": "1184807534615523328",
         *  userType": "1",
         *  userId": "1184807535013982208",
         *  token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTg0ODA3NTM1MDEzOTgyMjA4Iiwic2VydmljZVByb3ZpZGVySWQiOiIxMT
         */


        Map map = new HashMap<String, String>();
        map.put("Phone", loginVo.getObj().getMerchantId());
        map.put("Account", count.getText().toString());
        map.put("Cookie", loginVo.getObj().getToken());
        map.put("AppType", "10");
        map.put("Username", loginVo.getObj().getUserId());
        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(GsonUtil.getGson().toJson(map))
                .url(SettingActivity.getHost(this) + API.uploadPath)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "上传失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(MainActivity.this, "上传成功" + response, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            SettingActivity.start(this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
