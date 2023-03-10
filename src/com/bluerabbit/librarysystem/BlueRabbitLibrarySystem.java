package com.bluerabbit.librarysystem;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import com.bluerabbit.librarysystem.view.loginView;

/**
 * 主函数，设置皮肤，启动窗口
 *
 * @author minuhy
 */
public class BlueRabbitLibrarySystem {

    /**
     * 主函数
     *
     * @param args 不需要参数
     */
    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false; // 关闭半透明效果
        } catch (Exception e) {
            System.out.println("在启用皮肤时出错" + e.getMessage());
        }

        // 开启登录界面
        new loginView();
    }
}
