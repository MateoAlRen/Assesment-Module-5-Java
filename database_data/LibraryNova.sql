CREATE DATABASE LibraryNova;
use LibraryNova;

CREATE TABLE library_user(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    staff_member BOOLEAN NOT NULL
);

CREATE TABLE library_books(
	book_id INT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(200) NOT NULL,
    book_stock INT NOT NULL,
    book_status BOOLEAN
);

CREATE TABLE book_loan(
	loan_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    book_quantity INT NOT NULL,
    loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES library_user(user_id),
    FOREIGN KEY (book_id) REFERENCES library_books(book_id)
);

CREATE VIEW user_loans AS
SELECT 
    bl.user_id,
    bl.loan_id,
    lb.book_id,
    lb.book_name,
    bl.book_quantity
FROM book_loan bl
JOIN library_books lb 
ON lb.book_id = bl.book_id;


SELECT * FROM library_user;
SELECT * FROM library_books;
SELECT * FROM book_loan;
