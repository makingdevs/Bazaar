package com.bazar.bazar.network

import com.bazar.bazar.model.User
import groovy.transform.CompileStatic
import retrofit2.Call
import retrofit2.http.*

@CompileStatic
interface UserRestOperations {

    @GET("users/all")
    Call<List<User>> getUsers(@QueryMap Map<String, String> options)
}
