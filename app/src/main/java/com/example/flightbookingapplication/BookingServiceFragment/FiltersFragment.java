package com.example.flightbookingapplication.BookingServiceFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flightbookingapplication.CustomViewGroupDraggableBar.DraggableCircle;
import com.example.flightbookingapplication.CustomViewGroupDraggableBar.PositionPriceHelper;
import com.example.flightbookingapplication.FiltersAdapter.DepartureAdapter;
import com.example.flightbookingapplication.FiltersAdapter.SortAdapter;
import com.example.flightbookingapplication.FlightModel.FilterFlight;
import com.example.flightbookingapplication.FlightModel.FilterHelper;
import com.example.flightbookingapplication.FlightModel.FilterOptions;
import com.example.flightbookingapplication.FlightModel.Helper.SortFilterHelper;
import com.example.flightbookingapplication.R;
import com.example.flightbookingapplication.UserFlightInformation;
import com.example.flightbookingapplication.UserInformation;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FiltersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FiltersFragment extends Fragment {
    RecyclerView departure_list, arrival_list, sort_list;
    TextInputEditText min_price, max_price;
    DraggableCircle price_circle;
    private FilterOptions filterOptions;
    private FilterFlight filters;
    public interface onFragmentInteractionListener {
        void onBackPressed();
        void onFilterFlights(FilterFlight filters);
    }
    private onFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(onFragmentInteractionListener listener) {
        mListener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FiltersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FiltersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FiltersFragment newInstance(String param1, String param2) {
        FiltersFragment fragment = new FiltersFragment();
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
            UserFlightInformation userFlightInformation = getArguments().getParcelable("userFlightInfo");
            filters = new FilterFlight(userFlightInformation.getClassType());
            FilterOptions filterOptions1 = getArguments().getParcelable("filterOptions");
            if (filterOptions1 != null) {
                filters.setFilterOptions(filterOptions1);
                filterOptions = filterOptions1;
            }
            else {
                filterOptions = filters.getFilterOptions();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filters, container, false);
        departure_list = view.findViewById(R.id.departure_list);
        DepartureAdapter departureAdapter = new DepartureAdapter();
        departureAdapter.setSelectedPosition(FilterHelper.getPositionFromData(filters.getFilterOptions().getDepartureStartTime(), filters.getFilterOptions().getDepartureEndTime()));
        departureAdapter.setOnItemClickListener(new DepartureAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0) {
                    filters.getFilterOptions().setDepartureStartTime("00:00");
                    filters.getFilterOptions().setDepartureEndTime("06:00");
                }
                else if (position == 1) {
                    filters.getFilterOptions().setDepartureStartTime("06:00");
                    filters.getFilterOptions().setDepartureEndTime("12:00");
                }
                else if (position == 2) {
                    filters.getFilterOptions().setDepartureStartTime("12:00");
                    filters.getFilterOptions().setDepartureEndTime("18:00");
                }
                else if (position == 3) {
                    filters.getFilterOptions().setDepartureStartTime("18:00");
                    filters.getFilterOptions().setDepartureEndTime("23:59");
                }
                else {
                    filters.getFilterOptions().setDepartureStartTime("00:00");
                    filters.getFilterOptions().setDepartureEndTime("23:59");
                }
            }
        });
        departure_list.setAdapter(departureAdapter);
        departure_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        departure_list.scrollToPosition(departureAdapter.getSelectedPosition());
        DepartureAdapter arrivalAdapter = new DepartureAdapter();
        arrivalAdapter.setSelectedPosition(FilterHelper.getPositionFromData(filters.getFilterOptions().getArrivalStartTime(), filters.getFilterOptions().getArrivalEndTime()));
        arrivalAdapter.setOnItemClickListener(new DepartureAdapter.onItemClickListener() {
                                                  @Override
                                                  public void onItemClick(int position) {
                                                      if (position == 0) {
                                                          filters.getFilterOptions().setArrivalStartTime("00:00");
                                                          filters.getFilterOptions().setArrivalEndTime("06:00");
                                                      }
                                                      else if (position == 1) {
                                                          filters.getFilterOptions().setArrivalStartTime("06:00");
                                                          filters.getFilterOptions().setArrivalEndTime("12:00");
                                                      }
                                                      else if (position == 2) {
                                                          filters.getFilterOptions().setArrivalStartTime("12:00");
                                                          filters.getFilterOptions().setArrivalEndTime("18:00");
                                                      }
                                                      else if (position == 3) {
                                                          filters.getFilterOptions().setArrivalStartTime("18:00");
                                                          filters.getFilterOptions().setArrivalEndTime("23:59");
                                                      }
                                                      else {
                                                          filters.getFilterOptions().setArrivalStartTime("00:00");
                                                          filters.getFilterOptions().setArrivalEndTime("23:59");
                                                      }
                                                  }
                                              });
        arrival_list = view.findViewById(R.id.arrival_list);
        arrival_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        arrival_list.setAdapter(arrivalAdapter);
        arrival_list.scrollToPosition(arrivalAdapter.getSelectedPosition());
        sort_list = view.findViewById(R.id.sort_list);
        SortAdapter sortAdapter = new SortAdapter();
        sortAdapter.setSelectedPosition(SortFilterHelper.getSortOptionsFromString(filters.getFilterOptions().getSortOption()));
        sortAdapter.setOnItemSelectedListener(new SortAdapter.onItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                filters.getFilterOptions().setSortOption(FilterOptions.options[position]);
            }
        });
        sort_list.setAdapter(sortAdapter);
        sort_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        sort_list.scrollToPosition(sortAdapter.getSelectedPosition());
        ImageView back_button = view.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBackPressed();
            }
        });
        ImageView reset_button = view.findViewById(R.id.reset_button);

        ImageView done_button = view.findViewById(R.id.done_button);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFilterFlights(filters);
            }
        });
        min_price = view.findViewById(R.id.min_price);
        max_price = view.findViewById(R.id.max_price);
        price_circle = view.findViewById(R.id.price_circle);
        price_circle.setGetCx1Cx2Listener(new DraggableCircle.getCx1Cx2Listener() {
            @Override
            public float getCx1() {
                return PositionPriceHelper.PriceToPosition(filters.getFilterOptions().getMinPrice(), 974);
            }

            @Override
            public float getCx2() {
                return PositionPriceHelper.PriceToPosition(filters.getFilterOptions().getMaxPrice(), 974);
            }
        });
        addTextWatchers();
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departureAdapter.setSelectedPosition(-1);
                departureAdapter.notifyDataSetChanged();
                arrivalAdapter.setSelectedPosition(-1);
                arrivalAdapter.notifyDataSetChanged();
                sortAdapter.setSelectedPosition(2);
                sortAdapter.notifyDataSetChanged();
                filters.reset();
                min_price.setText("0");
                max_price.setText("500");
                price_circle.setPosForCircle1(0);
                price_circle.setPosForCircle2(price_circle.getWidth());
            }
        });
        initPrice();
        return view;
    }
    private void addTextWatchers() {
        CurrencyTextWatcher currencyTextWatcher1 = new CurrencyTextWatcher(min_price, true);
        min_price.addTextChangedListener(currencyTextWatcher1);
        price_circle.setOnDragCircle1Listener(currencyTextWatcher1);
        CurrencyTextWatcher currencyTextWatcher2 = new CurrencyTextWatcher(max_price, false);
        max_price.addTextChangedListener(currencyTextWatcher2);
        price_circle.setOnDragCircle2Listener(currencyTextWatcher2);
//        min_price.setOnFocusChangeListener((min_price, hasFocus) -> {
//            if (!hasFocus) {
////                String inputString = this.min_price.getText().toString().substring(1);
////                if (1 > inputString.length() - 4) inputString = "0";
////                else inputString = inputString.substring(1, inputString.length() - 4);
//                String inputString = this.min_price.getText().toString().replaceAll("[^\\d]", "");
//                int inputNumber = Integer.parseInt(inputString);
//                filters.getFilterOptions().setMinPrice(inputNumber);
//                price_circle.setPosForCircle1(PositionPriceHelper.PriceToPosition(inputNumber, price_circle.getWidth()));
//            }
//        });
//        max_price.setOnFocusChangeListener((min_price, hasFocus) -> {
//            if (!hasFocus) {
//                String inputString = this.max_price.getText().toString().replaceAll("[^\\d]", "");
//                if (1 > inputString.length() - 4) inputString = "0";
//                else inputString = inputString.substring(1, inputString.length() - 4);
//                int inputNumber = Integer.parseInt(inputString);
//                filters.getFilterOptions().setMaxPrice(inputNumber);
//                price_circle.setPosForCircle2(PositionPriceHelper.PriceToPosition(inputNumber, price_circle.getWidth()));
//            }
//        });
    }

    private void initPrice() {
//        min_price.setText(NumberFormat.getCurrencyInstance(Locale.US).format(filters.getFilterOptions().getMinPrice()));
//        max_price.setText(NumberFormat.getCurrencyInstance(Locale.US).format(filters.getFilterOptions().getMaxPrice()));
        min_price.setText(String.valueOf(filters.getFilterOptions().getMinPrice()));
        max_price.setText(String.valueOf(filters.getFilterOptions().getMaxPrice()));
        Log.d("min_price", String.valueOf(filters.getFilterOptions().getMinPrice()));
        Log.d("max_price", String.valueOf(filters.getFilterOptions().getMaxPrice()));
        Log.d("price_circle", String.valueOf(price_circle.getWidth()));
        price_circle.setPosForCircle1(PositionPriceHelper.PriceToPosition(filters.getFilterOptions().getMinPrice(), 974));
        price_circle.setPosForCircle2(PositionPriceHelper.PriceToPosition(filters.getFilterOptions().getMaxPrice(), 974));
    }

    private class CurrencyTextWatcher implements TextWatcher, DraggableCircle.onDragCircle1Listener, DraggableCircle.onDragCircle2Listener {
        private final TextInputEditText editText;
        private String previousText;
        private boolean isMinPrice;
        private boolean isEditing; // To prevent recursion

        public CurrencyTextWatcher(TextInputEditText editText, boolean isMinPrice) {
            this.editText = editText;
            this.isMinPrice = isMinPrice;
            this.isEditing = false;
            if (isMinPrice) previousText = String.valueOf(filters.getFilterOptions().getMinPrice());
            else previousText = String.valueOf(filters.getFilterOptions().getMaxPrice());

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            // No action needed
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (isEditing) return;

            String inputString = charSequence.toString();
            if (inputString.startsWith("$")) {
                inputString = inputString.substring(1); // Remove the currency symbol for parsing
            }
//            if (inputString.endsWith(".00")) {
//                inputString = inputString.substring(0, inputString.length() - 3);
//            }

            inputString = inputString.replaceAll("[^\\d]", ""); // Remove non-digit characters

            if (!inputString.isEmpty()) {
                try {
                    int inputNumber = Integer.parseInt(inputString);
                    if (isMinPrice) {
//                        if (inputNumber >= filters.getFilterOptions().getMaxPrice() - 5 || inputNumber < 0) {
//                            return;
//                        }
                        filters.getFilterOptions().setMinPrice(inputNumber);
                        price_circle.setPosForCircle1(PositionPriceHelper.PriceToPosition(inputNumber, price_circle.getWidth()));
                    } else {
//                        if (inputNumber <= filters.getFilterOptions().getMinPrice() + 5 || inputNumber > 500) {
//                            return;
//                        }
                        filters.getFilterOptions().setMaxPrice(inputNumber);
                        Log.d("max_price", String.valueOf(filters.getFilterOptions().getMaxPrice()));
                        price_circle.setPosForCircle2(PositionPriceHelper.PriceToPosition(inputNumber, price_circle.getWidth()));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (isEditing) return;

            isEditing = true;

            String inputString = editText.getText().toString().replaceAll("[^\\d]", "");
            if (!inputString.isEmpty()) {
                try {
                    int inputNumber = Integer.parseInt(inputString);
//                    if (isMinPrice) {
//                        if (inputNumber >= filters.getFilterOptions().getMaxPrice() - 5 || inputNumber < 0) {
//                            editText.setText(previousText);
//                            editText.setSelection(editText.getText().length());
//                            isEditing = false;
//                            return;
//                        }
//                    }
//                    else {
//                        if (inputNumber <= filters.getFilterOptions().getMinPrice() + 5 || inputNumber > 500) {
//                            editText.setText(previousText);
//                            editText.setSelection(editText.getText().length());
//                            isEditing = false;
//                            return;
//                        }
//                    }
//                    editText.setText("$" + inputNumber +".00");
                    previousText = editText.getText().toString();
                    editText.setText(String.valueOf(inputNumber));
                    editText.setSelection(String.valueOf(inputNumber).length());
                    Log.d("InputNumber", String.valueOf(inputNumber));
                    if (isMinPrice) {
                        filters.getFilterOptions().setMinPrice(inputNumber);
                        price_circle.setPosForCircle1(PositionPriceHelper.PriceToPosition(inputNumber, price_circle.getWidth()));
                    } else {
                        filters.getFilterOptions().setMaxPrice(inputNumber);
                        Log.d("max_price", String.valueOf(filters.getFilterOptions().getMaxPrice()));
                        price_circle.setPosForCircle2(PositionPriceHelper.PriceToPosition(inputNumber, price_circle.getWidth()));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            isEditing = false;
        }

        @Override
        public void onDragCircle1(float x) {
            if (!isMinPrice) return;
            editText.removeTextChangedListener(this);
            int price = PositionPriceHelper.PositionToPrice(x, price_circle.getViewWidth());
            filters.getFilterOptions().setMinPrice(price);
//            String formatted = NumberFormat.getCurrencyInstance(Locale.US).format(price);
            editText.setText(String.valueOf(price));
//            editText.setSelection(formatted.length());
            editText.setSelection(String.valueOf(price).length());
            editText.addTextChangedListener(this);
        }

        @Override
        public void onDragCircle2(float x) {
            if (isMinPrice) return;

            editText.removeTextChangedListener(this);
            int price = PositionPriceHelper.PositionToPrice(x, price_circle.getViewWidth());
            filters.getFilterOptions().setMaxPrice(price);
//            String formatted = NumberFormat.getCurrencyInstance(Locale.US).format(price);
            editText.setText(String.valueOf(price));
//            editText.setSelection(formatted.length());
            editText.setSelection(String.valueOf(price).length());
            editText.addTextChangedListener(this);
        }
    }

}