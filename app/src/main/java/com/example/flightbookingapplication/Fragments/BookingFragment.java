package com.example.flightbookingapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flightbookingapplication.BookingServiceFragment.TransportBookingFragment;
import com.example.flightbookingapplication.FragmentBookingRecyclerViewAdapter.BookingAdapter;
import com.example.flightbookingapplication.FragmentBookingRecyclerViewAdapter.BookingViewHolder;
import com.example.flightbookingapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment implements BookingViewHolder.OnItemClickListener {

    // use back stack or for every fragment, manage its child fragment like done in this fragment

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_PARAM3 = "param3";

    RecyclerView booking_list;
    ImageView booking_nav_bar;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int mParam3;

    public void hideViews() {
        booking_nav_bar.setVisibility(View.GONE);
        booking_list.setVisibility(View.GONE);
    }

    public void showViews() {
        booking_nav_bar.setVisibility(View.VISIBLE);
        booking_list.setVisibility(View.VISIBLE);
    }

    public void showFragment(Fragment fragment) {
        hideViews();
        getChildFragmentManager().beginTransaction().replace(R.id.booking_fragment, fragment).commit();
    }

    public void removeFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().remove(fragment).commit();
        showViews();
    }

    public BookingFragment() {
        // Required empty public constructor
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.booking_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void popBackFragment(String previousFragmentCLassName) {
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment previousFragment = fragmentManager.findFragmentByTag(previousFragmentCLassName);
        assert previousFragment != null;
        fragmentTransaction.show(previousFragment);
        fragmentTransaction.commit();
    }

    public void switchFragment(String fragmentClassName) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Hide all current fragment
        for (Fragment fragment : fragmentManager.getFragments()) {
            fragmentTransaction.hide(fragment);
        }

        Fragment targetFragment = fragmentManager.findFragmentByTag(fragmentClassName);
        if (targetFragment == null) {
            try{
                //this method should include handling exception
                targetFragment = (Fragment)Class.forName(fragmentClassName).newInstance();
                fragmentTransaction.add(R.id.booking_fragment, targetFragment, fragmentClassName);
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | java.lang.InstantiationException e) {
                Log.e("MainActivity", "Error instantiating fragment", e);
            }
        } else {
            fragmentTransaction.show(targetFragment);
        }
        fragmentTransaction.commit();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2, int param3) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM3, param3);
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
        else mParam3 = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        booking_list = view.findViewById(R.id.booking_list);
//        SnapHelper snapHelper = new LinearSnapHelper();
        booking_list.setAdapter(new BookingAdapter(this));
        booking_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (mParam3 != -1) booking_list.scrollToPosition(mParam3);
        else {
            hideViews();
            TransportBookingFragment transportBookingFragment = new TransportBookingFragment();
            transportBookingFragment.setOnBackButtonPressedListener(new TransportBookingFragment.OnBacKPressed() {
                @Override
                public void onBackButtonPressed() {
                    getChildFragmentManager().popBackStack();
                    showViews();
                }
            });
            getChildFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.booking_fragment, transportBookingFragment).commit();
        }
        booking_nav_bar = view.findViewById(R.id.booking_nav_bar);
//        snapHelper.attachToRecyclerView(booking_list);
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("MainActivity", "onItemClick: " + position);
        if (position == 2) {
            hideViews();
            TransportBookingFragment transportBookingFragment = new TransportBookingFragment();
            transportBookingFragment.setOnBackButtonPressedListener(new TransportBookingFragment.OnBacKPressed() {
                @Override
                public void onBackButtonPressed() {
                    getChildFragmentManager().popBackStack();
                    showViews();
                }
            });
            getChildFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.booking_fragment, transportBookingFragment).commit();
        }
    }
}