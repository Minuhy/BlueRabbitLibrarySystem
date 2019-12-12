package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.dao.AdministratorBeansDAO;
import com.bluerabbit.librarysystem.listener.AdminInfoUpdateView_ActionListener;
/**
 * ����Ա��Ϣ����ҳ�棬���ġ����ӡ��鿴����
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class AdminInfoUpdateView extends JDialog {
	private String oldPasswd;
	public ComboJLAndJT getLname() {
		return Lname;
	}

	public ComboJLAndJT getLaccout() {
		return Laccout;
	}

	public ComboJLAndJT getLid() {
		return Lid;
	}

	public JPasswordField getPwfPasswd() {
		return pwfPasswd;
	}
	
	public String getOldPasswd() {
		return oldPasswd;
	}

	private boolean mode;//t:add.f:fix
	private JPanel mianView;
	private JPanel contentView;
	private JPanel functionView;
	private int WindowsHeight;
	private int windowsWidth;

	//����Ա��Ϣ
	private ComboJLAndJT Lname;//�ǳ�
	private ComboJLAndJT Laccout;//�˺�
	private ComboJLAndJT Lid;//���
	
	private JPanel pnlPasswd;
	private JLabel Lpasswd;//����
	private JPasswordField pwfPasswd;

	private JCheckBox jcbIfsup;

	private JButton ok;
	private JButton cancel;

	private AdminInfoManageView wv;
	boolean editor;
	
	MainView mv;
	
	public AdminInfoUpdateView(AdminInfoManageView wv,String title,String Account,boolean editor,MainView mv){
		super(wv,title,true);
		this.wv = wv;
		this.mv = mv;
		mode = true;
		this.editor = editor;
		//��ø���ͼ�Ĵ�С
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//���沿��
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();
		pnlPasswd = new JPanel();

		//�ݲ���Ϣ
		Lname = new ComboJLAndJT("�ǳƣ�",editor,30);//��������
		Laccout = new ComboJLAndJT("�˺ţ�",editor,30);//�������
		Lid = new ComboJLAndJT("��ţ�",editor,30);//�鿯���
		Lpasswd = new JLabel("�����룺");//�ݲ����
		pwfPasswd = new JPasswordField(30);
		jcbIfsup = new JCheckBox("��������ԱȨ��");

	

		ok = new JButton("ȷ��");
		cancel = new JButton("ȡ��");

		if(Account == null){
			Account = "";
		}
		if(!Account.equals("")){
			//��Ϊ�գ����޸���Ϣ�������������ʼ����Ϣ
			System.out.println("�����˺���Ϣ��");
			loader(Account);
			mode = false;
			ok = new JButton("����");
			Laccout.getGettxt().setEditable(false);
		}

		Init();
	}

	public AdminInfoUpdateView(AdminInfoManageView wv,String title,String Account,boolean editor){
		super(wv,title,true);
		this.wv = wv;
		mode = true;
		this.editor = editor;
		//��ø���ͼ�Ĵ�С
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//���沿��
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();
		pnlPasswd = new JPanel();

		//�ݲ���Ϣ
		Lname = new ComboJLAndJT("�ǳƣ�",editor,30);//��������
		Laccout = new ComboJLAndJT("�˺ţ�",editor,30);//�������
		Lid = new ComboJLAndJT("��ţ�",editor,30);//�鿯���
		Lpasswd = new JLabel("�����룺");//�ݲ����
		pwfPasswd = new JPasswordField(30);
		jcbIfsup = new JCheckBox("��������ԱȨ��");

	

		ok = new JButton("ȷ��");
		cancel = new JButton("ȡ��");

		if(Account == null){
			Account = "";
		}
		if(!Account.equals("")){
			//��Ϊ�գ����޸���Ϣ�������������ʼ����Ϣ
			System.out.println("�����˺���Ϣ��");
			loader(Account);
			mode = false;
			ok = new JButton("����");
		}

		Init();
	}

	private void loader(String Account) {
		// TODO Auto-generated method stub
		AdministratorBeans adminInfo = null;
		//��ȡͼ����Ϣ
		adminInfo = new AdministratorBeansDAO().getAdminInfoByAcc(Account);

		//����ͼ����Ϣ
		//�ݲ���Ϣ
		Lname.setIText(adminInfo.getAdminName());//����
		Laccout.setIText(adminInfo.getAccount());//�˺�
		Lid.setIText(adminInfo.getAdminID() + "");//���
		pwfPasswd.setText(adminInfo.getPassword());//����
		jcbIfsup.setSelected(adminInfo.isIfsuper());
		//�������룬�����ж��Ƿ����
		oldPasswd = adminInfo.getPassword();

	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-650, WindowsHeight-300);
		CenterView.CenterByWindow(this);
		//�������û��������ڴ�С
		this.setResizable(false);

		//���ò���
		mianView.setLayout(new BorderLayout());
		contentView.setLayout(new GridLayout(4,1));
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//����������ͼ�߿�
		contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		
		

		functionView.add(jcbIfsup);
		jcbIfsup.setPreferredSize(new Dimension(200,20));
		//�ݲ���Ϣ
		contentView.add(Lname);//����
		contentView.add(Laccout);//�˺�
		contentView.add(Lid);//���
		
		pnlPasswd.setLayout(new FlowLayout());
		
		pnlPasswd.add(Lpasswd);
		pnlPasswd.add(pwfPasswd);
		contentView.add(pnlPasswd);//����
		if(!editor){
			pwfPasswd.setEnabled(false);
			jcbIfsup.setEnabled(false);
		}

		//��Ӽ����¼�
		AdminInfoUpdateView_ActionListener listener = new AdminInfoUpdateView_ActionListener(this,wv,mv);
		cancel.addActionListener(listener);
		ok.addActionListener(listener);

		//������Ա༭
		if(editor){
			functionView.add(ok);
		}
		functionView.add(cancel);


		mianView.add(functionView, BorderLayout.SOUTH);
		mianView.add(contentView, BorderLayout.CENTER);
		this.add(mianView);
		this.setVisible(true);
	}

	public boolean isMode() {
		return mode;
	}
	
	public JButton getOk() {
		return ok;
	}
	
	public JButton getCancel() {
		return cancel;
	}
	
	public JCheckBox getJcbIfsup() {
		return jcbIfsup;
	}
}
