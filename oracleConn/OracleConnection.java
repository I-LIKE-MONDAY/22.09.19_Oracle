package oracleConn;

import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleConnection {
	// �߰� //
	DBConnectionMgr pool;
	
	public OracleConnection() {
		try {
			pool = DBConnectionMgr.getInstance();
			Connection con = pool.getConnection();
			System.out.println("����");
		} catch(Exception e) {
			System.out.println("����");
			e.printStackTrace();
		}
	}

	public void getEmpCount() {
		// db1 + (Ctrl + Space)
		Connection con = null;
		// import //
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			con = pool.getConnection();
			// 1 //
			sql = "select count(*) from emp";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			// 2 //
			if(rs.next()) {
				System.out.println("count : " + rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		// return;  <--- ����(why?)
	}
	
	public static void main(String args[]) {
		OracleConnection mgr =  new OracleConnection();
		mgr.getEmpCount();
	}	
	

}