package com.bluerabbit.librarysystem.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
/**
 * ���ð�ť��Ч��һ����
 * @author minuy
 *
 */
public abstract class SetButtonUporDown {
	private SetButtonUporDown(){

	}
	/**
	 * ��ť����
	 * @param label ��ť
	 */
	public static void SetButtonUp(JLabel label){
		label.setBorder(BorderFactory.createRaisedBevelBorder());//Ĭ�ϵ�����ʽ
//		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));//��ɫ
//		label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY));//��ɫ
	}
	/**
	 * ��ť����
	 * @param label ��ť
	 */
	public static void setButtonDown(JLabel label){
		label.setBorder(BorderFactory.createLoweredBevelBorder());//Ĭ�ϰ�����ʽ
//		label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.WHITE));//��ɫ
//		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));//��ɫ
	}
	/**
	 * ��ť�ָ�
	 * @param label ��ť
	 */
	public static void reSetButton(JLabel label){
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));//Ĭ�ϱ߿�
//		label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));//��ɫ
	}

}
