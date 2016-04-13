package com.cc.spinach.ui;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.cc.spinach.R;
import com.cc.spinach.utils.T;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QRCodeActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    public static final String EXTRA_QRCODE = "qrcode";

    @Bind(R.id.qrcode_toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.qrcode_qr_view)
    protected QRCodeReaderView qrCodeReaderView;

    @Bind(R.id.qrcode_icon_line)
    protected View iconLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);

        //toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        qrCodeReaderView.setOnQRCodeReadListener(this);

        iconLine.startAnimation(AnimationUtils.loadAnimation(this, R.anim.qrcode_line_anim));
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.getCameraManager().stopPreview();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_QRCODE, text);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void cameraNotFound() {
        T.showLong("无法打开摄像头");
//        DialogUtils.createAlertDialogBuilder(this)
//                .setMessage(R.string.can_not_open_camera)
//                .setPositiveButton(R.string.confirm, null)
//                .setOnDismissListener(new DialogInterface.OnDismissListener() {
//
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        setResult(RESULT_CANCELED);
//                        finish();
//                    }
//
//                })
//                .show();
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

}
