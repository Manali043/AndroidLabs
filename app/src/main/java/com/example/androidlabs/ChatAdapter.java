package com.example.androidlabs;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.androidlabs.R.id;
import static com.example.androidlabs.R.layout;

public class ChatAdapter extends BaseAdapter {

    private List<MessageModel> messageModels;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return messageModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = inflater.inflate(layout.layout_chat_card, null);
            if (messageModels.get(position).isSend()){

                View imgView = view.findViewById(R.id.senderAvatar);
                imgView.setVisibility(View.INVISIBLE);
                TextView tvsend = view.findViewById(R.id.senderName);
                tvsend.setVisibility(View.INVISIBLE);
                TextView  messageText = (TextView)view.findViewById(id.textViewMessage);
                messageText.setText(messageModels.get(position).message);
                messageText.setGravity(Gravity.END);
            }else {
                View imgView = view.findViewById(id.receiverAvatar);
                imgView.setVisibility(View.INVISIBLE);
                TextView tvrec = view.findViewById(R.id.receiverName);
                tvrec.setGravity(Gravity.START);
                tvrec.setVisibility(View.INVISIBLE);
                TextView  messageText = (TextView)view.findViewById(id.textViewMessage);
                messageText.setText(messageModels.get(position).message);
                messageText.setGravity(Gravity.START);
            }

        }
        return view;
    }
}