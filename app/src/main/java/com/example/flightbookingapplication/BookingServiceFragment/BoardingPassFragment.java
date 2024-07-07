package com.example.flightbookingapplication.BookingServiceFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightbookingapplication.FlightModel.Flight;
import com.example.flightbookingapplication.FlightModel.FlightSeat;
import com.example.flightbookingapplication.FlightModel.SeatsReservation;
import com.example.flightbookingapplication.HomeActivity;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.UserFlightInformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardingPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardingPassFragment extends Fragment {
    public interface onBackPressedListener {
        void onBackPressed();
    }
    private onBackPressedListener onBackPressedListener;
    public void setOnBackPressedListener(onBackPressedListener listener) {
        this.onBackPressedListener = listener;
    }
    private UserFlightInformation userFlightInformation;
    private Flight flight;
    private SeatsReservation seatsReservation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BoardingPassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoardingPassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardingPassFragment newInstance(String param1, String param2) {
        BoardingPassFragment fragment = new BoardingPassFragment();
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
        if (getArguments() != null) {
            userFlightInformation = getArguments().getParcelable("userFlightInfo");
            flight = getArguments().getParcelable("flight");
            seatsReservation = getArguments().getParcelable("seatsReservation");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boarding_pass, container, false);
        ImageView download_ticket = view.findViewById(R.id.download_ticket);
        download_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the boarding pass ticket view
                View boardingPassView = getView().findViewById(R.id.boarding_pass_ticket);

                // Convert the view to a bitmap
                Bitmap bitmap = getBitmapFromView(boardingPassView);

                // Save the bitmap to storage
                try {
                    Uri imageUri = saveBitmapToStorage(bitmap, "boarding_pass_ticket.png");
                    if (imageUri != null) {
                        Toast.makeText(view.getContext(), "Ticket downloaded successfully.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "Failed to download ticket.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(view.getContext(), "Error downloading ticket.", Toast.LENGTH_SHORT).show();
                }

                // Show a dialog to notify the user
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Booking Successful")
                        .setMessage("Your ticket has been booked successfully.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Create an intent to navigate to the home activity
                                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                                startActivity(intent);

                                // Finish the current activity
                                if (getActivity() != null) {
                                    getActivity().finish();
                                }
                            }
                        })
//                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();

            }
        });
        ImageView back_button = view.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedListener.onBackPressed();
            }
        });
        TextView tv_from_city = view.findViewById(R.id.tv_from_city);
        TextView tv_to_city = view.findViewById(R.id.tv_to_city);
        TextView tv_date = view.findViewById(R.id.tv_date);
        TextView tv_departure = view.findViewById(R.id.tv_departure);
        TextView adult = view.findViewById(R.id.adult);
        TextView ticket_code = view.findViewById(R.id.ticket_code);
        TextView class_type = view.findViewById(R.id.business);
        TextView seat_code = view.findViewById(R.id.seat_code);
        tv_from_city.setText(flight.getOrigin());
        tv_to_city.setText(flight.getDestination());
        String formattedDate = Flight.getMonthDay(flight.getDepartureDate());
        tv_date.setText(formattedDate);
        tv_departure.setText(flight.getDepartureTime());
        if (Integer.parseInt(userFlightInformation.getPassenger()) == 1) {
            adult.setText(userFlightInformation.getPassenger() + " Adult");
        } else {
            adult.setText(userFlightInformation.getPassenger() + " Adults");
        }
        if (Integer.parseInt(userFlightInformation.getBaby()) == 1) {
            adult.append(", " + userFlightInformation.getBaby() + " Child");
        } else if (Integer.parseInt(userFlightInformation.getBaby()) > 1) {
            adult.append(", " + userFlightInformation.getBaby() + " Children");
        }
        ticket_code.setText(flight.getFlightNumber() + "-1");
        for (int i = 0; i < seatsReservation.getReservationSeats().size(); i++) {
            if (i == 0) {
                seat_code.setText(FlightSeat.convertSeatNumberToSeatCode(seatsReservation.getReservationSeats().get(i).getSeatNumber()));
            } else {
                seat_code.append(", " + FlightSeat.convertSeatNumberToSeatCode(seatsReservation.getReservationSeats().get(i).getSeatNumber()));
            }
        }
//        for (int i = 0; i < seatsReservation.getReservationSeats().size(); i++) {
//            if (i == 0) {
//                class_type.setText(seatsReservation.getReservationSeats().get(i).getSeatClass());
//            } else {
//                class_type.append(", " + seatsReservation.getReservationSeats().get(i).getSeatClass());
//            }
//        }
        class_type.setText(seatsReservation.getReservationSeats().get(0).getSeatClass());
        return view;
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private Uri saveBitmapToStorage(Bitmap bitmap, String fileName) throws IOException {
        ContentResolver resolver = requireContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (imageUri != null) {
            try (OutputStream outputStream = resolver.openOutputStream(imageUri)) {
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                }
            } catch (IOException e) {
                if (imageUri != null) {
                    resolver.delete(imageUri, null, null);
                }
                throw e;
            }
        }
        return imageUri;
    }

}