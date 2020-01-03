package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.RankApprenticeBean;

import java.util.List;

public class RankApprenticeAdapter extends RecyclerView.Adapter<RankApprenticeAdapter.ViewHolder> {

    private List<RankApprenticeBean.DataBean> rankApprenticeBeans;
    private Context mContext;


    public RankApprenticeAdapter(List<RankApprenticeBean.DataBean> rankReceiptBeans) {
        super();
        this.rankApprenticeBeans = rankReceiptBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankApprenticeAdapter.ViewHolder holder, int position) {
        holder.mTv_Ranking.setText(position + 1 + "");
        holder.mTv_user_account.setText(rankApprenticeBeans.get(position).UserName);
        holder.mTv_reward.setText(rankApprenticeBeans.get(position).RewardNum + "");
        holder.mTv_Order.setText(rankApprenticeBeans.get(position).TotalCount + "");
    }


    @Override
    public int getItemCount() {
        if (rankApprenticeBeans != null) {
            return rankApprenticeBeans.size();
        } else {
            return 0;
        }
//        return detailBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_Ranking, mTv_user_account, mTv_reward, mTv_Order;

        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_Ranking = view.findViewById(R.id.Ranking);
            mTv_user_account = view.findViewById(R.id.user_account);
            mTv_Order = view.findViewById(R.id.Order);
            mTv_reward = view.findViewById(R.id.reward);
        }
    }
}