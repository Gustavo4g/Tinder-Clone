package com.example.tinder.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.tinder.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public abstract class SearchDialog extends Dialog {
    private static final String TAG = "SearchDialog";

    private EditText searchByName;
    private CheckBox femaleCB;
    private CheckBox maleCB;
    private EditText minAge;
    private EditText maxAge;
    private Button searchButton;

    public SearchDialog(Context context) {
        super(context);
    }

    private static String getTheFuckingBirthday(int age) {
        Calendar birthday = Calendar.getInstance();

        birthday.set(Calendar.YEAR, birthday.get(Calendar.YEAR) - age);

        return new SimpleDateFormat("yyyy-MM-dd").format(birthday.getTime());
    }

    protected abstract void onSearch(HashMap<String, String> parameters);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialog);

        searchByName = findViewById(R.id.search_by_name);
        femaleCB = findViewById(R.id.female);
        maleCB = findViewById(R.id.male);
        minAge = findViewById(R.id.minAge);
        maxAge = findViewById(R.id.maxAge);
        searchButton = findViewById(R.id.search);

        searchButton.setOnClickListener(v -> {
            HashMap<String, String> parameters = new HashMap<>();

            parameters.put("displayName.contains", searchByName.getText().toString());

            String minAgeString = this.minAge.getText().toString();
            int minAge = -1;

            if (!minAgeString.isEmpty()) {
                minAge = Integer.parseInt(minAgeString);
            }

            String maxAgeString = this.maxAge.getText().toString();
            int maxAge = -1;

            if (!maxAgeString.isEmpty()) {
                maxAge = Integer.parseInt(maxAgeString);
            }

            if (maxAge != -1 && minAge > maxAge) {
                Log.d(TAG, "onClick: L'usuari es tonto: minAge > maxAge");
            } else {
                if (minAge != -1) {
                    parameters.put("birthDate.greaterOrEqualThan", getTheFuckingBirthday(minAge));
                }

                if (maxAge != -1) {
                    parameters.put("birthDate.lessOrEqualThan", getTheFuckingBirthday(maxAge));
                }
            }

            boolean female = this.femaleCB.isChecked(); // 1
            boolean male = this.maleCB.isChecked(); // 2

            if (!(female && male)) {
                if (female) {
                    parameters.put("genderId.equals", "1");
                } else if (male) {
                    parameters.put("genderId.equals", "2");
                }
            }

            // Li passem els parametres a la activity que implementi la funcio onSearch
            onSearch(parameters);

            this.dismiss();
        });
    }


}
