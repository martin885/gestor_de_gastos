package ar.com.sumo.gestor_de_gastos.Adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.sumo.gestor_de_gastos.R;
import ar.com.sumo.gestor_de_gastos.Services.Leyenda;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

public class AdapterRecyclerViewLegend extends RecyclerView.Adapter<AdapterRecyclerViewLegend.ViewHolderDatos> {

    ArrayList<Leyenda> listDatos;

    public AdapterRecyclerViewLegend(ArrayList<Leyenda> listDatos) {
        this.listDatos = listDatos;
    }


    @NonNull
    @Override
    public AdapterRecyclerViewLegend.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_legend, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewLegend.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {


        TextView monto;
        TextView nombreCategoria;
        TextView colorCategoria;


        public ViewHolderDatos(View itemView) {
            super(itemView);
            colorCategoria = itemView.findViewById(R.id.color_categoria);
            nombreCategoria = itemView.findViewById(R.id.nombre_categoria);
            monto = itemView.findViewById(R.id.monto);
        }

        public void asignarDatos(Leyenda datos) {
            monto.setText(String.format("%.2f", datos.getValorTotal()));
            nombreCategoria.setText(datos.getCategoria().getNombre());
            Drawable mDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.categoria_color);
            mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(datos.getCategoria().getColor()), PorterDuff.Mode.SRC));

            final int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                colorCategoria.setBackgroundDrawable(mDrawable);
            } else {
                colorCategoria.setBackground(mDrawable);
            }

        }
    }

}
