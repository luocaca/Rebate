package Fragment_list;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.integralmanagement.HtmlActivity;
import com.just.integralmanagement.IntegralDetailActivity;
import com.just.integralmanagement.R;
import com.just.integralmanagement.RechargeIntergralActivity;
import com.just.integralmanagement.SpecialActivity;
import com.just.integralmanagement.entity.DetailitemList;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.NoticeDataBean;
import com.just.integralmanagement.entity.exchangeDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
public class HomeFragment extends Fragment {
    private RelativeLayout mRl_save_moeny, mRl_integral_detail, mRl_integral_rank, mRl_apprentice, mRl_join_activity;
    private TextView mTv_UserID, Tv_Integral, mTv_TodayExChange, mTv_HistoryExChange, mTv_textView3;
    private Button mBtn_Recharge;
    private ViewFlipper mTv_Notice;
    private TextView mTv_Notice1, mTv_Notice2, mTv_Notice3, mTv_Notice4;
    private FragmentOnClick onFragmentClick;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<DetailitemList.DataBean> detailitemList = new ArrayList<>();
    private List<exchangeDataBean> exchangeDataBeans = new ArrayList<>();
    private String Authorization = "";
    private int ID;
    private LinearLayout notice1, notice2, notice3, notice4;
    private ApplicationClass applicationClass;
    private List<NoticeDataBean> dataBeans = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 150) {
            initData();
        }
    }

    public interface FragmentOnClick {
        void onClick(View view);
    }

    public FragmentOnClick getOnFragmentClick() {
        return onFragmentClick;
    }

    public void setOnFragmentClick(FragmentOnClick onfragmentclick) {
        this.onFragmentClick = onfragmentclick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        applicationClass = (ApplicationClass) getActivity().getApplication();
        mTv_UserID = view.findViewById(R.id.User);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mTv_UserID.setText(bundle.getString("UserName"));
            Authorization = bundle.getString("Authorization");
            ID = bundle.getInt("Id", 0);
            Log.i("IDID", "onCreateView: " + ID);
        }
        initData();
        try {
            initNotiData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;

        mRl_save_moeny = view.findViewById(R.id.save_moeny);
        mRl_integral_detail = view.findViewById(R.id.Integral_detail);
        mRl_integral_rank = view.findViewById(R.id.Integral_Rank);
        mRl_apprentice = view.findViewById(R.id.Apprentice);
        mTv_Notice = view.findViewById(R.id.Notice);
        mRl_join_activity = view.findViewById(R.id.join_activity);

        mTv_TodayExChange = view.findViewById(R.id.today_exchange);
        mTv_HistoryExChange = view.findViewById(R.id.history_exchange);
        Tv_Integral = view.findViewById(R.id.Integral);
        mTv_textView3 = view.findViewById(R.id.textView3);
        mTv_textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("请联系上级");
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder1.create();
                builder1.show();
            }
        });
        swipeRefreshLayout = view.findViewById(R.id.Swiperefresh);
        mBtn_Recharge = view.findViewById(R.id.Btn_Recharge);
        mBtn_Recharge.setVisibility(View.VISIBLE);
        mRl_apprentice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onFragmentClick != null) {
                    onFragmentClick.onClick(mRl_apprentice);
                }
            }
        });
        mRl_save_moeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onFragmentClick != null) {
                    onFragmentClick.onClick(mRl_save_moeny);
                }
            }
        });
        mRl_integral_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onFragmentClick != null) {
                    onFragmentClick.onClick(mRl_integral_rank);
                }
            }
        });
        mBtn_Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RechargeIntergralActivity.class);
                intent.putExtra("Id", ID);
                Log.i("TAG", "onClick: " + ID);
                intent.putExtra("Authorization", Authorization);
                startActivityForResult(intent, 150);
            }
        });
        mRl_integral_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IntegralDetailActivity.class);
                intent.putExtra("UserName", mTv_UserID.getText().toString());
                intent.putExtra("Id", ID);
                Log.i("TAG", "onClick: " + ID);
                intent.putExtra("Authorization", Authorization);
                startActivity(intent);
            }
        });
        mRl_join_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SpecialActivity.class);
                intent.putExtra("Id", ID);
                intent.putExtra("Authorization", Authorization);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    initData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        mTv_Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private void initFillView() {
        notice1 = (LinearLayout) View.inflate(getActivity(), R.layout.item_detail_bannaer, null);
        notice2 = (LinearLayout) View.inflate(getActivity(), R.layout.item_secondbannaer, null);
        notice3 = (LinearLayout) View.inflate(getActivity(), R.layout.item_thridbannaer, null);
        notice4 = (LinearLayout) View.inflate(getActivity(), R.layout.item_forthbannaer, null);
        switch (dataBeans.get(0).Data.size()) {
            case 1:
                mTv_Notice.addView(notice1);
                break;
            case 2:
                mTv_Notice.addView(notice1);
                mTv_Notice.addView(notice2);
                break;
            case 3:
                mTv_Notice.addView(notice1);
                mTv_Notice.addView(notice2);
                mTv_Notice.addView(notice3);
                break;
            case 4:
                mTv_Notice.addView(notice1);
                mTv_Notice.addView(notice2);
                mTv_Notice.addView(notice3);
                mTv_Notice.addView(notice4);
                break;
        }
        mTv_Notice1 = notice1.findViewById(R.id.FristBannaer);
        mTv_Notice2 = notice2.findViewById(R.id.SecondBannaer);
        mTv_Notice3 = notice3.findViewById(R.id.ThridBannaer);
        mTv_Notice4 = notice4.findViewById(R.id.ForthBannaer);
        if (dataBeans.size() != 0) {
            try {
                initSetText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initClick();
    }


    private void initClick() {
        notice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("HtmlText", "" + dataBeans.get(0).Data.get(0).content);
                intent.putExtra("HtmlTitle", "" + dataBeans.get(0).Data.get(0).NoticeName);
                startActivity(intent);
            }
        });
        notice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("HtmlText", "" + dataBeans.get(0).Data.get(1).content);
                intent.putExtra("HtmlTitle", "" + dataBeans.get(0).Data.get(1).NoticeName);
                startActivity(intent);
            }
        });
        notice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("HtmlText", "" + dataBeans.get(0).Data.get(2).content);
                intent.putExtra("HtmlTitle", "" + dataBeans.get(0).Data.get(2).NoticeName);
                startActivity(intent);
            }
        });
        notice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("HtmlText", "" + dataBeans.get(0).Data.get(3).content);
                intent.putExtra("HtmlTitle", "" + dataBeans.get(0).Data.get(3).NoticeName);
                startActivity(intent);
            }
        });
    }

    private void initSetText() {
        if (dataBeans.get(0).Data.get(0).NoticeName != null) {
            mTv_Notice1.setText(dataBeans.get(0).Data.get(0).NoticeName);
        } else {
            mTv_Notice1.setText("暂无公告");
        }
        if (dataBeans.get(0).Data.get(1).NoticeName != null) {
            notice2.setVisibility(View.VISIBLE);
            mTv_Notice2.setText(dataBeans.get(0).Data.get(1).NoticeName);
        } else {
            notice2.setVisibility(View.GONE);
        }
        if (dataBeans.get(0).Data.get(2).NoticeName != null) {
            notice3.setVisibility(View.VISIBLE);
            mTv_Notice3.setText(dataBeans.get(0).Data.get(2).NoticeName);
        } else {
            notice3.setVisibility(View.GONE);
        }
        if (dataBeans.get(0).Data.get(3).NoticeName != null) {
            notice4.setVisibility(View.VISIBLE);
            mTv_Notice4.setText(dataBeans.get(0).Data.get(3).NoticeName);
        } else {
            notice4.setVisibility(View.GONE);
        }
    }


    private void initNotiData() {
        Map<String, String> params = new HashMap<String, String>();
//        params.put("BeginTime", BeginTime);
//        params.put("EndTime", EndTime);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskNotice/GetTaskNoticeListByApp")
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
                            Log.i("noticeDataBean", "onResponse: " + response);
                            NoticeDataBean noticeDataBean = GsonUtil.getGsonLower().fromJson(response, NoticeDataBean.class);
                            dataBeans.clear();
                            dataBeans.add(noticeDataBean);
                            Log.i("noticeDataBean", "onResponse2: " + dataBeans.size());
                            if(dataBeans.size()!=0){
                                initFillView();
                            }else {

                            }

                        }
                    }
                });

    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
//        params.put("BeginTime", BeginTime);
//        params.put("EndTime", EndTime);
        params.put("UserId", ID + "");
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskIntegralDetail/GetTotalUserAppTasskIntegral")
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
                            exchangeDataBean exchangeDataBean = GsonUtil.getGsonLower().fromJson(response, exchangeDataBean.class);
                            exchangeDataBeans.clear();
                            exchangeDataBeans.add(exchangeDataBean);
                            Tv_Integral.setText(exchangeDataBean.getData().getTotalIntegral() + "");
                            if (exchangeDataBean.getData().getDayTotal() < 0) {
                                mTv_TodayExChange.setText(Math.abs(exchangeDataBean.getData().getDayTotal()) + "");
                            } else {
                                mTv_TodayExChange.setText(exchangeDataBean.getData().getDayTotal() + "");
                            }
                            if (exchangeDataBean.getData().getAllTotal() < 0) {
                                mTv_HistoryExChange.setText(Math.abs(exchangeDataBean.getData().getAllTotal()) + "");
                            } else {
                                mTv_HistoryExChange.setText(exchangeDataBean.getData().getAllTotal() + "");
                            }
                        }
                    }
                });
    }

}