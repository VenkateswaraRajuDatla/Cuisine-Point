package com.example.harsha.a277integratedproject;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewsListActivity extends AppCompatActivity {

    String restaurantId;
    ListView listView;
    Button addReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);

        listView = findViewById(R.id.reviewsList);
        addReviewButton = findViewById(R.id.addReviewButton);
        restaurantId = getIntent().getStringExtra("restaurantId");

        final ArrayList<HashMap<String, String>> reviewsList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("reviews");

        ListAdapter adapter = new SimpleAdapter(
                ReviewsListActivity.this, reviewsList,
                R.layout.review_list_item,
                new String[]{"text", "rating", "date"},
                new int[]{R.id.reviewText, R.id.ratingText, R.id.dateText});
        listView.setAdapter(adapter);

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("restaurantId", restaurantId);

                FragmentManager fm = getSupportFragmentManager();
                AddReviewFragment addReviewFragment = AddReviewFragment.newInstance();
                addReviewFragment.setArguments(bundle);
                addReviewFragment.show(fm, "AddReviewFragment");
            }
        });
    }
}
