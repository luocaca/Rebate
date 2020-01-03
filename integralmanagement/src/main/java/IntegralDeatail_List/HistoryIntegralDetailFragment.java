package IntegralDeatail_List;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.IntegralDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Adapter.HistoryIntegralAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryIntegralDetailFragment extends Fragment {
    private RecyclerView mRv_History;
    private List<IntegralDataBean.DataBean> integralDetailBeans = new ArrayList<>();
    private TextView mTv_EndTime, mTv_check, mTv_beginTime;
    private String BeginTime, EndTime;
    private int Id;
    private String Authorization;
    private ApplicationClass applicationClass;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);


    public HistoryIntegralDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_integral_detail, container, false);
        applicationClass= (ApplicationClass) getActivity().getApplication();
        mRv_History = view.findViewById(R.id.rv_HisDetail);
        mTv_EndTime = view.findViewById(R.id.ChooseTime);
        mTv_beginTime = view.findViewById(R.id.beginTime);
        mTv_check = view.findViewById(R.id.Check);
        Bundle bundle1 = this.getArguments();
        if (bundle1 != null) {
            Id = bundle1.getInt("Id");
            Authorization = bundle1.getString("Authorization");
        }
        initRecyclerview();
        mTv_beginTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBeginTime(getActivity(), 0, mTv_beginTime, container);
            }
        });
        mTv_EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDate(getActivity(), 0, mTv_EndTime, calendar);
            }
        });
        mTv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InitData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void initBeginTime(FragmentActivity activity, int themeResId, TextView mTv_beginTime, ViewGroup container) {
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mTv_beginTime.setText(year + "-" + (month + 1) + "-" + day);
            }
        }, calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void InitData() {
        BeginTime = mTv_beginTime.getText().toString() + " 00:00:00";
        EndTime = mTv_EndTime.getText().toString() + " 23:59:59";
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
                            IntegralDataBean integralDetailBean = GsonUtil.getGsonLower().fromJson(response, IntegralDataBean.class);
                            integralDetailBeans.addAll(integralDetailBean.getData());
                            mRv_History.getAdapter().notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initDate(FragmentActivity activity, int themeResId, TextView mTv_EndTime, Calendar calendar) {
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mTv_EndTime.setText(year + "-" + (month + 1) + "-" + day);
            }
        }, calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void initRecyclerview() {
        mRv_History.setLayoutManager(new LinearLayoutManager(getActivity()));
        HistoryIntegralAdapter historyIntegralAdapter = new HistoryIntegralAdapter(integralDetailBeans);
        mRv_History.setAdapter(historyIntegralAdapter);

    }

}
