package com.example.examenesther;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private MyList lista;

    public MyAdapter(Context context, int layout, MyList lista) {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {
        public TextView textView_Nombre;
        public TextView textView_Apellido;
        public TextView textView_Identificacion;
        public TextView textView_Cargo;
        public TextView textView_Area;
        public TextView textView_Telefono;
        public ImageView imageView_Imagen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder myholder;
        if ((convertView == null || convertView.getTag() == null)) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.item_list, null);
            myholder = new ViewHolder();
            myholder.textView_Nombre = (TextView) convertView.findViewById((R.id.textView_Nombre));
            myholder.textView_Apellido = (TextView) convertView.findViewById((R.id.textView_Apellidos));
            myholder.textView_Identificacion = (TextView) convertView.findViewById(R.id.textView_Identificacion);
            myholder.textView_Cargo = (TextView) convertView.findViewById(R.id.textView_Cargo);
            myholder.textView_Area = (TextView) convertView.findViewById(R.id.textView_Area);
            myholder.textView_Telefono = (TextView) convertView.findViewById(R.id.textView_Telefono);
            myholder.imageView_Imagen = (ImageView) convertView.findViewById(R.id.imageView_Imagen);
        } else {
            myholder = (ViewHolder) convertView.getTag();
        }
        MyModel current_Item = lista.get(position);
        myholder.textView_Nombre.setText(current_Item.getNombre());
        myholder.textView_Apellido.setText(current_Item.getApellidos());
        myholder.textView_Identificacion.setText(current_Item.getIdentificacion());
        myholder.textView_Cargo.setText(current_Item.getCargo());
        myholder.textView_Area.setText(current_Item.getArea());
        myholder.textView_Telefono.setText(current_Item.getTelefono());
        myholder.imageView_Imagen.setImageResource(current_Item.getImagen());
        return convertView;
    }
}

