package com.example.mvvmpractice.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmpractice.Adapter.ContestAdapter;
import com.example.mvvmpractice.Model.Contest;
import com.example.mvvmpractice.R;
import com.example.mvvmpractice.ViewModel.ContestViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContestAdapter contestAdapter;
    ContestViewModel contestViewModel;

    public static final String TAG = "Codechef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        initViewModel();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contestAdapter = new ContestAdapter(new ArrayList<>());
        recyclerView.setAdapter(contestAdapter);
    }

    private void initViewModel() {
        contestViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(ContestViewModel.class);

        contestViewModel.getContestsFromAPIAndStore();
        contestViewModel.getAllContests().observe(this, new Observer<List<Contest>>() {
            @Override
            public void onChanged(List<Contest> contestList) {
                Log.d(TAG, "onChanged: ");
                contestAdapter.setContestList(contestList);
            }
        });
    }



}