package com.bluerabbit.librarysystem.view;

import java.awt.Window;
/**
 * ��Ӧ����һ��������ģ����ڴ��ھ���
 * @author minuy
 *
 */
public abstract class CenterView {
	private CenterView(){

	}

	public static void CenterByWindow(Window windows) {
		//��ȡ��Ļ�ķֱ���
		int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		
		//��ȡ���ڴ�С
		int windowsWidth = windows.getWidth();
		int windowsHeight = windows.getHeight();
		
		System.out.println("��Ļ��С��" + screenWidth + " X " + screenHeight);
		System.out.println("���ڴ�С��" + windowsWidth + " X " + windowsHeight);

		//�жϴ���Խ��Ļ��
		if(screenWidth < windowsWidth) {
			windowsWidth = screenWidth;
		}
		if(screenHeight < windowsHeight) {
			windowsHeight = screenHeight;
		}
		//�������ô���λ�úʹ��ڴ�С
		windows.setSize(windowsWidth, windowsHeight);
		windows.setLocation((screenWidth-windowsWidth)/2,(screenHeight-windowsHeight)/2);
	}
}
