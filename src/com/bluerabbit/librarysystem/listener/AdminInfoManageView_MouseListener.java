package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.bluerabbit.librarysystem.service.AdminInfoSave;
import com.bluerabbit.librarysystem.view.AdminInfoManageView;
import com.bluerabbit.librarysystem.view.AdminInfoUpdateView;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.SetButtonUporDown;
/**
 * 管理员视图监听类，管理所有的点击事件
 * @author minuy
 *
 */
public class AdminInfoManageView_MouseListener implements MouseListener {
	AdminInfoManageView av;
	private static  boolean flag = false;		//双击事件已执行时置为真
	private static int clickNum = 1;		//指示鼠标点击次数，默认为单击
	MainView mv;
	public AdminInfoManageView_MouseListener(
			AdminInfoManageView adminInfoManageView,MainView mv) {
		// TODO Auto-generated constructor stub
		this.av = adminInfoManageView;
		this.mv = mv;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object clicked = e.getSource();
		if(clicked == av.getBdel()){
			System.out.println("删除");
			new AdminInfoSave().DelAdmin(av,mv);
			
		}else{
			if(clicked == av.getBadd()){
				System.out.println("增加");
				new AdminInfoUpdateView(av, "添加管理员", null, true);
				
			}else{
				if(clicked == av.getBclo()){
					System.out.println("关闭");
					av.dispose();
					
				}else{
					if(clicked == av.getBfix()){
						System.out.println("修改");
						new AdminInfoSave().FixAdmin(av,mv);
						
					}else{
						if(clicked == av.getLabResetPwd()){
							System.out.println("改密");
							new AdminInfoSave().ChangerAdminPwd(av);
							
						}else{
							if(clicked == av.getExeInquire()){
								System.out.println("搜索");
								//JOptionPane.showMessageDialog(null, "停用");
								new AdminInfoSave().search(av);
							}else{
								if(clicked == av.getTableDataView()){
									System.out.println("表格");
									mouse2Clicked(e);
								}
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
		if(clicked == av.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.setButtonDown(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.setButtonDown(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.setButtonDown(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.setButtonDown(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("改密");
							SetButtonUporDown.setButtonDown(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("搜索");
								SetButtonUporDown.setButtonDown(av.getExeInquire());
							}
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
		if(clicked == av.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.SetButtonUp(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.SetButtonUp(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.SetButtonUp(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.SetButtonUp(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("改密");
							SetButtonUporDown.SetButtonUp(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("搜索");
								SetButtonUporDown.SetButtonUp(av.getExeInquire());
							}
							
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
		if(clicked == av.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.SetButtonUp(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.SetButtonUp(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.SetButtonUp(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.SetButtonUp(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("改密");
							SetButtonUporDown.SetButtonUp(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("搜索");
								SetButtonUporDown.SetButtonUp(av.getExeInquire());
							}
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
		if(clicked == av.getBdel()){
//			System.out.println("删除");
			SetButtonUporDown.reSetButton(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("增加");
				SetButtonUporDown.reSetButton(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("关闭");
					SetButtonUporDown.reSetButton(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("修改");
						SetButtonUporDown.reSetButton(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("改密");
							SetButtonUporDown.reSetButton(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("搜索");
								SetButtonUporDown.reSetButton(av.getExeInquire());
							}
						}
					}
				}
			}
		}
	}
	

		public void mouse2Clicked(MouseEvent e) {
			// TODO Auto-generated method stub
			final MouseEvent me = e;
			AdminInfoManageView_MouseListener.flag= false;
			if (AdminInfoManageView_MouseListener.clickNum==2) {
				//鼠标点击次数为2调用双击事件
				this.mouseClickedTwice(me);
				//调用完毕clickNum置为1
				AdminInfoManageView_MouseListener.clickNum=1;
				AdminInfoManageView_MouseListener.flag=true;
				return;
			}
			//新建定时器，双击检测间隔为500ms
			Timer timer = new Timer();
			
			timer.schedule(new TimerTask() {
				//指示定时器执行次数
				int num = 0;
				public void run() {
					// 双击事件已经执行，取消定时器任务
					if(AdminInfoManageView_MouseListener.flag) {
						num=0;
						AdminInfoManageView_MouseListener.clickNum=1;
						this.cancel();
						return;
					}
					//定时器再次执行，调用单击事件，然后取消定时器任务
					if (num==1) {
						mouseClickedOnce(me);
						AdminInfoManageView_MouseListener.flag=true;
						AdminInfoManageView_MouseListener.clickNum=1;
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
			System.out.println("1");
		}
		private void mouseClickedTwice(MouseEvent me) {
			// 双击事件
			System.out.println("2");
			new AdminInfoSave().LookAdmin(av);
		}
	
	}


