package com.just.rebate.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.Personal_local_Item;
import com.just.rebate.entity.personal.Personal;
import com.just.rebate.ui.MainActivity;
import com.just.rebate.ui.activity.BalaceActivity;
import com.just.rebate.ui.activity.BankCardActivity;
import com.just.rebate.ui.activity.SetUpActivity;
import com.just.rebate.ui.activity.help.HelpCenterActivity;
import com.rebate.base.fragment.BaseFragment;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment {
    private List<Personal_local_Item> mData;
    private int[] Image = {
            R.mipmap.chat3, R.mipmap.wallet2, R.mipmap.chongzhi1, R.mipmap.mingxi, R.mipmap.bankcard, R.mipmap.info2
    };

    @BindView(R.id.rv_list2)
    RecyclerView recyclerView;

    @BindView(R.id.account)
    TextView mTv_account;

    @BindView(R.id.integral)
    TextView mTv_integral;

    @BindView(R.id.invaitat)
    TextView mTv_invitat;

    @BindView(R.id.totalRebate)
    TextView totalRebate;

    @BindView(R.id.preRebate)
    TextView preRebate;

    @BindView(R.id.headImage)
    ImageView mIv_head;





    @BindView(R.id.set_up)
    ImageView imageView;
    private List<Personal_local_Item> list;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 4));


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
            }
        });


        list = new ArrayList<>();
        Personal_local_Item p1 = new Personal_local_Item();
        p1.ItemNameid = Image[0];
        p1.ItemName = "客服";
        p1.activityClass = BalaceActivity.class;
        list.add(p1);
        Personal_local_Item p2 = new Personal_local_Item();
        p2.ItemNameid = Image[1];
        p2.ItemName = "提现";
        p2.activityClass = BalaceActivity.class;
        list.add(p2);
        Personal_local_Item p3 = new Personal_local_Item();
        p3.ItemNameid = Image[2];
        p3.ItemName = "充值";
        list.add(p3);
        Personal_local_Item p4 = new Personal_local_Item();
        p4.ItemNameid = Image[3];
        p4.ItemName = "到账明细";
        list.add(p4);
        Personal_local_Item p5 = new Personal_local_Item();
        p5.ItemNameid = Image[4];
        p5.ItemName = "银行卡";
        p5.activityClass = BankCardActivity.class;

        list.add(p5);
        Personal_local_Item p6 = new Personal_local_Item();
        p6.ItemNameid = Image[5];
        p6.ItemName = "帮助";
        p6.activityClass = HelpCenterActivity.class;
        list.add(p6);


        recyclerView.setAdapter(new BaseQuickAdapter<Personal_local_Item, BaseViewHolder>(R.layout.item_personal_content, list) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Personal_local_Item item) {
                helper.setImageResource(R.id.iv, item.ItemNameid);
                helper.setText(R.id.tv, item.ItemName);
                helper.itemView.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), item.activityClass == null ? MainActivity.class : item.activityClass)));
            }


        });
        requestDataOnline();


//        personalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(mActivity, "onItemChildClick", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void requestDataOnline() {

        OkHttpUtils
                .get()
                .url("http://192.168.1.171:8080/download/personaljson.txt")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ///Log.e("TAG", "日志");
                        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();


                        Personal personal = GsonUtil.getGson().fromJson(response, Personal.class);

                        mTv_account.setText(personal.account + "");
                        mTv_integral.setText(personal.integral + "");
                        mTv_invitat.setText(personal.invitationCode + "");

                        totalRebate.setText(personal.totalRebate);
                        preRebate.setText(personal.preRebate);


                        Glide.with(getActivity()).load(personal.headImage).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIv_head);


                    }
                });
    }

    @Override
    protected void initData() {

    }

}
