package cn.fanbaby.drug_sale.bean;

public class User {
    private int _id;
    private String name;
    private String password;
    private int role;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "_id=" + _id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", role=" + role + '}';
    }
}
