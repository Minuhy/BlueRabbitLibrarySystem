package com.bluerabbit.librarysystem;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import com.bluerabbit.librarysystem.view.loginView;

/**
 * 主函数，设置皮肤，启动窗口
 * @author minuy
 *
 */
public class BlueRabbitLibrarySystem {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {

		try
		{
			BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}


		new loginView();
		

	}
}



