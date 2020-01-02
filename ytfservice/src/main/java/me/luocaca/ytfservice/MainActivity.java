package me.luocaca.ytfservice;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rebate.commom.util.GsonUtil;
import com.rebate.commom.util.mock.超牛模拟工具;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.luocaca.ytfservice.api.API;
import me.luocaca.ytfservice.entity.login.LoginVo;
import me.luocaca.ytfservice.entity.order.OrderVo;
import me.luocaca.ytfservice.entity.order.OrderVoElements;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.rebate.commom.util.mock.超牛模拟工具.setMyClinet;

/**
 * 易通付
 */
public class MainActivity extends AppCompatActivity {



    public static List<String> orderIdListUploadEd = new ArrayList<>();
    public static List<String> orderIdListPre = new ArrayList<>();

    @BindView(R.id.order_query)
    EditText orderQuery;
    @BindView(R.id.count)
    EditText count;
    @BindView(R.id.count1)
    EditText count1;
    @BindView(R.id.button2)
    Button button2;
    //18659069848
    //90520515


    String token = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);







        count.setText(SettingActivity.getCount(this));
        count1.setText(SettingActivity.getPwd(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                                if (loginVo.getCode() == 200) {
                                    上传数据到后台(loginVo);
                                    button2.setText("已登陆");
                                }
                                {
                                    button2.setText(loginVo.getMsg() + " (点我重新登录)");
                                }
                            }
                        }
                );
            }
        });

        button2.performClick();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                orderQuery.setMovementMethod(ScrollingMovementMethod.getInstance());
                orderQuery.setSelection(orderQuery.getText().length(), orderQuery.getText().length());

                //
            }
        });


        orderQuery.setOnClickListener(v -> {

            if (TextUtils.isEmpty(token)) {
                Toast.makeText(v.getContext(), "请先登录哦。。。", Toast.LENGTH_SHORT).show();
                return;
            }
            超牛模拟工具.执行模拟(new HashMap(),
                    "GET /order/app/query_order?pageNumber=1&pageSize=10&start_payTime=&end_payTime=&payWay=&status=&storeId=&orderNumber= HTTP/1.1\n" +
                            "Content-Type: application/json\n" +
                            "user-agent: HUAWEI MLA-AL10(Android/5.1.1) (com.lingyundata.mchytf/19.09.20) Weex/0.26.0 540x936\n" +
                            "authorized: " + token + "\n" +
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
//
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                                orderQuery.setText();


                            try {
                                OrderVoElements orderVo = GsonUtil.getGson().fromJson(response, OrderVoElements.class);

                                Log.i("query_order", "onResponse: \n" + response);


                                JsonArray asJsonArray = ((JsonObject) orderVo.obj.content).getAsJsonObject().entrySet().iterator().next().getValue().getAsJsonObject().getAsJsonArray("orders");

                                for (JsonElement jsonElement : asJsonArray) {
                                    Log.i("onResponse", "onResponse: " + jsonElement);

                                    OrderVo vo = new OrderVo();
                                    vo.orderNumber = jsonElement.getAsJsonObject().get("orderNumber").getAsString();
                                    vo.id = jsonElement.getAsJsonObject().get("id").getAsString();
                                    vo.actPayPrice = jsonElement.getAsJsonObject().get("actPayPrice").getAsString();
                                    vo.refundPrice = jsonElement.getAsJsonObject().get("refundPrice").getAsString();
                                    vo.payWay = jsonElement.getAsJsonObject().get("payWay").getAsString();
                                    vo.status = jsonElement.getAsJsonObject().get("status").getAsInt();
                                    vo.createTime = jsonElement.getAsJsonObject().get("createTime").getAsString();
                                    vo.payTime = jsonElement.getAsJsonObject().get("payTime").getAsString();
                                    vo.userId = count.getText().toString();
                                    上传订单数据到后台(vo);
                                }

                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
            );
        });


        whiteTrue2Upload();


        getSupportActionBar().setTitle("地址：" + SettingActivity.getHost(this));


        if (SettingActivity.getHost(this).isEmpty()) {
            Toast.makeText(this, "先填写上传域名", Toast.LENGTH_SHORT).show();
            SettingActivity.start(this);
            return;
        }


    }


    public void whiteTrue2Upload() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                whiteTrue2Upload();
                orderQuery.performClick();
            }
        }, 10000);
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


        if (SettingActivity.getHost(this).isEmpty()) {
            Toast.makeText(this, "服务器上传地址没填写哦!", Toast.LENGTH_SHORT).show();
            SettingActivity.start(this);
            return;
        }


        Map map = new HashMap<String, String>();
        map.put("Phone", loginVo.getObj().getMerchantId());
        map.put("Account", count.getText().toString());
        map.put("Cookie", loginVo.getObj().getToken());
        map.put("AppType", "10");
        map.put("Username", loginVo.getObj().getUserId());

        //存储token
        token = loginVo.getObj().getToken();
        SettingActivity.saveToken(this, token);
        SettingActivity.saveCount(MainActivity.this, count.getText().toString());
        SettingActivity.savePwd(MainActivity.this, count1.getText().toString());


        setMyClinet(OkHttpUtils.getInstance());


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


    private void 上传订单数据到后台(OrderVo orderVo) {


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


        if (SettingActivity.getHost(this).isEmpty()) {
            Toast.makeText(this, "服务器上传地址没填写哦!", Toast.LENGTH_SHORT).show();
            SettingActivity.start(this);
            return;
        }


//        if (orderVo.status == 2) {
//            orderIdListUploadEd.add(orderVo.id);
//        } else {
//            //未支付成功的集合。
//            orderIdListPre.add(orderVo.id);
//        }

//        if (orderIdListUploadEd.contains(orderVo.id)) {
//            //如果已经上传。。。
//            Log.i("过滤", "过滤已经上传的id: " + orderVo.id);
//
//
//            return;
//
//        } else {


        if (orderVo.status == 2) {
            //成功的 添加到已支付列表
            if (orderIdListUploadEd.contains(orderVo.id)) {
                return;
            }
        } else {
            //失败的添加到未支付 或者失败列表
            if (orderIdListPre.contains(orderVo.id)) {
                return;
            }
        }


//        if (orderIdListPre.contains(orderVo.id)) {
//            Log.i("过滤", "过滤已经上传的id   待支付订单需要上传一次: " + orderVo.id);
//            if (orderIdListUploadEd.contains(orderVo.id)) {
//                //如果已经上传。。。
//                Log.i("过滤", "过滤已经上传的id 当前待支付的订单 已经被支付了: " + orderVo.id);
//                //已经产生订单 已上传
//
//                return;
//            } else {
//
//                //add
//
//            }
//
//        } else {
//            Log.i("过滤", "执行上传任务: " + orderVo.id);
//
//            //add 添加到待支付
//        }
//
//
//        if (orderIdListUploadEd.contains(orderVo.id)) {
//            return;
//        }


//        }


        OkHttpUtils
                .postString()
                .mediaType(MediaType.parse("application/json"))
                .content(GsonUtil.getGson().toJson(orderVo))
                .url(SettingActivity.getHost(this) + API.upOrderPath)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "上传失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                      Toast.makeText(MainActivity.this, "上传成功" + response, Toast.LENGTH_SHORT).show();
                        /**
                         * {
                         * 	"Type": 500,
                         * 	"Content": "订单已过期",
                         * 	"Data": null
                         * }
                         */


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String type = jsonObject.getString("Type");
                            if (type.equals("200")) {
                            } else if (type.equals("500")) {
                                Log.i("", "后台没有此数据。过滤掉");
                            } else {
                                Log.i("", "onResponse: 失败不进行上传操作");
                                return;
                            }
                        } catch (Exception e) {

                            Toast.makeText(MainActivity.this, "上传失败" + e.getClass(), Toast.LENGTH_SHORT).show();
                        }


                        if (orderVo.status == 2) {
                            orderIdListUploadEd.add(orderVo.id);

                            orderQuery.append("\n\n订单号:" + orderVo.orderNumber + "  [已支付]'");
                            orderQuery.append("\nID:" + orderVo.id);
                            orderQuery.append("\n支付时间:" + orderVo.payTime);
                            orderQuery.append("\n支付金额:" + orderVo.actPayPrice);
                            orderQuery.append("      退款金额::" + orderVo.refundPrice);

                        } else {
                            //未支付成功的集合。
                            orderIdListPre.add(orderVo.id);

                            orderQuery.append("\n\n订单号:" + orderVo.orderNumber + "  [未支付]'");
                            orderQuery.append("\nId:" + orderVo.id);
                            orderQuery.append("\n支付时间:" + orderVo.payTime);
                            orderQuery.append("\n支付金额:" + orderVo.actPayPrice);
                            orderQuery.append("      退款金额:" + orderVo.refundPrice);
                        }


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
        if (id == R.id.Edit_Delete) {

            orderQuery.setText("");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
