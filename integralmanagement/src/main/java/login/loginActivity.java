package login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.MainActivity;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.LoginDataBean;
import com.just.integralmanagement.entity.TokenDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.SPUtil;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * title 用户登录
 */

public class loginActivity extends AppCompatActivity {
    private TextView mTv_Register, mTv_VerificationCode, mTv_ForgetPassWord, mTvSaveAddress;
    private Button mBtn_Login;
    private TextView mTv_setAddress;
    private LinearLayout linearLayout;
    private EditText mEt_Account, mEt_PassWord, mEt_IpAddress;
    private List<LoginDataBean> loginDataBeans = new ArrayList<>();
    private List<TokenDataBean> tokenDataBeans = new ArrayList<>();
    private ApplicationClass applicationClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        applicationClass = (ApplicationClass) getApplication();
        Log.i("applicationClass", applicationClass.getHost());
        mEt_Account = findViewById(R.id.account_number);
        mEt_PassWord = findViewById(R.id.password);
        mBtn_Login = findViewById(R.id.btn_login);
        mTv_Register = findViewById(R.id.register);
        mTv_VerificationCode = findViewById(R.id.VerificationCode);
        mTv_ForgetPassWord = findViewById(R.id.ForgetPassWord);
        mTv_setAddress = findViewById(R.id.setAddress);
        linearLayout = findViewById(R.id.IPAddress);
        mEt_IpAddress = findViewById(R.id.IpAddress);
        mEt_IpAddress.setText(getHost(loginActivity.this));
        mEt_Account.setText(getAccount(loginActivity.this));
        mEt_PassWord.setText(getpassword(loginActivity.this));
        Log.i("applicationClass", "onCreate: "+mEt_IpAddress.getText().toString());
        applicationClass.setHost(mEt_IpAddress.getText().toString());
        Log.i("applicationClass", "onCreate: "+applicationClass.getHost());
        mTvSaveAddress = findViewById(R.id.SaveAddress);
        mTv_setAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearLayout.getVisibility() == View.GONE) {
                    linearLayout.setVisibility(View.VISIBLE);
                    mEt_IpAddress.setText(applicationClass.getHost());
                }

            }
        });
        mTvSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHost(loginActivity.this,mEt_IpAddress.getText().toString());
                applicationClass.setHost(getHost(loginActivity.this));
                Log.i("applicationClass", applicationClass.getHost());
                if(applicationClass.getHost().equals(mEt_IpAddress.getText().toString())){
                    Toast.makeText(loginActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    linearLayout.setVisibility(View.GONE);
                }else {
                    Toast.makeText(loginActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mBtn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InitData();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(applicationClass, "请核对地址", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mTv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(loginActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });
    }


//    public static void main(String[] args){
//        String str="https://h5api.m.taobao.com/h5/mtop.trade.order.create.h5/4.0/?jsv=2.5.6&appKey=12574478&t=1577261986290&sign=a33ca58e9261b9f9c49557ce17d610aa&v=4.0&post=1&type=originaljson&timeout=15000&dataType=json&isSec=1&ecode=1&api=mtop.trade.order.create.h5&ttid=%23t%23ip%23%23_h5_2019&H5Request=true&submitref=acd8e549db7cff3f54abcab0231d9d09";
//        String rule="(^https://h5api.m.taobao.com/h5/mtop.trade.order.create.h5/4.0/\\?jsv)=(.*)&appKey=(.*)&t=(.*)&sign=(.*)&v=(.*)&post=(.*)&type=(.*)&timeout=(.*)&dataType=json&isSec=(.*)&ecode=(.*)&api=mtop.trade.order.create.h5&ttid=(.*)&H5Request=true&submitref=(.*)";
//        Pattern pattern=Pattern.compile(rule);
//        Matcher matcher=pattern.matcher(str);
//        boolean b= matcher.find();
//        System.out.println(b+"\n");
//    }

    public static void saveHost(Context mContext, String host) {

        SPUtil.put(mContext, "host", host);
    }


    public static String getHost(Context mContext) {

        return SPUtil.get(mContext, "host", "").toString();

    }

    public static void saveAccount(Context mContext, String account) {

        SPUtil.put(mContext, "Account", account);
    }


    public static String getAccount(Context mContext) {

        return SPUtil.get(mContext, "Account", "").toString();

    }

    public static void savepassword(Context mContext, String password) {

        SPUtil.put(mContext, "Password", password);
    }


    public static String getpassword(Context mContext) {

        return SPUtil.get(mContext, "Password", "").toString();

    }


    private void InitData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Account", "" + mEt_Account.getText().toString());
        params.put("Password", "" + mEt_PassWord.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://" + applicationClass.getHost() + "/api/identity/jwtoken")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
                        Toast.makeText(loginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<TokenDataBean>() {
                        }.getType();
                        TokenDataBean tokenDataBean = GsonUtil.getGsonLower().fromJson(response, t);
                        tokenDataBeans.clear();
                        tokenDataBeans.add(tokenDataBean);
                        if (tokenDataBeans.get(0).getContent().equals("登录成功")) {
                            initInformationData();
                            saveAccount(loginActivity.this,mEt_Account.getText().toString());
                            savepassword(loginActivity.this,mEt_PassWord.getText().toString());
                        } else {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(loginActivity.this);
                            builder1.setMessage("登录失败");
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.create();
                            builder1.show();
                        }
                    }

                });
    }

    private void initInformationData() {
        Map<String, String> params = new HashMap<String, String>();
//        String sss= Credentials.basic("Authorization",);
        OkHttpUtils.get()
                .url("http://" + applicationClass.getHost() + "/api/identity/Profile")
                .addHeader("Authorization", "Bearer " + tokenDataBeans.get(0).getData())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<LoginDataBean>() {
                        }.getType();
                        LoginDataBean loginDataBean = GsonUtil.getGsonLower().fromJson(response, t);
                        loginDataBeans.clear();
                        loginDataBeans.add(loginDataBean);
                        Intent intent = new Intent(loginActivity.this, MainActivity.class);
                        intent.putExtra("UserName", mEt_Account.getText().toString());
                        intent.putExtra("Id", loginDataBeans.get(0).getId());
                        intent.putExtra("Authorization", tokenDataBeans.get(0).getData());
                        Log.i("IDID", "onResponse: " + loginDataBeans.get(0).getId());
                        startActivity(intent);
                    }
                });


    }


}
