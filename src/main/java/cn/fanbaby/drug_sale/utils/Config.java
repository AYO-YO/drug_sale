package cn.fanbaby.drug_sale.utils;

public class Config {

    public String getURL() {
        String HOST = "114.116.105.214";
        int PORT = 3306;
        String DATABASE = "drug_sale";
        return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    }

    public String getUSER() {
        return "drug_sale";
    }

    public String getPWD() {
        return "fanfan123";
    }
}
