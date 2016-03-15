package es.npatarino.android.gotchallenge.presentation.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.activity.GoTCharacterActivity;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;

public class GoTCharactersAdapter extends GoTBaseAdapter<GoTCharacterModel> {

    public GoTCharactersAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_got_character_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder viewHolder = (GotCharacterViewHolder) holder;
        configureViewHolderAtPosition(viewHolder, position);
    }

    @Override
    public void configureViewHolderAtPosition(RecyclerView.ViewHolder viewHolder, final int position) {
        final GotCharacterViewHolder characterViewHolder = (GotCharacterViewHolder) viewHolder;

        characterViewHolder.render(mData.get(position));

        characterViewHolder.backgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = buildIntentForHolderAtPosition(characterViewHolder, position);

                ActivityOptionsCompat options = buildTransitionOptions(characterViewHolder);

                characterViewHolder.itemView.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public Intent buildIntentForHolderAtPosition(RecyclerView.ViewHolder viewHolder, int position) {
        final GotCharacterViewHolder characterViewHolder = (GotCharacterViewHolder) viewHolder;

        Intent intent = new Intent((characterViewHolder.itemView.getContext()), GoTCharacterActivity.class);
        intent.putExtra("name", mData.get(position).getName());
        intent.putExtra("description", mData.get(position).getDescription());
        intent.putExtra("imageUrl", mData.get(position).getImageUrl());

        return intent;
    }

    private ActivityOptionsCompat buildTransitionOptions(RecyclerView.ViewHolder holder) {
        Pair[] pairs = new Pair[1];
        pairs[0] = Pair.create(((GotCharacterViewHolder) holder).backgroundImageView, "character_image");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mActivity,
                pairs
        );

        return options;
    }

    class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";

        private ImageView backgroundImageView;
        private TextView nameTextView;

        public GotCharacterViewHolder(View itemView) {
            super(itemView);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.img_background);
            nameTextView = (TextView) itemView.findViewById(R.id.lbl_name);
        }

        public void render(final GoTCharacterModel character) {
            if (character == null)
                return;

            nameTextView.setText(character.getName());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    try {
                        url = new URL(character.getImageUrl());
                        final Bitmap background = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                backgroundImageView.setImageBitmap(background);
                            }
                        });
                    } catch (IOException e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
            }).start();
        }
    }
}
