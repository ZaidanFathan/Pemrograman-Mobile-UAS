package id.pemrogramanmobile.tugasutspemrogramanmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    private ArrayList<String> dataList;
    private Context mContext;

    public CustomAdapter(Context context, ArrayList<String> data) {
        super(context, R.layout.list_item_layout, data);
        this.mContext = context;
        this.dataList = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout, null);
        }

        // Ambil elemen dari layout item
        TextView textTitle = view.findViewById(R.id.textTitle);

        // Set nilai untuk teks judul dan subjudul dari ArrayList
        String item = dataList.get(position);
        textTitle.setText(item); // Misalnya, judul diambil dari item

        return view;
    }
}
