package com.example.bingbindong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bingbindong.myapplication.utils.VeryfiyCode;

public class VeryfiyCodeActivity extends AppCompatActivity implements View.OnClickListener {
    //public static final String TAG = MainActivity.class.getName();
    private ImageView iv_showCode;
    private EditText et_phoneCode;

    //产生的验证码
    private String realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veryfiy_code);
        et_phoneCode = (EditText) findViewById(R.id.et_phoneCodes);
        Button but_toSetCode = (Button) findViewById(R.id.but_forgetpass_toSetCodes);
        but_toSetCode.setOnClickListener(VeryfiyCodeActivity.this);
        iv_showCode = (ImageView) findViewById(R.id.iv_showCode);
        //将验证码用图片的形式显示出来
        iv_showCode.setImageBitmap(VeryfiyCode.getInstance().createBitmap());
        realCode = VeryfiyCode.getInstance().getCode().toLowerCase();
        iv_showCode.setOnClickListener(VeryfiyCodeActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_showCode:
                iv_showCode.setImageBitmap(VeryfiyCode.getInstance().createBitmap());
                realCode = VeryfiyCode.getInstance().getCode().toLowerCase();
                //Log.v(TAG,"realCode"+realCode);
                break;
            case R.id.but_forgetpass_toSetCodes:
                String phoneCode = et_phoneCode.getText().toString().toLowerCase();
                String msg = "生成的验证码："+realCode+"输入的验证码:"+phoneCode;
                Toast.makeText(VeryfiyCodeActivity.this,msg,Toast.LENGTH_LONG).show();

                if (phoneCode.equals(realCode)) {
                    Toast.makeText(VeryfiyCodeActivity.this, phoneCode + "验证码正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VeryfiyCodeActivity.this, phoneCode + "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}