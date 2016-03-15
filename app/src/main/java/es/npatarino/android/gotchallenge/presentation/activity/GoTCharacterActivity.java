package es.npatarino.android.gotchallenge.presentation.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;

public class GoTCharacterActivity extends BaseActivity {

    private static final String TAG = "GoTCharacterActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.img_photo)
    ImageView mPhotoImageView;
    @Bind(R.id.lbl_description)
    TextView mDescriptionTextView;

    private String mName = "";
    private String mDescription = "";
    private String mImageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        getDataFromIntent(getIntent());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mName);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDescriptionTextView.setText(mDescription);

        update();
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

    private void getDataFromIntent(Intent intent) {
        if (intent == null)
            return;

        mName = intent.getStringExtra("name");
        mDescription = intent.getStringExtra("description");
        mImageUrl = intent.getStringExtra("imageUrl");
    }

    private void update() {
        if (!TextUtils.isEmpty(mImageUrl))
            Picasso.with(this)
                    .load(mImageUrl)
                    .into(mPhotoImageView);
    }
}
