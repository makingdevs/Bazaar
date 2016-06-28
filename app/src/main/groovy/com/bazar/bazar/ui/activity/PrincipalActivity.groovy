package com.bazar.bazar.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.Window
import com.bazar.bazar.R
import com.bazar.bazar.SingleFragmentActivity
import com.bazar.bazar.ui.fragment.HomeFragment
import com.bazar.bazar.ui.fragment.LoginFragment
import groovy.transform.CompileStatic

@CompileStatic
class PrincipalActivity extends SingleFragmentActivity{

    static Intent newIntentWithContext(Context context){
        Intent intent = new Intent(context, PrincipalActivity)
        intent
    }

    @Override
    Fragment createFragment() {
        new HomeFragment()
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
    }
}