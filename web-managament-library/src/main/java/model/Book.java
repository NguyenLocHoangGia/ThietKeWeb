package model;

public class Book {
	private int id;
	private String name;
	private String image;
	private Category category;
	private String author;
	private String description;
	private int amount;
	private String createdAt;
	
	public Book() {
		super();
	}
	
	
	public Book(int id, String name, String image, Category category, String author, String description, int amount,
			String createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.category = category;
		this.author = author;
		this.description = description;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", image=" + image + ", category=" + category + ", author="
				+ author + ", amount=" + amount + ", createdAt=" + createdAt + "]";
	}
}
