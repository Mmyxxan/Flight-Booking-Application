package com.example.flightbookingapplication.BookingServiceFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightbookingapplication.FlightModel.Flight;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.UserFlightInformation;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransportBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportBookingFragment extends Fragment {
    ConstraintLayout constraintLayout;
    public interface restoreBottomNavigationBar {
        void restoreBottomNavigationBar();
    }
    private restoreBottomNavigationBar restoreBottomNavigationBarListener;
    public void setRestoreBottomNavigationBarListener(restoreBottomNavigationBar listener) {
        restoreBottomNavigationBarListener = listener;
    }
    private UserFlightInformation userFlightInfo;

    public interface OnBacKPressed {
        void onBackButtonPressed();
    }
    private OnBacKPressed backPressedListener;
    public void setOnBackButtonPressedListener(OnBacKPressed listener) {
        backPressedListener = listener;
    }
    private TextInputEditText origin, destination, departure_date, return_date, passenger, baby, dog, luggage;
    int class_type = 0, transport_type = 0;
    private ImageView back;
    private String dayOfWeek;
    private TextView economy, business;
    private ImageView plane, ship, train, bus;
    DatePickerDialog.OnDateSetListener dateSetListener;

    DatePickerDialog datePickerDialog;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransportBookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransportBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransportBookingFragment newInstance(String param1, String param2) {
        TransportBookingFragment fragment = new TransportBookingFragment();
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
        if (savedInstanceState != null) {
            userFlightInfo = savedInstanceState.getParcelable("userFlightInfo");
        } else {
            userFlightInfo = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("userFlightInfo", userFlightInfo);
    }

    public void setClass(int position) {
        if (position == 0) {
            economy.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            economy.setTextColor(Color.parseColor("#FFFFFF"));
            business.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            business.setTextColor(Color.parseColor("#089083"));
            class_type = 0;
        } else if (position == 1) {
            business.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            business.setTextColor(Color.parseColor("#FFFFFF"));
            economy.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            economy.setTextColor(Color.parseColor("#089083"));
            class_type = 1;
        }
    }

    public void setTransport(int position) {
        if (position == 0) {
            plane.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.white_transport));
            ship.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport1));
            train.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport2));
            bus.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport3));
            plane.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            ship.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            train.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            bus.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            transport_type = 0;
        }
        else if (position == 1) {
            plane.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport));
            ship.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.white_transport1));
            train.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport2));
            bus.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport3));
            plane.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            ship.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            train.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            bus.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            transport_type = 1;
        }
        else if (position == 2) {
            plane.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport));
            ship.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport1));
            train.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.white_transport2));
            bus.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport3));
            plane.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            ship.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            train.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            bus.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            transport_type = 2;
        }
        else if (position == 3) {
            plane.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport));
            ship.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport1));
            train.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.green_transport2));
            bus.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.white_transport3));
            plane.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            ship.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            train.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            bus.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            transport_type = 3;
        }
    }

    public boolean validateDate() {
        String returnDateStr = return_date.getText().toString().trim();
        String departureDateStr = departure_date.getText().toString().trim();
        if (TextUtils.isEmpty(returnDateStr) || TextUtils.isEmpty(departureDateStr)) return true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate returnDate = LocalDate.parse(returnDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return !returnDate.isBefore(departureDate);

        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transport_booking, container, false);
        departure_date = view.findViewById(R.id.departure);
        return_date = view.findViewById(R.id.return_text);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // monthOfYear is 0-based, so add 1 to it
                int month = monthOfYear + 1;

                // Format the date to match "yyyy-MM-dd"
                String formattedDate = String.format("%04d-%02d-%02d", year, month, dayOfMonth);

                // Now you can use this formattedDate string as the departure_date
                // For example, parsing it to a LocalDate
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDate departureDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                if (departure_date.hasFocus()) {
                    departure_date.setText(formattedDate);
                    if (!validateDate()) {
                        departure_date.setError("Return date must be after departure date");
                        departure_date.setText("");
                        departure_date.clearFocus();
                    }
                    else {
                        departure_date.setError(null);
                        departure_date.clearFocus();
                        final Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.getTime());
                        Log.d("Date", dayOfWeek);
                    }
                } else if (return_date.hasFocus()) {
                    return_date.setText(formattedDate);
                    if (!validateDate()) {
                        return_date.setError("Return date must be after departure date");
                        return_date.setText("");
                        return_date.clearFocus();
                    }
                    else {
                        return_date.setError(null);
                        return_date.clearFocus();
                    }
                }
            }
        };

        datePickerDialog = new DatePickerDialog(requireContext(), dateSetListener, 2024, 6, 3);
        departure_date.setOnFocusChangeListener((departure_date, hasFocus) -> {
                                              if (hasFocus) {
                                                  datePickerDialog.show();
                                              }
                                          });
        return_date.setOnFocusChangeListener((return_date, hasFocus) -> {
            if (hasFocus) {
                datePickerDialog.show();
            }
        });


        origin = view.findViewById(R.id.from);
        destination = view.findViewById(R.id.to);
        origin.setOnFocusChangeListener((origin, hasFocus) -> {
            if (!hasFocus) {
                validateAndSetEditTextValue((TextInputEditText) origin);
            }
        });
        destination.setOnFocusChangeListener((destination, hasFocus) -> {
            if (!hasFocus) {
                validateAndSetEditTextValue((TextInputEditText) destination);
            }
        });
        ImageView convert = view.findViewById(R.id.convert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String origin_text = origin.getText().toString().trim();
                origin.setText(destination.getText().toString().trim());
                destination.setText(origin_text);
                return;
            }
        });

        economy = view.findViewById(R.id.economy);
        business = view.findViewById(R.id.business);

        economy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClass(0);
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClass(1);
            }
        });

        plane = view.findViewById(R.id.plane);
        ship = view.findViewById(R.id.ship);
        train = view.findViewById(R.id.train);
        bus = view.findViewById(R.id.bus);

        plane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTransport(0);
            }
        });
        ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTransport(1);
            }
        });
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTransport(2);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTransport(3);
            }
        });
        back = view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Back", "Back button pressed");
                if (backPressedListener != null) {
                    backPressedListener.onBackButtonPressed();
                }
            }
        });
        passenger = view.findViewById(R.id.passenger_edit_text);
        baby = view.findViewById(R.id.baby_edit_text);
        dog = view.findViewById(R.id.dog_edit_text);
        luggage = view.findViewById(R.id.luggage_edit_text);

        constraintLayout = view.findViewById(R.id.transport_booking_fragment);

        ImageView search = view.findViewById(R.id.search_flight);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToFlights();
            }
        });

        return view;
    }

    private void validateAndSetEditTextValue(TextInputEditText origin) {
        String userInput = origin.getText().toString().trim();

        if (TextUtils.isEmpty(userInput)) {
            // If user input is empty, default to the first valid string
            userInput = Flight.getValidCities().get(0);
        } else {
            // Check if user input matches any valid string
            boolean isValid = false;
            for (String validString : Flight.getValidCities()) {
                if (validString.equalsIgnoreCase(userInput)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                // Show dialog to select from valid options
                showOptionsDialog(origin);
                return;
            }
        }

        // Set the validated or default value to the EditText
        origin.setText(userInput + " (" + Flight.abbreviated_city(userInput) + ")");
    }

    private void checkAndFillDate() {

    }

    private void navigateToFlights() {
        String origin = this.origin.getText().toString().trim();
        String destination = this.destination.getText().toString().trim();
        String departureDate = this.departure_date.getText().toString().trim();
        String returnDate = this.return_date.getText().toString().trim();
        String passenger = this.passenger.getText().toString().trim();
        String baby = this.baby.getText().toString().trim();
        String dog = this.dog.getText().toString().trim();
        String luggage = this.luggage.getText().toString().trim();
        int class_type = this.class_type;
        int transport_type = this.transport_type;

        // Extract city names from the origin and destination strings
        origin = extractCityName(origin).trim();
        destination = extractCityName(destination).trim();

        userFlightInfo = new UserFlightInformation(
                origin, destination, departureDate, returnDate, passenger, baby, dog, luggage, class_type, transport_type, dayOfWeek
        );

        constraintLayout.setVisibility(View.GONE);
        getChildFragmentManager().beginTransaction().replace(R.id.transport_booking_fragment_container, new FlightsFragment()).commit();

//        Intent intent = new Intent(getActivity(), YourTargetActivity.class);
//        intent.putExtra("userFlightInfo", userFlightInfo);
//        startActivity(intent);

    }
    private String extractCityName(String text) {
        // Use regular expression to extract the city name (everything before the first parenthesis)
        return text.replaceAll(" \\(.*\\)$", "");
    }

    private void showOptionsDialog(TextInputEditText origin) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select an option");
        builder.setItems(Flight.validCities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Set the selected option to the EditText
                origin.setText(Flight.getValidCities().get(i)+ " (" + Flight.abbreviated_city(Flight.getValidCities().get(i)) + ")");
            }
        });
        builder.show();
    }

}