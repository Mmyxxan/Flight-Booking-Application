package com.example.flightbookingapplication.BookingServiceFragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flightbookingapplication.FlightModel.Flight;
import com.example.flightbookingapplication.FlightModel.FlightData;
import com.example.flightbookingapplication.FlightsAdapter.CalendarAdapter;
import com.example.flightbookingapplication.FlightsAdapter.FlightsAdapter;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.UserFlightInformation;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightsFragment extends Fragment {
    ConstraintLayout mainLayout;
    private RecyclerView calendarList, flightsList;
    private UserFlightInformation userFlightInformation;
    private TextView numFlights;
    public interface setUpSpaceForFlightsFragment {
        void setUpSpaceForFlightsFragment();
    }

    public interface onBackPressed {
        void onBackPressed();
    }
    private onBackPressed backListener;
    public void setBackListener(onBackPressed backListener) {
        this.backListener = backListener;
    }
    private setUpSpaceForFlightsFragment mListener;
    public void setListener(setUpSpaceForFlightsFragment listener) {
        mListener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FlightsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (setUpSpaceForFlightsFragment) context;
        mListener.setUpSpaceForFlightsFragment();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlightsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlightsFragment newInstance(String param1, String param2) {
        FlightsFragment fragment = new FlightsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        view.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backListener.onBackPressed();
            }
        });
        mainLayout = view.findViewById(R.id.flights_fragment);
        numFlights = view.findViewById(R.id.num_flights);
        calendarList = view.findViewById(R.id.flights_recycler_view);
        calendarList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        CalendarAdapter calendarAdapter = new CalendarAdapter();
        calendarAdapter.setNumberOfDays(30);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            userFlightInformation = getArguments().getParcelable("userFlightInfo", UserFlightInformation.class);
        }
        assert userFlightInformation != null;
        calendarAdapter.setStartDate(userFlightInformation.getDepartureDate());
        FlightData flightData = FlightData.getInstance(12345L, userFlightInformation.getDepartureDate());
        calendarAdapter.setCurrentPosition(0);
        FlightsAdapter flightsAdapter = new FlightsAdapter();
        flightsAdapter.setFlights(flightData.getFlightContainers().get(0).filterFlights(userFlightInformation.getOrigin(), userFlightInformation.getDestination()).getFlights());
//        flightsAdapter.setFlights(flightData.getFlightContainers().get(0).getFlights());
        numFlights.setText(flightsAdapter.getItemCount() + " flights available " + userFlightInformation.getOrigin() + " to " + userFlightInformation.getDestination());
        calendarAdapter.setOnItemSelectedListener(new CalendarAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                List<Flight> flights = flightData.getFlightContainers().get(position).filterFlights(userFlightInformation.getOrigin(), userFlightInformation.getDestination()).getFlights();
//                List<Flight> flights = flightData.getFlightContainers().get(position).getFlights();
                flightsAdapter.setFlights(flights);
                flightsAdapter.notifyDataSetChanged();
                numFlights.setText(flightsAdapter.getItemCount() + " flights available " + userFlightInformation.getOrigin() + " to " + userFlightInformation.getDestination());
            }
        });
        calendarList.setAdapter(calendarAdapter);
        flightsList = view.findViewById(R.id.flights_list);
        flightsAdapter.setOnFlightClickListener(new FlightsAdapter.onFlightClickListener() {
            @Override
            public void onFlightClick(Flight flight) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("flight", flight);
                bundle.putParcelable("userFlightInfo", userFlightInformation);
                SelectSeatsFragment selectSeatsFragment = new SelectSeatsFragment();
                selectSeatsFragment.setArguments(bundle);
                hideViews();
                selectSeatsFragment.setOnFragmentBackListener(new SelectSeatsFragment.OnFragmentBack() {
                    @Override
                    public void onFragmentBack() {
                        getChildFragmentManager().beginTransaction().remove(selectSeatsFragment).commit();
                        showViews();
                    }
                });
                getChildFragmentManager().beginTransaction().replace(R.id.flights_fragment_container, selectSeatsFragment).commit();
            }
        });
        flightsList.setAdapter(flightsAdapter);
        flightsList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    private void hideViews() {
        mainLayout.setVisibility(View.GONE);
    }
    private void showViews() {
        mainLayout.setVisibility(View.VISIBLE);
    }
}