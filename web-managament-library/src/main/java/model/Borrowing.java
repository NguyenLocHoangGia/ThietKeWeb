package model;

import java.util.Date;
import java.util.List;

public class Borrowing {
    private int id;
    private Reader reader;
    private User user;
    private Date borrowDate;
    private List<BorrowingDetails> borrowingDetails;

    public Borrowing() {
    }

    public Borrowing(int id, Reader reader, User user, Date borrowDate) {
        this.id = id;
        this.reader = reader;
        this.user = user;
        this.borrowDate = borrowDate;
    }

    public Borrowing(int id, Reader reader, User user, Date borrowDate, List<BorrowingDetails> borrowingDetails) {
        this.id = id;
        this.reader = reader;
        this.user = user;
        this.borrowDate = borrowDate;
        this.borrowingDetails = borrowingDetails;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public List<BorrowingDetails> getBorrowingDetails() {
        return borrowingDetails;
    }

    public void setBorrowingDetails(List<BorrowingDetails> borrowingDetails) {
        this.borrowingDetails = borrowingDetails;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", reader=" + reader +
                ", user=" + user +
                ", borrowDate=" + borrowDate +
                ", borrowingDetails=" + borrowingDetails +
                '}';
    }
}
