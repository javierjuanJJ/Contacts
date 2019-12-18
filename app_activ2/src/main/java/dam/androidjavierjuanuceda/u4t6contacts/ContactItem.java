package dam.androidjavierjuanuceda.u4t6contacts;

import android.graphics.Bitmap;

public class ContactItem {
    private Bitmap id_imagen;
    private String phone;
    private String name;
    private String tipo_contacto;

    private String _ID;
    private int id;
    private String Contact_ID;
    private String RAW_Contact;
    private String LOOKUP_KEY;

    public ContactItem(Bitmap imagen, String phone, String name, int id) {
        this.id_imagen = imagen;
        this.phone = phone;
        this.name = name;
        this.id = id;
    }

    public ContactItem(String phone, String name, int id) {
        this.id_imagen = null;
        this.phone = phone;
        this.name = name;
        this.id = id;
    }

    public ContactItem() {
        this.id_imagen = null;
        this.phone = "";
        this.name = "";
        this.id = 0;
    }

    public ContactItem(ContactItem contactItem) {
        this.id_imagen = contactItem.getId_imagen();
        this.phone = contactItem.getPhone();
        this.name = contactItem.getName();
        this.id = contactItem.getId();
    }

    public Bitmap getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(Bitmap id_imagen) {
        this.id_imagen = id_imagen;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTipo_contacto() {
        return tipo_contacto;
    }

    public void setTipo_contacto(String tipo_contacto) {
        this.tipo_contacto = tipo_contacto;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_Contact_ID() {
        return Contact_ID;
    }

    public void set_Contact_ID(String _ID) {
        this.Contact_ID = _ID;
    }
    public String get_RAW_Contact() {
        return RAW_Contact;
    }

    public void set_RAW_Contact(String _ID) {
        this.RAW_Contact = _ID;
    }

    public String get_LOOKUP_KEY() {
        return LOOKUP_KEY;
    }

    public void set_LOOKUP_KEY(String _ID) {
        this.LOOKUP_KEY = _ID;
    }


    public int getId() {
        return id;
    }
    public String getImagen() {
        return id_imagen+"";
    }
    public void setId(int id) {
        this.id = id;
    }

    public String toString()  {
        String nueva_linea=System.lineSeparator();
        StringBuilder texto_contacto= new StringBuilder();
        texto_contacto.append(getName() + " " + getPhone() + " (" + getTipo_contacto() + ") " + nueva_linea);
        texto_contacto.append("_ID " + get_ID() + " Contact_ID" + get_Contact_ID() + " RAW_CONTACT_ID: " + get_RAW_Contact() + nueva_linea);
        texto_contacto.append("LOOKUP_KEY: " + get_LOOKUP_KEY());

        return texto_contacto.toString();
    }
}
