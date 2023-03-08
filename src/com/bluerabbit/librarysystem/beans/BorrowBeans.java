package com.bluerabbit.librarysystem.beans;

/**
 * @author minuhy
 * @date 2023/3/8 17:00
 */
public class BorrowBeans {

    BookInfoBeans book;
    ReaderInfoBeans reader;

    String id; // 借阅编号
    String createTimestamp; // 借阅时间
    String willBackTimestamp; // 应该要归还的时间
    String isReturn; // 是否归还

    String borrowAdminId; // 借出管理员
    String returnAdminId; // 还入管理员
    String borrowAdminName; // 借出管理员
    String returnAdminName; // 还入管理员
    String updateTimestamp; // 更新时间戳
    String penalty; // 罚金
    String status; // 状态：0正常，1破碎，2破损严重，3丢失
    String bookNumber; // 借出数量

    public BorrowBeans() {
        book = new BookInfoBeans();
        reader = new ReaderInfoBeans();
    }

    /**
     * 借出管理员名字
     * @return 借出管理员名字
     */
    public String getBorrowAdminName() {
        return borrowAdminName;
    }

    /**
     * 借出管理员名字
     * @param borrowAdminName 借出管理员名字
     */
    public void setBorrowAdminName(String borrowAdminName) {
        this.borrowAdminName = borrowAdminName;
    }

    /**
     * 还入管理员名字
     * @return 还入管理员名字
     */
    public String getReturnAdminName() {
        return returnAdminName;
    }

    /**
     * 还入管理员名字
     * @param returnAdminName 还入管理员名字
     */
    public void setReturnAdminName(String returnAdminName) {
        this.returnAdminName = returnAdminName;
    }

    /**
     * 书籍作者
     * @return 书籍作者
     */
    public String getBookAuthor() {
        return book.getAuthor();
    }

    /**
     * 书籍作者
     * @param bookAuthor 书籍作者
     */
    public void setBookAuthor(String bookAuthor) {
        book.setAuthor(bookAuthor);
    }

    /**
     * 书籍出版社
     * @return 书籍出版社
     */
    public String getBookPublisher() {
        return book.getPublisher();
    }

    /**
     * 书籍出版社
     * @param bookPublisher 书籍出版社
     */
    public void setBookPublisher(String bookPublisher) {
        book.setPublisher(bookPublisher);
    }

    /**
     * 书室
     * @return 书室
     */
    public String getBookStack() {
        return book.getStack();
    }

    /**
     * 书室
     * @param bookStack 书室
     */
    public void setBookStack(String bookStack) {
        book.setStack(bookStack);
    }


    /**
     * 书架
     * @return 书架
     */
    public String getBookShelf() {
        return book.getBookShelf();
    }

    /**
     * 书架
     * @param bookShelf 书架
     */
    public void setBookShelf(String bookShelf) {
        book.setBookShelf(bookShelf);
    }


    /**
     * 书籍出版时间
     * @return 书籍出版时间
     */
    public String getBookPublishDate() {
        return book.getPublishDate();
    }

    /**
     * 书籍出版时间
     * @param bookPublishDate 书籍出版时间
     */
    public void setBookPublishDate(String bookPublishDate) {
        book.setPublishDate(bookPublishDate);
    }

    /**
     * 书籍ISBN编码
     * @return 书籍ISBN编码
     */
    public String getBookBarcode() {
        return book.getBookBarcode();
    }

    /**
     * 书籍ISBN编码
     * @param bookBarcode 书籍ISBN编码
     */
    public void setBookBarcode(String bookBarcode) {
        book.setBookBarcode(bookBarcode);
    }

    /**
     * 读者学院信息
     * @return 读者学院信息
     */
    public String getReaderApart() {
        return reader.getApart();
    }

    /**
     * 读者学院信息
     * @param readerApart 读者学院信息
     */
    public void setReaderApart(String readerApart) {
        reader.setApart(readerApart);
    }

    /**
     * 读者性别
     * @return 读者性别
     */
    public String getReaderSex() {
        return reader.getSex();
    }

    /**
     * 读者性别
     * @param readerSex 读者性别
     */
    public void setReaderSex(String readerSex) {
        reader.setSex(readerSex);
    }

    /**
     * 读者班级
     * @return 读者班级
     */
    public String getReaderClass() {
        return reader.getTheClass();
    }

    /**
     * 读者班级
     * @param readerClass 读者班级
     */
    public void setReaderClass(String readerClass) {
        reader.setTheClass(readerClass);
    }

