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
 * ����Ա��ͼ�����࣬�������еĵ���¼�
 * @author minuy
 *
 */
public class AdminInfoManageView_MouseListener implements MouseListener {
	AdminInfoManageView av;
	private static  boolean flag = false;		//˫���¼���ִ��ʱ��Ϊ��
	private static int clickNum = 1;		//ָʾ�����������Ĭ��Ϊ����
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
			System.out.println("ɾ��");
			new AdminInfoSave().DelAdmin(av,mv);
			
		}else{
			if(clicked == av.getBadd()){
				System.out.println("����");
				new AdminInfoUpdateView(av, "��ӹ���Ա", null, true);
				
			}else{
				if(clicked == av.getBclo()){
					System.out.println("�ر�");
					av.dispose();
					
				}else{
					if(clicked == av.getBfix()){
						System.out.println("�޸�");
						new AdminInfoSave().FixAdmin(av,mv);
						
					}else{
						if(clicked == av.getLabResetPwd()){
							System.out.println("����");
							new AdminInfoSave().ChangerAdminPwd(av);
							
						}else{
							if(clicked == av.getExeInquire()){
								System.out.println("����");
								//JOptionPane.showMessageDialog(null, "ͣ��");
								new AdminInfoSave().search(av);
							}else{
								if(clicked == av.getTableDataView()){
									System.out.println("���");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.setButtonDown(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.setButtonDown(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.setButtonDown(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.setButtonDown(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("����");
							SetButtonUporDown.setButtonDown(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("����");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.SetButtonUp(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.SetButtonUp(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.SetButtonUp(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.SetButtonUp(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("����");
							SetButtonUporDown.SetButtonUp(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("����");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.SetButtonUp(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.SetButtonUp(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.SetButtonUp(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.SetButtonUp(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("����");
							SetButtonUporDown.SetButtonUp(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("����");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.reSetButton(av.getBdel());
		}else{
			if(clicked == av.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.reSetButton(av.getBadd());
			}else{
				if(clicked == av.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.reSetButton(av.getBclo());
				}else{
					if(clicked == av.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.reSetButton(av.getBfix());
					}else{
						if(clicked == av.getLabResetPwd()){
//							System.out.println("����");
							SetButtonUporDown.reSetButton(av.getLabResetPwd());
						}else{
							if(clicked == av.getExeInquire()){
//								System.out.println("����");
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
				//���������Ϊ2����˫���¼�
				this.mouseClickedTwice(me);
				//�������clickNum��Ϊ1
				AdminInfoManageView_MouseListener.clickNum=1;
				AdminInfoManageView_MouseListener.flag=true;
				return;
			}
			//�½���ʱ����˫�������Ϊ500ms
			Timer timer = new Timer();
			
			timer.schedule(new TimerTask() {
				//ָʾ��ʱ��ִ�д���
				int num = 0;
				public void run() {
					// ˫���¼��Ѿ�ִ�У�ȡ����ʱ������
					if(AdminInfoManageView_MouseListener.flag) {
						num=0;
						AdminInfoManageView_MouseListener.clickNum=1;
						this.cancel();
						return;
					}
					//��ʱ���ٴ�ִ�У����õ����¼���Ȼ��ȡ����ʱ������
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
			// �����¼�
			System.out.println("1");
		}
		private void mouseClickedTwice(MouseEvent me) {
			// ˫���¼�
			System.out.println("2");
			new AdminInfoSave().LookAdmin(av);
		}
	
	}


