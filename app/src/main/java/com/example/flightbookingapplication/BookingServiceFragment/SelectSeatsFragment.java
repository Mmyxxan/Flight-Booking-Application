package com.example.flightbookingapplication.BookingServiceFragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightbookingapplication.FlightModel.Flight;
import com.example.flightbookingapplication.FlightModel.SeatsReservation;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.SeatsAdapter.CustomTravellerRecyclerView;
import com.example.flightbookingapplication.SeatsAdapter.SeatsAdapter;
import com.example.flightbookingapplication.SeatsAdapter.SeatsViewHolder;
import com.example.flightbookingapplication.SeatsAdapter.TravellersAdapter;
import com.example.flightbookingapplication.UserFlightInformation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectSeatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectSeatsFragment extends Fragment {
    ConstraintLayout constraintLayout;
    private SeatsReservation seatsReservation;
    private UserFlightInformation userFlightInformation;
    private Flight flight;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public interface OnFragmentBack {
        // refund all seats in seats reservation
        void onFragmentBack(boolean backToTransportBooking);
    }
    private OnFragmentBack mListener;
    public void setOnFragmentBackListener(OnFragmentBack listener) {
        mListener = listener;
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectSeatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectSeatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectSeatsFragment newInstance(String param1, String param2) {
        SelectSeatsFragment fragment = new SelectSeatsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        assert getArguments() != null;
        userFlightInformation = getArguments().getParcelable("userFlightInfo");
        flight = getArguments().getParcelable("flight");
        seatsReservation = new SeatsReservation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_seats, container, false);
        ImageView back = view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seatsReservation.refundAllSeats();
                mListener.onFragmentBack(false);
            }
        });
        assert userFlightInformation != null;
        assert flight != null;
        constraintLayout = view.findViewById(R.id.select_seats_fragment);
        TravellersAdapter travellersAdapter = new TravellersAdapter(Integer.parseInt(userFlightInformation.getPassenger()));
        CustomTravellerRecyclerView traveller_list = view.findViewById(R.id.traveller_list);
        traveller_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        travellersAdapter.setCheckCurrentPosition(traveller_list);
        traveller_list.setAdapter(travellersAdapter);
        traveller_list.setInitialPosition(0);
        RecyclerView seatRows = view.findViewById(R.id.seat_rows);
        SeatsAdapter seatsAdapter = new SeatsAdapter(flight.getSeats());
        TextView dollar = view.findViewById(R.id.dollar);
        TextView traveller_seat_number = view.findViewById(R.id.traveller_seat_number);
        seatsAdapter.setOnUserSeatTypeListener(new SeatsAdapter.getUserSeatType() {
            @Override
            public int getUserSeatType() {
                return userFlightInformation.getClassType();
            }
        });
        seatsAdapter.setOnSeatSelectedSuccessful(new SeatsViewHolder.onSeatSelectedSuccessful() {
            @Override
            public void onSeatSelected(int row, int column) {
                String seatCode = getSeatCode(row, column);
                traveller_seat_number.setText(traveller_list.getTravellerTag() + seatCode);
                dollar.setText("$"+ String.valueOf(seatsReservation.getTotalPrice() + flight.getSeats()[row][column].getPrice()) + ".00");
            }
        });
        seatRows.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        seatRows.setAdapter(seatsAdapter);
        ImageView continue_button = view.findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seatsAdapter.getCurrentRow() != -1 && seatsAdapter.getCurrentColumn() != -1) {
                    Log.d("SeatsReservation", "onClick: " + seatsAdapter.getCurrentRow() + " " + seatsAdapter.getCurrentColumn());
                    seatsReservation.reserveSeat(flight.getSeats()[seatsAdapter.getCurrentRow()][seatsAdapter.getCurrentColumn()]);
                    seatsAdapter.resetData();
                    if (traveller_list.getCurrentPosition() + 1 >= travellersAdapter.getItemCount()) {
                        constraintLayout.setVisibility(View.GONE);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("userFlightInfo", userFlightInformation);
                        bundle.putParcelable("flight", flight);
                        bundle.putParcelable("seatsReservation", seatsReservation);
                        BoardingPassFragment boardingPassFragment = new BoardingPassFragment();
                        boardingPassFragment.setOnBackPressedListener(new BoardingPassFragment.onBackPressedListener() {
                            @Override
                            public void onBackPressed() {
//                                seatsReservation.refundAllSeats();
                                getChildFragmentManager().beginTransaction().remove(boardingPassFragment).commit();
                                constraintLayout.setVisibility(View.VISIBLE);
                                mListener.onFragmentBack(true);
                            }
                        });
                        boardingPassFragment.setArguments(bundle);
                        getChildFragmentManager().beginTransaction().replace(R.id.select_seats_fragment_container, boardingPassFragment).commit();
                    }
                    else traveller_list.scrollToPosition(traveller_list.getCurrentPosition() + 1);
                }
            }
        });
        return view;
    }

    public String getSeatCode(int row, int column) {
        String column_code;
        switch (column) {
            case 0:
                column_code = "A";
                break;
            case 1:
                column_code = "B";
                break;
            case 2:
                column_code = "C";
                break;
            case 3:
                column_code = "D";
                break;
            default:
                column_code = "";
                break;
        }
        return String.valueOf(row + 1) + column_code;
    }
}