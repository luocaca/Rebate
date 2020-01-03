package Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.ApprenticeSloganData;

import java.util.List;

public class ApprenticeSloganAdapter extends RecyclerView.Adapter<ApprenticeSloganAdapter.ViewHolder> {

    private List<ApprenticeSloganData> apprenticeSloganDatas;
    private Context mContext;

    public ApprenticeSloganAdapter(List<ApprenticeSloganData> apprenticeSloganData, Context context) {
        super();
        this.apprenticeSloganDatas = apprenticeSloganData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slogan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprenticeSloganAdapter.ViewHolder holder, int position) {
        holder.mTv_Slogan.setText(apprenticeSloganDatas.get(position).getSlogan());
        holder.mTv_Copy_Slogan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, "" + apprenticeSloganDatas.get(position).getSlogan()));
                if (clipboardManager.hasPrimaryClip()) {
                    clipboardManager.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       if(apprenticeSloganDatas.size()!=0){
           return apprenticeSloganDatas.size();
       }else {
           return 0;
       }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  public final ImageView mImageView;
        private TextView mTv_Slogan, mTv_Copy_Slogan;

        public ViewHolder(View view) {
            super(view);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_Slogan = view.findViewById(R.id.Slogan);
            mTv_Copy_Slogan = view.findViewById(R.id.Copy_Slogan);


        }
    }

}
