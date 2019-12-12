package dam.androidjavierjuanuceda.u4t6contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private MyContacts myContacts;

    MyAdapter (MyContacts myContacts) {
        this.myContacts=myContacts;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(TextView view) {
            super(view);
            textView=view;
        }

        public void bind (String contactData){
            textView.setText(contactData);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TextView tv= (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);


        return new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {

        viewHolder.bind(myContacts.getContactData(position));

    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }

}
