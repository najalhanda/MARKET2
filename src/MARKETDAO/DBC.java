package MARKETDAO;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;



public class DBC {

        public static Connection DBConnect() {
            Connection con = null;

            String url = "jdbc:oracle:thin:@localhost:1521:xe";

            String user = "ONE";
            String password = "1111";


            try {
                // ojdbc8 파일을 현재 프로젝트 적용
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // 오라클에 접속할 계정정보를 con에 등록하기
                con = DriverManager.getConnection(url, user, password);

                System.out.println("DB 접속 성공!");
            } catch (ClassNotFoundException e) {
                // ojdbc8 파일이 존재하지 않거나
                // Class.forName을 잘못 입력했을 경우
                System.out.println("DB 접속 실패 : 드라이버 로딩 실패!");
                throw new RuntimeException(e);
            } catch (SQLException e) {
                // url, user, password를 잘못 입력했을 경우
                System.out.println("DB 접속 실패 : 접속 정보 확인!");
                throw new RuntimeException(e);
            }

            return con;
        }

    }


