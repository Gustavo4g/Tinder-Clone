package com.example.tinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        if (cardOfPeople.getPicture() != null){
            byte[] decodedString = Base64.decode(cardOfPeople.getPicture(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            myViewHolder.image.setImageBitmap(decodedByte);
        }else{
            myViewHolder.image.setImageResource(R.drawable.iscle);
        }
        myViewHolder.name.setText(cardOfPeople.getDisplayName());
        if (cardOfPeople.getBirthDate() != null){
            int age = 2019 - Integer.parseInt(cardOfPeople.getBirthDate().substring(0,4));
            Log.d("Hola",age+"");
            myViewHolder.age.setText(age+"");
        }else{
            myViewHolder.age.setText("Not Avaliable");
        }
        myViewHolder.description.setText(cardOfPeople.getAboutMe());
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, age;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.person_photo);
            name = view.findViewById(R.id.person_name);
            description = view.findViewById(R.id.person_description);
            age = view.findViewById(R.id.person_age);
        }
    }

    public InvitacionAdapter (List<CardOfPeople> peopleList){
        this.peopleList = peopleList;
    }


}
