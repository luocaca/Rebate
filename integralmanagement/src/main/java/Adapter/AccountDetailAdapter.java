package Adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.ModifyInformationActivity;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.AccountDetailBean;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.ApplicationClass;
import okhttp3.Call;

public class AccountDetailAdapter extends RecyclerView.Adapter<AccountDetailAdapter.ViewHolder> {

    private List<AccountDetailBean.DataBean> accountDetailBeans;
    private Context mContext;
    private ApplicationClass applicationClass;
    private String string1="";



    public AccountDetailAdapter(Context context, List<AccountDetailBean.DataBean> accountDetailBean) {
        super();
        this.mContext = context;
        this.accountDetailBeans = accountDetailBean;
    }


    private int Id;
    private String Authorization;

    public void setId(int id) {
        Id = id;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account_detail, parent, false);
        applicationClass= (ApplicationClass) mContext.getApplicationContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountDetailAdapter.ViewHolder holder, int position) {
        holder.mTv_Account_Number.setText(accountDetailBeans.get(position).Account);
        holder.mTv_Secret_Key.setText(accountDetailBeans.get(position).Secret);
        holder.mTv_phone_Number.setText(accountDetailBeans.get(position).Phone);
        if (accountDetailBeans.get(position).AppType == 0) {
            holder.mTv_Server_Type.setText("转转");
        } else if (accountDetailBeans.get(position).AppType == 1) {
            holder.mTv_Server_Type.setText("咸鱼");
        } else if (accountDetailBeans.get(position).AppType == 2) {
            holder.mTv_Server_Type.setText("卡密");
        } else if (accountDetailBeans.get(position).AppType == 3) {
            holder.mTv_Server_Type.setText("淘宝");
        } else if (accountDetailBeans.get(position).AppType == 8) {
            holder.mTv_Server_Type.setText("联通");
        } else if (accountDetailBeans.get(position).AppType == 9) {
            holder.mTv_Server_Type.setText("其他");
        }
        if(accountDetailBeans.get(position).BuyOrSeller==null){
            holder.mTv_Jurisdiction.setText("身份不明");
            holder.mTv_BuySynTime.setText("已下线");
            holder.mTv_SellSynTime.setVisibility(View.GONE);
        } else if (accountDetailBeans.get(position).BuyOrSeller.equals("0")) {
            holder.mTv_Jurisdiction.setText("买");
            holder.mTv_Jurisdiction.setTextColor(Color.rgb(37,155,36));
            holder.mTv_BuySynTime.setVisibility(View.VISIBLE);
            holder.mTv_SellSynTime.setVisibility(View.GONE);
            holder.mTv_BuySynTime.setText("买家: "+accountDetailBeans.get(position).BuyLastSynTimeName);
        } else if (accountDetailBeans.get(position).BuyOrSeller.equals("1")) {
            holder.mTv_Jurisdiction.setText("卖");
            holder.mTv_SellSynTime.setVisibility(View.VISIBLE);
            holder.mTv_BuySynTime.setVisibility(View.GONE);
            holder.mTv_SellSynTime.setText("卖家: "+accountDetailBeans.get(position).SellorLastSynTimeName);
        } else if (accountDetailBeans.get(position).BuyOrSeller.equals("0,1")) {
            SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder("买、卖");
            ForegroundColorSpan greenSpan1 = new ForegroundColorSpan(Color.parseColor("#259B24"));
            ForegroundColorSpan greenSpan2 = new ForegroundColorSpan(Color.parseColor("#666666"));
            spannableStringBuilder.setSpan(greenSpan1,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.setSpan(greenSpan2,1,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.mTv_Jurisdiction.setText(spannableStringBuilder);
            holder.mTv_BuySynTime.setVisibility(View.VISIBLE);
            holder.mTv_SellSynTime.setVisibility(View.VISIBLE);
            holder.mTv_BuySynTime.setText("买家: "+accountDetailBeans.get(position).BuyLastSynTimeName);
            holder.mTv_SellSynTime.setText("卖家: "+accountDetailBeans.get(position).SellorLastSynTimeName);
        }

        if (accountDetailBeans.get(position).UpStatus == 0) {
            holder.mTv_Use_Type.setText("开启");
            holder.mTv_Use_Type.setTextColor(Color.rgb(37,155,36));
        } else if (accountDetailBeans.get(position).UpStatus == 1) {
            holder.mTv_Use_Type.setText("停止");
            holder.mTv_Use_Type.setTextColor(Color.RED);
        }
        holder.mIv_modify_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ModifyInformationActivity.class);
                intent.putExtra("Account", accountDetailBeans.get(position).Account);
                intent.putExtra("Secret", accountDetailBeans.get(position).Secret);
                intent.putExtra("Phone", accountDetailBeans.get(position).Phone);
                intent.putExtra("AppType", accountDetailBeans.get(position).AppType);
                intent.putExtra("ClientType", accountDetailBeans.get(position).ClientType);
                intent.putExtra("BuyOrSeller", accountDetailBeans.get(position).BuyOrSeller);
                intent.putExtra("UpStatus", accountDetailBeans.get(position).UpStatus);
                intent.putExtra("PassWord",accountDetailBeans.get(position).Password);
                intent.putExtra("UserId", Id);
                intent.putExtra("Id", accountDetailBeans.get(position).Id);
                intent.putExtra("Authorization", Authorization);
                Log.i("Authorization", "onClick: " + Authorization);
                ((Activity) mContext).startActivityForResult(intent, 100);
            }
        });
        holder.mTv_Copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, "" + accountDetailBeans.get(position).Secret));
                if (clipboardManager.hasPrimaryClip()) {
                    clipboardManager.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mIv_delete_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                builder1.setMessage("确定要删除吗？");
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            initDeleteData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    private void initDeleteData() {
                        Map<String, String> params = new HashMap<String, String>();
                        OkHttpUtils.postString()
                                .content(GsonUtil.getGson().toJson(params))
                                .url("http://"+applicationClass.getHost()+"/api/Admin/TaskUserAccount/DeleteTaskUserAccountByApp?Id=" + accountDetailBeans.get(position).Id)
                                .addHeader("Authorization", "Bearer " + Authorization)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                            ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                                            if (errorDataBean.getType() == 403) {
                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                                                builder1.setMessage("" + errorDataBean.getContent());
                                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                    }
                                                });
                                                builder1.create();
                                                builder1.show();

                                        } else {
                                            accountDetailBeans.remove(position);
                                            notifyItemRemoved(position);
                                            notifyDataSetChanged();
                                        }
                                    }
                                });
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder1.create();
                builder1.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (accountDetailBeans != null) {
            return accountDetailBeans.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_Account_Number, mTv_BuySynTime, mTv_Secret_Key, mTv_Copy, mTv_phone_Number, mTv_Server_Type, mTv_Use_Type, mTv_Jurisdiction,mTv_SellSynTime;
        private ImageView mIv_modify_information, mIv_delete_information;

        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_Account_Number = view.findViewById(R.id.Account_Number);
            mTv_BuySynTime = view.findViewById(R.id.BuySynTime);
            mTv_SellSynTime=view.findViewById(R.id.SellSynTime);
            mTv_Secret_Key = view.findViewById(R.id.Secret_Key);
            mTv_Copy = view.findViewById(R.id.Copy);
            mTv_phone_Number = view.findViewById(R.id.phone_Number);
            mTv_Server_Type = view.findViewById(R.id.Server_Type);
            mTv_Use_Type = view.findViewById(R.id.Use_Type);
            mTv_Jurisdiction = view.findViewById(R.id.Jurisdiction);
            mIv_modify_information = view.findViewById(R.id.modify_information);
            mIv_delete_information = view.findViewById(R.id.delete_information);
        }
    }
}