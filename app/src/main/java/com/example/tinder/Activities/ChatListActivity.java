package com.example.tinder.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tinder.ChatListAdapter;
import com.example.tinder.Model.ChatRow;
import com.example.tinder.R;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        // Esto es para la barra de arriba
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        ArrayList<ChatRow> chatList = new ArrayList<>();
        chatList.add(new ChatRow("Gustavo", "Hola guapo ❤"));
        chatList.add(new ChatRow("Natalia", "jaja ya ves! \uD83D\uDE0F tengo ganas de hacerlo ya..."));
        chatList.add(new ChatRow("Emoji man", "\uD83D\uDE0F\uD83D\uDCA9\uD83E\uDD20\uD83E\uDD15\uD83D\uDE25\uD83D\uDC7A\uD83D\uDC85\uD83D\uDC69\u200D\uD83D\uDE92\uD83D\uDC83\uD83D\uDE21\uD83E\uDD70\uD83D\uDE07\uD83D\uDE0E\uD83C\uDF14\uD83D\uDC1F\uD83D\uDC37\uD83C\uDF73\uD83E\uDD5D\uD83C\uDFD3\uD83C\uDFCC️\u200D♀️\uD83C\uDFB8\uD83C\uDFAF\uD83E\uDDE9\uD83D\uDEF4\uD83D\uDE86\uD83C\uDFF0\uD83D\uDCEE\uD83C\uDF21\uD83D\uDD28\uD83E\uDDFA\uD83D\uDCC5\uD83D\uDCE8\uD83E\uDDFC\uD83D\uDD03❓4️⃣\uD83D\uDCAC\uD83D\uDC41️\u200D\uD83D\uDDE8️\uD83E\uDDDF\u200D♂️\uD83E\uDDDA\u200D♂️\uD83D\uDE25\uD83E\uDD29\uD83D\uDE03"));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChatListAdapter chatListAdapter = new ChatListAdapter(chatList);
        recyclerView.setAdapter(chatListAdapter);

    }
}
