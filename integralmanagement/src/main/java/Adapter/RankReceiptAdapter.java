package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.RankReceiptBean;

import java.util.List;

public class RankReceiptAdapter extends RecyclerView.Adapter<RankReceiptAdapter.ViewHolder> {

    private List<RankReceiptBean.DataBean> rankReceiptBean;
    private Context mContext;

    public RankReceiptAdapter(List<RankReceiptBean.DataBean> rankReceiptBeans) {
        super();
        this.rankReceiptBean = rankReceiptBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankReceiptAdapter.ViewHolder holder, int position) {
        holder.mTv_Ranking.setText(position + 1 + "");
        holder.mTv_user_account.setText(rankReceiptBean.get(position).UserName);
        holder.mTv_reward.setText(rankReceiptBean.get(position).RewardNum + "");
        holder.mTv_Order.setText(rankReceiptBean.get(position).IntegralNum + "");
    }

    @Override
    public int getItemCount() {
        if (rankReceiptBean != null) {
            return rankReceiptBean.size();
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