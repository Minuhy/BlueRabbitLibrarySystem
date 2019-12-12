package com.bluerabbit.librarysystem.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.listener.HexKeyCall;
/**
 * 自定义输入框，带暗示的那种
 * @author minuy
 *
 */
public class HexTextField extends JTextField {

	/**
	 * 文本框
	 */
	private static final long serialVersionUID = 4673274275189053952L;

	final int fontSize = 12;

	public HexTextField() {
		super();
		setFont(new java.awt.Font("宋体", 0, fontSize));
	}

	public HexTextField(String text) {
		super(text);
		setFont(new java.awt.Font("宋体", 0, fontSize));
		this.setForeground(Color.black);
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
		mask.setBounds(2, 0, bs.width - 2, bs.height-2);
		mask.setFont(new java.awt.Font("宋体", 0, 12));
		this.add(mask);
	}

	FocusListener focuAdp = new FocusListener() {
		public void focusGained(FocusEvent arg0) {
			mask.setVisible(false);
		}

		public void focusLost(FocusEvent arg0) {
			String text = getText().trim();
			if (text.length() == 0) {
				mask.setVisible(true);
			}
		}
	};

	public void setText(String text) {
		super.setText(text);
		if (mask != null) {
			mask.setVisible(false);
		}
	}

	JLabel mask = new JLabel();
	/* 默认显示值 */

	/* 键盘监听 */
	private HexKeyCall call;

	KeyAdapter adap = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			call.callBack(e);
		}
	};

	public void keyPressedCall(HexKeyCall call) {
		this.call = call;
		addKeyListener(adap);
	}
	/* 键盘监听 */

}
