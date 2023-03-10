package com.bluerabbit.librarysystem.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;

import javax.swing.*;

import com.bluerabbit.librarysystem.listener.LoginView_helpButton_ActionListener;
import com.bluerabbit.librarysystem.listener.LoginView_loginButton_ActionListener;

/**
 * ��¼����
 *
 * @author minuhy
 */
public class loginView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -576085559500436326L;
	//����һ�������������൱���ǻ��ؼ��İ�ֽ
    private final JPanel loginWin;
    //����һЩ��ǩ��������ʾ����
    private final JLabel loginTitle;//���⡰ͼ�����ϵͳ��
    private final JLabel loginName;//�˺�
    private final JLabel loginPWD;//����
    //������һЩ��ť����¼�Ͱ�����ť
    private final JButton loginButton;//��¼
    private final JButton helpButton;//����
    //һ�������б�����ѡ����ʷ��¼�˺�
    private final JComboBox<String> loginUserHistory;//��ʷ��¼�˻�
    //����ʾ��hint���������������
    private final HexTextField loginUser;//�˺������
    private final HexPasswordField loginUserPWD;//���������

    //���캯����һ�������������ֳ�ʼ����һ�������½���new�������캯������һ�����Ƕ���λ�ú����ԣ�init������
    public loginView() {
        //�½�һ�����������Ҵ�һ�������ڲ���
        loginWin = new JPanel() {
            /**
			 * UID
			 */
			private static final long serialVersionUID = 7352552878321669328L;
			//����һ��ͼƬ���½�һ��ImageIcon���󲢵���getImage�������һ��Image����
            private final Image image = new ImageIcon("res\\loginBackground.png"/*ͼƬ��·��*/).getImage();

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
        loginTitle = new JLabel("����ͼ�����ϵͳ", JLabel.CENTER);
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
    private void intiView() {
        //���ñ��⣬this��ʾLoginView������棬������ı����ǽ���ı���
        this.setTitle("����ͼ�����ϵͳ - ����Ա��¼");
        //���ô��ڿɹرգ��˳��ķ�ʽ�ж��֣�exit��dispose
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //���ô��ڴ�С
        this.setSize(550, 350);
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
        loginTitle.setFont(new Font("΢���ź�", Font.BOLD | Font.ITALIC, 27));
        //setBounds��null�����,��������ʹ�С����ߣ����
        loginTitle.setBounds(0, 20, 500, 30);

        loginName.setFont(new Font("΢���ź�", Font.PLAIN, 17));
        loginName.setBounds(115, 80, 60, 20);

        loginPWD.setFont(new Font("΢���ź�", Font.PLAIN, 17));
        loginPWD.setBounds(115, 120, 60, 20);

        //��¼��ť
        loginButton.setBounds(148, 170, 200, 40);

        //������ť
        helpButton.setBounds(218, 240, 60, 20);

        loginUser.setBounds(175, 80, 180, 30);

        //������������Լ�д��HexTextField�ķ�������������û�������ʱ���ռλ��
        loginUser.setPlaceholder("�������˺�");//�������û������ʱ,ռλ��ʾ��Ϣ

        loginUserHistory.setBounds(175, 80, 200, 30);
        //����һ�������飬�������������
        //Ҳ����ͨ��.addItem("")�ķ���������
        String[] select = {"admin", "10000", "10003", "10005"};
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
        loginUserHistory.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                //��ȡ�������ֵ��ŵ��ı�����
                loginUser.setText(loginUserHistory.getSelectedItem().toString());
                System.out.print(loginUser.getText());
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
