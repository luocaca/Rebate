package login;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.RegisterDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.ApplicationClass;
import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity {
    private TextView mTv_login;
    private EditText mEt_User_Account, mEt_User_PassWord, mEt_ConfirmPassword, mEt_InvitationCode;
    private Button mBtn_Register;
    private ApplicationClass applicationClass;
    private List<RegisterDataBean> registerDataBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        applicationClass = (ApplicationClass) getApplication();
        Log.i("applicationClass", "onCreate: " + applicationClass.getHost());
        mTv_login = findViewById(R.id.login);
        mBtn_Register = findViewById(R.id.btn_register);
        mEt_User_Account = findViewById(R.id.User_name);
        mEt_User_PassWord = findViewById(R.id.Password);
        mEt_ConfirmPassword = findViewById(R.id.ConfirmPassword);
        mEt_InvitationCode = findViewById(R.id.InvitationCode);
        mBtn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
        mTv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("UserName", "" + mEt_User_Account.getText().toString());
        params.put("Password", "" + mEt_User_PassWord.getText().toString());
        params.put("ConfirmPassword", "" + mEt_ConfirmPassword.getText().toString());
        params.put("InvitationCode", "" + mEt_InvitationCode.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://" + applicationClass.getHost() + "/api/Admin/User/AddUserLoginAccountByApp")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
                        Toast.makeText(RegisterActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<RegisterDataBean>() {
                        }.getType();
                        RegisterDataBean registerDataBean = GsonUtil.getGsonLower().fromJson(response, t);
                        registerDataBeans.clear();
                        registerDataBeans.add(registerDataBean);
                        if (registerDataBeans.get(0).getResultType() != 3) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
                            builder1.setMessage("" + registerDataBeans.get(0).getMessage());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.create();
                            builder1.show();
                        } else if (registerDataBeans.get(0).getResultType() == 3) {
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(RegisterActivity.this);
                            builder3.setMessage("" + registerDataBeans.get(0).getMessage());
                            builder3.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                                    startActivity(intent);
                                }
                            });
                            builder3.create();
                            builder3.show();
                        }
                    }
                });

    }
}
