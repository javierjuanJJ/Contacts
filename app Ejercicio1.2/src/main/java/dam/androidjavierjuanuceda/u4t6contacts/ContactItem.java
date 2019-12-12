package dam.androidjavierjuanuceda.u4t6contacts;

public class ContactItem {
    private int id_imagen;
    private String phone;
    private String name;
    private int id;

    public ContactItem(int id_imagen, String phone, String name, int id) {
        this.id_imagen = id_imagen;
        this.phone = phone;
        this.name = name;
        this.id = id;
    }

    public ContactItem(String phone, String name, int id) {
        this.id_imagen = 0;
        this.phone = phone;
        this.name = name;
        this.id = id;
    }

    public ContactItem() {
        this.id_imagen = 0;
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

    public int getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(int id_imagen) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Name: " + name + " phone: " + phone + " image: " + id_imagen + " id: " + id;
    }
}
