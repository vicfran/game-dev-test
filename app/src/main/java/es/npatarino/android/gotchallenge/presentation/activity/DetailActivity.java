package es.npatarino.android.gotchallenge.presentation.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.R;

public class DetailActivity extends AppCompatActivity {


    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ImageView photoImageView = (ImageView) findViewById(R.id.img_photo);
        final TextView nameTextView = (TextView) findViewById(R.id.lbl_name);
        final TextView descriptionTextView = (TextView) findViewById(R.id.lbl_description);

        final String description = getIntent().getStringExtra("description");
        final String name = getIntent().getStringExtra("name");
        final String imageUrl = getIntent().getStringExtra("imageUrl");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(imageUrl);
                    final Bitmap photo = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    DetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            photoImageView.setImageBitmap(photo);
                            nameTextView.setText(name);
                            descriptionTextView.setText(description);
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }).start();
    }
}
