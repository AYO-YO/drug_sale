package cn.fanbaby.drug_sale.bean;

public class Cart {
    private int user_id;
    private int drug_id;
    private int num;

    @Override
    public String toString() {
        return "Cart{" +
                "user_id=" + user_id +
                ", drug_id=" + drug_id +
                ", num=" + num +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
