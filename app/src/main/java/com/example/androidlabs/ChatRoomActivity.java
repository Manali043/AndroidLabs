package com.example.androidlabs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.content.DialogInterface;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<MessageModel> listMessage = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;
    DbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        receiveBtn = (Button)findViewById(R.id.ReceiveBtn);
        dbHelper = new DbHelper(this);
        ChatAdapter adapter = new ChatAdapter(listMessage, getApplicationContext());
        listMessage.addAll(dbHelper.read());
        listView.setAdapter(adapter);
        sendBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            MessageModel model = new MessageModel(message,true);
            listMessage.add(model);
            editText.setText("");

            dbHelper.create(model);
            listView.setAdapter(adapter);
        });

        receiveBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            MessageModel model = new MessageModel(message, false);
            listMessage.add(model);
            editText.setText("");
            dbHelper.create(model);
            listView.setAdapter(adapter);
        });

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setTitle(getString(R.string.delete_confirm_msg))
                    .setPositiveButton(R.string.yes, (DialogInterface dialog, int which) -> {

                        MessageModel message = listMessage.get(position);
                        dbHelper.drop(message);

                        listMessage.remove(position);

                        listView.setAdapter(adapter);

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        });


    }


}