package MARKETDTO;

public class Delivery {

    private int delNum;              // pk
    private int pNum;                // fk: pnum
    private String sDate;
    private String rDate;
    private String delResult;
    private String delKind;
    private int delPrice;
    private int totalPrice;
    private String memId;

    public int getDelNum() {
        return delNum;
    }

    public void setDelNum(int delNum) {
        this.delNum = delNum;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getDelResult() {
        return delResult;
    }

    public void setDelResult(String delResult) {
        this.delResult = delResult;
    }

    public String getDelKind() {
        return delKind;
    }

    public void setDelKind(String delKind) {
        this.delKind = delKind;
    }

    public int getDelPrice() {
        return delPrice;
    }

    public void setDelPrice(int delPrice) {
        this.delPrice = delPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "delNum=" + delNum +
                ", pNum=" + pNum +
                ", sDate='" + sDate + '\'' +
                ", rDate='" + rDate + '\'' +
                ", delResult='" + delResult + '\'' +
                ", delKind='" + delKind + '\'' +
                ", delPrice=" + delPrice +
                ", totalPrice=" + totalPrice +
                ", memId='" + memId + '\'' +
                '}';
    }
}
