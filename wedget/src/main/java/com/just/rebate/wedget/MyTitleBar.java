package com.just.rebate.wedget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import me.luocaca.wedget.R;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class MyTitleBar extends RelativeLayout {


    public TextView title_tv;
    private String titleText;
    private int titleColor, titleSize;

    public ImageView logoImg;
    private int logoImgId;

    public ImageView left_btnImg;
    private int leftImgId;
    public TextView left_btnTv;
    private String leftText;
    private int leftTextColor;

    public ImageView right_btnImg;
    private int rightImgId;
    public TextView right_btnTv;
    private String rightText;
    private int rightTextColor;

    public MyTitleBar(Context context) {
        super(context);
        intiUI(context, null);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        intiUI(context, attrs);

    }

    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiUI(context, attrs);
    }

    private void intiUI(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
            logoImgId = ta.getResourceId(R.styleable.MyTitleBar_logoSrc, -1);
            titleText = ta.getString(R.styleable.MyTitleBar_titleText);
            titleSize = ta.getInteger(R.styleable.MyTitleBar_titleSize, 20);
            titleColor = ta.getColor(R.styleable.MyTitleBar_titleColor, ContextCompat.getColor(context,R.color.colorWhite));

            leftImgId = ta.getResourceId(R.styleable.MyTitleBar_leftImgId,-1);
            leftText = ta.getString(R.styleable.MyTitleBar_leftText);
            leftTextColor = ta.getColor(R.styleable.MyTitleBar_leftTextColor, ContextCompat.getColor(context,R.color.colorText2));

            rightImgId = ta.getResourceId(R.styleable.MyTitleBar_rightImgId,-1);
            rightText = ta.getString(R.styleable.MyTitleBar_rightText);
            rightTextColor = ta.getColor(R.styleable.MyTitleBar_rightTextColor, ContextCompat.getColor(context,R.color.colorText2));
            ta.recycle();

        }else {
            logoImgId = -1;
            titleSize = 20;
            titleColor = ContextCompat.getColor(context,R.color.colorWhite);
            leftImgId = -1;
            leftTextColor = ContextCompat.getColor(context,R.color.colorText2);
            rightImgId = -1;
            rightTextColor = ContextCompat.getColor(context,R.color.colorText2);
        }

        RelativeLayout groupView = new RelativeLayout(context);
        groupView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LayoutParams params_content = new LayoutParams(LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.dimen_45));
        LinearLayout contentView = new LinearLayout(context);
        contentView.setGravity(Gravity.CENTER_VERTICAL);
        groupView.addView(contentView, params_content);

        LinearLayout.LayoutParams params_btn = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dimen_56), LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams params_view = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout leftView = new LinearLayout(context);
        contentView.addView(leftView, params_btn);
        leftView.setPadding((int) getResources().getDimension(R.dimen.dimen_0), (int) getResources().getDimension(R.dimen.dimen_7), (int) getResources().getDimension(R.dimen.dimen_7), (int) getResources().getDimension(R.dimen.dimen_7));
        leftView.setGravity(Gravity.CENTER);













        leftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback!=null)
                    callback.itemLeftonClick();
            }
        });

        left_btnImg = new ImageView(context);
        left_btnImg.setAdjustViewBounds(true);
        left_btnImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (leftImgId!=-1){
            left_btnImg.setImageResource(leftImgId);
        }
        left_btnImg.setPadding(0,10,10,10);

        leftView.addView(left_btnImg, params_view);

        left_btnTv = new TextView(context);
        left_btnTv.setTextSize(titleSize - 3);
        left_btnTv.setTextColor(leftTextColor);
        left_btnTv.setText(leftText);
        left_btnTv.setSingleLine();
        leftView.addView(left_btnTv, params_view);




        LinearLayout title_layout = new LinearLayout(context);
        title_layout.setGravity(Gravity.CENTER);
        title_layout.setBackgroundColor(Color.TRANSPARENT);
        contentView.addView(title_layout, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        LinearLayout.LayoutParams params_logoImg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) getResources().getDimension(R.dimen.dimen_30));
        logoImg = new ImageView(context);
        logoImg.setAdjustViewBounds(true);
        logoImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (logoImgId!=-1){
            logoImg.setImageResource(logoImgId);
        }
        title_layout.addView(logoImg, params_logoImg);

        title_tv = new TextView(context);
        title_tv.setTextSize(titleSize);
        title_tv.setTextColor(titleColor);
        title_tv.setText(titleText);
        title_tv.setSingleLine();
        title_tv.setEllipsize(TextUtils.TruncateAt.END);
        title_layout.addView(title_tv, params_view);
        //________________________________________________


        LinearLayout rightView = new LinearLayout(context);
        rightView.setGravity(Gravity.CENTER);
        rightView.setPadding((int) getResources().getDimension(R.dimen.dimen_7), (int) getResources().getDimension(R.dimen.dimen_7), (int) getResources().getDimension(R.dimen.dimen_7), (int) getResources().getDimension(R.dimen.dimen_7));
        contentView.addView(rightView, params_btn);
        rightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback !=null)
                    callback.itemRightonClick();
            }
        });


        right_btnImg = new ImageView(context);
        right_btnImg.setAdjustViewBounds(true);
        right_btnImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (rightImgId!=-1){
            right_btnImg.setImageResource(rightImgId);
        }
        rightView.addView(right_btnImg, params_view);

        right_btnTv = new TextView(context);
        right_btnTv.setTextSize(titleSize - 3);
        right_btnTv.setTextColor(rightTextColor);
        right_btnTv.setText(rightText);
        right_btnTv.setSingleLine();
        rightView.addView(right_btnTv, params_view);

//        setBackgroundResource(R.drawable.custom_bg1);
        addView(groupView);
    }


    private ClickCallback callback;

    public void setClickCallback(ClickCallback callback) {
        this.callback = callback;
    }

    public interface ClickCallback {
        void itemLeftonClick();

        void itemRightonClick();
    }
}
