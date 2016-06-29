package com.bazar.bazar.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bazar.bazar.R
import com.bazar.bazar.model.User
import com.bazar.bazar.ui.activity.ShowUserActivity
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.CropCircleTransformation
import me.gujun.android.taggroup.TagGroup

class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    Context mContext
    List<User> mUsers

    UserAdapter(Context context, List<User> usersList){
        mContext = context
        mUsers = usersList
    }

    @Override
    UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false)
        new UserViewHolder(view)
    }

    @Override
    void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mUsers[position]
        holder.bindCheckin(user)
    }

    @Override
    int getItemCount() {
        mUsers.size()
    }


    class UserViewHolder extends RecyclerView.ViewHolder{

        User mUser
        ImageView mImageView
        TextView mUserName
        TextView mCountry
        TextView mAboutMe
        TagGroup mSkills

        void bindCheckin(User user) {
            mUser = user
            String url = user.picture.contains("person_placeholder") ? "http://bazaar-dev.eu-central-1.elasticbeanstalk.com/${user.picture}": user.picture
            Glide.with(mContext).load(url)
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .into(mImageView)
            mUserName.text = user.name
            mCountry.text = user.hometown
            mSkills.setTags(user.skills)
            itemView.onClickListener = {
                Intent intent = ShowUserActivity.newIntentWithContext(mContext, user._id)
                mContext.startActivity(intent)
            }
        }

        UserViewHolder(View itemView) {
            super(itemView)
            mImageView = (ImageView) itemView.findViewById(R.id.photo)
            mUserName = (TextView) itemView.findViewById(R.id.user_name)
            mCountry = (TextView) itemView.findViewById(R.id.country)
            mAboutMe = (TextView) itemView.findViewById(R.id.about_me)
            mSkills = (TagGroup) itemView.findViewById(R.id.skill)
        }
    }

}

