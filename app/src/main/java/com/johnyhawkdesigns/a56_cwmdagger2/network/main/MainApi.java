package com.johnyhawkdesigns.a56_cwmdagger2.network.main;

import com.johnyhawkdesigns.a56_cwmdagger2.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    // https://jsonplaceholder.typicode.com/posts?userId=1
    //posts?userId=1
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );

}
