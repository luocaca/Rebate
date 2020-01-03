package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.IntegralDataBean;

import java.util.List;

public class TodayIntegralAdapter extends RecyclerView.Adapter<TodayIntegralAdapter.ViewHolder> {

    private List<IntegralDataBean.DataBean> integralDetailBeans;
    private Context mContext;



    public TodayIntegralAdapter(List<IntegralDataBean.DataBean> integralDetailBean) {
        super();
        this.integralDetailBeans = integralDetailBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_integral_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayIntegralAdapter.ViewHolder holder, int position) {
        if(integralDetailBeans.get(position).getOrderNo()==null){
            holder.mTv_User_Number.setText("1");
        }else {
            holder.mTv_User_Number.setText(integralDetailBeans.get(position).getOrderNo()+"");
        }
        holder.mTv_Closing_time.setText(integralDetailBeans.get(position).getCreatedTime());
        holder.mTv_Account_and_Email.setText(integralDetailBeans.get(position).getAccount());
        if(integralDetailBeans.get(position).getAppType()==0){
            holder.mTv_Type_Recharge.setText("转转");
        }else if(integralDetailBeans.get(position).getAppType()==1){
            holder.mTv_Type_Recharge.setText("咸鱼");
        } else if(integralDetailBeans.get(position).getAppType()==2){
            holder.mTv_Type_Recharge.setText("卡密");
        }else if(integralDetailBeans.get(position).getAppType()==3){
            holder.mTv_Type_Recharge.setText("淘宝");
        }else if(integralDetailBeans.get(position).getAppType()==8){
            holder.mTv_Type_Recharge.setText("联通");
        }else if(integralDetailBeans.get(position).getAppType()==9){
            holder.mTv_Type_Recharge.setText("其他");
        }

        if(integralDetailBeans.get(position).getIntegralNum()>0){
            holder.mTv_Recharge_Integral.setText("+"+integralDetailBeans.get(position).getIntegralNum());
            holder.mTv_Recharge_Integral.setTextColor(Color.RED);
        }else {
            holder.mTv_Recharge_Integral.setText(integralDetailBeans.get(position).getIntegralNum()+"");
            holder.mTv_Recharge_Integral.setTextColor(Color.BLACK);
        }
        holder.mTv_Remark.setText("("+integralDetailBeans.get(position).getRemark()+")");
        holder.mTv_HisIntegral.setText(integralDetailBeans.get(position).getTotalIntegral()+"");
        holder.mTv_NowIntegral.setText(integralDetailBeans.get(position).getTotalIntegral()+integralDetailBeans.get(position).getUseIntegral()+"");
    }


    @Override
    public int getItemCount() {
        if (integralDetailBeans != null) {
            return integralDetailBeans.size();
        } else {
            return 0;
        }
//        return detailBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_User_Number, mTv_Closing_time, mTv_Account_and_Email, mTv_Account_Name, mTv_Type_Recharge,mTv_Recharge_Integral;
        private  TextView mTv_Remark,mTv_HisIntegral,mTv_NowIntegral;
        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_User_Number = view.findViewById(R.id.User_Number);
            mTv_Closing_time = view.findViewById(R.id.Closing_time);
            mTv_Account_and_Email = view.findViewById(R.id.Account_and_Email);
            mTv_Type_Recharge = view.findViewById(R.id.Type_Recharge);
            mTv_Recharge_Integral = view.findViewById(R.id.Recharge_Integral);
            mTv_Remark=view.findViewById(R.id.Remark);
            mTv_HisIntegral=view.findViewById(R.id.HisIntegral);
            mTv_NowIntegral=view.findViewById(R.id.NowIntegral);
        }
    }
}
