package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 日期选择视图,选择日期
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class DateChooseView extends JDialog {
	private JButton btnOk;
	private JButton btnCancel;
	private JPanel mainView;
	private JPanel pnlN;
	private JPanel pnlS;
	private JTextField txtInput;
	private JTextField gett;
	private DateChooserBox dateBox;
	private DateChooseView my;

	public DateChooseView(BookInfoUpdateView bookInfoUpdateView,
			JTextField gettxt) {
		// TODO Auto-generated constructor stub
		super(bookInfoUpdateView,"日期选择",true);
		this.gett = gettxt;
		my = this;
		mainView = new JPanel(new BorderLayout());
		pnlN = new JPanel(new FlowLayout());
		pnlS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnOk = new JButton("确定");
		btnCancel = new JButton("取消");
		txtInput = new JTextField(12);

		dateBox = DateChooserBox.getInstance("yyyy-MM-dd");

		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小
		this.setResizable(false);

		//默认显示一个日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		txtInput.setText(df.format(new Date()));

		dateBox.register(txtInput);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String date = txtInput.getText();
				// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
					format.setLenient(false);
					format.parse(date);
					gett.setText(date);
					my.dispose();
				} catch (ParseException e1) {
					//e.printStackTrace();
					//如果throw java.text.ParseException或者NullPointerException，就说明格式不对
					JOptionPane.showMessageDialog(null, "日期格式错误！\n请输入如1999-02-17的日期格式！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				my.dispose();
			}
		});


		pnlN.add(txtInput);

		pnlS.add(btnCancel);
		pnlS.add(btnOk);

		mainView.add(pnlS, BorderLayout.SOUTH);
		mainView.add(pnlN, BorderLayout.CENTER);
		this.add(mainView);
		this.setVisible(true);
	}

}
