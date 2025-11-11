📚 Student Management REST API

A Spring Boot REST API for managing students with role-based access (ADMIN/USER), secure password encryption, and automated email notifications.

🧠 Tech Stack
  Java 17+
  Spring Boot
  Spring Security
  MySQL Database
  Lombok
  Java Mail Sender
  Postman (API Testing)

⚙️ Features

 👤 Role-based Access — Only ADMIN can create/delete, users can only view data

 🔐 Password Encryption — Use BCrypt for secure password storage

 📧 Email Notifications — Sent automatically email on create/update/delete

 🧾 DTO & Mapper pattern for clean architecture

 🚫 Error Handling & Logging with Slf4j


Test APIs using Postman:

 GET /api/student → View all students

 POST /api/student → Create new student

 DELETE /api/student/{id} → Delete student

 GET /api/student/{id} → Get student by ID

📧 Email Notification Example — When a student is added:

Subject: Welcome to Student Management System 

Hi [username],
Your record has been created successfully. 
Regards,
Admin

🧩 API JSON Example (POST) {

 "username": "Username,"

 "email": "abc@gmail.com,"

 "password": "1234,"

 "role": "USER"

 }

👨‍💻 Author

  Kishan Singh
  
  📧 Email : [kishansingh121128@gmail.com] 
  
  🌐 Linkedin : [https://www.linkedin.com/in/kishan-java-backend/]
