package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
// tblMember -> member //
public class MemberMgr {
	
	// pool 기법(튜브대여story)
	DBConnectionMgr pool;	
	public MemberMgr() {
		// 객체 생성 -> Connection객체 10개 생성
		pool = DBConnectionMgr.getInstance();
	}
	
	
	// 리스트
	public Vector<MemberBean> getMemberList() {
		// db1, import 해주기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MemberBean> vlist = new Vector<MemberBean>();
		try {
			con = pool.getConnection();
			sql = "select * from member";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPhone(rs.getString("phone"));
				bean.setTeam(rs.getString("team"));
				vlist.add(bean);			
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	// 저장
	public boolean insertMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false; // 기본값은 false
		String sql = null;
		try {
			con = pool.getConnection();
			// member에 맞게 수정 (into , seq_mem.nextval, ?, ?, ?) //
			sql = "insert into member values(seq_mem.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getPhone());
			pstmt.setString(3, bean.getTeam());
			int cnt = pstmt.executeUpdate();	// 적용될 레코드의 수가 리턴됨 = 1(true)	
			if(cnt == 1) {
				flag = true;		// cnt가 1이면 flag는 false에서 true로..(flag는 보통 제어권이 있냐없냐를 의미함)
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
		
	}
	
	// 하나의 레코드
	public MemberBean getMember(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberBean bean = new MemberBean();
		

		try {
			con = pool.getConnection();
			sql = "select * frommember where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPhone(rs.getString("phone"));
				bean.setTeam(rs.getString("team"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
		
	}
	
	// 수정
	public boolean updateMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false; // 기본값은 false
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update member set name = ?, phone = ?, team = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getPhone());
			pstmt.setString(3, bean.getTeam());
			pstmt.setInt(4, bean.getId());
			int cnt = pstmt.executeUpdate();	// 적용될 레코드의 수가 리턴됨 = 1(true)	
			if(cnt == 1) {
				flag = true;		// cnt가 1이면 flag는 false에서 true로..(flag는 보통 제어권이 있냐없냐를 의미함)
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
		
	}
	
	// 삭제
	public boolean deleteMember(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1) {
				flag = true;		// cnt가 1이면 flag는 false에서 true로..(flag는 보통 제어권이 있냐없냐를 의미함)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
		
	}
	
	
	
	

}
