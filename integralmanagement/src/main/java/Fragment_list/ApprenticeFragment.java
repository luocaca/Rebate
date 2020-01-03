package Fragment_list;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.ApprenticeRecordActivity;
import com.just.integralmanagement.ApprenticeSloganActivity;
import com.just.integralmanagement.ApprenticeStrategyActivity;
import com.just.integralmanagement.AwardRecordActivity;
import com.just.integralmanagement.BenefitForApprenticeActivity;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.ApprenticeDataBean;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.IntegralQueryData;
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
import okhttp3.MediaType;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApprenticeFragment extends Fragment {
    private IntegralQueryData integralQueryDatas;
    private TextView mTv_seed, mTv_Gone, mTv_CumulativeApprenticeNumber;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView mTv_Copy, mTv_ApprenticeRecord, mTv_CumulativeIntegral, mTv_TodayProfit, mTv_TodayApprenticeNumber;
    private TextView mTv_InvitationCode, mTv_ApprenticeDetail, mTv_Good_For_Apprentice, mTv_Apprentice_Strategy, mTv_Apprentice_Propaganda;
    private Button mBtn_ImmediatelyApprentice,mBtn_CheckDetail;
    private String Authorization = "";
    private int Id;
    private ApplicationClass applicationClass;
    private List<ApprenticeDataBean> apprenticeDataBeans = new ArrayList<>();


    public ApprenticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        applicationClass = (ApplicationClass) getActivity().getApplication();
        mTv_Copy = view.findViewById(R.id.Copy_InvitationCode);
        mBtn_ImmediatelyApprentice = view.findViewById(R.id.immediatelyApprentice);
        mTv_InvitationCode = view.findViewById(R.id.InvitationCode);
        mTv_CumulativeApprenticeNumber = view.findViewById(R.id.CumulativeApprenticeNumber);
        mTv_CumulativeIntegral = view.findViewById(R.id.CumulativeIntegral);
        mTv_TodayProfit = view.findViewById(R.id.TodayProfit);
        mTv_ApprenticeDetail = view.findViewById(R.id.ApprenticeDetail);
        mTv_TodayApprenticeNumber = view.findViewById(R.id.TodayApprenticeNumber);
        mTv_ApprenticeRecord = view.findViewById(R.id.ApprenticeRecord);
        mTv_Good_For_Apprentice = view.findViewById(R.id.Good_For_Apprentice);
        mTv_Apprentice_Strategy = view.findViewById(R.id.Apprentice_Strategy);
        mTv_Apprentice_Propaganda = view.findViewById(R.id.Apprentice_Propaganda);
        mBtn_CheckDetail=view.findViewById(R.id.CheckDetail);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Authorization = bundle.getString("Authorization");
            Id = bundle.getInt("Id", 0);
            Log.i("IDID", "onCreateView: " + Id);
        }
        initData();
//        mTv_Gone=view.findViewById(R.id.Gone);
        //点击复制邀请码
        mTv_Copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, "" + mTv_InvitationCode.getText().toString()));
                if (clipboardManager.hasPrimaryClip()) {
                    clipboardManager.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(getActivity(), "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        mBtn_ImmediatelyApprentice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share_Intent = new Intent();
                share_Intent.setAction(Intent.ACTION_SEND);
                share_Intent.setType("InvitationCode/邀请码");
                share_Intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                share_Intent.putExtra(Intent.EXTRA_TEXT, "推荐您使用一款可以赚钱的app");
                share_Intent = Intent.createChooser(share_Intent, "分享");
                startActivity(share_Intent);
            }
        });
        mTv_ApprenticeRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ApprenticeRecordActivity.class);
                intent.putExtra("Id", Id);
                intent.putExtra("Authorization", Authorization);
                startActivity(intent);
            }
        });
        mTv_ApprenticeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到奖励记录界面
                //奖励界面尚未填写 AwardRecordActivity
                Intent intent = new Intent(getActivity(), AwardRecordActivity.class);
                intent.putExtra("Id", Id);
                intent.putExtra("Authorization", Authorization);
                startActivity(intent);
            }
        });
        mBtn_CheckDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AwardRecordActivity.class);
                intent.putExtra("Id", Id);
                intent.putExtra("Authorization", Authorization);
                startActivity(intent);
            }
        });
        mTv_Good_For_Apprentice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BenefitForApprenticeActivity.class);
                startActivity(intent);
            }
        });
        mTv_Apprentice_Strategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ApprenticeStrategyActivity.class);
                startActivity(intent);
            }
        });
        mTv_Apprentice_Propaganda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ApprenticeSloganActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout = view.findViewById(R.id.SwipereFresh);
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
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/User/GetDistributionIndex?UserId=" + Id)
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
                            Type t = new TypeToken<ApprenticeDataBean>() {
                            }.getType();
                            ApprenticeDataBean apprenticeDataBean = GsonUtil.getGsonLower().fromJson(response, t);
                            apprenticeDataBeans.clear();
                            apprenticeDataBeans.add(apprenticeDataBean);
                            mTv_CumulativeIntegral.setText(apprenticeDataBeans.get(0).getData().getTotalBonusIntegral() + "");
                            mTv_TodayProfit.setText(apprenticeDataBeans.get(0).getData().getTodayIntegral() + "");
                            mTv_CumulativeApprenticeNumber.setText(apprenticeDataBeans.get(0).getData().getTotalCount() + "");
                            mTv_TodayApprenticeNumber.setText(apprenticeDataBeans.get(0).getData().getTodayCount() + "");
                            mTv_InvitationCode.setText(apprenticeDataBeans.get(0).getData().getInvitationCode());
                        }
                    }

                });
    }
}
