package com.bazar.bazar.manager

import com.bazar.bazar.network.UserRestOperations
import com.bazar.bazar.network.impl.RetrofitTemplate
import groovy.transform.CompileStatic

@Singleton
@CompileStatic
class UserManagerImpl implements UserManager {

    static operations = UserRestOperations

    @Override
    void list(Map params, Closure onSuccess, Closure onError) {
        RetrofitTemplate.instance.withRetrofit(operations as Class, onSuccess, onError){ UserRestOperations restOperations ->
            restOperations.getUsers(params)
        }
    }

    @Override
    void show(String id, Closure onSuccess, Closure onError) {
        RetrofitTemplate.instance.withRetrofit(operations as Class, onSuccess, onError){ UserRestOperations restOperations ->
            restOperations.getUser(id)
        }
    }

}
