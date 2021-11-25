package tin.ngolamquang.onandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ThemNhanVienActivity extends AppCompatActivity {

    EditText edtTenNhanVien, edtngaysinh;
    RadioButton rdoNam, rdoNu;
    Button btnQuayLai, btnLuu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);

        addControls();

        addEvents();

        updateUI();
    }

    public void updateUI() {
        Intent i = getIntent();

        if(i.hasExtra("NHANVIEN")){
            NhanVien nhanVien = (NhanVien) i.getSerializableExtra("NHANVIEN");

            if(nhanVien.isPhai()){
                rdoNam.setChecked(true);
            }else{
                rdoNu.setChecked(true);
            }

            edtTenNhanVien.setText(nhanVien.getTenNV());

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(nhanVien.getDate());

            edtngaysinh.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));

        }

    }

    public void addEvents() {
        edtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ngayhientai = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                int thanghientai = Calendar.getInstance().get(Calendar.MONTH);
                int namhientai = Calendar.getInstance().get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(ThemNhanVienActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaysinh.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, namhientai, thanghientai, ngayhientai);

                dialog.show();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();

                if(i.hasExtra("NHANVIEN")){

                    String tenNV = edtTenNhanVien.getText().toString();
                    boolean phai = rdoNam.isChecked() ? true : false;

//                27/01/2021 -> ["27", "01", "2021"]
                    String[] dateParse = edtngaysinh.getText().toString().split("/");

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParse[0]));
                    calendar.set(Calendar.MONTH, Integer.parseInt(dateParse[1]));
                    calendar.set(Calendar.YEAR, Integer.parseInt(dateParse[2]));

                    Date ngaysinh = calendar.getTime();

                    NhanVien newNhanVien = MainActivity.nhanViens.get(i.getIntExtra("POSION_NHANVIEN", -1));

                    newNhanVien.setTenNV(tenNV);
                    newNhanVien.setPhai(phai);
                    newNhanVien.setDate(ngaysinh);

                    Toast.makeText(ThemNhanVienActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                    finish();

                }else{
                    String tenNV = edtTenNhanVien.getText().toString();
                    boolean phai = rdoNam.isChecked() ? true : false;

//                27/01/2021 -> ["27", "01", "2021"]
                    String[] dateParse = edtngaysinh.getText().toString().split("/");

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParse[0]));
                    calendar.set(Calendar.MONTH, Integer.parseInt(dateParse[1]));
                    calendar.set(Calendar.YEAR, Integer.parseInt(dateParse[2]));

                    Date ngaysinh = calendar.getTime();

                    NhanVien newNhanVien = new NhanVien();

                    newNhanVien.setId(MainActivity.nhanViens.get(MainActivity.nhanViens.size() - 1).getId() + 1);
                    newNhanVien.setTenNV(tenNV);
                    newNhanVien.setPhai(phai);
                    newNhanVien.setDate(ngaysinh);

                    MainActivity.nhanViens.add(newNhanVien);

                    Toast.makeText(ThemNhanVienActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                    finish();
                }

            }
        });
    }

    public void addControls() {
        edtTenNhanVien = findViewById(R.id.edtTenNV);
        edtngaysinh = findViewById(R.id.edtNgaySinh);
        rdoNam = findViewById(R.id.rdoNam);
        rdoNu = findViewById(R.id.rdoNu);
        btnQuayLai = findViewById(R.id.btnQuaylai);
        btnLuu = findViewById(R.id.btnLuu);
    }
}