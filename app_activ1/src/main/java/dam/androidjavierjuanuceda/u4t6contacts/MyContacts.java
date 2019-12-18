package dam.androidjavierjuanuceda.u4t6contacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyContacts {

    //TODO Activity1: Atributos de myContacts

    private ArrayList<ContactItem> myDataSet;
    private Context context;

    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();
    }

    private Bitmap retrieveContactPhoto(String contactID) {

        Bitmap photo;
        InputStream inputStream = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            inputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver,
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));
            photo = BitmapFactory.decodeStream(inputStream);
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (Exception e) {
            }
        }

        return photo;

    }

    private ArrayList<ContactItem> getContacts() {
        //TODO Activity1: Coger todos los contactos
        ArrayList<ContactItem> contactsList = new ArrayList();

        ContentResolver contentResolver = context.getContentResolver();
        Uri phoneUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode("Phone number"));

        String[] projection = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
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
            int numberIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            int CONTACT_IDIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID);
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
                Bitmap bitmap = retrieveContactPhoto(CONTACT_IDIndex_number);
                String URI = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));


                ContactItem contactItem = new ContactItem(bitmap, number, name, CONTACT_IDIndex);

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
        //TODO Activity1: Recuperar el dato del contacto
        return myDataSet.get(position);
    }

    public int getCount() {
        //TODO Activity1: Coger el numero de contactos,
        return myDataSet.size();
    }

}
