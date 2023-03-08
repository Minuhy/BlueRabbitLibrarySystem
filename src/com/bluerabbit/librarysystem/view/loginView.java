package com.bluerabbit.librarysystem.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.listener.LoginView_helpButton_ActionListener;
import com.bluerabbit.librarysystem.listener.LoginView_loginButton_ActionListener;
/**
 * ��¼����
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class loginView extends JFrame{
	//����һ�������������൱���ǻ��ؼ��İ�ֽ
	private JPanel loginWin;
	//����һЩ��ǩ��������ʾ����
	private JLabel loginTitle;//���⡰ͼ�����ϵͳ��
	private JLabel loginName;//�˺�
	private JLabel loginPWD;//����
	//������һЩ��ť����¼�Ͱ�����ť
	private JButton loginButton;//��¼
	private JButton helpButton;//����
	//һ�������б�����ѡ����ʷ��¼�˺�
	private JComboBox<String> loginUserHistory;//��ʷ��¼�˻�
	//����ʾ��hint���������������
	private HexTextField loginUser;//�˺������
	private HexPasswordField loginUserPWD;//���������

	//���캯����һ�������������ֳ�ʼ����һ�������½���new�������캯������һ�����Ƕ���λ�ú����ԣ�init������
	public loginView(){
		//�½�һ�����������Ҵ�һ�������ڲ���
		loginWin = new JPanel(){
			//����һ��ͼƬ���½�һ��ImageIcon���󲢵���getImage�������һ��Image����
			private Image image = new ImageIcon("res\\loginBackground.png"/*ͼƬ��·��*/).getImage();
			//����ϵͳҪ�������paintComponent������������ͼƬ������ϵͳ������һ��Graphics���󣨻��ʣ���
			//������Ҫ�����������������ͼƬ
			protected void paintComponent(Graphics g) {
				//���û��ʵ�drawImage������������Ҫ����ͼƬ����ʼ���꣬�������꣬�������ﻭ��this������LoginWin��
				//��������������
				g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
			}  
			//������ʵ���˱���ͼ
		};
		//�½�һ��label�������ʼ��������Ҫ��ʾ�����ݣ�����Ҳ����setText����������
		loginTitle = new JLabel("����ͼ�����ϵͳ");
		loginName = new JLabel("�˺ţ�");
		loginPWD = new JLabel("���룺");
		loginButton = new JButton("��¼");
		helpButton = new JButton("����");
		//����ֻ���½�һ��
		loginUserHistory = new JComboBox<String>();
		loginUser = new HexTextField();
		loginUserPWD = new HexPasswordField();
		
		System.out.println("�½����ڳɹ���");
		//���ó�ʼ����������������������ꡢ��С�����ü�������
		intiView();
	}
	
	//������Ҫ����Ϊ˽�еģ��Է�ֹ����������ö�β��������������������һ�ΰ�ť��Ӧ���εĽ��
	private void intiView(){
		//���ñ��⣬this��ʾLoginView������棬������ı����ǽ���ı���
		this.setTitle("����ͼ�����ϵͳ - ����Ա��¼");
		//���ô��ڿɹرգ��˳��ķ�ʽ�ж��֣�exit��dispose
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڴ�С
		this.setSize(550,350);
		//���û����ô� frame ��װ��
		//this.setUndecorated(true);
		//���ô���λ�ã����ݷֱ����Զ�����
		CenterView.CenterByWindow(this);
		//�������û��������ڴ�С
		this.setResizable(false);
		

		//�����������ַ�ʽΪ�ղ���
		loginWin.setLayout(null);
		//Ĭ���ǽ����ý���
		loginWin.setFocusable(true);

		//��ߣ����
		//ͨ��setFont���������ñ�ǩ�����壬������С
		loginTitle.setFont(new Font("΢���ź�", 3, 27));
		//setBounds��null�����,��������ʹ�С����ߣ����
		loginTitle.setBounds(100, 20, 250, 30);

		loginName.setFont(new Font("΢���ź�", 0, 17));
		loginName.setBounds(115, 80, 60, 20);

		loginPWD.setFont(new Font("΢���ź�", 0, 17));
		loginPWD.setBounds(115, 120, 60, 20);

		//��¼��ť
		loginButton.setBounds(148, 170, 200, 40);
		
		//������ť
		helpButton.setBounds(218, 240, 60, 20);

		loginUser.setBounds(175, 80, 180, 30);
		
		//������������Լ�д��HexTextFild�ķ�������������û�������ʱ���ռλ��
		loginUser.setPlaceholder("�������˺�");//�������û������ʱ,ռλ��ʾ��Ϣ

		loginUserHistory.setBounds(175, 80, 200, 30);
		//����һ�������飬�������������
		//Ҳ����ͨ��.addItem("")�ķ���������
		String[] select = {"admin","10000","10003","10005"};
		loginUserHistory.setModel(new DefaultComboBoxModel<String>(select));
		loginUser.setText(select[0]);
		
		//������һ��
		loginUserPWD.setBounds(175, 120, 200, 30); 
		loginUserPWD.setPlaceholder("����������");



		//���Ӱ�ť�������������Լ����
		helpButton.addActionListener(new LoginView_helpButton_ActionListener(this));
		loginButton.addActionListener(new LoginView_loginButton_ActionListener(this));

		//�����úõĿؼ�ȫ�ӵ����������ע��һ��˳���ȼӵ������棬����
		//�ȼ��˺ſ��ټ�������
		loginWin.add(loginTitle);
		loginWin.add(loginName);
		loginWin.add(loginPWD);
		loginWin.add(loginButton);
		loginWin.add(helpButton);
		loginWin.add(loginUser);
		loginWin.add(loginUserHistory);
		loginWin.add(loginUserPWD);

		//�ѻ����ŵ�������
		this.add(loginWin);
		//����ʹ��
		this.setVisible(true);
		System.out.println("��ʼ�����ڳɹ���");
		
		//����������Ϊ����ģ��Զ������˺�����
//		loginUser.setText("10000");
//		loginUserPWD.setText("10000");
		
		//�������������Ϊ�˺ſ�����������ģ�������������¼�����ѡ����б�ŵ��ı�����
		loginUserHistory.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                	//��ȡ�������ֵ��ŵ��ı�����
                	loginUser.setText(loginUserHistory.getSelectedItem().toString());
                    System.out.print(loginUser.getText());
                }
            }
        });
	}
	
	//���������Ǹ���¼������ų��Ķ˿�
	public JPasswordField getLoginUserPWD() {
		return loginUserPWD;
	}
	
	public JTextField getLoginUser() {
		return loginUser;
	}
}
