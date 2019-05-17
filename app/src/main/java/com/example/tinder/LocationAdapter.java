package com.example.tinder;

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

import com.example.tinder.Model.CardOfPeople;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private List<CardOfPeople> peopleList;

    public LocationAdapter(ArrayList<CardOfPeople> peopleList) {
        this.peopleList = peopleList;
    }

    private static String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return Integer.toString(age);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profile_rv_row, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CardOfPeople profile = peopleList.get(i);

        viewHolder.name.setText(profile.getDisplayName());
        viewHolder.description.setText(profile.getAboutMe());

        if (profile.getBirthDate() != null) {
            int year = Integer.parseInt(profile.getBirthDate().substring(0, 4));
            int month = Integer.parseInt(profile.getBirthDate().substring(5, 7));
            int day = Integer.parseInt(profile.getBirthDate().substring(8, 10));
            viewHolder.age.setText(getAge(year, month, day));
        } else {
            viewHolder.age.setText("-");
        }

        if (profile.getPicture() != null) {
            byte[] decodedString = Base64.decode(profile.getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewHolder.image.setImageBitmap(decodedByte);
        } else {
            viewHolder.image.setImageResource(R.drawable.iscle);
        }

        if (profile.getLocation() != null) {
            viewHolder.location.setText(profile.getLocation().getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void setDataset(ArrayList<CardOfPeople> profiles) {
        this.peopleList = profiles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, age, location;
        ImageView image;

        ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.person_photo);
            name = view.findViewById(R.id.person_name);
            description = view.findViewById(R.id.person_description);
            age = view.findViewById(R.id.person_age);
            location = view.findViewById(R.id.person_location);
        }
    }
}
