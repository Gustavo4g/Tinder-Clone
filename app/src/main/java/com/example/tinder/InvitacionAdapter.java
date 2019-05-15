package com.example.tinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Activities.PofileActivity;
import com.example.tinder.Connection.TinderManager;
import com.example.tinder.Model.CardOfPeople;

import java.util.Calendar;
import java.util.List;

public class InvitacionAdapter  extends RecyclerView.Adapter<InvitacionAdapter.ViewHolder> {
    private List<CardOfPeople> peopleList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_column, viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CardOfPeople profile = peopleList.get(i);

        viewHolder.name.setText(profile.getDisplayName());
        viewHolder.description.setText(profile.getAboutMe());

        if (profile.getBirthDate() != null){
            int year = Integer.parseInt(profile.getBirthDate().substring(0, 4));
            int month = Integer.parseInt(profile.getBirthDate().substring(5, 7));
            int day = Integer.parseInt(profile.getBirthDate().substring(8, 10));
            viewHolder.age.setText(getAge(year, month, day));
        } else {
            viewHolder.age.setText("-");
        }

        if (profile.getPicture() != null){
            byte[] decodedString = Base64.decode(profile.getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewHolder.image.setImageBitmap(decodedByte);
            viewHolder.image.setOnClickListener(v -> click(profile, viewHolder));
        } else {
            viewHolder.image.setImageResource(R.drawable.iscle);
        }
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, age;
        ImageView image;
        public View view;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            image = view.findViewById(R.id.person_photo);
            name = view.findViewById(R.id.person_name);
            description = view.findViewById(R.id.person_description);
            age = view.findViewById(R.id.person_age);
        }

        public View getView() {
            return view;
        }
    }

    public InvitacionAdapter (List<CardOfPeople> peopleList){
        this.peopleList = peopleList;
    }

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return Integer.toString(age);
    }

    private void click(CardOfPeople cardOfPeople, ViewHolder viewHolder){

        Context context = viewHolder.getView().getContext();
        Intent intent = new Intent(context, PofileActivity.class);

        TinderManager.getInstance().setAaaaa(cardOfPeople);
        context.startActivity(intent);
    }
}
