package com.johnyhawkdesigns.a56_cwmdagger2.ui.main.posts;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.SessionManager;
import com.johnyhawkdesigns.a56_cwmdagger2.models.Post;
import com.johnyhawkdesigns.a56_cwmdagger2.network.main.MainApi;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {

    private static final String TAG = PostsViewModel.class.getSimpleName();
    
    //inject
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> postsList; // LOL. There was an error like <List<Resource<Post>>>

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi){
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: viewmodel is working...");
    }


    public LiveData<Resource<List<Post>>> observePosts(){
         if (postsList == null){
             postsList = new MediatorLiveData<>();

             // https://stackoverflow.com/questions/55696560/resource-class-and-null-argument
             postsList.setValue(Resource.loading((List<Post>) null));

             int currentUserId = sessionManager.getAuthUser().getValue().data.getId();
             if (currentUserId == -1){ // null
                 Log.d(TAG, "observePosts: ERROR!!! currentUserId = " + currentUserId );
             } else {
                 Log.d(TAG, "observePosts: currentUserId = " + currentUserId );
             }

             final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(

                     mainApi.getPostsFromUser(currentUserId)

                             // instead of calling onError, do this
                             .onErrorReturn(new Function<Throwable, List<Post>>() {
                                 @Override
                                 public List<Post> apply(Throwable throwable) throws Exception {
                                     Log.e(TAG, "apply: ", throwable);
                                     Post post = new Post();
                                     post.setId(-1);
                                     ArrayList<Post> posts = new ArrayList<>();
                                     posts.add(post);
                                     return posts;
                                 }
                             })

                             .map(new Function<List<Post>, Resource<List<Post>>>() {
                                 @Override
                                 public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                                     if(posts.size() > 0){
                                         if(posts.get(0).getId() == -1){ // if it is -1, means this is an error.
                                             return Resource.error("Something went wrong", null);
                                         }
                                     }
                                     return Resource.success(posts); // if list size is > 0 and also there is no error, then simply return the List<Post>
                                 }
                             })
                             .subscribeOn(Schedulers.io()));

             // Add source for MutableLiveData
             postsList.addSource(source, new Observer<Resource<List<Post>>>() {
                 @Override
                 public void onChanged(Resource<List<Post>> listResource) {
                     postsList.setValue(listResource);
                     postsList.removeSource(source);
                 }
             });
         }
        return postsList;
    }

}
