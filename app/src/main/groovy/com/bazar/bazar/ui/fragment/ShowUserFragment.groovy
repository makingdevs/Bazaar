package com.bazar.bazar.ui.fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bazar.bazar.R
import com.bazar.bazar.manager.UserManager
import com.bazar.bazar.manager.UserManagerImpl
import com.bazar.bazar.model.User
import com.bumptech.glide.Glide
import groovy.transform.CompileStatic
import jp.wasabeef.glide.transformations.CropCircleTransformation
import me.gujun.android.taggroup.TagGroup
import retrofit2.Call
import retrofit2.Response

@CompileStatic
class ShowUserFragment extends Fragment{

    static final String TAG = "ShowUserFragment"
    static String ID_USER
    String mUserId
    User user
    ImageView mImageView
    TextView mUserName
    TextView mCountry
    TextView mAboutMe
    TagGroup mSkills
    TagGroup mInterests
    TextView mHometown
    TextView mLocation

    UserManager mUserManager = UserManagerImpl.instance

    ShowUserFragment(String id){
        Bundle args = new Bundle()
        args.putSerializable(ID_USER, id)
        this.arguments = args
    }

    @Override
    void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        if (!getArguments() || !getArguments()?.getSerializable(ID_USER))
            throw new IllegalArgumentException("No arguments $ID_USER")
        mUserId = getArguments()?.getSerializable(ID_USER)
    }

    View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_show_user, container, false)
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar)
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar)
        mImageView = (ImageView) root.findViewById(R.id.photo)
        mUserName = (TextView) root.findViewById(R.id.user_name)
        mCountry = (TextView) root.findViewById(R.id.country)
        mAboutMe = (TextView) root.findViewById(R.id.about_me)
        mHometown = (TextView) root.findViewById(R.id.message_hometown)
        mLocation = (TextView) root.findViewById(R.id.message_location)
        mSkills = (TagGroup) root.findViewById(R.id.skill)
        mInterests = (TagGroup) root.findViewById(R.id.interesting)
        mUserManager.show(mUserId, onSuccess(), onError())
        root
    }

    void setUserInView(User user){
        String url = user.picture.contains("person_placeholder") ? "http://bazaar-dev.eu-central-1.elasticbeanstalk.com/${user.picture}": user.picture
        Glide.with(getContext()).load(url)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mImageView)
        mUserName.text = user.name
        mCountry.text = user.hometown

        mHometown.text = user.hometown
        mLocation.text = user.location

        mSkills.setTags(user.skills)
        mInterests.setTags(user.interests)
    }

    private Closure onSuccess() {
        { Call<User> call, Response<User> response ->
            user = response.body()
            setUserInView(user)
        }
    }

    private Closure onError() {
        { Call<User> call, Throwable t -> Log.d("ERRORZ", "el error " + t.message) }
    }

}