package com.zhxh.xservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements XIntentService.UpdateUI {

    private String url[] = {
            "https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.png",
            "https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.png",
            "https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.png",
            "https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.png",
            "https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.png",
            "https://github.com/zhxhcoder/XButton/blob/master/screenshots/xbutton.png"
    };

    private static ImageView imageView;
    private static final Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imageView.setImageBitmap((Bitmap) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);

        Intent intent = new Intent(this, XIntentService.class);
        for (int i = 0; i < 7; i++) {
            intent.putExtra(XIntentService.DOWNLOAD_URL, url[i]);
            intent.putExtra(XIntentService.INDEX_FLAG, i);
            startService(intent);
        }
        XIntentService.setUpdateUI(this);
    }

    @Override
    public void updateUI(Message message) {
        mUIHandler.sendMessageDelayed(message, message.what * 1000);
    }
}
