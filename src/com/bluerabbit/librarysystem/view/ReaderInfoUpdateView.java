package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.dao.ReaderInfoBeansDAO;
import com.bluerabbit.librarysystem.listener.ReaderInfoUpdateView_ActionListener;
/**
 * ������Ϣ�鿴���޸ģ���������
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class ReaderInfoUpdateView extends JDialog {
	private boolean mode;//t:add.f:fix
	private JPanel mianView;
	private JPanel contentView;
	private JPanel functionView;
	private int WindowsHeight;
	private int windowsWidth;

	//������Ϣ
	private ComboJLAndJT cltReaderID;//ѧ��
	private ComboJLAndJT cltReaderName;//����
	private ComboJLAndJT cltApart;//ѧԺ
	private ComboJLAndJT cltTheClass;//�༶
	private ComboJLAndJT cltTelNo;//�༶

	private JPanel pnlSex;
	private JComboBox<String> jcbSex;
	private JLabel LabSex;//�Ա�


	private JButton ok;
	private JButton cancel;

	private ReaderInfoManageView rv;
	boolean editor;
	private String readerId = null;

	String[] select = {"��","Ů"};//�Ա��
	/**
	 * ������Ϣ��ϸҳ��
	 * @param rv ������Ϣ������������ͼ
	 * @param title ��ǰ������ҳ�����
	 * @param readerId ���ߵ�ѧ�ţ���ѧ�Ÿ���ѧ�ż��ظ�����Ϣ��û��Ϊnull�Ļ��Ͳ�����
	 * @param editor �Ƿ��ܱ��༭
	 */
	public ReaderInfoUpdateView(ReaderInfoManageView rv,String title,String readerId,boolean editor){
		super(rv,title,true);
		this.rv = rv;
		mode = true;
		this.editor = editor;

		//���ѧ��Ϊ�գ�ģʽ����Ϊfalse,��������Ϣ
		if(readerId == null){
			readerId = "";
		}
		if(!readerId.equals("")){
			mode = false;
			this.readerId = readerId;
		}

		//��ø���ͼ�Ĵ�С
		WindowsHeight = rv.getHeight();
		windowsWidth = rv.getWidth();

		//���沿��
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();
		pnlSex = new JPanel();

		/**
		 * �����ӺͲ鿴����Ĵ�С��������
		 */
		int w;
		if(!editor){
			//�鿴ģʽ
			w = 130;
		}else{
			//����ģʽ
			w = 20;
		}

		//�ݲ���Ϣ
		cltReaderID = new ComboJLAndJT("    ѧ�ţ�",editor,w);//��������
		cltReaderName = new ComboJLAndJT("    ������",editor,w);//�������
		cltApart = new ComboJLAndJT("    ѧԺ��",editor,w);//�鿯���
		cltTheClass = new ComboJLAndJT("    �༶��",editor,w);//�鿯���
		cltTelNo = new ComboJLAndJT("��ϵ��ʽ��",editor,w);//�鿯���
		jcbSex = new JComboBox<String>();
		LabSex = new JLabel("    �Ա�");



		ok = new JButton("ȷ��");
		cancel = new JButton("ȡ��");

		if(!mode){
			//��Ϊ�գ����޸���Ϣ�������������ʼ����Ϣ
			System.out.println("�����˺���Ϣ��");
			loader(readerId);
			mode = false;
			ok = new JButton("����");
			//��ֹ����ѧ��
			//cltReaderID.getGettxt().setEditable(false);
		}
		if(!editor){
			//��ֹ�����Ա�
			jcbSex.setEnabled(false);
		}

		Init();
	}

	/**
	 * ���ض�����Ϣ���������ô�ʱ�ĳ�ʼֵ
	 * @param readerId �򿪵Ķ�����Ϣ
	 */
	private void loader(String readerId) {
		// TODO Auto-generated method stub
		ReaderInfoBeans readerInfo = null;
		//��ȡͼ����Ϣ
		readerInfo = new ReaderInfoBeansDAO().getReaderInfoById(readerId);

		//���ö�����Ϣ
		cltReaderID.setIText(readerInfo.getReaderID());//ѧ��

		cltReaderName.setIText(readerInfo.getReaderName());//����

		cltApart.setIText(readerInfo.getApart());//ѧԺ

		cltTheClass.setIText(readerInfo.getTheClass ());//�༶

		cltTelNo.setIText(readerInfo.getTelNo());//��ϵ��ʽ

		if(readerInfo.getSex().equals("Ů")){
			select[0] = "Ů";
			select[1] = "��";
		}
	}

	/**
	 * �����ʼ���������ڲ����ã����ò���ʲô��
	 */
	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-550, WindowsHeight-350);
		CenterView.CenterByWindow(this);
		//�������û��������ڴ�С

		//���ò���
		mianView.setLayout(new BorderLayout());
		contentView.setLayout(new GridLayout(3,2));
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//����������ͼ�߿�
		contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

		//�ݲ���Ϣ
		contentView.add(cltReaderID);//ѧ��
		contentView.add(cltReaderName);//����
		contentView.add(cltApart);//ѧԺ

		pnlSex.setLayout(new FlowLayout());

		pnlSex.add(LabSex);
		pnlSex.add(jcbSex);
		contentView.add(pnlSex);//�Ա�
		jcbSex.setModel(new DefaultComboBoxModel<String>(select));
		jcbSex.setPreferredSize(new Dimension(130,27));


		contentView.add(cltTheClass);//�༶
		contentView.add(cltTelNo);//��ϵ��ʽ



		//��Ӽ����¼�
		ReaderInfoUpdateView_ActionListener listener = new ReaderInfoUpdateView_ActionListener(this,rv);
		cancel.addActionListener(listener);
		ok.addActionListener(listener);

		//������Ա༭
		if(editor){
			functionView.add(ok);
		}
		functionView.add(cancel);


		mianView.add(functionView, BorderLayout.SOUTH);
		mianView.add(contentView, BorderLayout.CENTER);
		this.add(mianView);
		this.setVisible(true);
	}

	/**
	 * ��������ģʽ�����޸�ģʽ��false���޸�ģʽ��true������ģʽ
	 * @return ģʽ
	 */
	public boolean isMode() {
		return mode;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return ȷ������°�ť
	 */
	public JButton getOk() {
		return this.ok;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return ȡ����ť
	 */
	public JButton getCancel() {
		return this.cancel;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return ����ѧ�������
	 */
	public ComboJLAndJT getCltReaderID() {
		return cltReaderID;
	}	

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return �������������
	 */
	public ComboJLAndJT getCltReaderName() {
		return cltReaderName;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return ����ѧԺ�����
	 */
	public ComboJLAndJT getCltApart() {
		return cltApart;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return ���߰༶�����
	 */
	public ComboJLAndJT getCltTheClass() {
		return cltTheClass;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return ������ϵ��ʽ�����
	 */
	public ComboJLAndJT getCltTelNo() {
		return cltTelNo;
	}

	/**
	 * �ṩ�Ŀؼ��ӿں���
	 * @return �����Ա�������
	 */
	public JComboBox<String> getJcbSex(){
		return jcbSex;
	}
	/**
	 * ��ȡ��������ID
	 * @return �ո��½���ʱ��Ķ���ID��û�еĻ���null
	 */
	public String getReaderId() {
		return readerId;
	}
}
