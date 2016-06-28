package com.bazar.bazar.network.impl

import com.bazar.bazar.model.User
import com.bazar.bazar.network.UserRestOperations
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import groovy.transform.CompileStatic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Singleton
@CompileStatic
class RetrofitTemplate {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://bazaar-dev.eu-central-1.elasticbeanstalk.com/")
            .build()

    def withRetrofit(Class operations, Closure onSuccess, Closure onError, Closure action){
        UserRestOperations restOperations = retrofit.create(operations) as UserRestOperations
        Call<User> model = action(restOperations) as Call<User>
        def callback = [
                onResponse :onSuccess,
                onFailure : onError
        ]
        model.enqueue(callback as Callback<User>)
    }

}