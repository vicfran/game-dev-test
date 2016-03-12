package es.npatarino.android.gotchallenge.presentation.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.activity.GoTCharacterActivity;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;

public class GoTCharactersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTCharacterModel> mCharacters;
    private Activity mActivity;

    public GoTCharactersAdapter(Activity activity) {
        this.mCharacters = new ArrayList<>();
        mActivity = activity;
    }

    public void addAll(Collection<GoTCharacterModel> characters) {
        for (int i = 0; i < characters.size(); i++) {
            mCharacters.add((GoTCharacterModel) characters.toArray()[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_got_character_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        gotCharacterViewHolder.render(mCharacters.get(position));
        ((GotCharacterViewHolder) holder).backgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(((GotCharacterViewHolder) holder).itemView.getContext(), GoTCharacterActivity.class);
                intent.putExtra("description", mCharacters.get(position).description);
                intent.putExtra("name", mCharacters.get(position).name);
                intent.putExtra("imageUrl", mCharacters.get(position).imageUrl);
                ((GotCharacterViewHolder) holder).itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";
        ImageView backgroundImageView;
        TextView nameTextView;

        public GotCharacterViewHolder(View itemView) {
            super(itemView);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.img_background);
            nameTextView = (TextView) itemView.findViewById(R.id.lbl_name);
        }

        public void render(final GoTCharacterModel character) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    try {
                        url = new URL(character.imageUrl);
                        final Bitmap background = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                backgroundImageView.setImageBitmap(background);
                                nameTextView.setText(character.name);
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
