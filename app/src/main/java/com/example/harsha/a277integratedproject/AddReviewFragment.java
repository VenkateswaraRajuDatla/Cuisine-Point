package com.example.harsha.a277integratedproject;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReviewFragment extends DialogFragment {

    EditText reviewText;
    Button submitButton, cancelButton;
    RatingBar ratingBar;

    public static AddReviewFragment newInstance() {
        return new AddReviewFragment();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Required in order for fragment to take the phone's width
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_review, container, false);

        reviewText   = view.findViewById(R.id.reviewText);
        ratingBar    = view.findViewById(R.id.ratingBar);
        submitButton = view.findViewById(R.id.submitButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = dateFormat.format(date);

                //TODO: Set the current restaurant's id for 'request[3]' below
                String request[] = new String[7];
                request[0] = "http://cuisine-point-mysql.herokuapp.com/reviews/add";
                request[1] = "POST";
                request[2] = "review";
                request[3] = "71";
                request[4] = reviewText.getText().toString();
                request[5] = "" + ratingBar.getRating();
                request[6] = currentDate;

                HttpUrlRequest httpUrlRequest = new HttpUrlRequest();
                httpUrlRequest.execute(request);

                Toast.makeText(getActivity(), "Review submitted!", Toast.LENGTH_LONG).show();
                getDialog().dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
