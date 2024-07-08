package com.example.flightbookingapplication;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flightbookingapplication.BookingServiceFragment.FlightsFragment;
import com.example.flightbookingapplication.BookingServiceFragment.TransportBookingFragment;
import com.example.flightbookingapplication.CustomDialog.CustomDialog;
import com.example.flightbookingapplication.Fragments.BookingFragment;
import com.example.flightbookingapplication.Fragments.HomeFragment;
import com.example.flightbookingapplication.Fragments.NotificationFragment;
import com.example.flightbookingapplication.Fragments.ProfileFragment;
import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity implements HomeFragment.onFragmentInteractionListener, FlightsFragment.setUpSpaceForFlightsFragment, TransportBookingFragment.restoreBottomNavigationBar {
    FrameLayout frameLayout;
    ImageView home, booking, noti, profile;

    int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        home = this.findViewById(R.id.home);
        booking = this.findViewById(R.id.booking);
        noti = this.findViewById(R.id.noti);
        profile = this.findViewById(R.id.profile);
        frameLayout = this.findViewById(R.id.fragment_container);

        home.setImageResource(R.drawable.active_tab);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setOnFragmentInteractionListener(this);
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), homeFragment).commit();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (currentPosition == 0) return;
                currentPosition = 0;
                setBottomIconDefault();
                v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                    }
                }).start();
                home.setImageResource(R.drawable.active_tab);
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setOnFragmentInteractionListener(HomeActivity.this);
                getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), homeFragment).commit();

            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (currentPosition == 1) return;
                currentPosition = 1;
                setBottomIconDefault();
                v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                    }
                }).start();
                booking.setImageResource(R.drawable.active_tab1);
                getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new BookingFragment()).commit();

            }
        });

//        noti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentPosition == 2) return;
//                currentPosition = 2;
//                setBottomIconDefault();
//                v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
//                    }
//                }).start();
//
//                getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new NotificationFragment()).commit();
//
//            }
//        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (currentPosition == 3) return;
                currentPosition = 3;
                setBottomIconDefault();
                v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                    }
                }).start();
                profile.setImageResource(R.drawable.active_tab3);
                getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new ProfileFragment()).commit();

            }
        });

    }

    private void setBottomIconDefault() {
        home.setImageResource(R.drawable.tab);
        booking.setImageResource(R.drawable.tab1);
        noti.setImageResource(R.drawable.tab2);
        profile.setImageResource(R.drawable.tab3);
        // remove existing fragment in R.id.fragment_container
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(frameLayout.getId())).commit();
    }

    private void showSearchDialog() {
        TextInputEditText searchEditText = findViewById(R.id.search);
        String searchText = searchEditText.getText().toString();
        CustomDialog dialog = new CustomDialog(this, searchText);
        dialog.show();
    }

    @Override
    public void onTripsClick() {
        HomeFragment.onFragmentInteractionListener.super.onTripsClick();
        setBottomIconDefault();
        currentPosition = 1;
        booking.setImageResource(R.drawable.active_tab1);
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), BookingFragment.newInstance("", "", 0)).commit();
    }

    @Override
    public void onHotelClick() {
        HomeFragment.onFragmentInteractionListener.super.onHotelClick();
        setBottomIconDefault();
        currentPosition = 1;
        booking.setImageResource(R.drawable.active_tab1);
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), BookingFragment.newInstance("", "", 1)).commit();

    }

    @Override
    public void onTransportClick() {
        HomeFragment.onFragmentInteractionListener.super.onTransportClick();
        setBottomIconDefault();
        currentPosition = 1;
        booking.setImageResource(R.drawable.active_tab1);
//        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), BookingFragment.newInstance("", "", 2)).commit();
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), BookingFragment.newInstance("", "", -1)).commit();
    }

    @Override
    public void onEventsClick() {
        HomeFragment.onFragmentInteractionListener.super.onEventsClick();
        setBottomIconDefault();
        currentPosition = 1;
        booking.setImageResource(R.drawable.active_tab1);
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), BookingFragment.newInstance("", "", 3)).commit();
    }

    @Override
    public void setUpSpaceForFlightsFragment() {
        findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = findViewById(R.id.main);
        constraintSet.clone(constraintLayout);

        // Apply new constraints to make fragment_container match parent
        constraintSet.connect(R.id.fragment_container, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(R.id.fragment_container, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(R.id.fragment_container, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(R.id.fragment_container, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

        // Apply the constraint set to the layout
        constraintSet.applyTo(constraintLayout);

        // Set LayoutParams to match parent
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        frameLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void restoreBottomNavigationBar() {
        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = findViewById(R.id.main);
        constraintSet.clone(constraintLayout);

        // Apply new constraints to set fragment_container height relative to bottom_navigation
        constraintSet.connect(R.id.fragment_container, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(R.id.fragment_container, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(R.id.fragment_container, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(R.id.fragment_container, ConstraintSet.BOTTOM, R.id.bottom_navigation, ConstraintSet.TOP);

        // Apply the constraint set to the layout
        constraintSet.applyTo(constraintLayout);

        // Convert dp to pixels and set the height
        int heightInDp = 810;
        int heightInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, heightInDp, getResources().getDisplayMetrics()
        );
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        layoutParams.height = heightInPx;
        frameLayout.setLayoutParams(layoutParams);
    }
}