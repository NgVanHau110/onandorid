package tin.ngolamquang.onandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter arrayAdapter;
    public static List<NhanVien> nhanViens;

    ListView lvNhanVien;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();

        fakeData();

        addEvents();
    }

    public void addEvents() {
        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = nhanViens.get(position);

                if(nhanVien != null){
                    Intent i = new Intent(MainActivity.this, ThemNhanVienActivity.class);

                    i.putExtra("NHANVIEN", nhanVien);

                    i.putExtra("POSION_NHANVIEN", position);

                    startActivity(i);
                }
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ThemNhanVienActivity.class);

                startActivity(i);
            }
        });

        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = nhanViens.get(position);

                if(nhanVien != null){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                                    .setTitle("Th??ng b??o")
                                                    .setMessage("B???n c?? mu???n x??a nh??n vi??n " + nhanVien.getTenNV())
                                                    .setNegativeButton("H???y b???", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                        }
                                                    }).setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    nhanViens.remove(nhanVien);

                                    Toast.makeText(MainActivity.this, "X??a nh??n vi??n th??nh c??ng", Toast.LENGTH_SHORT).show();

                                    arrayAdapter.notifyDataSetChanged();
                                }
                            }).create();

                    alertDialog.show();
                }

                return true;
            }
        });
    }

    public void fakeData() {
        nhanViens.add(new NhanVien(1, "Nguy???n V??n A", true,Calendar.getInstance().getTime()));
        nhanViens.add(new NhanVien(2, "Nguy???n V??n B", true,Calendar.getInstance().getTime()));
        nhanViens.add(new NhanVien(3, "Nguy???n V??n C", false,Calendar.getInstance().getTime()));
        nhanViens.add(new NhanVien(4, "Nguy???n V??n D", false,Calendar.getInstance().getTime()));

        arrayAdapter.notifyDataSetChanged();

    }

    public void addControls() {
        lvNhanVien = findViewById(R.id.lvNhanVien);
        btnThem = findViewById(R.id.btnThemNhanVien);

        nhanViens = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, nhanViens);

        lvNhanVien.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayAdapter.notifyDataSetChanged();
    }
}