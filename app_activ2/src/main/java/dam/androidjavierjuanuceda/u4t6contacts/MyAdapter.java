package dam.androidjavierjuanuceda.u4t6contacts;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private MyContacts myContacts;
    private OnItemClickListener click_listener;
    private OnItemLongClickListener long_listener;

    public MyAdapter (MyContacts myContacts , OnItemClickListener listener , OnItemLongClickListener long_listener) {
        this.myContacts=myContacts;
        this.click_listener=listener;
        this.long_listener=long_listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(ContactItem Contact);


    }

    public interface OnItemLongClickListener {
        boolean OnItemLongClickListener(ContactItem contactItem);
    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView_nombre;
        TextView textView_id;
        TextView textView_telefono;
        ImageView imagen_contacto;

        public MyViewHolder(@NonNull View textView_nombre) {
            super(textView_nombre);
            this.textView_nombre=textView_nombre.findViewById(R.id.nombre);
            this.textView_id=textView_nombre.findViewById(R.id.contact_id);
            this.textView_telefono=textView_nombre.findViewById(R.id.telefono);
            this.imagen_contacto=textView_nombre.findViewById(R.id.imageView);

        }

        public void bind (ContactItem contactItem , OnItemClickListener listener , OnItemLongClickListener long_listener){
            this.textView_nombre.setText(contactItem.getName());
            this.textView_id.setText(String.valueOf(contactItem.getId()));
            this.textView_telefono.setText(contactItem.getPhone());
            this.imagen_contacto.setImageBitmap(contactItem.getId_imagen());

            this.imagen_contacto.setOnLongClickListener(v-> long_listener.OnItemLongClickListener(contactItem));
            this.textView_telefono.setOnLongClickListener(v-> long_listener.OnItemLongClickListener(contactItem));
            this.textView_id.setOnLongClickListener(v-> long_listener.OnItemLongClickListener(contactItem));
            this.textView_nombre.setOnLongClickListener(v-> long_listener.OnItemLongClickListener(contactItem));
            this.itemView.setOnLongClickListener(v-> long_listener.OnItemLongClickListener(contactItem));




            this.imagen_contacto.setOnClickListener(v-> listener.OnItemClick(contactItem));
            this.textView_telefono.setOnClickListener(v-> listener.OnItemClick(contactItem));
            this.textView_id.setOnClickListener(v-> listener.OnItemClick(contactItem));
            this.textView_nombre.setOnClickListener(v-> listener.OnItemClick(contactItem));
            this.itemView.setOnClickListener(v-> listener.OnItemClick(contactItem));

        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate item_layout
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myadapter, parent, false);
        MyViewHolder vh = new MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        Log.i("Contacto",myContacts.getContactData(position).toString());
        viewHolder.bind(myContacts.getContactData(position),click_listener,long_listener);
    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }

}
