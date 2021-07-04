package com.example.mvvmpractice.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmpractice.Model.Contest;
import com.example.mvvmpractice.Repository.ContestRepository;
import static com.example.mvvmpractice.Activity.MainActivity.TAG;

import java.util.List;

public class ContestViewModel extends AndroidViewModel {

    private LiveData<List<Contest>> allContests;
    private ContestRepository contestRepository;

    public ContestViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "ContestViewModel: ");
        contestRepository = new ContestRepository(application);
        allContests = contestRepository.getAllContest();
    }

    public void insert(Contest contest) {
        contestRepository.insert(contest);
    }

    public void update(Contest contest) {
        contestRepository.update(contest);
    }

    public void delete(Contest contest) {
        contestRepository.delete(contest);
    }

    public LiveData<List<Contest>> getAllContests() {
        return allContests;
    }

    public void getContestsFromAPIAndStore() {
        contestRepository.makeAPICallAndStore();
    }
}