    /**
     * 读者联系方式
     * @return 读者联系方式
     */
    public String getReaderTel() {
        return reader.getTelNo();
    }

    /**
     * 读者联系方式
     * @param readerTel 读者联系方式
     */
    public void setReaderTel(String readerTel) {
        reader.setTelNo(readerTel);
    }

    /**
     * 书数据对象
     * @return 书数据对象
     */
    public BookInfoBeans getBook() {
        return book;
    }

    /**
     * 书数据对象
     * @param book 书数据对象
     */
    public void setBook(BookInfoBeans book) {
        this.book = book;
    }

    /**
     * 读者书籍对象
     * @return 读者书籍对象
     */
    public ReaderInfoBeans getReader() {
        return reader;
    }

    /**
     * 读者书籍对象
     * @param reader 读者书籍对象
     */
    public void setReader(ReaderInfoBeans reader) {
        this.reader = reader;
    }

    /**
     * 借出管理员
     * @return 借出管理员
     */
    public String getBorrowAdminId() {
        return borrowAdminId;
    }

    /**
     * 借出管理员
     * @param borrowAdminId 借出管理员
     */
    public void setBorrowAdminId(String borrowAdminId) {
        this.borrowAdminId = borrowAdminId;
    }

    /**
     * 还入管理员
     * @return 还入管理员
     */
    public String getReturnAdminId() {
        return returnAdminId;
    }

    /**
     * 还入管理员
     * @param returnAdminId 还入管理员
     */
    public void setReturnAdminId(String returnAdminId) {
        this.returnAdminId = returnAdminId;
    }

    /**
     * 数据修改时间
     * @return 数据修改时间
     */
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 数据修改时间
     * @param updateTimestamp 数据修改时间
     */
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    /**
     * 罚金
     * @return 罚金
     */
    public String getPenalty() {
        return penalty;
    }

    /**
     * 罚金
     * @param penalty 罚金
     */
    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    /**
     * 书籍状态 0正常，1破碎，2破损严重，3丢失
     * @return 书籍状态 0正常，1破碎，2破损严重，3丢失
     */
    public String getStatus() {
        return status;
    }

    /**
     * 书籍状态 0正常，1破碎，2破损严重，3丢失
     * @param status 书籍状态 0正常，1破碎，2破损严重，3丢失
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 借出数量
     * @return 借出数量
     */
    public String getBookNumber() {
        return bookNumber;
    }

    /**
     * 借出数量
     * @param bookNumber 借出数量
     */
    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    /**
     * 读者编号
     * @return 读者编号
     */
    public String getReaderId() {
        return reader.getReaderID();
    }

    /**
     * 读者编号
     * @param readerId 读者编号
     */
    public void setReaderId(String readerId) {
        this.reader.setReaderID(readerId);
    }

    /**
     * 读者名字
     * @return 读者名字
     */
    public String getReaderName() {
        return reader.getReaderName();
    }

    /**
     * 读者名字
     * @param readerName 读者名字
     */
    public void setReaderName(String readerName) {
        reader.setReaderName(readerName);
    }

    /**
     * 书籍ID
     * @return 书籍ID
     */
    public String getBookId() {
        return book.getBookID();
    }

    /**
     * 书籍ID
     * @param bookId 书籍ID
     */
    public void setBookId(String bookId) {
        book.setBookID(bookId);
    }

    /**
     * 书籍名字
     * @return 书籍名字
     */
    public String getBookName() {
        return book.getBookName();
    }

    /**
     * 书籍名字
     * @param bookName 书籍名字
     */
    public void setBookName(String bookName) {
        book.setBookName(bookName);
    }

    /**
     * 借出时间
     * @return 借出时间
     */
    public String getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * 借出时间
     * @param createTimestamp 借出时间
     */
    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * 预计归还时间
     * @return 预计归还时间
     */
    public String getWillBackTimestamp() {
        return willBackTimestamp;
    }

    /**
     * 预计归还时间
     * @param willBackTimestamp 预计归还时间
     */
    public void setWillBackTimestamp(String willBackTimestamp) {
        this.willBackTimestamp = willBackTimestamp;
    }

    /**
     * 是否归还
     * @return 是否归还
     */
    public String getIsReturn() {
        return isReturn;
    }

    /**
     * 是否归还
     * @param isReturn 是否归还
     */
    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * 借出ID
     * @return 借出ID
     */
    public String getId() {
        return id;
    }

    /**
     * 借出ID
     * @param id 借出ID
     */
    public void setId(String id) {
        this.id = id;
    }
}
