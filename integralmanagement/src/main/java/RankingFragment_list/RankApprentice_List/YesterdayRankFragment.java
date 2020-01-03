package RankingFragment_list.RankApprentice_List;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.RankApprenticeBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.RankApprenticeAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * A simple {@link Fragment} subclass.
 */
public class YesterdayRankFragment extends Fragment {
    private RecyclerView mRv_RankList;
    private ApplicationClass applicationClass;
    private String stringbeginTime,stringendTime;
    private String Authorization = "";
    private int Id;
    private List<RankApprenticeBean.DataBean> rankApprenticeBeans = new ArrayList<>();


    public YesterdayRankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_yesterday_rank, container, false);
        applicationClass = (ApplicationClass) getActivity().getApplication();
        mRv_RankList=view.findViewById(R.id.Rv_ApprenticeRankList_Day);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Authorization = bundle.getString("Authorization");
            Id = bundle.getInt("Id", 0);
            Log.i("IDID", "onCreateView: " + Id);
        }
        initRecyclerviewData();
        initRecyclerview();
        return view;
    }
    private void initRecyclerview() {
        mRv_RankList.setLayoutManager(new LinearLayoutManager(getActivity()));
        RankApprenticeAdapter rankReceiptAdapter=new RankApprenticeAdapter(rankApprenticeBeans);
        mRv_RankList.setAdapter(rankReceiptAdapter);
    }

    private void initRecyclerviewData() {
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date date1=new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date date2=new Date(System.currentTimeMillis());
        stringbeginTime=simpleDateFormat1.format(date1);
        stringendTime=simpleDateFormat2.format(date2);
        Map<String, String> params = new HashMap<String, String>();
        params.put("BeginTime",stringbeginTime);
        params.put("EndTime",stringendTime);
        params.put("RewardType",""+2);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/User/GetParentRankingData?UserId=" + Id)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
                        Toast.makeText(applicationClass, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<RankApprenticeBean>() {
                        }.getType();
                        RankApprenticeBean rankApprenticeBean = GsonUtil.getGsonLower().fromJson(response, t);
                        rankApprenticeBeans.clear();
                        rankApprenticeBeans.addAll(rankApprenticeBean.Data);
                        mRv_RankList.getAdapter().notifyDataSetChanged();
                    }
                });

    }

}
