package model;

import java.sql.Date;

public class Reader {
    private int id;
    private String identityCard;
    private String name;
    private Date dob;
    private String email;
    private String phone;
    private String address;
    private Date startDate;
    private Date endDate;

    public Reader() {
		super();
	}


    // Constructor đầy đủ
    public Reader(int id, String identityCard, String name, Date dob, String email, 
                  String phone, String address, Date startDate, Date endDate) {
        this.id = id;
        this.identityCard = identityCard;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Constructor rút gọn (6 tham số)
    public Reader(int id, String name, Date dob, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Constructor rút gọn (7 tham số)
    public Reader(int id, String name, Date dob, String email, String phone, String address, String identityCard) {
        this.id = id;
        this.identityCard = identityCard;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", identityCard=" + identityCard + ", name=" + name + ", dob=" + dob + ", email="
				+ email + ", phone=" + phone + ", address=" + address + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}

