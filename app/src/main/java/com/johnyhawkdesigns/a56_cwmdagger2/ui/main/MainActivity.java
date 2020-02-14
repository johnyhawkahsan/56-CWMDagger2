package com.johnyhawkdesigns.a56_cwmdagger2.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.johnyhawkdesigns.a56_cwmdagger2.BaseActivity;
import com.johnyhawkdesigns.a56_cwmdagger2.R;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();
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
        }
        return super.onOptionsItemSelected(item);
    }
}
