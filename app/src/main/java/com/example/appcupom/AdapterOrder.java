package com.example.appcupom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterOrder extends BaseAdapter {

    private List<Order> orderList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterOrder(Context context, List<Order> listaOrder){
        this.orderList = listaOrder;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orderList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte item;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvListaOrderNumber = convertView.findViewById(R.id.tvListaOrderNumber);
            item.tvPrice = convertView.findViewById(R.id.tvListaPrice);
            item.tvQuantity = convertView.findViewById(R.id.tvListaQuantity);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag(item);
        }else{
            item = (ItemSuporte) convertView.getTag();
        }

        Order order = orderList.get(position);
        item.tvListaOrderNumber.setText(order.getOrderNumber());
        item.tvPrice.setText(order.getTotalPrice().toString());
        item.tvQuantity.setText(order.getTotalQuantity().toString());

        return convertView;
    }

    private class ItemSuporte{
        TextView tvListaOrderNumber, tvPrice, tvQuantity;
        LinearLayout layout;
    }
}
