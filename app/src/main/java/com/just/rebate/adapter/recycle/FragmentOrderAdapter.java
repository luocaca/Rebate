package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.just.rebate.R;
import com.just.rebate.entity.OrderListData;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.ui.fragment.FragmentHome_list.OrderFragment;
import com.rebate.commom.util.bitmap.GlideRoundTransform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Set;

public class FragmentOrderAdapter extends RecyclerView.Adapter<FragmentOrderAdapter.ViewHolder> {
    private List<OrderListData.RowsBean> rowsBeans;
    private List<OrderListData.RowsBean.OrderItemsBean> orderItemsBeans;
    private Context mContext;
    private JSONArray jsonArray;
    public int amount;
    public JSONObject JSONObject;

    public FragmentOrderAdapter(List<OrderListData.RowsBean> rowsBean, Context context) {
        this.rowsBeans = rowsBean;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderall, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentOrderAdapter.ViewHolder holder, int position) {
        if (rowsBeans.get(position).OrderItems.size() != 0) {
            if (rowsBeans.get(position).OrderPlatformName == null) {
                holder.mTv_PlatformName.setText("");
            } else {
                holder.mTv_PlatformName.setText(rowsBeans.get(position).OrderPlatformName);
            }

            DecimalFormat df = new DecimalFormat("######0.00");
            String time = rowsBeans.get(position).PaymentExpried.replace("T", " ");
            String timeers = time.replaceAll("T", " ");
            String[] times = timeers.split("\\.", 2);
            if (rowsBeans.get(position).PaymentExpried == null) {
                holder.mTv_PaymentExpried.setText("");
            } else {
                holder.mTv_PaymentExpried.setText(times[0]);
            }
            if (rowsBeans.get(position).OrderItems.get(0).Count == 0) {
                holder.mTv_Count.setText(0);
            } else {
                holder.mTv_Count.setText(rowsBeans.get(position).OrderItems.get(0).Count + "");
            }
            if (rowsBeans.get(position).OrderItems.get(0).ProductName == null) {
                holder.mTv_order_name.setText("");
            } else {
                holder.mTv_order_name.setText(rowsBeans.get(position).OrderItems.get(0).ProductName);
            }
            if ((rowsBeans.get(position).Amount == 0)) {
                holder.mTv_Amount.setText("");
            } else {
                holder.mTv_Amount.setText(df.format(rowsBeans.get(position).Amount) + "");
            }
            if (rowsBeans.get(position).OrderItems.get(0).SpecName == null) {
                holder.mTv_SpecName.setText("");
            } else {
                holder.mTv_SpecName.setText(rowsBeans.get(position).OrderItems.get(0).SpecName);
            }
//        holder.mTv_rich_moeny
            RequestOptions myOptions = new RequestOptions()
//                        .transform(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                    .transform(new GlideRoundTransform(holder.itemView.getContext(), 6));
//                      .centerCrop()

            DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(100).setCrossFadeEnabled(true).build();
            if (rowsBeans.get(position).OrderItems.get(0).Image == null) {
                Glide.with(holder.itemView.getContext()).load("").transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).apply(myOptions).into(holder.mIv_order);
            } else {
                Glide.with(holder.itemView.getContext()).load(rowsBeans.get(position).OrderItems.get(0).Image).transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).apply(myOptions).into(holder.mIv_order);
            }
            holder.mRb_CheckBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        JSONObject = initChooseData(holder, position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


            //判断所有item是否都被选中
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//
        }

    }

    public JSONObject initChooseData(ViewHolder holder, int position) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < rowsBeans.size(); i++) {
            if (holder.mRb_CheckBtn.isChecked()) {
                jsonArray.put(rowsBeans.get(i).OrderNo);
                amount += rowsBeans.get(i).OrderItems.get(0).Price;
                Log.i("onBindViewHolder", "onBindViewHolder: 查询支付的内容" + jsonObject.toString() + "\n" + amount);
            }
        }
        jsonObject.put("OrderNos", jsonArray);
        jsonObject.put("Amount", amount);
        return jsonObject;
    }

//            holder.mRb_CheckBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Log.i("onClick", "onClick: 查询支付的内容");
//                        jsonObject = initCheckboX(holder, position);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

//    public JSONObject initCheckboX(ViewHolder holder, int position) throws Exception {
//        if (!holder.mRb_CheckBtn.isChecked()) {
//            JSONObject jsonObject = new JSONObject();
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.put(rowsBeans.get(position).OrderNo);
//            jsonObject.put("OrderNos", jsonArray);
//            amount += rowsBeans.get(position).OrderItems.get(0).Price + "";
//            Log.i("initCheckboX", "initCheckboX: 查询支付的内容" + jsonObject.toString() + "\n" + amount);
//            return jsonObject;
//        } else {
//            Log.i("initCheckboX", "initCheckboX: 查询支付的内容为空");
//            return null;
//        }
//        if (CheckAll) {
//            holder.mRb_CheckBtn.setChecked(true);
//            for (int i = 0; i < rowsBeans.size(); i++) {
//                jsonArray.put(rowsBeans.get(i).OrderNo);
//                jsonObject.put("OrderNos", jsonArray);
//                amount+=rowsBeans.get(i).OrderItems.get(0).Price;
//            }
//            Log.i("initCheckboX", "initCheckboX: 选中返回的值"+jsonObject.toString());
//            return jsonObject;
//        } else {
//            holder.mRb_CheckBtn.setChecked(false);
//            if (holder.mRb_CheckBtn.isChecked()) {
//                jsonArray.put(rowsBeans.get(position).OrderNo);
//                jsonObject.put("OrderNos", jsonArray);
//            }
//            return jsonObject;
//        }


    @Override
    public int getItemCount() {
        return rowsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv_PlatformName, mTv_PaymentExpried, mTv_rich_moeny, mTv_order_name, mTv_Amount, mTv_SpecName;
        private TextView mTv_AddCount, mTv_DeleteCount, mTv_Count;
        private ImageView mIv_order, mIv_Check;
        private CheckBox mRb_CheckBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_PlatformName = itemView.findViewById(R.id.OrderPlatformName);
            mTv_PaymentExpried = itemView.findViewById(R.id.PaymentExpried);
            mTv_rich_moeny = itemView.findViewById(R.id.rich_moeny);
            mTv_order_name = itemView.findViewById(R.id.order_name);
            mTv_Amount = itemView.findViewById(R.id.Amount);
            mTv_SpecName = itemView.findViewById(R.id.SpecName);
            mIv_order = itemView.findViewById(R.id.order_iv);
            mTv_Count = itemView.findViewById(R.id.count);
            mIv_Check = itemView.findViewById(R.id.checkbox_context);
            mRb_CheckBtn = itemView.findViewById(R.id.CheckBtn);
        }
    }
}
