package com.bluerabbit.librarysystem.view;

import java.awt.Window;
/**
 * 这应该是一个服务类的，用于窗口居中
 * @author minuy
 *
 */
public abstract class CenterView {
	private CenterView(){

	}

	public static void CenterByWindow(Window windows) {
		//获取屏幕的分辨率
		int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		
		//获取窗口大小
		int windowsWidth = windows.getWidth();
		int windowsHeight = windows.getHeight();
		
		System.out.println("屏幕大小：" + screenWidth + " X " + screenHeight);
		System.out.println("窗口大小：" + windowsWidth + " X " + windowsHeight);

		//判断窗口越屏幕界
		if(screenWidth < windowsWidth) {
			windowsWidth = screenWidth;
		}
		if(screenHeight < windowsHeight) {
			windowsHeight = screenHeight;
		}
		//重新设置窗口位置和窗口大小
		windows.setSize(windowsWidth, windowsHeight);
		windows.setLocation((screenWidth-windowsWidth)/2,(screenHeight-windowsHeight)/2);
	}
}
