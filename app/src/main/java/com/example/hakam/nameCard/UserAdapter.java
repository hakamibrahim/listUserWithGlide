package com.example.hakam.nameCard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.hakam.namecard.R;

import java.util.List;

/**
 * Created by hakam on 03/12/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    List<User> users;
    Context context;

    /**
     *
     * @param users
     * @param context
     */
    public UserAdapter(List<User> users, Context context){
        this.users = users;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_photo;
        TextView tv_name;
        TextView tv_age;

        public ViewHolder(View v) {
            super(v);

            iv_photo = v.findViewById(R.id.iv_photo);
            tv_name = v.findViewById(R.id.tv_name);
            tv_age = v.findViewById(R.id.tv_age);
        }
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return vh
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_user, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final User user = users.get(position);

        GlideApp.with(context)
                .load(user.getPhoto())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.tv_name.setText(user.getName());
                        holder.tv_age.setText("Umur : " + user.getAge());
                        return false;
                    }
                }).into(holder.iv_photo);
    }

    /**
     *
     * @return users
     */
    @Override
    public int getItemCount() {
        return users.size();
    }


}