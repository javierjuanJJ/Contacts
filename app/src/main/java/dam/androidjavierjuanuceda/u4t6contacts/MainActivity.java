package dam.androidjavierjuanuceda.u4t6contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private static final int REQUEST_CONTACTS = 1;
    private static String[] PERMISSIONS_CONTACTS = {Manifest.permission.READ_CONTACTS};
    TextView emptyList;
    MyContacts myContacts;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyList=findViewById(R.id.tvEmptyList);
        try {

            if (checkPermissions()){
                setListAdapter();
                putInformation(true);
            }
        }
        catch (SecurityException e){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            putInformation(checkPermissions());
        }

        setUI();





    }

    private void setUI() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    private void setListAdapter() {
        myContacts= new MyContacts(this);
        recyclerView.setAdapter(new MyAdapter(myContacts));
        if (myContacts.getCount() > 0) findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);

    }

    private boolean checkPermissions() {

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,MainActivity.PERMISSIONS_CONTACTS,MainActivity.REQUEST_CONTACTS);
            return false;
        }
        else {
            return true;
        }

    }

    private void putInformation(boolean available) {


        if (available) {
            setUI();
        }
        else {
            emptyList.setText(getString(R.string.no_contacts_available));
            Toast.makeText(this, getString(R.string.contacts_read_right_required), Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setListAdapter();

            }
            else Toast.makeText(this, getString(R.string.contacts_read_right_required), Toast.LENGTH_SHORT).show();
        }
        else super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
