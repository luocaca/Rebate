package IntegralDeatail_List;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.IntegralDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.TodayIntegralAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayIntegralDeatilFragment extends Fragment {
    private RecyclerView mRv_Today;
    private List<IntegralDataBean.DataBean> integralDetailBeans = new ArrayList<>();
    private String BeginTime, EndTime;
    private int Id;
    private String Authorization;
    private ApplicationClass applicationClass;

    public TodayIntegralDeatilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_integral_deatil, container, false);
        applicationClass= (ApplicationClass) getActivity().getApplication();
        mRv_Today = view.findViewById(R.id.rv_TodayDetail);
        Bundle bundle1 = this.getArguments();
        if (bundle1 != null) {
            BeginTime = bundle1.getString("BeginTime");
            EndTime = bundle1.getString("EndTime");
            Authorization = bundle1.getString("Authorization");
            Id = bundle1.getInt("Id");
        }
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initRecyclerview();
        return view;
    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("BeginTime", BeginTime);
        params.put("EndTime", EndTime);
        params.put("UserId", Id + "");
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://"+applicationClass.getHost()+"/api/Admin/TaskIntegralDetail/GetAppTasskIntegralReport")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
//                        Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
                        Toast.makeText(applicationClass, "网络异常", Toast.LENGTH_SHORT).show();
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
                            IntegralDataBean integralDetailBean = GsonUtil.getGsonLower().fromJson(response, IntegralDataBean.class);
                            integralDetailBeans.addAll(integralDetailBean.getData());
                            mRv_Today.getAdapter().notifyDataSetChanged();
                        }
                    }
                });
    }


    private void initRecyclerview() {
        mRv_Today.setLayoutManager(new LinearLayoutManager(getActivity()));
        TodayIntegralAdapter todayIntegralAdapter = new TodayIntegralAdapter(integralDetailBeans);
        mRv_Today.setAdapter(todayIntegralAdapter);

    }

}
