package com.example.flightbookingapplication.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightbookingapplication.AvatarService.AvatarService;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.UserInformation;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final String PhoneArea = "+38";

    private final AvatarService avatarService = new AvatarService();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    TextView name;
    ImageView  avatar;
    String first_name, last_name, phone_number, email;

    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    public void setAvatar() {
        Bitmap userAvatar = avatarService.loadImageFromInternalStorage(getContext(), "avatar.png");
        if (userAvatar != null) avatar.setImageBitmap(userAvatar);
        else avatar.setImageResource(R.drawable.victoria);
    }

    public void setName(String first_name, String last_name) {
        this.name.setText(first_name + " " + last_name);
        this.first_name = first_name;
        this.last_name = last_name;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(R.string.first_name), first_name);
        editor.putString(String.valueOf(R.string.last_name), last_name);
        editor.apply();
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(R.string.phone), phone_number);
        editor.apply();
    }

    public void setEmail(String email) {
        this.email = email;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(R.string.email), email);
//        Intent intent = new Intent();
//        UserInformation userInformation = new UserInformation("mao", "", "");
//        intent.putExtra("user information", userInformation);
        editor.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main_view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = main_view.findViewById(R.id.name);
        // Store the name "Victoria Yoker" in SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.preferences), MODE_PRIVATE);

        // Retrieve the name from SharedPreferences
        first_name = sharedPreferences.getString(String.valueOf(R.string.first_name), "Victoria");
        last_name = sharedPreferences.getString(String.valueOf(R.string.last_name), "Yoker");
        String storedName = first_name + " " + last_name;
        if (storedName.equals("Victoria Yoker")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(String.valueOf(R.string.first_name), "Victoria");
            editor.putString(String.valueOf(R.string.last_name), "Yoker");
            editor.apply();
        }
        name.setText(storedName);

        phone_number = sharedPreferences.getString(String.valueOf(R.string.phone), "0123456789");
        if (phone_number.equals("0123456789")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(String.valueOf(R.string.phone), "0123456789");
            editor.apply();
        }
        email = sharedPreferences.getString(String.valueOf(R.string.email), "victoria.yoker@gmail.com");
        if (email.equals("victoria.yoker@gmail.com")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(String.valueOf(R.string.email), "victoria.yoker@gmail.com");
            editor.apply();
        }

        avatar = main_view.findViewById(R.id.avatar);
        setAvatar();

        main_view.findViewById(R.id.personal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_view.findViewById(R.id.account_nav_bar).setVisibility(View.GONE);
                main_view.findViewById(R.id.menu).setVisibility(View.GONE);
                main_view.findViewById(R.id.avatar).setVisibility(View.GONE);
                main_view.findViewById(R.id.name).setVisibility(View.GONE);
                PersonalFragment personalFragment = PersonalFragment.newInstance(first_name, last_name, phone_number, email);
                personalFragment.setOnFragmentInteractionListener(new PersonalFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getChildFragmentManager().beginTransaction().remove(personalFragment).commit();
                        main_view.findViewById(R.id.account_nav_bar).setVisibility(View.VISIBLE);
                        main_view.findViewById(R.id.menu).setVisibility(View.VISIBLE);
                        main_view.findViewById(R.id.avatar).setVisibility(View.VISIBLE);
                        main_view.findViewById(R.id.name).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFragmentSaveChanges() {
                        TextInputEditText[] info = personalFragment.getInfo();
                        String first_name = info[0].getText().toString();
                        String last_name = info[1].getText().toString();
                        setName(first_name, last_name);
                        String phone_number = info[2].getText().toString();
                        setPhoneNumber(phone_number);
                        String email = info[3].getText().toString();
                        setEmail(email);
                        setAvatar();
                        onFragmentBack();
                    }
                });
                // Create a Bundle to store the name
//                Bundle bundle = new Bundle();
//                bundle.putString("name", storedName);
//                personalFragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().replace(R.id.container, personalFragment).commit();

            }
        });
        return main_view;
    }
}