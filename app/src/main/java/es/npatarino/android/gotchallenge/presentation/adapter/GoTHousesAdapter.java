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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.activity.GoTCharacterActivity;
import es.npatarino.android.gotchallenge.presentation.activity.GoTCharactersActivity;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;

public class GoTHousesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTCharacterModel.GoTHouseModel> mHouses;
    private Activity mActivity;

    public GoTHousesAdapter(Activity activity) {
        this.mHouses = new ArrayList<>();
        mActivity = activity;
    }

    public void update(Collection<GoTCharacterModel.GoTHouseModel> houses) {
        if ((houses == null) || (mHouses == null))
            return;

        mHouses.clear();
        mHouses.addAll(houses);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotHouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_got_house_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotHouseViewHolder houseViewHolder = (GotHouseViewHolder) holder;
        houseViewHolder.render(mHouses.get(position));
        ((GotHouseViewHolder) holder).backgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(((GotHouseViewHolder) holder).itemView.getContext(), GoTCharactersActivity.class);
                intent.putExtra("id", mHouses.get(position).getId());
                intent.putExtra("name", mHouses.get(position).name);
                intent.putExtra("imageUrl", mHouses.get(position).imageUrl);
                ((GotHouseViewHolder) holder).itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHouses.size();
    }

    class GotHouseViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotHouseViewHolder";
        ImageView backgroundImageView;

        public GotHouseViewHolder(View itemView) {
            super(itemView);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.img_background);
        }

        public void render(final GoTCharacterModel.GoTHouseModel house) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    try {
                        url = new URL(house.imageUrl);
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
