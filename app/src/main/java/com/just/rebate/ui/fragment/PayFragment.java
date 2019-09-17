package com.just.rebate.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.just.rebate.R;
import com.just.rebate.ui.activity.PwdEditText;
import com.just.rebate.ui.activity.PwdInputMethodView;

public class PayFragment extends DialogFragment implements View.OnClickListener {

    public static final String EXTRA_CONTENT = "extra_content";    //提示框内容

    public PwdEditText psw_input;
    private PwdEditText.InputCallBack inputCallBack;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_cash_with_drawal);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);

        initView(dialog);
        return dialog;
    }

    private void initView(Dialog dialog) {
        Bundle bundle = getArguments();
        if (bundle != null) {
        }

        psw_input = dialog.findViewById(R.id.PwdEditText);
        PwdInputMethodView inputMethodView = dialog.findViewById(R.id.inputMethodView);
        psw_input.setInputMethodView(inputMethodView);
        psw_input.setInputCallBack(inputCallBack);

        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;

        }
    }

    /**
     * 设置输入回调
     *
     * @param inputCallBack
     */
    public void setPaySuccessCallBack(PwdEditText.InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }

}


