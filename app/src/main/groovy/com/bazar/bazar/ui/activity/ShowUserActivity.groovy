package com.bazar.bazar.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import com.bazar.bazar.SingleFragmentActivity
import com.bazar.bazar.ui.fragment.ShowUserFragment
import groovy.transform.CompileStatic

@CompileStatic
class ShowUserActivity extends SingleFragmentActivity{

    static String EXTRA_USER_ID = "user_id"

    static Intent newIntentWithContext(Context context, String id){
        Intent intent = new Intent(context, ShowUserActivity)
        intent.putExtra(EXTRA_USER_ID, id)
        intent
    }

    @Override
    Fragment createFragment() {
        String id = getIntent()?.extras.getSerializable(EXTRA_USER_ID)
        new ShowUserFragment(id)
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
    }
}