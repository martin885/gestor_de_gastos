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

import ar.com.sumo.gestor_de_gastos.Transacciones.CategoriaTransaccion;
import ar.com.sumo.gestor_de_gastos.R;

public class AdapterRecyclerViewGastos extends RecyclerView.Adapter<AdapterRecyclerViewGastos.ViewHolderGastos> implements View.OnClickListener {
    ArrayList<CategoriaTransaccion> listDatos;
    private View.OnClickListener listener;

    public AdapterRecyclerViewGastos(ArrayList<CategoriaTransaccion> listDatos) {
        this.listDatos = listDatos;
    }


    @NonNull
    @Override
    public AdapterRecyclerViewGastos.ViewHolderGastos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gastos_categoria, null, false);
        view.setOnClickListener(this);
        return new ViewHolderGastos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewGastos.ViewHolderGastos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolderGastos extends RecyclerView.ViewHolder {


        TextView nombreCategoria;
        TextView colorCategoria;


        public ViewHolderGastos(View itemView) {
            super(itemView);
            nombreCategoria = itemView.findViewById(R.id.nombre_categoria);
            colorCategoria = itemView.findViewById(R.id.color_categoria);
        }

        public void asignarDatos(CategoriaTransaccion datos) {
            nombreCategoria.setText(datos.getNombre());
            Drawable mDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.categoria_color);
            mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(datos.getColor()), PorterDuff.Mode.SRC));

            final int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                colorCategoria.setBackgroundDrawable(mDrawable);
            } else {
                colorCategoria.setBackground(mDrawable);
            }
        }
    }


}
