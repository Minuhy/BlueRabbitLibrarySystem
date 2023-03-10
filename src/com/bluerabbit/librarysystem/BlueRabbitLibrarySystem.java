package com.bluerabbit.librarysystem;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import com.bluerabbit.librarysystem.view.loginView;

/**
 * ������������Ƥ������������
 *
 * @author minuhy
 */
public class BlueRabbitLibrarySystem {

    /**
     * ������
     *
     * @param args ����Ҫ����
     */
    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false; // �رհ�͸��Ч��
        } catch (Exception e) {
            System.out.println("������Ƥ��ʱ����" + e.getMessage());
        }

        // ������¼����
        new loginView();
    }
}
