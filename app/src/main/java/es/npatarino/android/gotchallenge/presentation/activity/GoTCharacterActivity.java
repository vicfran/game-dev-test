package es.npatarino.android.gotchallenge.presentation.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.R;

public class GoTCharacterActivity extends BaseActivity {


    private static final String TAG = "GoTCharacterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ImageView photoImageView = (ImageView) findViewById(R.id.img_photo);
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

        descriptionTextView.setText(description);

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(imageUrl);
                    final Bitmap photo = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    GoTCharacterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            photoImageView.setImageBitmap(photo);
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
