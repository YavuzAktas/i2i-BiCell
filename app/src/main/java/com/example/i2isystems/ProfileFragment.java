package com.example.i2isystems;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String phone,email,name,surname;

    public ProfileFragment() {
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        EditText editTextName = view.findViewById(R.id.editTextName);
        EditText editTextSurname = view.findViewById(R.id.editTextSurname);
        EditText editTextPhone = view.findViewById(R.id.editTextPhone);
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);

        Call<SubscriberRequest> packageInfoRequestCall = RetrofitClientInstance.getSubscriber().subscriber();
        packageInfoRequestCall.enqueue(new Callback<SubscriberRequest>() {
            @Override
            public void onResponse(Call<SubscriberRequest> call1, Response<SubscriberRequest> response1) {
                if(response1.isSuccessful()){
                    SubscriberRequest subscriberRequest = response1.body();
                    phone =subscriberRequest.getMsisdn();
                    email = subscriberRequest.getEmail();
                    name = subscriberRequest.getName();
                    surname = subscriberRequest.getSurname();
                }
            }
            @Override
            public void onFailure(Call<SubscriberRequest> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });

        editTextEmail.setText(email);
        editTextName.setText(name);
        editTextPhone.setText(phone);
        editTextSurname.setText(surname);
        Toast.makeText(getActivity(),phone,Toast.LENGTH_LONG).show();
        return view;
    }
}