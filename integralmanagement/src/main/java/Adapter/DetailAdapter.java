package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.DetailBean;
import com.just.integralmanagement.entity.DetailitemList;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<DetailitemList.DataBean> DetailitemList;
    private List<DetailBean> detailBeans;
    private Context mContext;


    public DetailAdapter(List<DetailitemList.DataBean> detailitemList) {
        super();
        this.DetailitemList = detailitemList;
    }
//    public DetailAdapter(List<DetailBean> detailBean) {
//        super();
//        this.detailBeans = detailBean;
//    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detaill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(DetailitemList.get(position).getIntegralNum()>0){
            holder.mTv_moeny.setText("+"+DetailitemList.get(position).getIntegralNum());
        }else {
            holder.mTv_moeny.setText(DetailitemList.get(position).getIntegralNum()+"");
            holder.mTv_moeny.setTextColor(Color.rgb(21,156,134));
        }
        holder.mTv_time.setText(DetailitemList.get(position).getCreatedTime());
        holder.mTv_use_account.setText("账号: "+DetailitemList.get(position).getAccount());
        holder.mTv_use_device.setText("设备: "+DetailitemList.get(position).getClientId());
        if(DetailitemList.get(position).getIntegralNum()>0){
            holder.mUser_Linear.setVisibility(View.GONE);
            holder.mTv_purpose.setText("积分充值");
        }else {
            holder.mUser_Linear.setVisibility(View.VISIBLE);
            holder.mTv_purpose.setText(DetailitemList.get(position).getUser().getUserTypeName());
        }
//        if(detailBeans.get(position).getIntegralNum()>0){
//            holder.mTv_moeny.setText("+"+detailBeans.get(position).getIntegralNum());
//        }else {
//            holder.mTv_moeny.setText(detailBeans.get(position).getIntegralNum()+"");
//        }
//        holder.mTv_time.setText(detailBeans.get(position).getCreatedTime());
//        holder.mTv_use_account.setText("账号: "+detailBeans.get(position).getAccount());
//        holder.mTv_use_device.setText("设备: "+detailBeans.get(position).getClientId());
//        if(detailBeans.get(position).getIntegralNum()>0){
//            holder.mUser_Linear.setVisibility(View.GONE);
//            holder.mTv_purpose.setText("积分充值");
//        }else {
//            holder.mUser_Linear.setVisibility(View.VISIBLE);
//            holder.mTv_purpose.setText(detailBeans.get(position).getUser().getUserTypeName());
//        }

    }

    @Override
    public int getItemCount() {
        if (DetailitemList != null) {
            return DetailitemList.size();
        } else {
            return 0;
        }
//        return detailBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_purpose, mTv_moeny, mTv_time, mTv_use_account, mTv_use_device;
        private LinearLayout mUser_Linear;

        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_purpose = view.findViewById(R.id.purpose);
            mTv_moeny = view.findViewById(R.id.moeny);
            mTv_time = view.findViewById(R.id.time);
            mTv_use_account = view.findViewById(R.id.use_aacount);
            mTv_use_device = view.findViewById(R.id.use_device);
            mUser_Linear=view.findViewById(R.id.User_linear);
        }
    }
}