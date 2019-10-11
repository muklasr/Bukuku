package com.muklasr.bukuku;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        changeTransparentBar();

        ImageView img = findViewById(R.id.book_img);
        TextView title = findViewById(R.id.tvTitle);
        TextView writer = findViewById(R.id.tvWriter);
        TextView publisher = findViewById(R.id.tvPublisher);
        TextView genre = findViewById(R.id.tvGenre);
        TextView year = findViewById(R.id.tvYear);
        TextView desc = findViewById(R.id.tvDesc);
        Button btnBack = findViewById(R.id.btnBack);


        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("image"))
                .apply(new RequestOptions().override(140, 180))
                .into(img);

        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
        writer.setText(getIntent().getStringExtra("writer"));
        publisher.setText(getIntent().getStringExtra("publisher"));
        year.setText(getIntent().getStringExtra("year"));
        genre.setText(getIntent().getStringExtra("genre"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void changeTransparentBar() {
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
