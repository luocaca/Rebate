package Fragment_list;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.integralmanagement.AddAccountInformationActivity;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.AccountDetailBean;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.AccountDetailAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveMoenyFragment extends Fragment {
    private RecyclerView mRv_Account_Detail;
    private Button mBtn_add_account_operation;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView mTv_addAccount_operation, mTv_Null_recList;
    private List<AccountDetailBean.DataBean> accountDetailBeans = new ArrayList<>();
    private String Authorization = "";
    private int Id;
    private ApplicationClass applicationClass;

    public SaveMoenyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 80) {
            initData();
        } else if (resultCode == 100) {
            initData();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        applicationClass = (ApplicationClass) getActivity().getApplication();
        mRv_Account_Detail = view.findViewById(R.id.rv_Accoun_Detail);
        mBtn_add_account_operation = view.findViewById(R.id.add_account_operation);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mTv_addAccount_operation = view.findViewById(R.id.addAccount_operation);
        mTv_Null_recList = view.findViewById(R.id.Null_recList);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Id = bundle.getInt("Id", 0);
            Log.i("IDID", "onCreateView: " + Id);
            Authorization = bundle.getString("Authorization");
        }
//        initAccountDetawilBeanData();
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initRecyclerview();

        mTv_addAccount_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAccountInformationActivity.class);
                intent.putExtra("Id", Id);
                intent.putExtra("Authorization", Authorization);
                startActivityForResult(intent, 80);
            }
        });
        mBtn_add_account_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAccountInformationActivity.class);
                intent.putExtra("Id", Id);
                intent.putExtra("Authorization", Authorization);
                startActivityForResult(intent, 80);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }

        });
        return view;
    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
//        params.put("BeginTime", BeginTime);
//        params.put("EndTime", EndTime);
//        params.put("UserId", Id + "");
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskUserAccount/GetTaskUserAccountByApp?UserId=" + Id)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
                        Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                        if (errorDataBean.getType() == 403) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage("" + errorDataBean.getContent());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.create();
                            builder1.show();
                        } else {
                            AccountDetailBean accountDetailBean = GsonUtil.getGsonLower().fromJson(response, AccountDetailBean.class);
                            accountDetailBeans.clear();
                            if (accountDetailBean.Data != null) {
                                accountDetailBeans.addAll(accountDetailBean.Data);
                                mRv_Account_Detail.getAdapter().notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                                if (accountDetailBean.Data.size() == 0) {
                                    mTv_Null_recList.setVisibility(View.VISIBLE);
                                    mRv_Account_Detail.setVisibility(View.GONE);
                                } else {
                                    mRv_Account_Detail.setVisibility(View.VISIBLE);
                                    mTv_Null_recList.setVisibility(View.GONE);
                                }
                            }else {
                                Toast.makeText(applicationClass, "数据解析错误", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }

//    private void initAccountDetawilBeanData() {
//        AccountDetailBean accountDetailBean1 = new AccountDetailBean();
//        accountDetailBean1.setAccount_Number("88568875");
//        accountDetailBean1.setOn_line_type("在线");
//        accountDetailBean1.setPhone_Number("13152528484");
//        accountDetailBean1.setSecret_Key("FF1188151514848");
//        accountDetailBean1.setServer_Type("转转");
//        accountDetailBean1.setUse_Type("停用");
//        accountDetailBean1.setJurisdiction("买、卖");
//        accountDetailBeans.add(accountDetailBean1);
//
//        AccountDetailBean accountDetailBean2 = new AccountDetailBean();
//        accountDetailBean2.setAccount_Number("88568875");
//        accountDetailBean2.setOn_line_type("在线");
//        accountDetailBean2.setPhone_Number("13152528484");
//        accountDetailBean2.setSecret_Key("FF1188151514848");
//        accountDetailBean2.setServer_Type("转转");
//        accountDetailBean2.setUse_Type("停用");
//        accountDetailBean2.setJurisdiction("买、卖");
//        accountDetailBeans.add(accountDetailBean2);
//
//        AccountDetailBean accountDetailBean3 = new AccountDetailBean();
//        accountDetailBean3.setAccount_Number("88568875");
//        accountDetailBean3.setOn_line_type("在线");
//        accountDetailBean3.setPhone_Number("13152528484");
//        accountDetailBean3.setSecret_Key("FF1188151514848");
//        accountDetailBean3.setServer_Type("转转");
//        accountDetailBean3.setUse_Type("停用");
//        accountDetailBean3.setJurisdiction("买、卖");
//        accountDetailBeans.add(accountDetailBean3);
//
//        AccountDetailBean accountDetailBean4 = new AccountDetailBean();
//        accountDetailBean4.setAccount_Number("88568875");
//        accountDetailBean4.setOn_line_type("在线");
//        accountDetailBean4.setPhone_Number("13152528484");
//        accountDetailBean4.setSecret_Key("FF1188151514848");
//        accountDetailBean4.setServer_Type("转转");
//        accountDetailBean4.setUse_Type("停用");
//        accountDetailBean4.setJurisdiction("买、卖");
//        accountDetailBeans.add(accountDetailBean4);
//    }

    private void initRecyclerview() {
        mRv_Account_Detail.setLayoutManager(new LinearLayoutManager(getActivity()));
        AccountDetailAdapter accountDetailAdapter = new AccountDetailAdapter(getActivity(), accountDetailBeans);
        accountDetailAdapter.setId(Id);
        accountDetailAdapter.setAuthorization(Authorization);
        Log.i("Authorization", "initRecyclerview: " + Authorization);
        mRv_Account_Detail.setAdapter(accountDetailAdapter);
    }


}
