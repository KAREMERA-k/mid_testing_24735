# AUCA Library Management System

This project is a command-line-based Library Management System designed for the Software Testing and Techniques course. It is built using Java and follows a modular structure to manage users, memberships, book borrowing, and location data. 

The project does not include a graphical UI and instead uses Java User Input (Scanner) for data input, with a simulated in-memory database to manage records.

## Project Features

1. **User Management**:
   - Create accounts with hashed passwords for secure storage.
   - Authenticate users with their hashed passwords.
   - Register users for membership plans (Gold, Silver, or Striver).
   
2. **Membership Plans**:
   - **Gold**: 50 Rwf/day, max 5 books.
   - **Silver**: 30 Rwf/day, max 3 books.
   - **Striver**: 10 Rwf/day, max 2 books.

3. **Book Borrowing System**:
   - Borrow books with borrowing limits based on membership.
   - Validate borrowing limits to prevent exceeding the allowed number of books.
   - Calculate late return fees based on membership type.

4. **Location and Room Management**:
   - Create locations (Province, District, Sector, Cell, Village).
   - Retrieve a location’s province by village.
   - Count books in specific rooms and find rooms with the fewest books.

## Project Structure

