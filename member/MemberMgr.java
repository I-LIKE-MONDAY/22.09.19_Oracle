package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
// tblMember -> member //
public class MemberMgr {
	
	// pool ���(Ʃ��뿩story)
	DBConnectionMgr pool;	
	public MemberMgr() {
		// ��ü ���� -> Connection��ü 10�� ����
		pool = DBConnectionMgr.getInstance();
	}
	
	
	// ����Ʈ
	public Vector<MemberBean> getMemberList() {
		// db1, import ���ֱ�
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
	
	// ����
	public boolean insertMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false; // �⺻���� false
		String sql = null;
		try {
			con = pool.getConnection();
			// member�� �°� ���� (into , seq_mem.nextval, ?, ?, ?) //
			sql = "insert into member values(seq_mem.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getPhone());
			pstmt.setString(3, bean.getTeam());
			int cnt = pstmt.executeUpdate();	// ����� ���ڵ��� ���� ���ϵ� = 1(true)	
			if(cnt == 1) {
				flag = true;		// cnt�� 1�̸� flag�� false���� true��..(flag�� ���� ������� �ֳľ��ĸ� �ǹ���)
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
		
	}
	
	// �ϳ��� ���ڵ�
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
	
	// ����
	public boolean updateMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false; // �⺻���� false
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update member set name = ?, phone = ?, team = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getPhone());
			pstmt.setString(3, bean.getTeam());
			pstmt.setInt(4, bean.getId());
			int cnt = pstmt.executeUpdate();	// ����� ���ڵ��� ���� ���ϵ� = 1(true)	
			if(cnt == 1) {
				flag = true;		// cnt�� 1�̸� flag�� false���� true��..(flag�� ���� ������� �ֳľ��ĸ� �ǹ���)
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
		
	}
	
	// ����
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
				flag = true;		// cnt�� 1�̸� flag�� false���� true��..(flag�� ���� ������� �ֳľ��ĸ� �ǹ���)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
		
	}
	
	
	
	

}
