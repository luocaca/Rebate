package com.just.integralmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.AddDeviceBean;
import com.just.integralmanagement.entity.ClientTypeBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.ClientTypeAdapter;
import okhttp3.Call;

public class AddDeviceActivity extends AppCompatActivity {
    private EditText mEt_User, mEt_Client_id, mEt_Alias,mEt_Key;
    private Button mBtn_send;
    private Switch mSw_Enabled;
    private ImageView mIv_state;
    private TextView mTv_text, mEt_Client_Type;
    private List<ClientTypeBean> clientTypeBeans = new ArrayList<>();
    private List<AddDeviceBean> addDeviceBeans = new ArrayList<>();
    private Boolean IsEnabled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        mEt_User = findViewById(R.id.User_id);
        mEt_Client_Type = findViewById(R.id.Client_Type);
        mEt_Client_id = findViewById(R.id.Client_name);
        mBtn_send = findViewById(R.id.send_Add_Device);
        mIv_state = findViewById(R.id.add_succece_img);
        mTv_text = findViewById(R.id.add_succece_text);
        mEt_Alias = findViewById(R.id.Alias);
        mEt_Key=findViewById(R.id.key);
        mSw_Enabled = findViewById(R.id.IsEnabled);
        getSupportActionBar().setTitle("添加设备");
        initClientTypeData();
        ReceiveData();
        setToolbars();
        mBtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_User.getText().toString().isEmpty()) {
                    Toast.makeText(AddDeviceActivity.this, "用户ID不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Client_id.getText().toString().isEmpty()) {
                    Toast.makeText(AddDeviceActivity.this, "设备ID不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Client_Type.getText().toString().isEmpty()) {
                    Toast.makeText(AddDeviceActivity.this, "ClientType不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    initData();
                }
            }
        });

        mEt_Client_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDigLog();
            }

            private void initDigLog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDeviceActivity.this);
                LayoutInflater inflater = LayoutInflater.from(AddDeviceActivity.this);
                View view = inflater.inflate(R.layout.diglog_client_type, null);
                RecyclerView recyclerView = view.findViewById(R.id.rv_diglog);
                final AlertDialog dialog = builder.create();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddDeviceActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                ClientTypeAdapter adapter = new ClientTypeAdapter(AddDeviceActivity.this, clientTypeBeans);
                dialog.show();
                dialog.getWindow().setContentView(view);
                adapter.setOnItemClickListener(new ClientTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mEt_Client_Type.setText(clientTypeBeans.get(position).getClientType());
                        dialog.dismiss();
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
        mSw_Enabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    IsEnabled = true;
                } else {
                    IsEnabled = false;
                }
            }
        });
    }

    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);//这两句就可以让actionBar的图标可以响应点击事件
        mActionBar.setDisplayHomeAsUpEnabled(true);//这一句主要用于后面返回效果，后面会讲
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //id不要写错，前面要加android
                onBackPressed();
                break;
        }
        return true;
    }

    private void initClientTypeData() {
        ClientTypeBean clientTypeBean0 = new ClientTypeBean();
        clientTypeBean0.setClientType("转转买家");
        clientTypeBeans.add(clientTypeBean0);
        ClientTypeBean clientTypeBean1 = new ClientTypeBean();
        clientTypeBean1.setClientType("转转卖家");
        clientTypeBeans.add(clientTypeBean1);
        ClientTypeBean clientTypeBean2 = new ClientTypeBean();
        clientTypeBean2.setClientType("淘宝卖家");
        clientTypeBeans.add(clientTypeBean2);
        ClientTypeBean clientTypeBean3 = new ClientTypeBean();
        clientTypeBean3.setClientType("中国联通");
        clientTypeBeans.add(clientTypeBean3);
        ClientTypeBean clientTypeBean4 = new ClientTypeBean();
        clientTypeBean4.setClientType("买家服务端");
        clientTypeBeans.add(clientTypeBean4);
        ClientTypeBean clientTypeBean5 = new ClientTypeBean();
        clientTypeBean5.setClientType("其它");
        clientTypeBeans.add(clientTypeBean5);
    }

    private void ReceiveData() {
        Intent intent = getIntent();
        String UserId = intent.getStringExtra("UserId");
        mEt_User.setText(UserId);
    }

    private void initData() {
        String clienttype = "";
        if (mEt_Client_Type.getText().toString().equals("转转买家")) {
            clienttype = "" + 0;
        } else if (mEt_Client_Type.getText().toString().equals("转转卖家")) {
            clienttype = "" + 1;
        } else if (mEt_Client_Type.getText().toString().equals("淘宝卖家")) {
            clienttype = "" + 2;
        } else if (mEt_Client_Type.getText().toString().equals("中国联通")) {
            clienttype = "" + 8;
        } else if (mEt_Client_Type.getText().toString().equals("买家服务端")) {
            clienttype = "" + 9;
        } else if (mEt_Client_Type.getText().toString().equals("其它")) {
            clienttype = "" + 3;
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("UserId", "1");
        params.put("ClientType", "" + clienttype);
        params.put("ClientId", "" + mEt_Client_id.getText().toString());
        params.put("Alias", "" + mEt_Alias.getText().toString());
        params.put("Secret", "" + mEt_Key.getText().toString());
        params.put("IsEnabled", "" + IsEnabled);

        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://192.168.1.190:5000/api/Admin/TaskClient/AddTaskClientIdByApp")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("积分信息错误日志", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("积分信息日志", "onResponse: " + response);
                        Type t = new TypeToken<AddDeviceBean>() {
                        }.getType();
                        AddDeviceBean addDeviceBean = GsonUtil.getGsonLower().fromJson(response, t);
                        addDeviceBeans.clear();
                        addDeviceBeans.add(addDeviceBean);
                        if (addDeviceBeans.get(0).getMessage().equals("添加成功")) {
                            mIv_state.setImageResource(R.mipmap.buttonsymbol);
                            mTv_text.setText("添加成功");
                        } else if (addDeviceBeans.get(0).getMessage().equals("添加失败")) {
                            mIv_state.setImageResource(R.mipmap.bigwarning);
                            mTv_text.setText("失败成功");
                        }
                    }
                });
    }
}
