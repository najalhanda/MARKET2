package MARKETDAO;

import MARKETDTO.Delivery;
import MARKETDTO.Member;
import MARKETDTO.Orderlist;
import MARKETDTO.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketSQL {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect(){
        con = DBC.DBConnect();
    }

    public void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkId(String memId) {
        boolean checkId = false;
        String sql = "SELECT MEMID FROM MEMBERLIST WHERE MEMID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memId);
            int result = pstmt.executeUpdate();

            if(result>0){
                System.out.print("ID가 중복됩니다. 다시 입력해주세요 >> ");
            } else {
                checkId = true;
            } pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkId;
    }

    public int checkmemNum() {
        int maxNum = 0;
        String sql = "SELECT MAX(MEMNUM) FROM MEMBERLIST";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                maxNum = rs.getInt(1);
            }
            pstmt.close();
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxNum;
    }

    public void memJoin(Member mem) {
        String sql = "INSERT INTO MEMBERLIST VALUES(?,?,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, mem.getMemNum());
            pstmt.setString(2, mem.getMemName());
            pstmt.setString(3, mem.getMemId());
            pstmt.setString(4, mem.getMemPw());
            pstmt.setString(5, mem.getMemPhone());
            pstmt.setString(6, mem.getMemAddr());
            pstmt.setInt(7, mem.getPoint());

            int result = pstmt.executeUpdate();

            if(result>0){
                System.out.println("회원가입 성공!");
                System.out.println(mem.toString());
            } else {
                System.out.println("가입실패!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean checkLogin(String memId, String memPw) {
        boolean checkLogin = false;
        String sql = "SELECT * FROM MEMBERLIST WHERE MEMID=? AND MEMPW=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memId);
            pstmt.setString(2, memPw);

            rs = pstmt.executeQuery();
            if(rs.next()){
                checkLogin = true;
            } else {
                System.out.println("다시 로그인 해주세요!");
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkLogin;
    }

    public void prodInsert(Product prod) {
        String sql = "INSERT INTO PRODUCTLIST VALUES(?,?,?,?,?,?,SYSDATE,0,?,'판매중')";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, prod.getpCategory());
            pstmt.setInt(2, prod.getpNum());
            pstmt.setString(3, prod.getpName());
            pstmt.setInt(4, prod.getpNewUsed());
            pstmt.setInt(5, prod.getpPrice());
            pstmt.setString(6, prod.getpMemo());
            pstmt.setString(7, prod.getMemId());

            int result = pstmt.executeUpdate();

            if(result>0){
                System.out.println("상품등록을 완료했습니다.");
            }
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String newUsedcheck(int i) {
        String txtNewUsed = null;
        if(i==1){
            txtNewUsed = "1.새상품";
        } else {
            txtNewUsed = "2.중고상품";
        }
        return txtNewUsed;
    }


    public void searchCategory(int menu2) {
        int pCategory = menu2*10;
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST WHERE PCATEGORY = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pCategory);

            rs = pstmt.executeQuery();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Product searchpNum(int menu2) {
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST WHERE PNUM = ?";
        Product p = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, menu2);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("[상품 상세설명]");
                System.out.println("     상품 번호 : "+rs.getString("PNUM"));
                System.out.println(" 새상품1/중고2 : : "+rs.getInt("PNEWUSED"));
                System.out.println("     상품 이름 : "+rs.getString("PNAME"));
                System.out.println("         가격 : "+rs.getInt("PPRICE"));
                System.out.println("         설명 : "+rs.getString("PMEMO"));
                System.out.println("       등록일 : "+rs.getDate("PDATE"));
                System.out.println("       만족도 : "+rs.getInt("PLIKE"));
                System.out.println("     판매자ID : "+rs.getString("MEMID"));

                p = new Product();
                p.setpNum(rs.getInt("PNUM"));
                p.setpNewUsed(rs.getInt("PNEWUSED"));
                p.setpName(rs.getString("PNAME"));
                p.setpPrice(rs.getInt("PPRICE"));
                p.setpMemo(rs.getString("PMEMO"));
                p.setpDate(rs.getString("PDATE"));
                p.setpLike(rs.getInt("PLIKE"));
                p.setMemId(rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }


    public void searchDate() {
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST ORDER BY PDATE DESC";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchPrice() {
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST ORDER BY PPRICE ASC";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLike() {
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST ORDER BY PLIKE DESC";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchId() {
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST ORDER BY MEMID ASC";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchNewUsed() {
        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST ORDER BY PNEWUSED ASC";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLastProd() {
        System.out.println("[최신 등록 상품]");
        System.out.println("\t상품번호\t\t새상품(1)/중고(2)\t상품이름\t\t\t\t상품가격\t\t설명\t\t\t\t등록일\t만족도\t판매자ID");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        String sql = "SELECT PNUM, PNEWUSED, PNAME, PPRICE, PMEMO, PDATE, PLIKE, MEMID FROM PRODUCTLIST WHERE ROWNUM<=5 ORDER BY PDATE DESC ";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.print("\t" + rs.getInt("PNUM"));
                System.out.print("\t" + rs.getInt("PNEWUSED"));
                System.out.print("\t" + rs.getString("PNAME"));
                System.out.print("\t" + rs.getInt("PPRICE"));
                System.out.print("\t" + rs.getString("PMEMO"));
                System.out.print("\t" + rs.getDate("PDATE"));
                System.out.print("\t" + rs.getInt("PLIKE"));
                System.out.println("\t" + rs.getString("MEMID"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buy() {

    }

    public int orderNum() {
        int ordNum = 0;
        String sql = "SELECT MAX(ORDNUM) FROM ORDERLIST";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()){
                ordNum = rs.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordNum;

    }

    public boolean orderInsert(Orderlist order) {
        boolean checkOrder = false;
        String  sql = "INSERT INTO ORDERLIST VALUES(?,?,?,?,SYSDATE)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,order.getOrdNum());
            pstmt.setInt(2,order.getpNum());
            pstmt.setString(3, order.getSeller());
            pstmt.setString(4,order.getBuyer());

            rs = pstmt.executeQuery();

            if(rs.next()){
                checkOrder = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkOrder;

    }

    public int delNum() {
        int delNum = 0;
        String sql = "SELECT MAX(DELNUM) FROM DELIVERY";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                delNum = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return delNum;
    }

    public void delInsert(Delivery del, Orderlist order) {
        String sql = "INSERT INTO DELIVERY VALUES(?,?,SYSDATE,SYSDATE,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, del.getDelNum());
            pstmt.setInt(2, del.getpNum());
            pstmt.setString(3, del.getDelResult());
            pstmt.setString(4, del.getDelKind());
            pstmt.setInt(5, del.getDelPrice());
            pstmt.setInt(6, del.getTotalPrice());
            pstmt.setString(7, order.getBuyer());

            int result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delSelect(Delivery del, int pNum, Orderlist order) {
        String sql = "SELECT DELNUM, PNUM, DELRESULT, DELKIND, DELPRICE, TOTALPRICE FROM DELIVERY WHERE PNUM=? AND MEMID=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pNum);
            pstmt.setString(2, order.getBuyer());

            rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.println("   배송번호 : "+rs.getInt("DELNUM"));
                System.out.println("   상품번호 : "+rs.getInt("PNUM"));
                System.out.println("   배송상태 : "+rs.getString("DELRESULT"));
                System.out.println("   배송종류 : "+rs.getString("DELKIND"));
                System.out.println("   배송비용 : "+rs.getInt("DELPRICE"));
                System.out.println("상품+배송료 : "+rs.getInt("TOTALPRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void loginIdCheck(String loginId) {
        String sql = "SELECT MEMNAME, MEMID, MEMPHONE, MEMADDR, POINT FROM MEMBERLIST WHERE MEMID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);

            rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.println("  이름 > "+rs.getString("MEMNAME"));
                System.out.println("   ID > "+rs.getString("MEMID"));
                System.out.println("연락처 > "+rs.getString("MEMPHONE"));
                System.out.println("  주소 > "+rs.getString("MEMADDR"));
                System.out.println("포인트 > "+rs.getInt("POINT"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void buyHistory(String loginId) {
        String sql = "SELECT A.PNUM, B.PNAME, A.RDATE, A.DELRESULT, A.TOTALPRICE FROM DELIVERY A, PRODUCTLIST B WHERE A.PNUM=B.PNUM AND A.MEMID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.println("구매이력 >> ");
                System.out.println("     상품번호 : "+rs.getInt("PNUM"));
                System.out.println("     상품이름 : "+rs.getString("PNAME"));
                System.out.println("     받은날짜 : "+rs.getDate("RDATE"));
                System.out.println("     배송상태 : "+rs.getString("DELRESULT"));
                System.out.println("     결제금액 : "+rs.getInt("TOTALPRICE"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mem(String memPhone) {
        String memId = null;

        String sql = "SELECT * FROM MEMBERLIST WHERE MEMPHONE = ?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, memPhone);

            rs = pstmt.executeQuery();

            while (rs.next()){
                memId = rs.getString("MEMID");
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memId;
    }

    public String pmem(String memId){
        String memPw = null;

        String sql = "SELECT * FROM MEMBERLIST WHERE MEMID = ?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,memId);

            rs = pstmt.executeQuery();

            while (rs.next()){
                memPw = rs.getString("MEMPW");
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return memPw;
    }
}
