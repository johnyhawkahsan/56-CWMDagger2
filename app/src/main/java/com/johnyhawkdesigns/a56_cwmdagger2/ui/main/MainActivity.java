package com.johnyhawkdesigns.a56_cwmdagger2.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.johnyhawkdesigns.a56_cwmdagger2.BaseActivity;
import com.johnyhawkdesigns.a56_cwmdagger2.R;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.posts.PostsFragment;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.profile.ProfileFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();
    }

    private void init(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment); // this is placed inside mainActivity.xml
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this); // for this, we have implemented onNavigationItemSelected
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:{
                sessionManager.logout(); // logout method defined in sessionManager
                return true;
            }

            // This method is used to close the drawer once we click on HamburgerIcon (when drawer is already open of course)
            case R.id.home:{
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected: item = " + item.getItemId());

        switch (item.getItemId()){

            case R.id.nav_profile:{

                NavOptions navOptions = new NavOptions.Builder() // set custom options for profileScreen
                        .setPopUpTo(R.id.main, true)
                        .build();

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate( // nav_host_fragment is inside activity_main.xml
                        R.id.profileScreen, // profileScreen is inside main.xml in navigation directory
                        null,
                        navOptions); // in navOptions, we have settings which ensure that profileScreen is not stored in the back stack and when user presses back button on profileScreen, app closes.
                break;
            }

            case R.id.nav_posts:{

                if (isValidDestination(R.id.postsScreen)){
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen); // nav_host_fragment is inside activity_main.xml and postsScreen is inside main.xml in navigation directory
                }
                break;
            }
        }

        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // This method helps us prevent adding postsScreen fragment to backStack over and over again, and with this, we make sure it is added to the back stack only once.
    private boolean isValidDestination(int destination){
        int destinationId = Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
        Log.d(TAG, "isValidDestination: destinationId = " + destinationId);
        return destination != destinationId;
    }


    // This method is used to make backArrow work and to make hamburger icon to work on top left side.
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }
}
