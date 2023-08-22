package MARKETMAIN;

import MARKETDAO.MarketSQL;
import MARKETDTO.Delivery;
import MARKETDTO.Member;
import MARKETDTO.Orderlist;
import MARKETDTO.Product;

import java.util.Scanner;

public class marketMain {
    public static void main(String[] args) {
        MarketSQL sql = new MarketSQL();
        Member mem = new Member();
        Product prod = new Product();
        Orderlist order = new Orderlist();
        Delivery del = new Delivery();
        Scanner sc = new Scanner(System.in);

        boolean run = true;
        boolean run1 = true;
        boolean run2 = true;
        boolean overpage = false;

        int menu = 0;
        int menu1 = 0;
        int menu2 = 0;
        int menu3 = 0;
        int menu4 = 0;
        int basket = 0;
        int buyselect = 0;
        int beforepage = 0;
        int ordNum = 0;
        int delNum = 0;
        int delKind = 0;

        String seller;
        String buyer;
        String loginId;

        sql.connect();

        while (run) {
            System.out.println("========================== 쓰리마켓에 오신 것을 환영합니다. ========================== ");
            System.out.println("(아래에 랜덤상품예정)");
            System.out.println("  1.회원가입          2.로그인          3.아이디/비밀번호 찾기          4.종료");
            System.out.print("메뉴 선택 >> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("---------------------------------- 마켓 회원 가입 ----------------------------------");
                    System.out.print("1.ID 입력 >> ");
                    String memId = sc.next();
                    if (sql.checkId(memId)) {
                        System.out.print("2.이름 입력 >> ");
                        String memName = sc.next();
                        System.out.print("3.PassWord 입력 >> ");
                        String memPw = sc.next();
                        System.out.print("4.연락처 입력 >> ");
                        String memPhone = sc.next();
                        System.out.print("5.주소 입력 >> ");
                        sc.nextLine().trim();
                        String memAddr = sc.nextLine();

                        int memNum = sql.checkmemNum() + 1;
                        int point = 0;

                        mem.setMemId(memId);
                        mem.setMemName(memName);
                        mem.setMemPw(memPw);
                        mem.setMemPhone(memPhone);
                        mem.setMemAddr(memAddr);
                        mem.setMemNum(memNum);
                        mem.setPoint(point);

                        sql.memJoin(mem);
                    }
                    break;

                case 2:
                    System.out.println("---------------------------------- 마켓 로그인 ----------------------------------- ");
                    System.out.print("1.ID 입력 >> ");
                    memId = sc.next();
                    System.out.print("2.PassWord 입력 >> ");
                    String memPw = sc.next();
                    if (sql.checkLogin(memId, memPw)) {
                        loginId = memId;
                        while (run1) {
                            System.out.println("---------------------------------------------------------------------------------");
                            System.out.println("로그인  >>   1.상품등록           2.상품조회           3.내정보           4.종료");

                            System.out.print("메뉴를 선택 >> ");
                            menu1 = sc.nextInt();

                            switch (menu1) {
                                case 1:
                                    System.out.println("---------------------------------------------------------------------------------");
                                    System.out.println("10.패션의류/잡화   20.자전거   30.노트북   40.가전제품   50.도서");

                                    System.out.print("상품 카테고리 등록  >>  ");
                                    int pCategory = sc.nextInt();

                                    System.out.print("항목: " + pCategory + "/ 상품번호  등록  >>  ");
                                    int pNum = sc.nextInt();
                                    // 상품번호 중복체크

                                    System.out.print("항목: " + pCategory + " /상품번호: " + pNum + " /상품이름 등록 >> ");
                                    sc.nextLine().trim();
                                    String pName = sc.nextLine();

                                    System.out.print("항목: " + pCategory + " /상품번호: " + pNum + " / 상품이름: " + pName + " / 1.새상품  2.중고상품 선택 >> ");
                                    int pNewUsed = sc.nextInt();
                                    String txtNewUsed = sql.newUsedcheck(pNewUsed);

                                    System.out.print("항목: " + pCategory + " /상품번호: " + pNum + " / 상품이름: " + pName + " /" + txtNewUsed + " / 상품가격 >> ");
                                    int pPrice = sc.nextInt();

                                    System.out.println("항목: " + pCategory + " /상품번호: " + pNum + " / 상품이름: " + pName + " /" + txtNewUsed + "/ 상품가격: " + pPrice + "원");
                                    System.out.println("상품설명 >>");
                                    sc.nextLine().trim();
                                    String pMemo = sc.nextLine();

                                    String pState = "판매중";
                                    prod.setpCategory(pCategory);
                                    prod.setpNum(pNum);
                                    prod.setpName(pName);
                                    prod.setpNewUsed(pNewUsed);
                                    prod.setpPrice(pPrice);
                                    prod.setpMemo(pMemo);
                                    prod.setpState(pState);
                                    prod.setMemId(memId);

                                    sql.prodInsert(prod);
                                    break;

                                case 2:
                                    sql.searchLastProd();
                                    while (run2) {
                                        System.out.println("[상품조회 검색기준]");
                                        System.out.println("1.패션의류/잡화   2.자전거   3.노트북   4.가전제품   5.도서   6.최신순   7.가격순   8.인기순   9.판매자(ID)   10.새/중고   *상품별상세조회");
                                        System.out.print("검색 번호 또는 상품번호 입력 >> ");
                                        menu2 = sc.nextInt();

                                        switch (menu2) {
                                            case 1 :
                                                sql.searchCategory(menu2);
                                                break;
                                            case 2:
                                                sql.searchCategory(menu2);
                                                break;
                                            case 3:
                                                sql.searchCategory(menu2);
                                                break;
                                            case 4:
                                                sql.searchCategory(menu2);
                                                break;
                                            case 5:
                                                sql.searchCategory(menu2);
                                                break;
                                            case 6:
                                                sql.searchDate();
                                                break;
                                            case 7:
                                                sql.searchPrice();
                                                break;
                                            case 8:
                                                sql.searchLike();
                                                break;
                                            case 9:
                                                sql.searchId();
                                                break;
                                            case 10:
                                                sql.searchNewUsed();
                                                break;
                                            default:
                                                Product pro = sql.searchpNum(menu2);

                                                System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
                                                System.out.print("1.장바구니담기       2.구매하기       3.이전화면   >> ");
                                                menu3 = sc.nextInt();
                                                switch (menu3){
                                                    case 1:
                                                        System.out.println("장바구니담기 만들기!");
                                                        break;
                                                    case 2:


                                                        ordNum = sql.orderNum()+1;
                                                        pNum = menu2;
                                                        seller = pro.getMemId();
                                                        buyer = loginId;

                                                        order.setOrdNum(ordNum);
                                                        order.setpNum(pNum);
                                                        order.setSeller(seller);
                                                        order.setBuyer(buyer);

                                                        if(sql.orderInsert(order)){
                                                            System.out.print("배송정보 ( 1.직거래(대면)   2.택배 ) >> ");
                                                            delKind = sc.nextInt();
                                                            String kind = null;
                                                            int delPrice = 0;
                                                            if(delKind==1){
                                                                del.setDelPrice(delPrice);
                                                                kind = "직거래";
                                                            } else {
                                                                delPrice = 3000;
                                                                del.setDelPrice(delPrice);
                                                                kind = "택배";
                                                            }
                                                            delNum = sql.delNum()+1;
                                                            del.setDelNum(delNum);
                                                            del.setpNum(pNum);
                                                            del.setDelResult("배송준비중");
                                                            del.setDelKind(kind);
                                                            int totalPrice = delPrice + pro.getpPrice();

                                                            del.setTotalPrice(totalPrice);

                                                            sql.delInsert(del, order);
                                                            sql.delSelect(del, pNum, order);

                                                            System.out.print("계속 쇼핑하시겠습니까? 1.예    2.아니요   >> ");
                                                            menu4 = sc.nextInt();
                                                            switch (menu4){
                                                                case 1:
                                                                    break;
                                                                case 2:
                                                                    run2 = false;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        System.out.println("다시 조회해 주세요!");
                                                        break;

                                                }

                                                break;
                                        }
                                    }
                                    break;

                                case 3:
                                    System.out.println("---------- 나의 정보 ---------- ");
                                    sql.loginIdCheck(loginId);     // 로그인ID 개인정보출력
                                    sql.buyHistory(loginId);       // 구매이력 출력
                                    break;

                                case 4:
                                    run1 = false;
                                    System.out.println("-- 이용해 주셔서 감사합니다. -- ");
                                    break;

                                default:
                                    System.out.println("다시 입력해 주세요!");
                                    break;
                            }
                        }
                    }
                    break;

                case 3:

                    System.out.println("----[아이디 찾기]----");
                    System.out.print("전화번호 입력 >> ");
                    String memPhone = sc.next();
                    memId = sql.mem(memPhone);
                    if (memId != null){
                        System.out.println("아이디 : " + memId);
                    } else {
                        System.out.println("아이디가 존재하지 않습니다! 다시 입력해주세요.");
                    }

                    System.out.println("----[비밀번호 찾기]----");
                    System.out.print("아이디 입력 >> ");
                    memId = sc.next();
                    memPw = sql.pmem(memId);
                    if (memPw != null){
                        System.out.println("비밀번호 : " + memPw);
                    } else {
                        System.out.println("비밀번호가 존재하지 않습니다! 다시 입력해주세요.");
                    }
                    break;

                case 4:
                    System.out.println("-- 이용해 주셔서 감사합니다! --");
                    run = false;
                    break;

                default:
                    System.out.println("다시 입력해주세요!");
                    break;
            }
        }
    }
}

