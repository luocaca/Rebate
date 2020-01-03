package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.AwardRecordDataBean;

import java.util.List;

public class AwardRecordAdapter extends RecyclerView.Adapter<AwardRecordAdapter.ViewHolder> {

    private List<AwardRecordDataBean.DataBean> awardRecordDataBeans;
    private Context mContext;

    public AwardRecordAdapter(List<AwardRecordDataBean.DataBean> awardRecordDataBeanList,Context context) {
        super();
        this.awardRecordDataBeans = awardRecordDataBeanList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_award_record,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AwardRecordAdapter.ViewHolder holder, int position) {
        if(awardRecordDataBeans.size()!=0){
            holder.mTv_ContributionUser.setText(awardRecordDataBeans.get(position).Account);
            holder.mTv_Contribution_Integral.setText(awardRecordDataBeans.get(position).BonusIntegral+"");
            holder.mTv_Contribution_Integral.setTextColor(R.color.red);
            holder.mTv_CreatTime.setText(awardRecordDataBeans.get(position).CreatedTime);
            if(awardRecordDataBeans.get(position).IsOneBonus==1){
                holder.mTv_identity.setText("徒弟");
            }else if(awardRecordDataBeans.get(position).IsOneBonus==2){
                holder.mTv_identity.setText("徒孙");
            }
        }else {
            Toast.makeText(mContext, "数据解析错误", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if (awardRecordDataBeans != null) {
            return awardRecordDataBeans.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_ContributionUser, mTv_Contribution_Integral, mTv_CreatTime,mTv_identity;

        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_ContributionUser = view.findViewById(R.id.ContributionUser);
            mTv_Contribution_Integral = view.findViewById(R.id.Contribution_Integral);
            mTv_CreatTime = view.findViewById(R.id.CreatTime);
            mTv_identity=view.findViewById(R.id.identity);

        }
    }
}
