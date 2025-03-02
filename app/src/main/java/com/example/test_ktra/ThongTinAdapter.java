package com.example.test_ktra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ThongTinAdapter extends ArrayAdapter<ThongTin> {

    private Context context;
    private int resource;
    private List<ThongTin> thongTinList;

    public ThongTinAdapter(Context context, int resource, List<ThongTin> thongTinList) {
        super(context, resource, thongTinList);
        this.context = context;
        this.resource = resource;
        this.thongTinList = thongTinList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        // Lấy đối tượng ThongTin tại vị trí hiện tại
        ThongTin thongTin = thongTinList.get(position);

        // Ánh xạ các thành phần giao diện
        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        TextView tvHoTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvSoDienThoai = convertView.findViewById(R.id.tvSoDienThoai);
        TextView tvGioiTinhQueQuan = convertView.findViewById(R.id.tvGioiTinhQueQuan);

        // Gán giá trị cho các thành phần giao diện
        imgAvatar.setImageResource(thongTin.getAvatar());
        tvHoTen.setText(thongTin.getHoTen());
        tvSoDienThoai.setText("SĐT: " + thongTin.getSoDienThoai());
        tvGioiTinhQueQuan.setText(thongTin.getGioiTinh() + " - " + thongTin.getQueQuan());

        return convertView;
    }
}
