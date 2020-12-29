package com.example.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChampionsTableListVIewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> users;

    public ChampionsTableListVIewAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    /**
     * @return The size of {@link #users}.
     */
    @Override
    public int getCount() {
        return this.users.size();
    }

    /**
     * @param position The position of the item in the list.
     * @return The item in that position.
     */
    @Override
    public User getItem(int position) {
        return this.users.get(position);
    }

    /**
     * @param position The position of the item in the list.
     * @return The id of the item in that position(in our case, position).
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User userInPosition = this.users.get(position);
        UserData userDataInPosition = userInPosition.getData();
        View v = LayoutInflater.from(this.context).inflate(R.layout.custom_champions_table_list_view, parent, false);

        TextView tvName = v.findViewById(R.id.tvName);
        tvName.setText(tvName.getText().toString() + " " + userInPosition.getUserName()); // Update tvName to show the user name.

        TextView tvNumOfGames = v.findViewById(R.id.tvNumOfGames);
        tvNumOfGames.setText(tvNumOfGames.getText().toString() + " " + userDataInPosition.getTotalNumberOfGames()); // Update tvNumOfGames to show the total number of games.

        TextView tvNumOfWins = v.findViewById(R.id.tvNumOfWins);
        tvNumOfWins.setText(tvNumOfWins.getText().toString() + " " + userDataInPosition.getNumOfVictories()); // Update tvNumOfVictories to show the total number of victories.

        TextView tvNumOfTies = v.findViewById(R.id.tvNumOfTies);
        tvNumOfTies.setText(tvNumOfTies.getText().toString() + " " + userDataInPosition.getNumOfTies()); // Update tvNumOfTies to show the total number of ties.

        TextView tvNumOfDefeats = v.findViewById(R.id.tvNumOfDefeats);
        tvNumOfDefeats.setText(tvNumOfDefeats.getText().toString() + " " + userDataInPosition.getNunOfDefeats()); // Update tvNumOfDefeats to show the total number of defeats.

        return v;
    }
}
