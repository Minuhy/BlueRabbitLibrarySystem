package com.bluerabbit.librarysystem.beans;

/**
 * @author minuhy
 * @date 2023/3/8 17:00
 */
public class BorrowBeans {

    BookInfoBeans book;
    ReaderInfoBeans reader;

    String id; // ���ı��
    String createTimestamp; // ����ʱ��
    String willBackTimestamp; // Ӧ��Ҫ�黹��ʱ��
    String isReturn; // �Ƿ�黹

    String borrowAdminId; // �������Ա
    String returnAdminId; // �������Ա
    String borrowAdminName; // �������Ա
    String returnAdminName; // �������Ա
    String updateTimestamp; // ����ʱ���
    String penalty; // ����
    String status; // ״̬��0������1���飬2�������أ�3��ʧ
    String bookNumber; // �������

    public BorrowBeans() {
        book = new BookInfoBeans();
        reader = new ReaderInfoBeans();
    }

    /**
     * �������Ա����
     * @return �������Ա����
     */
    public String getBorrowAdminName() {
        return borrowAdminName;
    }

    /**
     * �������Ա����
     * @param borrowAdminName �������Ա����
     */
    public void setBorrowAdminName(String borrowAdminName) {
        this.borrowAdminName = borrowAdminName;
    }

    /**
     * �������Ա����
     * @return �������Ա����
     */
    public String getReturnAdminName() {
        return returnAdminName;
    }

    /**
     * �������Ա����
     * @param returnAdminName �������Ա����
     */
    public void setReturnAdminName(String returnAdminName) {
        this.returnAdminName = returnAdminName;
    }

    /**
     * �鼮����
     * @return �鼮����
     */
    public String getBookAuthor() {
        return book.getAuthor();
    }

    /**
     * �鼮����
     * @param bookAuthor �鼮����
     */
    public void setBookAuthor(String bookAuthor) {
        book.setAuthor(bookAuthor);
    }

    /**
     * �鼮������
     * @return �鼮������
     */
    public String getBookPublisher() {
        return book.getPublisher();
    }

    /**
     * �鼮������
     * @param bookPublisher �鼮������
     */
    public void setBookPublisher(String bookPublisher) {
        book.setPublisher(bookPublisher);
    }

    /**
     * ����
     * @return ����
     */
    public String getBookStack() {
        return book.getStack();
    }

    /**
     * ����
     * @param bookStack ����
     */
    public void setBookStack(String bookStack) {
        book.setStack(bookStack);
    }


    /**
     * ���
     * @return ���
     */
    public String getBookShelf() {
        return book.getBookShelf();
    }

    /**
     * ���
     * @param bookShelf ���
     */
    public void setBookShelf(String bookShelf) {
        book.setBookShelf(bookShelf);
    }


    /**
     * �鼮����ʱ��
     * @return �鼮����ʱ��
     */
    public String getBookPublishDate() {
        return book.getPublishDate();
    }

    /**
     * �鼮����ʱ��
     * @param bookPublishDate �鼮����ʱ��
     */
    public void setBookPublishDate(String bookPublishDate) {
        book.setPublishDate(bookPublishDate);
    }

    /**
     * �鼮ISBN����
     * @return �鼮ISBN����
     */
    public String getBookBarcode() {
        return book.getBookBarcode();
    }

    /**
     * �鼮ISBN����
     * @param bookBarcode �鼮ISBN����
     */
    public void setBookBarcode(String bookBarcode) {
        book.setBookBarcode(bookBarcode);
    }

    /**
     * ����ѧԺ��Ϣ
     * @return ����ѧԺ��Ϣ
     */
    public String getReaderApart() {
        return reader.getApart();
    }

    /**
     * ����ѧԺ��Ϣ
     * @param readerApart ����ѧԺ��Ϣ
     */
    public void setReaderApart(String readerApart) {
        reader.setApart(readerApart);
    }

    /**
     * �����Ա�
     * @return �����Ա�
     */
    public String getReaderSex() {
        return reader.getSex();
    }

    /**
     * �����Ա�
     * @param readerSex �����Ա�
     */
    public void setReaderSex(String readerSex) {
        reader.setSex(readerSex);
    }

    /**
     * ���߰༶
     * @return ���߰༶
     */
    public String getReaderClass() {
        return reader.getTheClass();
    }

    /**
     * ���߰༶
     * @param readerClass ���߰༶
     */
    public void setReaderClass(String readerClass) {
        reader.setTheClass(readerClass);
    }

    /**
     * ������ϵ��ʽ
     * @return ������ϵ��ʽ
     */
    public String getReaderTel() {
        return reader.getTelNo();
    }

