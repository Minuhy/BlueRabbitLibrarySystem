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
 * ����ѡ����ͼ,ѡ������
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
		super(bookInfoUpdateView,"����ѡ��",true);
		this.gett = gettxt;
		my = this;
		mainView = new JPanel(new BorderLayout());
		pnlN = new JPanel(new FlowLayout());
		pnlS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnOk = new JButton("ȷ��");
		btnCancel = new JButton("ȡ��");
		txtInput = new JTextField(12);

		dateBox = DateChooserBox.getInstance("yyyy-MM-dd");

		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		CenterView.CenterByWindow(this);
		//�������û��������ڴ�С
		this.setResizable(false);

		//Ĭ����ʾһ������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		txtInput.setText(df.format(new Date()));

		dateBox.register(txtInput);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String date = txtInput.getText();
				// ָ�����ڸ�ʽΪ��λ��/��λ�·�/��λ���ڣ�ע��yyyy/MM/dd���ִ�Сд��
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					//����lenientΪfalse. ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�����2007/02/29�ᱻ���ܣ���ת����2007/03/01
					format.setLenient(false);
					format.parse(date);
					gett.setText(date);
					my.dispose();
				} catch (ParseException e1) {
					//e.printStackTrace();
					//���throw java.text.ParseException����NullPointerException����˵����ʽ����
					JOptionPane.showMessageDialog(null, "���ڸ�ʽ����\n��������1999-02-17�����ڸ�ʽ��", "����", JOptionPane.ERROR_MESSAGE);
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
