package dam.androidjavierjuanuceda.u4t6contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener , MyAdapter.OnItemLongClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    TextView emptyList;
    MyContacts myContacts;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyList=findViewById(R.id.tvEmptyList);
        if (!checkPermissions()) emptyList.setText(getString(R.string.no_contacts_available));
    }

    private void setUI() {

        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setListAdapter();

    }



    private void setListAdapter() {
        myContacts= new MyContacts(this);
        mAdapter = new MyAdapter(myContacts, this::OnItemClick, this::OnItemLongClickListener);
        recyclerView.setAdapter(mAdapter);
        
        if (myContacts.getCount() > 0) findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);

    }



    private boolean checkPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        boolean are_contacts_available;
        try {
            are_contacts_available=(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED);
        }
        catch (Exception e){
            are_contacts_available=false;
        }
        return are_contacts_available;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setUI();
                } else
                    Toast.makeText(this, getString(R.string.contacts_read_right_required), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnItemClick(ContactItem Contact) {
        Log.i("Apretado","Apretado corto " + Contact);
    }


    @Override
    public boolean OnItemLongClickListener(ContactItem contactItem) {
        Log.i("Apretado","Apretado largo");


        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactItem.getId()));
        intent.setData(uri);
        startActivity(intent);

        return false;
    }
}
