package com.example.test_ktra;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtPhone;
    private RadioGroup radioGroupGender;
    private Spinner spinnerLocation;
    private Spinner spinnerAvatar;
    private Button btnAdd;
    private ListView listViewData;

    private ArrayList<ThongTin> thongTinList; // Danh sách thông tin
    private ThongTinAdapter adapter;         // Adapter cho ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        spinnerLocation = findViewById(R.id.spinnerLocation);
        btnAdd = findViewById(R.id.btnAdd);
        listViewData = findViewById(R.id.listViewData);
        spinnerAvatar = findViewById(R.id.spinnerAvatar);

        // Dữ liệu cho Spinner Avatar (sử dụng ID của ảnh trong drawable)
        int[] avatars = { R.drawable.image12, R.drawable.ic_launcher_foreground};
        AvatarSpinnerAdapter avatarAdapter = new AvatarSpinnerAdapter(this, avatars);
        spinnerAvatar.setAdapter(avatarAdapter);


        // Dữ liệu cho Spinner (Quê quán)
        String[] locations = {"Vui lòng chọn quê quán","Hà Nội", "Nam Định", "Hà Nam", "Thái Bình"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(spinnerAdapter);

        // Dữ liệu cho ListView
        thongTinList = new ArrayList<>();

        // Kết nối Adapter với ListView
        adapter = new ThongTinAdapter(this, R.layout.item_thongtin, thongTinList);
        listViewData.setAdapter(adapter);

        // Xử lý sự kiện nút ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                int selectedLocationPosition = spinnerLocation.getSelectedItemPosition();
                int selectedAvatarPosition = spinnerAvatar.getSelectedItemPosition();

                // Kiểm tra xem tất cả các trường đã được điền đầy đủ chưa
                if (name.isEmpty() || phone.isEmpty() || selectedGenderId == -1 ||
                        selectedLocationPosition == 0 || selectedAvatarPosition == -1) {
                    // Hiển thị thông báo lỗi nếu có trường nào chưa được điền
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Nếu tất cả các trường đã được điền, thêm thông tin mới vào danh sách
                    String gender = ((RadioButton) findViewById(selectedGenderId)).getText().toString();
                    String location = spinnerLocation.getSelectedItem().toString();
                    int selectedAvatar = avatars[selectedAvatarPosition];

                    thongTinList.add(new ThongTin(name, phone, gender, location, selectedAvatar));
                    adapter.notifyDataSetChanged();

                    // Xóa nội dung các trường nhập liệu sau khi thêm thành công
                    edtName.setText("");
                    edtPhone.setText("");
                    radioGroupGender.clearCheck();
                    spinnerLocation.setSelection(0);
                    spinnerAvatar.setSelection(0);

                    Toast.makeText(MainActivity.this, "Đã thêm thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
