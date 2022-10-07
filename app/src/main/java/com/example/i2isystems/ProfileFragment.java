package com.example.i2isystems;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private String phone,email,name,surname,msisdn;
    LoginActivity loginActivity;

    public ProfileFragment() {
    }

    public ProfileFragment(String msisdn) {
        this.msisdn = msisdn;
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        Button buttonQuit = (Button) v.findViewById(R.id.buttonQuit);
        buttonQuit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });


        EditText editTextName = v.findViewById(R.id.editTextName);
        EditText editTextSurname = v.findViewById(R.id.editTextSurname);
        EditText editTextPhone = v.findViewById(R.id.editTextPhone);
        EditText editTextEmail = v.findViewById(R.id.editTextEmail);

        loginActivity = new LoginActivity();
        Toast.makeText(getActivity(),loginActivity.msisdn(),Toast.LENGTH_LONG).show();

        SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setMsisdn("12345679812");

        Call<SubscriberRequest> packageInfoRequestCall = RetrofitClientInstance.getSubscriber().subscriber("12345679812");
        packageInfoRequestCall.enqueue(new Callback<SubscriberRequest>() {
            @Override
            public void onResponse(Call<SubscriberRequest> call1, Response<SubscriberRequest> response1) {
                if(response1.isSuccessful()){
                    SubscriberRequest subscriberRequest1 = response1.body();
                    phone = subscriberRequest1.getMsisdn();
                    email = subscriberRequest1.getEmail();
                    name = subscriberRequest1.getName();
                    surname = subscriberRequest1.getSurname();
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
        return v;
    }
}