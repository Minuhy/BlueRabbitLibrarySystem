package com.bluerabbit.librarysystem.view;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.listener.MainView_AdminPhoto_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button1_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button2_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button3_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button4_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_exit_MouseListener;

/**
 * 主界面
 *
 * @author minuhy
 */
public class MainView extends JFrame {
    /**
	 * UID
	 */
	private static final long serialVersionUID = -893839572059524069L;
	private static AdministratorBeans admin;
    private final JPanel mainWin;
    private final JLabel status;
    private final JLabel adminName;
    private final JLabel adminPhoto;
    private final JLabel adminBack;
    private final JLabel button1;
    private final JLabel button2;
    private final JLabel button3;
    private final JLabel button4;
    private final JLabel exit;
    
    // 用户头像
    private static String avater;

    /**
     * 主界面
     */
    public MainView(AdministratorBeans admin) {
        MainView.admin = admin;
        mainWin = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1858000266892218472L;

			@Override
            protected void paintComponent(Graphics g) {
                Image img = new ImageIcon("res/background2.png").getImage();
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        adminName = new JLabel();
        adminPhoto = new JLabel();
        adminBack = new JLabel();
        button1 = new JLabel("借阅管理");
        button2 = new JLabel("读者管理");
        button3 = new JLabel("图书管理");
        button4 = new JLabel("人员管理");
        status = new JLabel();

        exit = new JLabel();

        Init();
    }

    private void Init() {
        //设置标题
        this.setTitle("蓝兔图书管理系统 - 主界面");
        //设置窗口大小
        this.setSize(1152, 679);
        //注册关闭事件
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //窗口居中
        CenterView.CenterByWindow(this);
        //设置容器布局方式为空布局
        mainWin.setLayout(null);

        //不允许用户调整窗口大小
        this.setResizable(false);


        //设置用户名参数
        adminName.setBounds(70, 0, 600, 30);
        adminName.setFont(new Font("微软雅黑", Font.BOLD, 27));

        status.setBounds(70, 40, 100, 30);
        status.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, 17));

        adminName.setText(admin.getAdminName());
        if (admin.isIfsuper()) {
            status.setText("超级管理员");
        } else {
            status.setText("管理员");
        }

        ArrayList<String> avatars;
        int avatarIndex;
        // 设置随机头像
        {
            String basePath = "res/avatar/";
            File dir = new File(basePath);
            avatars = new ArrayList<>();
            if (dir.exists()) {
                getAllFilePath(dir, avatars);
            } else {
                System.out.println("头像目录不存在");
                avatars.add("res/photo1.png");
            }

            Random random = new Random();
            avatarIndex = random.nextInt(avatars.size());

            avater = avatars.get(avatarIndex);
            System.out.println("设置头像：" + avater);
        }
        //设置头像参数
        setJLabelImageAndSize(5, 5, 60, 60, adminPhoto, avater);

        adminPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //设置名片背景参数
        setJLabelImageAndSize(0, 0, 1098, 80, adminBack, "res/titlebackground.png");
        //借阅管理
        setJLabelImageAndSize(90, 120, 350, 430, button1, "res/main/1/default.png");
        //button1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //读者管理
        setJLabelImageAndSize(460, 120, 250, 430, button2, "res/main/2/default.png");
        //button2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //图书管理
        setJLabelImageAndSize(730, 120, 275, 210, button3, "res/main/3/default.png");
        //button3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //人员管理
        setJLabelImageAndSize(730, 350, 275, 200, button4, "res/main/4/default.png");
        //button4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //退出登录
        setJLabelImageAndSize(910, 10, 170, 57, exit, "res/exit1.png");
        //exit.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        adminPhoto.addMouseListener(new MainView_AdminPhoto_MouseListener(this, admin));
        button1.addMouseListener(new MainView_Button1_MouseListener(this));
        button2.addMouseListener(new MainView_Button2_MouseListener(this));
        button3.addMouseListener(new MainView_Button3_MouseListener(this));
        button4.addMouseListener(new MainView_Button4_MouseListener(this));
        exit.addMouseListener(new MainView_exit_MouseListener(this, exit));


        mainWin.add(status);
        mainWin.add(adminPhoto);
        mainWin.add(adminName);
        mainWin.add(button4);
        mainWin.add(button3);
        mainWin.add(button2);
        mainWin.add(button1);
        mainWin.add(exit);

        mainWin.add(adminBack);

        this.add(mainWin);
        //设置窗口可见
        this.setVisible(true);
    }

    public void setJLabelImageAndSize(int x, int y, int width, int height, JLabel label, String imagePath) {
        //实例化ImageIcon 对象
        ImageIcon image = new ImageIcon(imagePath);
        //得到Image对象
        Image img = image.getImage();
        //创建缩放版本
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        //替换为缩放版本
        image.setImage(img);
        //JLabel设置图像
        label.setIcon(image);
        //JLabel设置坐标和大小
        label.setBounds(x, y, width, height);
    }

    /**
     * 重写窗口的事件中转方法，程序是从这个方法processWindowEvent进入到窗口关闭事件的
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        //这里需要对进来的WindowEvent进行判断，因为，不仅会有窗口关闭的WindowEvent进来，还可能有其他的WindowEvent进来
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            int option = JOptionPane.showConfirmDialog(null, "是否关闭程序？", "程序退出提示", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                //用户选择关闭程序，以上代码提示后确认传给父类处理
                super.processWindowEvent(e);
            }
            //用户选择不退出程序，这里把关闭事件截取
        } else {
            //如果是其他事件，交给父类处理
            super.processWindowEvent(e);
        }
    }

    public JLabel getButton1() {
        return button1;
    }

    public JLabel getButton2() {
        return button2;
    }

    public JLabel getButton3() {
        return button3;
    }

    public JLabel getButton4() {
        return button4;
    }

    public void updateAdmin(AdministratorBeans admin) {
        MainView.admin = admin;
        adminName.setText(admin.getAdminName());
        if (admin.isIfsuper()) {
            status.setText("超级管理员");
        } else {
            status.setText("管理员");
        }
    }

    public boolean isSu() {
        return admin.isIfsuper();
    }

    public String getAdminAcc() {
        return admin.getAccount();
    }


    public static void getAllFilePath(File dir, ArrayList<String> files) {
        // 获取文件列表
        File[] fileList = dir.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                getAllFilePath(file, files);
            } else {
                // 如果是文件则将其加入到文件数组中
                files.add(file.getPath());
            }
        }
    }
    
    
    public static String getAvater() {
		return avater;
	}

	public static AdministratorBeans getAdmin() {
        return admin;
    }
}

