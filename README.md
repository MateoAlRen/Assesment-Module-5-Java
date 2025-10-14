# 📚 LibroNova - CSV to Database Uploader

A Java Swing application that uploads CSV data directly into a MySQL database for the **LibraryNova** system.  
This tool allows quick import of user and book data into your database from `.csv` files.

---

## 🧩 Features

- Simple GUI built with **Java Swing**
- Upload `.csv` files for:
  - `library_user` table
  - `library_books` table
- Bulk insertion using JDBC batch operations
- CSV parsing with quoted field support
- Progress indicator with status updates
- Error handling and file validation

---

## ⚙️ Requirements

Before running the program, ensure the following are installed:

| Requirement | Version / Note |
|--------------|----------------|
| ☕ Java JDK | 17 or later |
| 🐬 MySQL Server | 8.0+ |
| 🧱 JDBC Driver | MySQL Connector/J |
| 🖥 IDE | IntelliJ IDEA, Eclipse, or NetBeans |
| 🧾 Database | `LibraryNova` with the following tables |

---

## 🗃️ Database Structure

Create the database and tables before running the program:

```sql
CREATE DATABASE LibraryNova;
USE LibraryNova;

CREATE TABLE library_user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    staff_member BOOLEAN NOT NULL
);

CREATE TABLE library_books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(200) NOT NULL,
    book_stock INT NOT NULL,
    book_status BOOLEAN
);
```

📦 Installation

Clone or extract this repository:

git clone https://github.com/yourusername/LibroNova.git


or extract the LibroNova.zip file.

Open the project in your preferred Java IDE.

Add MySQL JDBC driver to your project dependencies:
Example for IntelliJ:

File → Project Structure → Libraries → Add MySQL Connector/J

Update your database credentials in CSVtoDBUploader.java:

private static final String URL = "jdbc:mysql://localhost:3306/LibraryNova";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

▶️ Usage

Run the application by executing:

java -cp .;mysql-connector-j.jar view.CSVtoDBUploader


or directly from your IDE.

The main window will appear:

Click “Select Users CSV” to import user data.

(If implemented) Click “Select Books CSV” to import books.

You will see the progress bar moving while data is uploaded.

Once complete, a confirmation message will appear:

Upload complete ✅

📄 Example CSV Files
library_user.csv
user_name,user_email,user_password,staff_member
Juan Perez,juan.perez@mail.com,1234,true
Ana Gómez,ana.gomez@mail.com,abcd,false

library_books.csv
book_name,book_stock,book_status
El Quijote,5,true
1984,10,false


```

🧑‍💻 Author

Mateo Algarin Rendon
C.C. 1034919321
mateoalgarinrendon38@gmail.com
