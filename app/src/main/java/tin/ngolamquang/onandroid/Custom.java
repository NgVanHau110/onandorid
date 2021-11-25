package tin.ngolamquang.onandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Custom extends BaseAdapter {
    List<NhanVien> nv;
    Context context;

    public Custom(List<NhanVien> nv, Context context) {
        this.nv = nv;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nv.size();
    }

    @Override
    public Object getItem(int position) {
        return nv.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.abc, null);

        TextView txt1, txt2;

        txt1 = convertView.findViewById(R.id.txt1);
        txt2 = convertView.findViewById(R.id.txt2);

        NhanVien nv = (NhanVien) getItem(position);

        txt1.setText(nv.getTenNV());
        txt2.setText(nv.getTenNV());


        return convertView;
    }
}
