package com.bluerabbit.librarysystem.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
/**
 * 设置按钮特效的一个类
 * @author minuy
 *
 */
public abstract class SetButtonUporDown {
	private SetButtonUporDown(){

	}
	/**
	 * 按钮弹起
	 * @param label 按钮
	 */
	public static void SetButtonUp(JLabel label){
		label.setBorder(BorderFactory.createRaisedBevelBorder());//默认弹起样式
//		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));//白色
//		label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY));//灰色
	}
	/**
	 * 按钮按下
	 * @param label 按钮
	 */
	public static void setButtonDown(JLabel label){
		label.setBorder(BorderFactory.createLoweredBevelBorder());//默认按下样式
//		label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.WHITE));//白色
//		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));//灰色
	}
	/**
	 * 按钮恢复
	 * @param label 按钮
	 */
	public static void reSetButton(JLabel label){
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));//默认边框
//		label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));//灰色
	}

}
