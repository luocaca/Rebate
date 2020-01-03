package com.just.integralmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

public class ModifyDeviceActivity extends AppCompatActivity {
    private EditText mEt_User, mEt_Client_id, mEt_Alias,mEt_Key;
    private Button mBtn_send;
    private Switch mSw_Enabled;
    private TextView mEt_Client_Type;
    private List<ClientTypeBean> clientTypeBeans = new ArrayList<>();
    private List<AddDeviceBean> addDeviceBeans = new ArrayList<>();
    private Boolean IsEnabled;
    private String String1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_device);
        mEt_User = findViewById(R.id.modify_User_id);
        mEt_Client_Type = findViewById(R.id.modify_Client_Type);
        mEt_Client_id = findViewById(R.id.modify_Client_name);
        mBtn_send = findViewById(R.id.send_modify_Device);
        mEt_Alias = findViewById(R.id.modify_Alias);
        mSw_Enabled = findViewById(R.id.modify_IsEnabled);
        mEt_Key =findViewById(R.id.modify_key);
        getSupportActionBar().setTitle("修改设备");
        initClientTypeData();
        ReceiveData();
        setToolbars();
        mBtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_User.getText().toString().isEmpty()) {
                    Toast.makeText(ModifyDeviceActivity.this, "用户ID不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Client_id.getText().toString().isEmpty()) {
                    Toast.makeText(ModifyDeviceActivity.this, "设备ID不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Client_Type.getText().toString().isEmpty()) {
                    Toast.makeText(ModifyDeviceActivity.this, "ClientType不能为空", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyDeviceActivity.this);
                LayoutInflater inflater = LayoutInflater.from(ModifyDeviceActivity.this);
                View view = inflater.inflate(R.layout.diglog_client_type, null);
                RecyclerView recyclerView = view.findViewById(R.id.rv_diglog);
                final AlertDialog dialog = builder.create();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ModifyDeviceActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                ClientTypeAdapter adapter = new ClientTypeAdapter(ModifyDeviceActivity.this, clientTypeBeans);
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
        params.put("IsEnabled", "" + IsEnabled);
        params.put("Id",""+String1);
        params.put("Secret", "" + mEt_Key.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url(getResources().getString(R.string.Host)+"/api/Admin/TaskClient/AddTaskClientIdByApp")
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
                        if (addDeviceBeans.get(0).getMessage().equals("添加成功")||addDeviceBeans.get(0).getMessage().equals("修改成功")) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(ModifyDeviceActivity.this);
                            builder.setTitle("修改成功");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                            builder.create();
                            builder.show();
                        } else if (addDeviceBeans.get(0).getMessage().equals("添加失败")||addDeviceBeans.get(0).getMessage().equals("修改失败")) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(ModifyDeviceActivity.this);
                            builder.setTitle("修改失败");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                            builder.create();
                            builder.show();
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

    private void ReceiveData() {
        Intent intent1 = getIntent();
        String UserId = intent1.getStringExtra("UserId");
        String Id =intent1.getStringExtra("Id");
        String ClientId=intent1.getStringExtra("ClientId");
        int AppType=intent1.getIntExtra("AppType",-1);
        String clienttypename = "";
        if (AppType==0) {
            clienttypename = "转转买家" ;
        } else if (AppType==1) {
            clienttypename = "转转卖家" ;
        } else if (AppType==2) {
            clienttypename = "淘宝卖家" ;
        } else if (AppType==8) {
            clienttypename = "中国联通" ;
        } else if (AppType==9) {
            clienttypename = "买家服务端" ;
        } else if (AppType==3) {
            clienttypename = "其它" ;
        }
        mEt_User.setText(UserId);
        mEt_Client_id.setText(ClientId);
        String1=Id;
        mEt_Client_Type.setText(clienttypename);
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
}
