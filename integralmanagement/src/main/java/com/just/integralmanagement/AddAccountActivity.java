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

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.AccountAppTypeBean;
import com.just.integralmanagement.entity.AddAccountBean;
import com.just.integralmanagement.entity.TokenDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.AccounApptypeAdapter;
import okhttp3.Call;

public class AddAccountActivity extends AppCompatActivity {
    private EditText mEt_UserID, mEt_ClientID, mEt_AccountID, mEt_UserName, mEt_Phone, mEt_AppType,mEt_PassWord,mEt_Latitude,mEt_Longitude;
    private Button mBtn_AddAccount;
    private TextView  mEt_AppTypeName;
    private Switch mSc_ClientType;
    private int intType;
    private List<AccountAppTypeBean> accountAppTypeBeans = new ArrayList<>();
    private List<AddAccountBean> addAccountBeans = new ArrayList<>();
    private List<TokenDataBean>tokenDataBeans=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        mEt_UserID = findViewById(R.id.UserID);
        mEt_UserName = findViewById(R.id.UserName);
        mEt_ClientID = findViewById(R.id.ClientID);
        mEt_AccountID = findViewById(R.id.AccountID);
        mEt_Phone = findViewById(R.id.Phone);
        mSc_ClientType=findViewById(R.id.ClientType);
        mEt_AppTypeName = findViewById(R.id.AppTypeName);
        mEt_AppType = findViewById(R.id.AppType);
        mEt_PassWord=findViewById(R.id.Password);
        mEt_Latitude= findViewById(R.id.latitude);
        mEt_Longitude=findViewById(R.id.longitude);
        mBtn_AddAccount = findViewById(R.id.addAccount);
        getSupportActionBar().setTitle("添加账号");
        initAccountData();
        initReceiveData();
        setToolbars();
        mBtn_AddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEt_UserID.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "UserID不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_ClientID.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "ClientID不能为空", Toast.LENGTH_SHORT).show();
                } else if(mEt_AccountID.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "AccountID不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_UserName.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "UserName不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_Phone.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "Phone不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_AppTypeName.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "AppTypeName不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_AppType.getText().toString().isEmpty()){
                    Toast.makeText(AddAccountActivity.this, "AppType不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    initData();
                }
            }
        });
        mEt_AppTypeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDiglog();
            }
        });
       mSc_ClientType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(b){
                   intType=1;
               }else {
                    intType=0;
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

    private void initDiglog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
        LayoutInflater inflater = LayoutInflater.from(AddAccountActivity.this);
        View view = inflater.inflate(R.layout.diglog_account_layout, null);
        RecyclerView recyclerView = view.findViewById(R.id.rv_diglog_Account);
        final AlertDialog dialog = builder.create();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddAccountActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        AccounApptypeAdapter adapter = new AccounApptypeAdapter(AddAccountActivity.this, accountAppTypeBeans);
        dialog.show();
        dialog.getWindow().setContentView(view);
        adapter.setOnItemClickListener(new AccounApptypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mEt_AppTypeName.setText(accountAppTypeBeans.get(position).getAccountAppTypeName());
                String accountType = "";
                if (mEt_AppTypeName.getText().toString().equals("转转")) {
                    accountType = "" + 0;
                } else if (mEt_AppTypeName.getText().toString().equals("咸鱼")) {
                    accountType = "" + 1;
                } else if (mEt_AppTypeName.getText().toString().equals("卡密")) {
                    accountType = "" + 2;
                } else if (mEt_AppTypeName.getText().toString().equals("淘宝")) {
                    accountType = "" + 3;
                } else if (mEt_AppTypeName.getText().toString().equals("联通")) {
                    accountType = "" + 8;
                } else if (mEt_AppTypeName.getText().toString().equals("其他")) {
                    accountType = "" + 9;
                }
                mEt_AppType.setText(accountType);
                dialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        String accountType = "";
        if (mEt_AppTypeName.getText().toString().equals("转转")) {
            accountType = "" + 0;
        } else if (mEt_AppTypeName.getText().toString().equals("咸鱼")) {
            accountType = "" + 1;
        } else if (mEt_AppTypeName.getText().toString().equals("卡密")) {
            accountType = "" + 2;
        } else if (mEt_AppTypeName.getText().toString().equals("淘宝")) {
            accountType = "" + 3;
        } else if (mEt_AppTypeName.getText().toString().equals("联通")) {
            accountType = "" + 8;
        } else if (mEt_AppTypeName.getText().toString().equals("其他")) {
            accountType = "" + 9;
        }
        mEt_AppType.setText(accountType);
        Map<String, String> params = new HashMap<String, String>();
        params.put("UserId", "" + mEt_UserID.getText().toString());
        params.put("Account", "" + mEt_AccountID.getText().toString());
        params.put("ClientId", "" + mEt_ClientID.getText().toString());
        params.put("Username", "" + mEt_UserName.getText().toString());
        params.put("AppType", "" + mEt_AppType.getText().toString());
        params.put("AppTypeName", "" + mEt_AppTypeName.getText().toString());
        params.put("Phone", "" + mEt_Phone.getText().toString());
        params.put("ClientType",""+intType);
        params.put("Longitude",""+mEt_Longitude.getText().toString());
        params.put("Latitude",""+mEt_Latitude.getText().toString());
        params.put("Password",""+mEt_PassWord.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + tokenDataBeans.get(0).getData())
                .url(getResources().getString(R.string.Host)+"/api/Admin/TaskUserAccount/AddTaskUserAccountByApp")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("积分信息错误日志", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("积分信息日志", "onResponse: " + response);
                        Type t = new TypeToken<AddAccountBean>() {
                        }.getType();
                        AddAccountBean addDeviceBean = GsonUtil.getGsonLower().fromJson(response, t);
                        addAccountBeans.clear();
                        addAccountBeans.add(addDeviceBean);
                        if (addAccountBeans.get(0).getMessage().equals("添加成功")) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(AddAccountActivity.this);
                            builder.setTitle("添加成功");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                            builder.create();
                            builder.show();
                        } else if (addAccountBeans.get(0).getMessage().equals("添加失败")) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(AddAccountActivity.this);
                            builder.setTitle("添加失败");
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

    private void initReceiveData() {
        Intent intent = getIntent();
        String UserId = intent.getStringExtra("UserId");
        String ClientId = intent.getStringExtra("ClientId");
        mEt_UserID.setText(UserId);
        mEt_ClientID.setText(ClientId);
    }

    private void initAccountData() {
        AccountAppTypeBean accountAppTypeBean0 = new AccountAppTypeBean();
        accountAppTypeBean0.setAccountAppTypeName("转转");
        accountAppTypeBeans.add(accountAppTypeBean0);
        AccountAppTypeBean accountAppTypeBean1 = new AccountAppTypeBean();
        accountAppTypeBean1.setAccountAppTypeName("咸鱼");
        accountAppTypeBeans.add(accountAppTypeBean1);
        AccountAppTypeBean accountAppTypeBean2 = new AccountAppTypeBean();
        accountAppTypeBean2.setAccountAppTypeName("淘宝");
        accountAppTypeBeans.add(accountAppTypeBean2);
        AccountAppTypeBean accountAppTypeBean3 = new AccountAppTypeBean();
        accountAppTypeBean3.setAccountAppTypeName("卡密");
        accountAppTypeBeans.add(accountAppTypeBean3);
        AccountAppTypeBean accountAppTypeBean4 = new AccountAppTypeBean();
        accountAppTypeBean4.setAccountAppTypeName("联通");
        accountAppTypeBeans.add(accountAppTypeBean4);
        AccountAppTypeBean accountAppTypeBean5 = new AccountAppTypeBean();
        accountAppTypeBean5.setAccountAppTypeName("其他");
        accountAppTypeBeans.add(accountAppTypeBean5);
    }
}
