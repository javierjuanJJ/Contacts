package dam.androidjavierjuanuceda.u4t6contacts;

import android.graphics.Bitmap;

public class ContactItem {

    //TODO Activity1: Atributos

    private Bitmap id_imagen;
    private String phone;
    private String name;
    private int id;

    //TODO Activity1: Constructor

    public ContactItem(Bitmap imagen, String phone, String name, int id) {
        this.id_imagen = imagen;
        this.phone = phone;
        this.name = name;
        this.id = id;
    }

    //TODO Activity1: Getters y setters

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

    public int getId() {
        return id;
    }

    public String getImagen() {
        return id_imagen + "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Name: " + name + " phone: " + phone + " image: " + id_imagen + " id: " + id;
    }
}
