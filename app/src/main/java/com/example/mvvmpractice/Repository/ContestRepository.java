package com.example.mvvmpractice.Repository;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvvmpractice.Api.ContestApi;
import com.example.mvvmpractice.Dao.ContestDao;
import com.example.mvvmpractice.Database.ContestDatabase;
import com.example.mvvmpractice.Model.Contest;
import static com.example.mvvmpractice.Activity.MainActivity.TAG;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContestRepository {

    private ContestDao contestDao;
    private ContestApi contestApi;
    private LiveData<List<Contest>> allContest;

    public ContestRepository(Application application) {
        contestDao = ContestDatabase.getInstance(application).getContestDao();
        allContest = contestDao.getAllContest();
    }

    public void insert(Contest contest) {
        new InsertAsyncTask().execute(contest);
    }

    public void update(Contest contest) {
        new UpdateAsyncTask().execute(contest);
    }

    public void delete(Contest contest) {
        new DeleteAsyncTask().execute(contest);
    }

    public LiveData<List<Contest>> getAllContest() {
        return allContest;
    }

    private class InsertAsyncTask extends AsyncTask<Contest, Void, Void> {
        @Override
        protected Void doInBackground(Contest... contests) {
            contestDao.insert(contests[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Contest, Void, Void> {
        @Override
        protected Void doInBackground(Contest... contests) {
            contestDao.update(contests[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Contest, Void, Void> {
        @Override
        protected Void doInBackground(Contest... contests) {
            contestDao.delete(contests[0]);
            return null;
        }
    }

    public void makeAPICallAndStore() {
        Log.d(TAG, "makeAPICallAndStore: ");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kontests.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        contestApi = retrofit.create(ContestApi.class);

        Call<List<Contest>> call = contestApi.getContests("codeforces");
        call.enqueue(new Callback<List<Contest>>() {
            @Override
            public void onResponse(Call<List<Contest>> call, Response<List<Contest>> response) {
                if (response.isSuccessful()) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            contestDao.deleteAll();
                            contestDao.insertAll(response.body());
                            List<Contest> temp = response.body();
                            Log.d(TAG, "run: tempSize="+temp.size());
                            Log.d(TAG, "run: "+temp.get(0).toString());
                        }
                    });
                    thread.start();
                }
            }

            @Override
            public void onFailure(Call<List<Contest>> call, Throwable t) {

            }
        });

    }


}
