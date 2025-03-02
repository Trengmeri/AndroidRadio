package com.example.test_ktra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AvatarSpinnerAdapter extends BaseAdapter {

    private Context context;
    private int[] avatars; // Mảng chứa ID của các hình ảnh

    public AvatarSpinnerAdapter(Context context, int[] avatars) {
        this.context = context;
        this.avatars = avatars;
    }

    @Override
    public int getCount() {
        return avatars.length;
    }

    @Override
    public Object getItem(int position) {
        return avatars[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_avatar, parent, false);
        }

        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        imgAvatar.setImageResource(avatars[position]);

        return convertView;
    }
}
