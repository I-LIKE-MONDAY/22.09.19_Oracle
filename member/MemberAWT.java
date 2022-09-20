package member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberAWT extends MyFrame implements ActionListener {
	JButton b1, b2, b3, b4;
	List list;

	JLabel label;
	JTextField tf1, tf2, tf3;
	JPanel p1, p2, p3;
	JButton insBtn, upBtn;
	Vector<MemberBean> vlist;
	MemberMgr mgr;
	int id;

	public MemberAWT() {
		super(300, 400);
		setTitle("회원관리 프로그램");
		mgr = new MemberMgr();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		label = new JLabel("");
		viewList();
		add(label, BorderLayout.NORTH);
		b1 = new JButton("수정");
		b2 = new JButton("삭제");
		b3 = new JButton("입력");
		b4 = new JButton("리스트");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		p1 = new JPanel();
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		add(p1, BorderLayout.SOUTH);
		validate();
	}

	private void viewList() {
		label.setOpaque(true);
		label.setText("NO  NAME          PHONE                TEAM");
		label.setBackground(Color.CYAN);
		vlist = mgr.getMemberList();
		list = new List(vlist.size(), false);
		for (int i = 0; i < vlist.size(); i++) {
			MemberBean bean = vlist.get(i);
			String str = (i+1)/*bean.getId()*/ + "    " + bean.getName().trim()
					+ "       " + bean.getPhone().trim() + "       "
					+ bean.getTeam();
			list.add(str);
		}// for
		add(list, BorderLayout.CENTER);
		int len = list.getItemCount();
		if (len > 0)
			list.select(0);
	}

	public void insertForm() {
		label.setText("회원입력폼");
		label.setBackground(Color.GRAY);
		p3.setLayout(new GridLayout(4, 1));

		JPanel p5 = new JPanel();
		p5.add(new JLabel("NAME  :"));
		tf1 = new JTextField(20);
		p5.add(tf1);
		p3.add(p5);

		JPanel p6 = new JPanel();
		p6.add(new JLabel("PHONE  :"));
		tf2 = new JTextField(20);
		p6.add(tf2);
		p3.add(p6);

		JPanel p7 = new JPanel();
		p7.add(new JLabel("TEAM  :"));
		tf3 = new JTextField(20);
		p7.add(tf3);
		p3.add(p7);

		JPanel p8 = new JPanel();
		insBtn = new JButton("저장");
		insBtn.addActionListener(this);
		p8.add(insBtn);
		p3.add(p8);

		add(p3, BorderLayout.CENTER);
	}

	 public void updateForm(MemberBean bean){
		  label.setText("회원수정폼");
		  label.setBackground(Color.YELLOW);
		     p3.setLayout(new GridLayout(4,1));
		     id = bean.getId();
		  JPanel p4 = new JPanel();
		  p4.add(new JLabel("NAME  :"));
		  tf1 = new JTextField(bean.getName(),20);
		  p4.add(tf1);
		  p3.add(p4);
		  
		  JPanel p5 = new JPanel();
		  p5.add(new JLabel("PHONE  :"));
		  tf2 = new JTextField(bean.getPhone(),20);
		  p5.add(tf2);
		  p3.add(p5);
		  
		  JPanel p6 = new JPanel();
		  p6.add(new JLabel("TEAM  :"));
		  tf3 = new JTextField(bean.getTeam(),20);
		  p6.add(tf3);
		  p3.add(p6);
		  
		  JPanel p7 = new JPanel();
		  upBtn = new JButton("수정저장");
		  upBtn.addActionListener(this);
		  p7.add(upBtn);
		  p3.add(p7);
		  
		  add(p3,BorderLayout.CENTER);
		 }
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1/* 수정버튼 */) {
			int i = list.getSelectedIndex();
			MemberBean bean = vlist.get(i);
			list.removeAll();
			remove(list);
			updateForm(bean);
		} else if (obj == b2/* 삭제버튼 */) {
			int i = list.getSelectedIndex();
			MemberBean bean = vlist.get(i);
			if(mgr.deleteMember(bean.getId())){
				p3.removeAll();
				remove(p3);
				list.removeAll();
				remove(list);
				vlist.removeAllElements();
				viewList();
			}
		} else if (obj == b3/* 입력버튼 */) {
			p3.removeAll();
			list.removeAll();
			remove(list);
			insertForm();
		} else if (obj == b4/*리스트*/) {
			p3.removeAll();
			remove(p3);
			list.removeAll();
			remove(list);
			vlist.removeAllElements();
			viewList();
		} else if (obj == insBtn/*입력저장*/) {
			MemberBean bean = new MemberBean();
			bean.setName(tf1.getText());
			bean.setPhone(tf2.getText());
			bean.setTeam(tf3.getText());
			if(mgr/*MemberMgr*/.insertMember(bean)){
				p3.removeAll();
				remove(p3);
				vlist.removeAllElements();
				viewList();
			}
		} else if (obj == upBtn/*수정저장*/) {
			MemberBean bean = new MemberBean();
			bean.setId(id);
			bean.setName(tf1.getText());
			bean.setPhone(tf2.getText());
			bean.setTeam(tf3.getText());
			if(mgr.updateMember(bean)){
				p3.removeAll();
				remove(p3);
				vlist.removeAllElements();
				viewList();
			}
		}
		validate();
	}

	public static void main(String[] args) {
		new MemberAWT();
	}
}
