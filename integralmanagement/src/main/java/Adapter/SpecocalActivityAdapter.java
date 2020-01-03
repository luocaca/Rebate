package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.just.integralmanagement.HtmlActivity;
import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.ActivityDataBean;

import java.util.List;

public class SpecocalActivityAdapter extends RecyclerView.Adapter<SpecocalActivityAdapter.ViewHolder> {

    private List<ActivityDataBean.DataBean> activityDataBeans;
    private Context mContext;

    public SpecocalActivityAdapter(List<ActivityDataBean.DataBean> activityDataBean, Context context) {
        super();
        this.activityDataBeans = activityDataBean;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_special, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecocalActivityAdapter.ViewHolder holder, int position) {
        if (activityDataBeans != null) {
            holder.mTv_activity_beganTime.setText("有效时间:" + activityDataBeans.get(position).BeginTime + " — " + activityDataBeans.get(position).EndTime);
            holder.mTv_activity_name.setText(activityDataBeans.get(position).ActivityName);
            if (activityDataBeans.get(position).ActivityPayType == 1) {
                holder.mTv_activity_rule.setText("消费满" + activityDataBeans.get(position).BeginNum + ",则奖励" + activityDataBeans.get(position).GiveNum + "积分");
            } else if (activityDataBeans.get(position).ActivityPayType == 2) {
                holder.mTv_activity_rule.setText("充值满" + activityDataBeans.get(position).BeginNum + ",则奖励" + activityDataBeans.get(position).GiveNum + "积分");
            }
            Glide.with(mContext).load(activityDataBeans.get(position).ImageServerUrl + "/" + activityDataBeans.get(position).ActivityImg).into(holder.mIv_activity);
            Log.i("Glide", "onBindViewHolder: " + activityDataBeans.get(position).ImageServerUrl + "/" + activityDataBeans.get(position).ActivityImg);
        } else {
            Toast.makeText(mContext, "解析异常", Toast.LENGTH_SHORT).show();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, HtmlActivity.class);
                intent.putExtra("HtmlText",""+activityDataBeans.get(position).ActivityDesc);
                intent.putExtra("HtmlTitle",""+activityDataBeans.get(position).ActivityName);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        if (activityDataBeans.size() != 0) {
            return activityDataBeans.size();
        } else {
            return 0;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_activity_name, mTv_activity_rule, mTv_activity_beganTime, mTv_activity_endTime;
        private ImageView mIv_activity;

        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_activity_name = view.findViewById(R.id.activity_name);
            mTv_activity_rule = view.findViewById(R.id.activity_rule);
            mTv_activity_beganTime = view.findViewById(R.id.activity_beganTime);
            mIv_activity = view.findViewById(R.id.Img_activity);

        }
    }
}
