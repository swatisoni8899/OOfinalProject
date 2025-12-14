📦 Inventory Management System (JavaFX)

A JavaFX-based desktop application for managing inventory records with role-based access control. The system uses a MySQL database for persistent storage and follows the Model–View–Controller (MVC) design pattern.

🛠️ Technologies Used

Language: Java (JDK 17)

GUI Framework: JavaFX

Database: MySQL (Remote – papademas.net)

Database Connectivity: JDBC (MySQL Connector/J)

IDE: Eclipse

Architecture: MVC (Model–View–Controller)

📂 Project Structure
JavaFX project/
│
├── src/
│   ├── application/
│   │   ├── Main.java
│   │   └── application.css   (optional / not used)
│
│   ├── controllers/
│   │   ├── LoginController.java
│   │   ├── UserDashboardController.java
│   │   └── AdminDashboardController.java
│
│   ├── models/
│   │   ├── DBConnect.java
│   │   ├── User.java
│   │   ├── UserDAO.java
│   │   ├── InventoryItem.java
│   │   └── InventoryDAO.java
│
├── resources/   ← Source Folder
│   └── views/
│       ├── LoginView.fxml
│       ├── UserDashboard.fxml
│       ├── AdminDashboard.fxml
│       └── login.css
│
├── lib/
│   └── mysql-connector-j-9.5.0.jar
│
├── build.fxbuild
└── README.md


📌 Important:
The resources folder must be marked as a Source Folder so that FXML and CSS files load correctly.

▶️ How to Run the Application
1️⃣ Prerequisites

Java JDK 17

JavaFX SDK installed

Eclipse IDE for Java Developers

MySQL Connector/J (already included in /lib)

2️⃣ Import Project into Eclipse

Open Eclipse

Go to File → Import → Existing Projects into Workspace

Select the project folder

Click Finish

3️⃣ Configure JavaFX (VM Arguments)

Go to:
Run → Run Configurations → Java Application → Main

Add the following to VM arguments
(Update the JavaFX SDK path as per your system.)

--module-path "PATH_TO_JAVAFX_SDK/lib"
--add-modules javafx.controls,javafx.fxml
--enable-native-access=javafx.graphics

4️⃣ Run the Application

Run Main.java as a Java Application

The Login screen will appear on startup

🔐 Login Credentials & Dashboards

The application supports two user roles, each with a dedicated dashboard.

👤 Admin User

Username: admin

Password: admin123

View Loaded: AdminDashboard.fxml

Permissions:

View inventory

Add new inventory items

Update inventory items

Delete inventory items

👥 Regular User

Username: user1

Password: user123

View Loaded: UserDashboard.fxml

Permissions:

View inventory

Add new inventory items

❌ Cannot update or delete items

Dashboard navigation is handled dynamically after login based on the user’s role.

🗄️ Database Configuration

Host: www.papademas.net

Port: 3307

Database: 510fp

Username: fp510

Password: 510

Required Tables

users_new – stores user credentials and roles

inventory – stores inventory records

✨ Application Features

Secure login system

Role-based dashboard loading

JavaFX GUI with CSS styling

Inventory CRUD operations

JDBC-based database connectivity

MVC-based clean code organization

📘 Course Information

Course: ITMD-510 – Object-Oriented Application Development

Institution: Illinois Institute of Technology

Semester: Fall 2025

👤 Author

Swati Soni

📝 Notes

Ensure the MySQL connector JAR is included only once in the build path.

Ensure JavaFX VM arguments are set correctly before running.

This project is intended for academic demonstration purposes.
