package com.example.flightbookingapplication.BookingServiceFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightbookingapplication.FlightModel.Flight;
import com.example.flightbookingapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransportBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportBookingFragment extends Fragment {
    private TextInputEditText origin, destination, departure_date, return_date;
    private TextView economy, business;
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
    }

    public void setClass(int position) {
        if (position == 0) {
            economy.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            economy.setTextColor(Color.parseColor("#FFFFFF"));
            business.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            business.setTextColor(Color.parseColor("#089083"));
        } else if (position == 1) {
            business.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.class_background));
            business.setTextColor(Color.parseColor("#FFFFFF"));
            economy.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.economy_background));
            economy.setTextColor(Color.parseColor("#089083"));
        }
    }

    public void setTransport(int position) {
        if (position == 0) {

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
                    else departure_date.setError(null);
                } else if (return_date.hasFocus()) {
                    return_date.setText(formattedDate);
                    if (!validateDate()) {
                        return_date.setError("Return date must be after departure date");
                        return_date.setText("");
                        return_date.clearFocus();
                    }
                    else departure_date.setError(null);
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
                validateAndSetEditTextValue();
            }
        });
        destination.setOnFocusChangeListener((destination, hasFocus) -> {
            if (!hasFocus) {
                validateAndSetEditTextValue();
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

        return view;
    }

    private void validateAndSetEditTextValue() {
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
                showOptionsDialog();
                return;
            }
        }

        // Set the validated or default value to the EditText
        origin.setText(userInput + " (" + Flight.abbreviated_city(userInput) + ")");
    }

    private void showOptionsDialog() {
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