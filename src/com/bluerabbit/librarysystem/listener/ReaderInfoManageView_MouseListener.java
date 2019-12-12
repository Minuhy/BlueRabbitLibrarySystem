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
 * ���߹������ļ����࣬�����������߹����������а�ť�����÷����ִ�в���
 * @author minuy
 *
 */
public class ReaderInfoManageView_MouseListener implements MouseListener {
	ReaderInfoManageView rv;
	private static  boolean flag = false;		//˫���¼���ִ��ʱ��Ϊ��
	private static int clickNum = 1;		//ָʾ�����������Ĭ��Ϊ����
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
			System.out.println("ɾ��");
			new ReaderInfoServer(rv, null).del();
		}else{
			if(clicked == rv.getBadd()){
				System.out.println("����");
				//�򿪽���
				new ReaderInfoUpdateView(rv,"��Ӷ�����Ϣ",null,true);
			}else{
				if(clicked == rv.getBclo()){
					System.out.println("�ر�");
					rv.dispose();

				}else{
					if(clicked == rv.getBfix()){
						System.out.println("�޸�");
						//���޸Ĵ���
						new ReaderInfoServer(rv, null).FixInfoGet(true);
					}else{
						if(clicked == rv.getExeInquire()){
							System.out.println("����");
							new ReaderInfoServer(rv, null).search();
						}else{
							if(clicked == rv.getTableDataView()){
								System.out.println("���");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.setButtonDown(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.setButtonDown(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.setButtonDown(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.setButtonDown(rv.getBfix());
					}else{

						if(clicked == rv.getExeInquire()){
//							System.out.println("����");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.SetButtonUp(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.SetButtonUp(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.SetButtonUp(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.SetButtonUp(rv.getBfix());
					}else{
						if(clicked == rv.getExeInquire()){
//							System.out.println("����");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.SetButtonUp(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.SetButtonUp(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.SetButtonUp(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.SetButtonUp(rv.getBfix());
					}else{
						if(clicked == rv.getExeInquire()){
//							System.out.println("����");
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
//			System.out.println("ɾ��");
			SetButtonUporDown.reSetButton(rv.getBdel());
		}else{
			if(clicked == rv.getBadd()){
//				System.out.println("����");
				SetButtonUporDown.reSetButton(rv.getBadd());
			}else{
				if(clicked == rv.getBclo()){
//					System.out.println("�ر�");
					SetButtonUporDown.reSetButton(rv.getBclo());
				}else{
					if(clicked == rv.getBfix()){
//						System.out.println("�޸�");
						SetButtonUporDown.reSetButton(rv.getBfix());
					}else{
						if(clicked == rv.getExeInquire()){
//							System.out.println("����");
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
			//���������Ϊ2����˫���¼�
			this.mouseClickedTwice(me);
			//�������clickNum��Ϊ1
			ReaderInfoManageView_MouseListener.clickNum=1;
			ReaderInfoManageView_MouseListener.flag=true;
			return;
		}
		//�½���ʱ����˫�������Ϊ500ms
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			//ָʾ��ʱ��ִ�д���
			int num = 0;
			public void run() {
				// ˫���¼��Ѿ�ִ�У�ȡ����ʱ������
				if(ReaderInfoManageView_MouseListener.flag) {
					num=0;
					ReaderInfoManageView_MouseListener.clickNum=1;
					this.cancel();
					return;
				}
				//��ʱ���ٴ�ִ�У����õ����¼���Ȼ��ȡ����ʱ������
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
		// �����¼�
//		System.out.println("1");
	}
	private void mouseClickedTwice(MouseEvent me) {
		// ˫���¼�
		System.out.println("2");
		new ReaderInfoServer(rv, null).FixInfoGet(false);
	}

}


