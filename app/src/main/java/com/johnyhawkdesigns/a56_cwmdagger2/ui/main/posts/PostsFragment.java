package com.johnyhawkdesigns.a56_cwmdagger2.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.johnyhawkdesigns.a56_cwmdagger2.R;
import com.johnyhawkdesigns.a56_cwmdagger2.models.Post;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.Resource;
import com.johnyhawkdesigns.a56_cwmdagger2.util.VerticalSpaceItemDecoration;
import com.johnyhawkdesigns.a56_cwmdagger2.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = PostsFragment.class.getSimpleName();

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter postRecyclerAdapter; // Provided inside MainModule

    @Inject
    ViewModelProviderFactory providerFactory; // Provided inside ViewModelFactoryModule

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    // We should inflate our widgets inside onViewCreated because it is more safe.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        // instantiate viewModel
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribeObservers();
    }

    // we just call viewModel's methods here
    private void subscribeObservers(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null){
                    Log.d(TAG, "onChanged: listResource.data = " + listResource.data);

                    switch (listResource.status){
                        case LOADING:{
                            Log.d(TAG, "onChanged: LOADING.... ");
                            break;
                        }

                        case SUCCESS:{
                            Log.d(TAG, "onChanged: got posts... ");
                            postRecyclerAdapter.setPosts(listResource.data);
                            break;
                        }

                        case ERROR:{
                            Log.e(TAG, "onChanged: ERROR.... " + listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    // Initialize RecyclerView
    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postRecyclerAdapter);
    }

}


