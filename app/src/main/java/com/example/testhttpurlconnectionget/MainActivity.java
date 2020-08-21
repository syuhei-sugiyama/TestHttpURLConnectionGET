package com.example.testhttpurlconnectionget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText editText;
    private DownloadTask task;

    String param0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.uri);
        imageView = findViewById(R.id.result);

        Button downloadButton = findViewById(R.id.download);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                param0 = editText.getText().toString();

                if (param0.length() != 0){
                    // ボタンをタップして非同期処理を開始
                    task = new DownloadTask();
                    // Listenerを設定
                    task.setListener(createListener());
                    task.execute(param0);
                }
            }
        });

    }

    @Override
    protected void onDestroy(){
        task.setListener(null);
        super.onDestroy();
    }

    private DownloadTask.Listener createListener(){
        return new DownloadTask.Listener() {
            @Override
            public void onSuccess(Bitmap bmp) {
                imageView.setImageBitmap(bmp);
            }
        };
    }
}
