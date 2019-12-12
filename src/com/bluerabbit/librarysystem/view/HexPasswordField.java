package com.bluerabbit.librarysystem.view;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.bluerabbit.librarysystem.listener.HexKeyCall;
/**
 * 自定义密码框类，来自互联网
 * @author minuy
 *
 */
public class HexPasswordField extends JPasswordField {

	/**
	 * 密码框
	 */
	private static final long serialVersionUID = 65487378352678L;
	final int fontSize = 12;

	public HexPasswordField() {
		super();
		setFont(new java.awt.Font("宋体", 0, fontSize));
	}

	public HexPasswordField(String text) {
		super(text);
		setFont(new java.awt.Font("宋体", 0, fontSize));
	}

	/* 默认显示值 */
	/**
	 * @Description 设置空值时的默认显示
	 * @param @param text 参数
	 * @return void 返回类型
	 * @throws
	 */
	public void setPlaceholder(String text) {
		addFocusListener(focuAdp);
		Rectangle bs = this.getBounds();
		mask.setText(text);
		mask.setForeground(Color.lightGray);
		mask.setBounds(2, 0, bs.width - 2, bs.height - 2);
		mask.setFont(new java.awt.Font("宋体", 0, 12));
		this.add(mask);
	}

	FocusListener focuAdp = new FocusListener() {
		public void focusGained(FocusEvent arg0) {
			mask.setVisible(false);
		}

		public void focusLost(FocusEvent arg0) {
			String text = String.valueOf(getPassword()).trim();
			if (text.length() == 0) {
				mask.setVisible(true);
			}
		}
	};
	JLabel mask = new JLabel();

	/* 默认显示值 */

	/* 键盘监听 */
	public void keyPressedCall(HexKeyCall call) {
		this.call = call;
		addKeyListener(adap);
	}

	private HexKeyCall call;

	KeyAdapter adap = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			call.callBack(e);
		}
	};
	/* 键盘监听 */
}