    /**
     * ������ϵ��ʽ
     * @param readerTel ������ϵ��ʽ
     */
    public void setReaderTel(String readerTel) {
        reader.setTelNo(readerTel);
    }

    /**
     * �����ݶ���
     * @return �����ݶ���
     */
    public BookInfoBeans getBook() {
        return book;
    }

    /**
     * �����ݶ���
     * @param book �����ݶ���
     */
    public void setBook(BookInfoBeans book) {
        this.book = book;
    }

    /**
     * �����鼮����
     * @return �����鼮����
     */
    public ReaderInfoBeans getReader() {
        return reader;
    }

    /**
     * �����鼮����
     * @param reader �����鼮����
     */
    public void setReader(ReaderInfoBeans reader) {
        this.reader = reader;
    }

    /**
     * �������Ա
     * @return �������Ա
     */
    public String getBorrowAdminId() {
        return borrowAdminId;
    }

    /**
     * �������Ա
     * @param borrowAdminId �������Ա
     */
    public void setBorrowAdminId(String borrowAdminId) {
        this.borrowAdminId = borrowAdminId;
    }

    /**
     * �������Ա
     * @return �������Ա
     */
    public String getReturnAdminId() {
        return returnAdminId;
    }

    /**
     * �������Ա
     * @param returnAdminId �������Ա
     */
    public void setReturnAdminId(String returnAdminId) {
        this.returnAdminId = returnAdminId;
    }

    /**
     * �����޸�ʱ��
     * @return �����޸�ʱ��
     */
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * �����޸�ʱ��
     * @param updateTimestamp �����޸�ʱ��
     */
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    /**
     * ����
     * @return ����
     */
    public String getPenalty() {
        return penalty;
    }

    /**
     * ����
     * @param penalty ����
     */
    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    /**
     * �鼮״̬ 0������1���飬2�������أ�3��ʧ
     * @return �鼮״̬ 0������1���飬2�������أ�3��ʧ
     */
    public String getStatus() {
        return status;
    }

    /**
     * �鼮״̬ 0������1���飬2�������أ�3��ʧ
     * @param status �鼮״̬ 0������1���飬2�������أ�3��ʧ
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * �������
     * @return �������
     */
    public String getBookNumber() {
        return bookNumber;
    }

    /**
     * �������
     * @param bookNumber �������
     */
    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    /**
     * ���߱��
     * @return ���߱��
     */
    public String getReaderId() {
        return reader.getReaderID();
    }

    /**
     * ���߱��
     * @param readerId ���߱��
     */
    public void setReaderId(String readerId) {
        this.reader.setReaderID(readerId);
    }

    /**
     * ��������
     * @return ��������
     */
    public String getReaderName() {
        return reader.getReaderName();
    }

    /**
     * ��������
     * @param readerName ��������
     */
    public void setReaderName(String readerName) {
        reader.setReaderName(readerName);
    }

    /**
     * �鼮ID
     * @return �鼮ID
     */
    public String getBookId() {
        return book.getBookID();
    }

    /**
     * �鼮ID
     * @param bookId �鼮ID
     */
    public void setBookId(String bookId) {
        book.setBookID(bookId);
    }

    /**
     * �鼮����
     * @return �鼮����
     */
    public String getBookName() {
        return book.getBookName();
    }

    /**
     * �鼮����
     * @param bookName �鼮����
     */
    public void setBookName(String bookName) {
        book.setBookName(bookName);
    }

    /**
     * ���ʱ��
     * @return ���ʱ��
     */
    public String getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * ���ʱ��
     * @param createTimestamp ���ʱ��
     */
    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * Ԥ�ƹ黹ʱ��
     * @return Ԥ�ƹ黹ʱ��
     */
    public String getWillBackTimestamp() {
        return willBackTimestamp;
    }

    /**
     * Ԥ�ƹ黹ʱ��
     * @param willBackTimestamp Ԥ�ƹ黹ʱ��
     */
    public void setWillBackTimestamp(String willBackTimestamp) {
        this.willBackTimestamp = willBackTimestamp;
    }

    /**
     * �Ƿ�黹
     * @return �Ƿ�黹
     */
    public String getIsReturn() {
        return isReturn;
    }

    /**
     * �Ƿ�黹
     * @param isReturn �Ƿ�黹
     */
    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * ���ID
     * @return ���ID
     */
    public String getId() {
        return id;
    }

    /**
     * ���ID
     * @param id ���ID
     */
    public void setId(String id) {
        this.id = id;
    }
}
