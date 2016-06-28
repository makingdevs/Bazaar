package com.bazar.bazar.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bazar.bazar.R
import com.bazar.bazar.ui.activity.PrincipalActivity
import groovy.transform.CompileStatic

@CompileStatic
class LoginFragment extends Fragment{

    static final String TAG = "LoginFragment"
    LinearLayout mButtonLogin

    LoginFragment(){}

    View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_login, container, false)
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar)
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar)
        mButtonLogin = (LinearLayout) root.findViewById(R.id.login_button)
        mButtonLogin.onClickListener = {
            Intent intent = PrincipalActivity.newIntentWithContext(getContext())
            startActivity(intent)
            getActivity().finish()
        }
        root
    }

}