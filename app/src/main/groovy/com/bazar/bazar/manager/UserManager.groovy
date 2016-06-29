package com.bazar.bazar.manager

import groovy.transform.CompileStatic

@CompileStatic
interface UserManager {
    void list(Map params, Closure onSuccess, Closure onError)
    void show(String id, Closure onSuccess, Closure onError)
}