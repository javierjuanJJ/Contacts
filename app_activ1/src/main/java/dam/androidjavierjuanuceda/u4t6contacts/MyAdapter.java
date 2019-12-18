package dam.androidjavierjuanuceda.u4t6contacts;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private MyContacts myContacts;

    MyAdapter(MyContacts myContacts) {
        this.myContacts = myContacts;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        //TODO Activity1: Atributos del holder

        TextView textView_nombre;
        TextView textView_id;
        TextView textView_telefono;
        ImageView imagen_contacto;

        public MyViewHolder(@NonNull View textView_nombre) {
            super(textView_nombre);

            //TODO Activity1: Referencia a los widgets del layout del recyclerview

            this.textView_nombre = textView_nombre.findViewById(R.id.nombre);
            this.textView_id = textView_nombre.findViewById(R.id.contact_id);
            this.textView_telefono = textView_nombre.findViewById(R.id.telefono);
            this.imagen_contacto = textView_nombre.findViewById(R.id.imageView);

        }

        public void bind(ContactItem contactItem) {

            //TODO Activity1: Poner la informacion.

            this.textView_nombre.setText(contactItem.getName());
            this.textView_id.setText(String.valueOf(contactItem.getId()));
            this.textView_telefono.setText(contactItem.getPhone());
            this.imagen_contacto.setImageBitmap(contactItem.getId_imagen());
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate item_layout

        //TODO Activity1: Poner el layout que pondra el recyclerview

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myadapter, parent, false);
        MyViewHolder vh = new MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        //TODO Activity1: Poner el contacto
        viewHolder.bind(myContacts.getContactData(position));
    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }

}
