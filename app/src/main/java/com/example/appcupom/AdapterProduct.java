package com.example.appcupom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterProduct extends BaseAdapter {

    private List<Product> productList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterProduct(Context context, List<Product> listaProduct){
        this.productList = listaProduct;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte item;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvProductName = convertView.findViewById(R.id.tvListaOrderNumber);
            item.tvPrice = convertView.findViewById(R.id.tvListaPrice);
            item.tvQuantity = convertView.findViewById(R.id.tvListaQuantity);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag(item);
        }else{
            item = (ItemSuporte) convertView.getTag();
        }

        Product product = productList.get(position);
        item.tvProductName.setText(product.getProductName());
        item.tvPrice.setText(product.getPrice().toString());
        item.tvQuantity.setText(String.valueOf(product.getQuantity()));

        return convertView;
    }

    private class ItemSuporte{
        TextView tvProductName, tvPrice, tvQuantity;
        LinearLayout layout;
    }
}
