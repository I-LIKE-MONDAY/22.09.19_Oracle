package member;

public class MemberBean {

	// 공식 1 : 변수명 '반드시 일치'되게 (예시-tblMember)
	private int id;
	private String name;
	private String phone;
	private String team;
	
	
	// Getter, Setter 자동생성
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
}
