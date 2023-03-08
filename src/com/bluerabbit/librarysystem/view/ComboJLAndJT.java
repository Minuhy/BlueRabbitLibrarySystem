package com.bluerabbit.librarysystem.view;


import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 一个组合类，组合标签和输入框，或者两个标签，这样更方便创建
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class ComboJLAndJT extends JPanel {
	private JLabel txt;
	private JLabel labInfo;
	private JTextField gettxt;

	public ComboJLAndJT(String text){
		this(text,false);
	}

	public ComboJLAndJT(String text,int col){
		this(text,col,false);
	}
	public ComboJLAndJT(String text,boolean mode){
		this(text,50,mode);
	}
	//private boolean mode;
	public ComboJLAndJT(String text,int col,boolean mode){
		//int JpW = this.getWidth();
		//int JpH = this.getHeight();
		//this.mode = mode;
		txt = new JLabel(text);
		this.setLayout(new FlowLayout());

		//txt.setBounds(0, 0, JpW/2, JpH);
		//gettxt.setBounds(JpW/2, 0, JpW/2, JpH);

		this.add(txt);
		gettxt = new JTextField(col);
		labInfo = new JLabel();
		labInfo.setPreferredSize(new Dimension(50,20));
		if(mode){
			this.add(gettxt);
		}else{
			this.add(gettxt);
			gettxt.setEditable(false);
		}
	}

	public ComboJLAndJT(String text,boolean mode,int w){
		//int JpW = this.getWidth();
		//int JpH = this.getHeight();
		//this.mode = mode;
		txt = new JLabel(text);
		this.setLayout(new FlowLayout());

		//txt.setBounds(0, 0, JpW/2, JpH);
		//gettxt.setBounds(JpW/2, 0, JpW/2, JpH);

		this.add(txt);
		gettxt = new JTextField(w);
		labInfo = new JLabel();
		labInfo.setPreferredSize(new Dimension(w,20));
		if(mode){
			this.add(gettxt);
		}else{
			this.add(labInfo);
		}
	}

	public void setLText(String text){
		this.txt.setText(text);
	}

	public void setIText(String text){
		this.gettxt.setText(text);
		this.labInfo.setText(text);
//		if(mode){
//		}else{
//		}
	}

	public String getText(){
		if(this.gettxt.getText() == null){
			return "";
		}
		return this.gettxt.getText();
	}

	public void setRatio(int ratio){
		int JpW = this.getWidth();
		int JpH = this.getHeight();
		this.setLayout(null);
		if(ratio>JpW){
			return;
		}
		txt.setBounds(0, 0, ratio, JpH);
		gettxt.setBounds(ratio, 0, JpW - ratio, JpH);
	}

	public JLabel getTxt() {
		return txt;
	}

	public JTextField getGettxt() {
		return this.gettxt;
	}

}
