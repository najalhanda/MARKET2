package MARKETDTO;

public class Orderlist {

    private int ordNum;
    private int pNum;
    private String seller;
    private String buyer;
    private String sellDate;

    public int getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(int ordNum) {
        this.ordNum = ordNum;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    @Override
    public String toString() {
        return "Orderlist[" +
                "ordNum=" + ordNum +
                ", pNum=" + pNum +
                ", seller='" + seller + '\'' +
                ", buyer='" + buyer + '\'' +
                ", sellDate='" + sellDate + '\'' +
                ']';
    }
}







