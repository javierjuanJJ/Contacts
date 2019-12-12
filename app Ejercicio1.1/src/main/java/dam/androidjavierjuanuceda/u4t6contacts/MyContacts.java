package dam.androidjavierjuanuceda.u4t6contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class MyContacts {

    private ArrayList<String> myDataSet;
    private Context context;

    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();
    }

    private ArrayList<String> getContacts() {
        ArrayList<String> contactsList = new ArrayList();

        ContentResolver contentResolver = context.getContentResolver();

        String[] projection = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER ,
                ContactsContract.Data.CONTACT_ID ,
                ContactsContract.Data.LOOKUP_KEY,
                ContactsContract.Data.RAW_CONTACT_ID ,
                ContactsContract.Data.PHOTO_THUMBNAIL_URI,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };

        String selectionFilter = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor contactsCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionFilter, null, ContactsContract.Data.DISPLAY_NAME + " ASC");

        if (contactsCursor != null) {
            int nameIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int numberIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            int CONTACT_IDIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int LOOKUP_KEYIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            int RAW_CONTACT_IDIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int PHOTO_THUMBNAIL_URIIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            int TYPEIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);


            while (contactsCursor.moveToNext()) {
                String name = contactsCursor.getString(nameIndex);
                String number = contactsCursor.getString(numberIndex);

                String CONTACT_IDIndex_number = contactsCursor.getString(CONTACT_IDIndex);
                String RAW_CONTACT_IDIndex_number = contactsCursor.getString(RAW_CONTACT_IDIndex);

                String PHOTO_THUMBNAIL_URIIndex_number = contactsCursor.getString(PHOTO_THUMBNAIL_URIIndex);
                String TYPEIndex_number = contactsCursor.getString(TYPEIndex);
                contactsList.add(name + " :" + number);
                String n= System.lineSeparator();
                Log.i("Datos Extra", n+"CONTACT_IDIndex_number " +n+ CONTACT_IDIndex_number +n+ " RAW_CONTACT_IDIndex_number " +n+ RAW_CONTACT_IDIndex_number +n+" PHOTO_THUMBNAIL_URIIndex_number " +n+ PHOTO_THUMBNAIL_URIIndex_number+n+ " TYPEIndex_number " +n+ TYPEIndex_number);
            }

            contactsCursor.close();
        }


        return contactsList;
    }

    public String getContactData(int position) {
        return myDataSet.get(position);
    }

    public int getCount() {
        return myDataSet.size();
    }

}
