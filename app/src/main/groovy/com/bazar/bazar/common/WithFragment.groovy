package com.bazar.bazar

import android.support.v4.app.Fragment
import groovy.transform.CompileStatic

@CompileStatic
trait WithFragment {
    abstract Fragment createFragment()
}