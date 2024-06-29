package com.example.flightbookingapplication.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.flightbookingapplication.AvatarService.AvatarService;
import com.example.flightbookingapplication.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    private static final String TAG = PersonalFragment.class.getSimpleName();
    private final TextInputEditText[] info = new TextInputEditText[4];
    public TextInputEditText[] getInfo() {
        return info;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
    }

    private OnFragmentInteractionListener mListener;

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String mParam3;
    private String mParam4;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2, String param3, String param4) {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        view.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentBack();
            }
        });

        info[0] = view.findViewById(R.id.first_name);
        info[1] = view.findViewById(R.id.last_name);
        info[2] = view.findViewById(R.id.phone_number);
        info[3] = view.findViewById(R.id.email);
        info[0].setText(mParam1);
        info[1].setText(mParam2);
        info[2].setText(mParam3);
        info[3].setText(mParam4);

        ImageView image_avatar = view.findViewById(R.id.avatar_image);
        AvatarService avatarService = new AvatarService();
        Bitmap userAvatar = avatarService.loadImageFromInternalStorage(getContext(), "avatar.png");
        if (userAvatar != null) image_avatar.setImageBitmap(userAvatar);
        else image_avatar.setImageResource(R.drawable.victoria);

        ImageView change_avatar = view.findViewById(R.id.change_image);


        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        image_avatar.setImageURI(imageUri);
                        // Handle the selected image here
                        avatarService.saveImagePNGfromUri(getContext(), imageUri, "avatar_unsaved.png");
                    }
                }
        );

        change_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                imagePickerLauncher.launch(intent);
            }
        });

        view.findViewById(R.id.save_changes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarService.copyImage(getContext(), "avatar_unsaved.png", "avatar.png");
                mListener.onFragmentSaveChanges();
            }
        });


        return view;
    }

}