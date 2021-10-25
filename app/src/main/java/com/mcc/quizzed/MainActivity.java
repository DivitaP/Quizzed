package com.mcc.quizzed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public AppBarLayout appBar;
    private RecyclerView courseRV;
    private NavigationView navigationView;

    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;

    // Arraylist for storing data
    private ArrayList<CourseModel> courseModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.nav_bar_layout);

        setUpNavigationView();

//        courseRV = findViewById(R.id.idRVCourse);

        // here we have created new array list and added data to it.
//        courseModelArrayList = new ArrayList<>();
//        courseModelArrayList.add(new CourseModel("Cellular Architecture", 4, R.drawable.ic_course));
//        courseModelArrayList.add(new CourseModel("GSM", 3, R.drawable.ic_course));
//        courseModelArrayList.add(new CourseModel("GSM Encryption", 4, R.drawable.ic_course));
//        courseModelArrayList.add(new CourseModel("Mobile Networks", 4, R.drawable.ic_course));
//        courseModelArrayList.add(new CourseModel("WLAN", 4, R.drawable.ic_course));
//        courseModelArrayList.add(new CourseModel("MANET", 4, R.drawable.ic_course));
//        courseModelArrayList.add(new CourseModel("LTE", 4, R.drawable.ic_course));

        // we are initializing our adapter class and passing our arraylist to it.
//        CourseAdapter courseAdapter = new CourseAdapter(this, courseModelArrayList);
//
//        // below line is for setting a layout manager for our recycler view.
//        // here we are creating vertical list so we will provide orientation as vertical
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//
//        // in below two lines we are setting layoutmanager and adapter to our recycler view.
//        courseRV.setLayoutManager(linearLayoutManager);
//        courseRV.setAdapter(courseAdapter);

        recyclerView = findViewById(R.id.idCourseRV);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new RecyclerData("Cellular Architecture",R.drawable.ic_course));
        recyclerDataArrayList.add(new RecyclerData("GSM",R.drawable.ic_course));
        recyclerDataArrayList.add(new RecyclerData("WLAN",R.drawable.ic_course));
        recyclerDataArrayList.add(new RecyclerData("MANET",R.drawable.ic_course));
        recyclerDataArrayList.add(new RecyclerData("LTE",R.drawable.ic_course));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);





    }


    private void setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;

                    case R.id.nav_account:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AccountActivity.class));
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_settings:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, StartingScreenActivity.class));
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_logout:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, LogoutActivity.class));
                        drawerLayout.closeDrawers();
                        return true;

                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);


                return true;
            }
        });

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}