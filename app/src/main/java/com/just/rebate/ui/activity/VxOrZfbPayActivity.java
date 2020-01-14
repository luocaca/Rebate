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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.just.rebate.data.Bank_Card_DataServer;
import com.just.rebate.entity.PaymentData;
import com.just.rebate.entity.ResponseData;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private int PayMode;
    private String IntegralNum = "";
    private String Authorization = "";
    private MyApplication applicationClass;
    private List<ResponseData> responseDatas = new ArrayList<>();
    private List<PaymentData.DataBean> paymentDatas = new ArrayList<>();
    private List<Bank_Card_DataServer.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected void requestData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("PayModeType", "" + 2);
        params.put("ReceivingType", "" + ReceivingType);
        //传递参数有问题，需要整改
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeByApp")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("积分信息错误日志", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("积分信息日志", "onResponse: " + response);
                        Type t = new TypeToken<PaymentData>() {
                        }.getType();
                        PaymentData paymentData = GsonUtil.getGsonLower().fromJson(response, t);
                        paymentDatas.clear();
                        paymentDatas.addAll(paymentData.Data);
                        Glide.with(VxOrZfbPayActivity.this).load(paymentDatas.get(0).ImageServerUrl + "/" + paymentDatas.get(0).ReceivingImg).into(mIv_QR_Code);
//                        if (rechargeIntegralData1s.get(0).getResultType() == 3) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(VxOrZfbPayActivity.this);
//                            builder.setTitle("提交成功,充值结果请咨询上级");
//                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    setResult(150);
//                                    finish();
//                                }
//                            });
//                            builder.create();
//                            builder.show();
//                        } else {
//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(VxOrZfbPayActivity.this);
//                            builder1.setMessage("提交失败,请稍后重试");
//                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                }
//                            });
//                            builder1.create();
//                            builder1.show();
//                        }
                    }

                });
    }

    @Override
    protected void initView() {
        applicationClass = (MyApplication) getApplication();
        initRecevierData();
        try {
            initPayCardData();
            RecevierCardData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initOnClick();
    }

    private void initPayCardData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + ReceivingType);
        params.put("PayModeType", "" + 1);

        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + applicationClass.getAuthorization())
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeListByApp?receivingType=1")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Bank_Card_DataServer bankcard = GsonUtil.getGsonLower().fromJson(response, Bank_Card_DataServer.class);
                        dataBeans.clear();
                        dataBeans.addAll(bankcard.Data);
                        for (int i = 0; i <= dataBeans.size() - 1; i++) {
                            if (dataBeans.get(i).IsDefault == 1) {
                                PayMode = dataBeans.get(i).Id;

                            }
                        }
                    }
                });
    }

    private void RecevierCardData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + ReceivingType);
        params.put("PayModeType", "" + 2);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeByApp?receivingType" + "=" + ReceivingType)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 收款方银行卡信息" + response);

                        PaymentData paymentData = GsonUtil.getGsonLower().fromJson(response, PaymentData.class);
                        if (paymentData != null) {
                            paymentDatas.clear();
                            paymentDatas.addAll(paymentData.Data);
                            if (paymentData.ResultType == 3) {

                            } else {
                                Toast.makeText(applicationClass, "数据加载错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(applicationClass, "数据请求错误", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private void initRecevierData() {
        Intent intent = getIntent();
        ReceivingType = intent.getIntExtra("ReceivingType", 1);
        Log.i("initRecevierData", "initRecevierData: 充值接口" + ReceivingType);
        Authorization = intent.getStringExtra("Authorization");
        PayMode = intent.getIntExtra("PayMode", 0);
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
                initSendVxOrZFB();
            }
        });
    }

    private void initSendVxOrZFB() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("OrderType", "" + 1);
            jsonObject.put("Amount", "" + IntegralNum);
            jsonObject.put("RealAmount", "" + IntegralNum);
            jsonObject.put("PayModeId", "" + PayMode);
            jsonObject.put("ReceivePayModeId", "" + paymentDatas.get(0).Id);
            jsonArray.put(jsonObject);
            OkHttpUtils.postString()
                    .content(jsonArray.toString())
                    .addHeader("Authorization", "Bearer " + applicationClass.getAuthorization())
                    .url("http://192.168.1.190:12004/api/Admin/OrderRechargePay/Create")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i("积分信息错误日志", "onError: " + e);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("积分信息日志", "onResponse: " + response);
                            Type t = new TypeToken<ResponseData>() {
                            }.getType();
                            ResponseData responseData = GsonUtil.getGsonLower().fromJson(response, t);
                            responseDatas.clear();
                            responseDatas.add(responseData);
                            if (responseData.Type == 200) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(VxOrZfbPayActivity.this);
                                builder.setTitle(responseData.Content);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(VxOrZfbPayActivity.this);
                                builder1.setMessage(responseData.Content);
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create();
                                builder1.show();
                            }
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
