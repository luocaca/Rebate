package IntegralDeatail_List;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.IntegralDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.TotalIntegralAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalIntegralDetailFragment extends Fragment {
    private RecyclerView mRv_Total;
    private List<IntegralDataBean.DataBean> integralDetailBeans = new ArrayList<IntegralDataBean.DataBean>();
    private String BeginTime, EndTime;
    private int Id;
    private ApplicationClass applicationClass;
    private String Authorization="";


    public TotalIntegralDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total_integral_detail, container, false);
        mRv_Total = view.findViewById(R.id.rv_TotalDetail);
        applicationClass= (ApplicationClass) getActivity().getApplication();
        Bundle bundle1 = this.getArguments();
        if (bundle1 != null) {
            BeginTime = bundle1.getString("BeginTime");
            EndTime = bundle1.getString("EndTime");
            Id = bundle1.getInt("Id");
            Log.i("TAG", "onCreateView: "+Id);
            Authorization=bundle1.getString("Authorization");
        }
        initRecyclerview();
        initData();
        return view;
    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
//        params.put("BeginTime", BeginTime);
//        params.put("EndTime", EndTime);
        params.put("UserId", Id+"");
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://"+applicationClass.getHost()+"/api/Admin/TaskIntegralDetail/GetAppTasskIntegralReport")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("请求", "onError: " + e);
                        Toast.makeText(applicationClass, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        IntegralDataBean integralDetailBean = GsonUtil.getGsonLower().fromJson(response, IntegralDataBean.class);
                        integralDetailBeans.clear();
                        integralDetailBeans.addAll(integralDetailBean.getData());
                        mRv_Total.getAdapter().notifyDataSetChanged();
                    }
                });
    }



    private void initRecyclerview() {
        mRv_Total.setLayoutManager(new LinearLayoutManager(getActivity()));
        TotalIntegralAdapter totalIntegralAdapter = new TotalIntegralAdapter(integralDetailBeans);
        mRv_Total.setAdapter(totalIntegralAdapter);

    }

}
