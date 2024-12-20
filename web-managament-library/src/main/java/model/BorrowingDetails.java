package model;

import java.util.Date;

public class BorrowingDetails {
    private int id;
    private Book book;
    private Date returnDate;
    private String status;

    public BorrowingDetails() {
    }

    public BorrowingDetails(int id, Book book, Date returnDate, String status) {
        this.id = id;
        this.book = book;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowingDetail{" +
                "id=" + id +
                ", book=" + book +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}
