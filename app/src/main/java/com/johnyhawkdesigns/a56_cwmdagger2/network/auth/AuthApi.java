package com.johnyhawkdesigns.a56_cwmdagger2.network.auth;

import com.johnyhawkdesigns.a56_cwmdagger2.models.User;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    // https://jsonplaceholder.typicode.com/users // Shows all users
    // https://jsonplaceholder.typicode.com/users/1 // Shows user with id 1
    @GET("users/{id}")
    Flowable<User> getUser( // After importing Retrofit RxJava, now we can use Flowable instead of Call
            @Path("id") int id
    );

}
