package es.npatarino.android.gotchallenge.presentation.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GoTBaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final List<T> mData;
    protected Activity mActivity;

    public GoTBaseAdapter(Activity activity) {
        mActivity = activity;
        mData = new ArrayList<>();
    }

    public void update(Collection<T> data) {
        if ((data == null) || (mData == null))
            return;

        mData.clear();
        mData.addAll(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void configureViewHolderAtPosition(RecyclerView.ViewHolder viewHolder, final int position);
    public abstract Intent buildIntentForHolderAtPosition(RecyclerView.ViewHolder viewHolder, int position);
}
