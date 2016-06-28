package com.bazar.bazar.ui.fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bazar.bazar.R
import com.bazar.bazar.manager.UserManager
import com.bazar.bazar.manager.UserManagerImpl
import com.bazar.bazar.model.User
import com.bazar.bazar.ui.adapter.UserAdapter
import groovy.transform.CompileStatic
import retrofit2.Call
import retrofit2.Response

@CompileStatic
class HomeFragment extends Fragment{

    static final String TAG = "HomeFragment"
    UserManager mUserManager = UserManagerImpl.instance
    RecyclerView mListUsers
    UserAdapter mUserAdapter

    HomeFragment(){}

    View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_home, container, false)
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar)
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar)
        mListUsers = (RecyclerView) root.findViewById(R.id.list_users)
        mListUsers.setLayoutManager(new LinearLayoutManager(getActivity()))
        updateUI()
        root
    }

    void updateUI() {
        mUserManager.list([:],onSuccess(),onError())
    }

    private Closure onSuccess(){
        { Call<List<User>> call, Response<List<User>> response ->
            if(!mUserAdapter){
                mUserAdapter = new UserAdapter(getContext(), response.body().toList())
                mListUsers.adapter = mUserAdapter
            } else {
                mUserAdapter.setmUsers(response.body().toList())
                mUserAdapter.notifyDataSetChanged()
            }
        }
    }

    private Closure onError(){
        { Call<List<User>> call, Throwable t -> Log.d("ERRORZ", "el error") }
    }

}