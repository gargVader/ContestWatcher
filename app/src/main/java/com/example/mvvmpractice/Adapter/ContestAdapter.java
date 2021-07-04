package com.example.mvvmpractice.Adapter;

import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmpractice.Activity.MainActivity;
import com.example.mvvmpractice.Model.Contest;
import com.example.mvvmpractice.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.mvvmpractice.Activity.MainActivity.TAG;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.ContestViewHolder> {

    List<Contest> contestList;

    public ContestAdapter(List<Contest> contestList) {
        Log.d(TAG, "ContestAdapter: ");
        this.contestList = contestList;
    }

    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_item, parent, false);
        return new ContestViewHolder(view);
    }

    public void setContestList(List<Contest> contestList) {
        this.contestList = contestList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ContestAdapter.ContestViewHolder holder, int position) {
        Contest contest = contestList.get(position);
        holder.contestName.setText(contest.getContestName());
        holder.contestUrl.setText(contest.getContestUrl());
        holder.contestStartTime.setText(contest.getContestStartTime());
        holder.contestEndTime.setText(contest.getContestEndTime());
        holder.contestDuration.setText(String.valueOf(contest.getContestDuration()));
        holder.contestSite.setText(contest.getContestSite());
        holder.contestIn24Hours.setText(contest.getContestIn24Hours());
        holder.contestStatus.setText(contest.getContestStatus());
    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    protected class ContestViewHolder extends RecyclerView.ViewHolder {

        public TextView contestName, contestUrl, contestStartTime, contestEndTime, contestDuration, contestSite, contestIn24Hours, contestStatus;

        public ContestViewHolder(View itemView) {
            super(itemView);
            contestName = itemView.findViewById(R.id.contestName);
            contestUrl = itemView.findViewById(R.id.contestUrl);
            contestStartTime = itemView.findViewById(R.id.contestStartTime);
            contestEndTime = itemView.findViewById(R.id.contestEndTime);
            contestDuration = itemView.findViewById(R.id.contestDuration);
            contestSite = itemView.findViewById(R.id.contestSite);
            contestIn24Hours = itemView.findViewById(R.id.contestIn24Hours);
            contestStatus = itemView.findViewById(R.id.contestStatus);
        }
    }

}
