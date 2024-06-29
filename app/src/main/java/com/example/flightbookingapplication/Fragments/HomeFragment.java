package com.example.flightbookingapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightbookingapplication.BottomSheetDialog.BottomDialogFragment;
import com.example.flightbookingapplication.CustomTextInputLayout.CustomTextInputLayout;
import com.example.flightbookingapplication.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public interface onFragmentInteractionListener {
        public default void onTripsClick() {

        }
        public default void onHotelClick() {

        }
        public default void onTransportClick() {

        }
        public default void onEventsClick() {

        }
    }

    onFragmentInteractionListener listener;
    public void setOnFragmentInteractionListener(onFragmentInteractionListener listener) {
        this.listener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2, onFragmentInteractionListener listener) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View main_view = inflater.inflate(R.layout.fragment_home, container, false);
        CustomTextInputLayout search_bar = main_view.findViewById(R.id.search_bar);
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText searchEditText = main_view.findViewById(R.id.search);
                String searchText = searchEditText.getText().toString();
                BottomDialogFragment bottomDialogFragment = BottomDialogFragment.newInstance("You searched for: " + searchText, "");
                bottomDialogFragment.show(getChildFragmentManager(), bottomDialogFragment.getTag());
//        if (searchText.isEmpty()) return;
//        showSearchDialog();
//        Toast.makeText(this, searchText, Toast.LENGTH_SHORT).show();
            }
        });

        main_view.findViewById(R.id.trips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTripsClick();
            }
        });

        main_view.findViewById(R.id.hotel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onHotelClick();
            }
        });

        main_view.findViewById(R.id.transport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTransportClick();
            }
        });

        main_view.findViewById(R.id.events).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEventsClick();
            }
        });
        return main_view;
    }
}