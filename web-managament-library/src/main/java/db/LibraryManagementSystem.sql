-- DROP DATABASE libman;-- 

CREATE DATABASE libman;

-- Sử dụng cơ sở dữ liệu
USE libman;

create table Users (
	id int not null auto_increment primary key,
	username varchar(255) not null,
	password varchar(255) not null,
	name varchar(255) not null,
	position varchar(255) not null
);

create table Categories (
	id int not null auto_increment primary key,
	name varchar(255) not null,
	description varchar(255)
);

create table Books (
	id int not null auto_increment primary key,
	name varchar(255) not null,
	categoryId int not null,
	image varchar(255) default null,
	author varchar(255),
	description varchar(255),
	amount int,
	createdAt timestamp null default current_timestamp,
	FOREIGN KEY (categoryId) REFERENCES Categories(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Readers (
	id int not null auto_increment primary key,
	identityCard varchar(255) not null,
	name varchar(255) not null,
	dob date,
	email varchar(255),
	phone varchar(15),
	address varchar(255),
	startDate date,
	endDate date
);

create table Borrowings (
	id int not null auto_increment primary key,
	readerId int,
	userId int,
	borrowDate date,
	createdAt timestamp DEFAULT NOW(),
	FOREIGN KEY (readerId) REFERENCES Readers(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table BorrowingDetails (
	id int not null auto_increment primary key,
	borrowingId int,
	bookId int,
	returnDate date,
	FOREIGN KEY (borrowingId) REFERENCES Borrowings(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (bookId) REFERENCES Books(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Chèn dữ liệu vào bảng Users
INSERT INTO Users (username, password, name, position) VALUES
('admin', 'admin123', 'Nguyễn Văn A', 'Quản trị viên'),
('user1', 'user123', 'Trần Thị B', 'Thủ thư'),
('user2', 'user456', 'Lê Văn C', 'Thủ thư');

-- Chèn dữ liệu vào bảng Categories
INSERT INTO Categories (name) VALUES
('Khoa học'),
('Văn học'),
('Công nghệ thông tin'),
('Lịch sử'),
('Tâm lý học');

-- Chèn dữ liệu vào bảng Books
INSERT INTO Books (name, categoryId, image, author, amount, createdAt) VALUES
('Lược sử loài người', 4, NULL, 'Yuval Noah Harari', 10, '2024-01-01'),
('Đắc Nhân Tâm', 5, NULL, 'Dale Carnegie', 10, '2024-01-05'),
('Chí Phèo', 2, NULL, 'Nam Cao', 10, '2024-01-10'),
('Lập trình Python', 3, NULL, 'Mark Lutz', 10, '2024-01-15'),
('Toán học cao cấp', 1, NULL, 'Nguyễn Đình Trí', 10, '2024-01-20');

-- Chèn dữ liệu vào bảng Readers
INSERT INTO Readers (identityCard, name, dob, email, phone, address, startDate, endDate) VALUES
('0123456789', 'Phạm Văn D', '1990-05-20', 'phamvand@example.com', '0987654321', 'Hà Nội', '2024-01-01', '2025-01-01'),
('9876543210', 'Nguyễn Thị E', '1995-08-15', 'nguyenthie@example.com', '0976543210', 'TP. Hồ Chí Minh', '2024-01-05', '2025-01-05'),
('1234567890', 'Lê Minh F', '2000-12-01', 'leminhf@example.com', '0965432109', 'Đà Nẵng', '2024-01-10', '2025-01-10');

-- Chèn dữ liệu vào bảng Borrowings
INSERT INTO Borrowings (readerId, userId, borrowDate) VALUES
(1, 1, '2024-02-01'),
(2, 2, '2024-02-03'),
(3, 3, '2024-02-05');

-- Chèn dữ liệu vào bảng BorrowingDetails
INSERT INTO BorrowingDetails (borrowingId, bookId, returnDate) VALUES
(1, 1, '2024-02-15'),
(1, 2, '2024-02-15'),
(2, 3, '2024-02-20'),
(2, 4, '2024-02-20'),
(3, 5, '2024-02-25');
