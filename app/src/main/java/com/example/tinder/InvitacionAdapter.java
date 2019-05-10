package com.example.tinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tinder.Model.CardOfPeople;

import java.util.List;

public class InvitacionAdapter  extends RecyclerView.Adapter<InvitacionAdapter.MyViewHolder>{

    private List<CardOfPeople> peopleList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_column,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CardOfPeople cardOfPeople = peopleList.get(i);
        myViewHolder.name.setText(cardOfPeople.getName());
        myViewHolder.age.setText(cardOfPeople.getAge());
        myViewHolder.description.setText(cardOfPeople.getDescription());
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, age;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.person_name);
            description = view.findViewById(R.id.person_description);
            age = view.findViewById(R.id.person_age);
        }
    }

    public InvitacionAdapter (List<CardOfPeople> peopleList){
        this.peopleList = peopleList;
    }

}
