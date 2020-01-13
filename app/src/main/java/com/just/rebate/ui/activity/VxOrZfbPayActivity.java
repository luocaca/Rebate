package com.just.rebate.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.PaymentData;
import com.just.integralmanagement.entity.RechargeIntegralData1;
import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

public class VxOrZfbPayActivity extends BaseActivity {

    @BindView(R.id.QR_Code)
    ImageView mIv_QR_Code;

    @BindView(R.id.btn_Payed)
    Button mBtn_Payed;

    private int ReceivingType;
    private String IntegralNum = "";
    private String Authorization = "";
    private MyApplication applicationClass;
    private List<PaymentData> paymentDatas = new ArrayList<>();
    private List<RechargeIntegralData1> rechargeIntegralData1s = new ArrayList<>();

    @Override
    protected void requestData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("AppType", "" + 0);
        params.put("IntegralNum", "" + IntegralNum);
        params.put("TaskPayModeId", "" + paymentDatas.get(0).Data.get(0).Id);
        params.put("IsState", "" + 0);
        //传递参数有问题，需要整改
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeByApp?receivingType"+"="+ReceivingType)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("积分信息错误日志", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                        if (errorDataBean.getType() == 403) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(VxOrZfbPayActivity.this);
                            builder1.setMessage("" + errorDataBean.getContent());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.create();
                            builder1.show();
                        } else {
                            Log.i("积分信息日志", "onResponse: " + response);
                            Type t = new TypeToken<RechargeIntegralData1>() {
                            }.getType();
                            RechargeIntegralData1 rechargeIntegralData1 = GsonUtil.getGsonLower().fromJson(response, t);
                            rechargeIntegralData1s.clear();
                            rechargeIntegralData1s.add(rechargeIntegralData1);
                            if (rechargeIntegralData1s.get(0).getResultType() == 3) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(VxOrZfbPayActivity.this);
                                builder.setTitle("提交成功,充值结果请咨询上级");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setResult(150);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(VxOrZfbPayActivity.this);
                                builder1.setMessage("提交失败,请稍后重试");
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create();
                                builder1.show();
                            }
                        }
                    }
                });
    }

    @Override
    protected void initView() {
        initRecevierData();
        initOnClick();
    }

    private void initRecevierData() {
        Intent intent = getIntent();
        ReceivingType = intent.getIntExtra("ReceivingType", 1);
        Authorization = intent.getStringExtra("Authorization");
        IntegralNum = intent.getStringExtra("IntegralNum");
    }

    private void initOnClick() {
        mIv_QR_Code.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VxOrZfbPayActivity.this);
                builder.setMessage("保存图片");
                builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] PERMISSIONS = {
                                "android.permission.READ_EXTERNAL_STORAGE",
                                "android.permission.WRITE_EXTERNAL_STORAGE"};
                        //检测是否有写的权限
                        int permission = ContextCompat.checkSelfPermission(VxOrZfbPayActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            //                            // 没有写的权限，去申请写的权限，会弹出对话框
                            ActivityCompat.requestPermissions(VxOrZfbPayActivity.this, PERMISSIONS, 1);
                        }
                        SaveBitmapFromView(view);
                    }
                });


                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });

        mBtn_Payed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
    }

    public void SaveBitmapFromView(View view) {
        int w = view.getWidth();
        int h = view.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
//        view.layout(0, 0, w, h);
        view.draw(c);
        // 缩小图片
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f); //长和宽放大缩小的比例
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        saveBitmap(bmp, format.format(new Date()) + ".JPEG");
    }

    private void saveBitmap(Bitmap bmp, String s) {
        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + s;
        } else {  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + s;
        }
        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bmp.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
                // 插入图库
                MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), s, null);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 发送广播，通知刷新图库的显示
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
    }

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_vx_zfb_pay;
    }
}
