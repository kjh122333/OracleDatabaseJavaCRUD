import java.util.*;  
import java.sql.*;  
  
public class EmpDao {  
  
    public static Connection getConnection(){  

        // 오라클 데이터 베이스 연결
/** 
create table user905( 
id number(10) not null,
name varchar2(4000),
password varchar2(4000),
email varchar2(4000),
country varchar2(4000)
);

*/
        Connection con=null;  
        try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","!@#!#@","!@#!#!@#");  
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  


    //데이터 입력
    public static int save(Emp e){  
        int status=0;  
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "insert into user905(id, name,password,email,country) values (no_seq.nextval,?,?,?,?)");  
            ps.setString(1,e.getName());  
            ps.setString(2,e.getPassword());  
            ps.setString(3,e.getEmail());  
            ps.setString(4,e.getCountry());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    //데이터 수정
    public static int update(Emp e){  
        int status=0;  
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update user905 set name=?,password=?,email=?,country=? where id=?");  
            ps.setString(1,e.getName());  
            ps.setString(2,e.getPassword());  
            ps.setString(3,e.getEmail());  
            ps.setString(4,e.getCountry());  
            ps.setInt(5,e.getId());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    //데이터 삭제
    public static int delete(int id){  
        int status=0;  
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from user905 where id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    public static Emp getEmployeeById(int id){  
        Emp e=new Emp();  
          
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from user905 where id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return e;  
    }  

    //데이터 조회(전부)
    public static List<Emp> getAllEmployees(){  
        List<Emp> list=new ArrayList<Emp>();  
          
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from user905");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Emp e=new Emp();  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  
}  