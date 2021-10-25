package com.mcc.quizzed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public AppBarLayout appBar;
    private RecyclerView courseRV;
    private NavigationView navigationView;

    EditText account_email, account_password;
    Button save_btn, cancel_btn, edit_btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.nav_bar_layout);

        setUpNavigationView();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        save_btn = findViewById(R.id.save_btn);
        edit_btn = findViewById(R.id.edit_btn);
        account_email = findViewById(R.id.account_email);
        account_password = findViewById(R.id.account_password);

//        edit_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                enableEditing();
//            }
//        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null && !account_email.getText().toString().trim().equals("")) {
                    user.updateEmail(account_email.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AccountActivity.this, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show();
                                        signOut();
                                    } else {
                                        Toast.makeText(AccountActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                } else if (account_email.getText().toString().trim().equals("")) {
                    account_email.setError("Enter email");
                }
                if (user != null && !account_password.getText().toString().trim().equals("")) {
                    if (account_password.getText().toString().trim().length() < 6) {
                        account_password.setError("Password too short, enter minimum 6 characters");

                    } else {
                        user.updatePassword(account_password.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(AccountActivity.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                            signOut();
                                        } else {
                                            Toast.makeText(AccountActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                    }
                } else if (account_password.getText().toString().trim().equals("")) {
                    account_password.setError("Enter password");
                }
            }
        });

//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AccountActivity.this, MainActivity.class));
//                finish();
//            }
//        });
    }

    private void enableEditing() {
        account_email.setEnabled(true);
        account_password.setEnabled(true);
    }

    private void signOut() {
        mAuth.signOut();
        startActivity(new Intent(AccountActivity.this, LoginActivity.class));
        finish();
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
                        startActivity(new Intent(AccountActivity.this, AccountActivity.class));
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_settings:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(AccountActivity.this, StartingScreenActivity.class));
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_logout:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(AccountActivity.this, LogoutActivity.class));
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
}