package com.cc.spinach.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cc.spinach.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注释：登录界面
 * 作者：菠菜 on 2016/3/31 11:51
 * 邮箱：971859818@qq.com
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.tv_saoma)
    TextView tvSaoma;

    private static final int REQUEST_QRCODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_saoma)
    public void onClick() {
        startActivityForResult(new Intent(this, QRCodeActivity.class), REQUEST_QRCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_QRCODE && resultCode == RESULT_OK) {
            tvSaoma.setText(data.getStringExtra(QRCodeActivity.EXTRA_QRCODE));

        }
    }
}
