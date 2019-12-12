package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.bluerabbit.librarysystem.service.ReaderInfoServer;
import com.bluerabbit.librarysystem.view.ReaderInfoManageView;
import com.bluerabbit.librarysystem.view.ReaderInfoUpdateView;
import com.bluerabbit.librarysystem.view.SetButtonUporDown;
/**
 * 读者管理界面的监听类，用来监听读者管理界面的所有按钮并调用服务层执行操作
 * @author minuy
 *
 */
public class ReaderInfoManageView_MouseListener implements MouseListener {
	ReaderInfoManageView rv;
	private static  boolean flag = false;		//双击事件已执行时置为真
	private static int clickNum = 1;		//指示鼠标点击次数，默认为单击
	public ReaderInfoManageView_MouseListener(
			ReaderInfoManageView readerInfoManageView) {
		// TODO Auto-generated constructor stub
		this.rv = readerInfoManageView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object clicked = e.getSource();
		if(clicked == rv.getBdel()){
			System.out.println("删除");
			new ReaderInfoServer(rv, null).del();
		}else{
			if(clicked == rv.getBadd()){
				System.out.println("增加");
				//打开界面
				new ReaderInfoUpdateView(rv,"添加读者信息",null,true);
			}else{
				if(clicked == rv.getBclo()){
					System.out.println("关闭");
					rv.dispose();

				}else{
					if(clicked == rv.getBfix()){
						System.out.println("修改");
						//打开修改窗口
						new ReaderInfoServer(rv, null).FixInfoGet(true);
					}else{
						if(clicked == rv.getExeInquire()){
							System.out.println("搜索");
							new ReaderInfoServer(rv, null).search();
						}else{
							if(clicked == rv.getTableDataView()){
								System.out.println("表格");
								mouse2Clicked(e);
							}
						}
					}
				}
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object clicked = e.getSource();
		if(clicked == rv.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.setButtonDown(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.setButtonDown(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.setButtonDown(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.setButtonDown(rv.getBfix());
					}else{

						if(clicked == rv.getExeInquire()){
//							System.out.println("搜索");
							SetButtonUporDown.setButtonDown(rv.getExeInquire());
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Object clicked = e.getSource();
		if(clicked == rv.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.SetButtonUp(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.SetButtonUp(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.SetButtonUp(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.SetButtonUp(rv.getBfix());
					}else{
						if(clicked == rv.getExeInquire()){
//							System.out.println("搜索");
							SetButtonUporDown.SetButtonUp(rv.getExeInquire());
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		Object clicked = e.getSource();
		if(clicked == rv.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.SetButtonUp(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.SetButtonUp(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.SetButtonUp(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.SetButtonUp(rv.getBfix());
					}else{
						if(clicked == rv.getExeInquire()){
//							System.out.println("搜索");
							SetButtonUporDown.SetButtonUp(rv.getExeInquire());
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object clicked = e.getSource();
		if(clicked == rv.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.reSetButton(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.reSetButton(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.reSetButton(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.reSetButton(rv.getBfix());
					}else{
						if(clicked == rv.getExeInquire()){
//							System.out.println("搜索");
							SetButtonUporDown.reSetButton(rv.getExeInquire());
						}
					}
				}
			}
		}
	}

	public void mouse2Clicked(MouseEvent e) {
		// TODO Auto-generated method stub
		final MouseEvent me = e;
		ReaderInfoManageView_MouseListener.flag= false;
		if (ReaderInfoManageView_MouseListener.clickNum==2) {
			//鼠标点击次数为2调用双击事件
			this.mouseClickedTwice(me);
			//调用完毕clickNum置为1
			ReaderInfoManageView_MouseListener.clickNum=1;
			ReaderInfoManageView_MouseListener.flag=true;
			return;
		}
		//新建定时器，双击检测间隔为500ms
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			//指示定时器执行次数
			int num = 0;
			public void run() {
				// 双击事件已经执行，取消定时器任务
				if(ReaderInfoManageView_MouseListener.flag) {
					num=0;
					ReaderInfoManageView_MouseListener.clickNum=1;
					this.cancel();
					return;
				}
				//定时器再次执行，调用单击事件，然后取消定时器任务
				if (num==1) {
					mouseClickedOnce(me);
					ReaderInfoManageView_MouseListener.flag=true;
					ReaderInfoManageView_MouseListener.clickNum=1;
					num=0;
					this.cancel();
					return;
				}
				clickNum++;
				num++;
			}
		},new Date(), 500);
	}
	protected void mouseClickedOnce(MouseEvent me) {
		// 单击事件
//		System.out.println("1");
	}
	private void mouseClickedTwice(MouseEvent me) {
		// 双击事件
		System.out.println("2");
		new ReaderInfoServer(rv, null).FixInfoGet(false);
	}

}


