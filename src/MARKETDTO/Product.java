package MARKETDTO;

public class Product {
    private int pCategory;
    private int pNum;
    private String pName;
    private int pNewUsed;
    private int pPrice;
    private String pMemo;
    private String pDate;
    private int pLike;
    private String memId;
    private String pState;

    public int getpCategory() {
        return pCategory;
    }

    public void setpCategory(int pCategory) {
        this.pCategory = pCategory;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpNewUsed() {
        return pNewUsed;
    }

    public void setpNewUsed(int pNewUsed) {
        this.pNewUsed = pNewUsed;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public String getpMemo() {
        return pMemo;
    }

    public void setpMemo(String pMemo) {
        this.pMemo = pMemo;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public int getpLike() {
        return pLike;
    }

    public void setpLike(int pLike) {
        this.pLike = pLike;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getpState() {
        return pState;
    }

    public void setpState(String pState) {
        this.pState = pState;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pCategory=" + pCategory +
                ", pNum=" + pNum +
                ", pName='" + pName + '\'' +
                ", pNewUsed=" + pNewUsed +
                ", pPrice=" + pPrice +
                ", pMemo='" + pMemo + '\'' +
                ", pDate='" + pDate + '\'' +
                ", pLike=" + pLike +
                ", memId='" + memId + '\'' +
                ", pState='" + pState + '\'' +
                '}';
    }
}