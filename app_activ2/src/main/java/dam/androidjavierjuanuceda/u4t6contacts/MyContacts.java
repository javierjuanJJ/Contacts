package dam.androidjavierjuanuceda.u4t6contacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.util.ArrayList;

public class MyContacts extends AppCompatActivity {

    private ArrayList<ContactItem> myDataSet;
    private Context context;

    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();
    }

    private Bitmap retrieveContactPhoto(String contactID) {
        Bitmap photo = null;
        InputStream inputStream = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            inputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(contactID)));
            photo = BitmapFactory.decodeStream(inputStream);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
        return photo;
    }

    private ArrayList<ContactItem> getContacts() {
        ArrayList<ContactItem> contactsList = new ArrayList();
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.Contacts.Entity.DATA1,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.LOOKUP_KEY,
                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.Contacts.PHOTO_URI,
                ContactsContract.Data.PHOTO_THUMBNAIL_URI,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };

        String selectionFilter = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        Cursor contactsCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionFilter, null, ContactsContract.Data.DISPLAY_NAME + " ASC");

        if (contactsCursor != null) {

            int nameIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int _id_int = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data._ID);
            int numberIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int CONTACT_IDIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID);
            int LOOKUP_KEYIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.LOOKUP_KEY);
            int tipo_contacto = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);
            int RAW_CONTACT_IDIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.RAW_CONTACT_ID);
            int PHOTO_THUMBNAIL_URIIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int TYPEIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (contactsCursor.moveToNext()) {
                String name = contactsCursor.getString(nameIndex);
                String number = contactsCursor.getString(numberIndex);
                String CONTACT_IDIndex_number = contactsCursor.getString(CONTACT_IDIndex);
                String RAW_CONTACT_IDIndex_number = contactsCursor.getString(RAW_CONTACT_IDIndex);
                String URI_CONTACT = contactsCursor.getString(LOOKUP_KEYIndex);
                String PHOTO_THUMBNAIL_URIIndex_number = contactsCursor.getString(PHOTO_THUMBNAIL_URIIndex);
                String TYPEIndex_number = contactsCursor.getString(TYPEIndex);
                String tipo_de_contacto = contactsCursor.getString(tipo_contacto);
                String _id = contactsCursor.getString(_id_int);
                Bitmap bitmap = retrieveContactPhoto(CONTACT_IDIndex_number);
                String URI = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                ContactItem contactItem = new ContactItem(bitmap, number, name, Integer.parseInt(CONTACT_IDIndex_number));
                switch (tipo_de_contacto) {
                    case "1": contactItem.setTipo_contacto("HOME"); break;
                    case "2": contactItem.setTipo_contacto("MOBILE"); break;
                    case "3": contactItem.setTipo_contacto("WORK"); break;
                    default: contactItem.setTipo_contacto("OTHER"); break;
                }
                contactItem.set_ID(_id);
                contactItem.set_LOOKUP_KEY(URI_CONTACT);
                contactItem.set_Contact_ID(CONTACT_IDIndex_number);
                contactItem.set_RAW_Contact(RAW_CONTACT_IDIndex_number);
                try {
                    Log.i("imagen", URI);
                } catch (NullPointerException e) {
                    contactItem.setId_imagen(BitmapFactory.decodeResource(context.getResources(), R.drawable.contacto_por_defecto));
                }
                contactsList.add(contactItem);
            }
            contactsCursor.close();
        }
        return contactsList;
    }

    public ContactItem getContactData(int position) {
        return myDataSet.get(position);
    }

    public int getCount() {
        return myDataSet.size();
    }
}
