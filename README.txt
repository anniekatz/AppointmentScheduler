# Appointments Scheduler
WGU August 2022
C195 Performance Assessment
Java GUI-Based App: Appointments Scheduler for Fictional Company

# Contact
Ann (Annie) Katz
Program: B.S. Computer Science
ID: 010458098
Email: akatz8@wgu.edu

# Version Info
IntelliJ IDEA Community 2021.1.3
Java SE 17.0.1
JavaFX SDK 17.0.1
MySQL Connector Java 8.1.25

# Additional Report
The third report is one that lists all Customer IDs (with appointments) in the database, along with that customer's total number of past appointments and their total number of future appointments scheduled.

# Running the Program
Program Start
- The program will start by connecting to the local MySQL database. Please ensure username, password, and database URL are correct.
- Upon program open, the application will determine your local time zone and language (English or French) based on your system.
Login
- Program will open to the login page. Please enter a correct username (not User ID) and password based on the user table in database to proceed.
- Login attempts will be tracked in login_activity.txt
- Upon successful login, user will be notified if they have any upcoming appointments within the next 15 minutes.
Home
- User will be redirected to home page. Navigate to Appointments, Customers, or Reports page from here.
- You can use buttons at the top of each page to navigate between pages (Appointments, Customers, and Reports).
Appointments Page
- User can view, add, change, or delete appointments in the application and database here.
- Note: Appointment time information will be shown in user's local time zone, but will save to database in UTC.
- To add an appointment, fill out the form and click Add/Update. Form items will be validated to ensure information is in the correct format. A new appointment ID will be generated automatically. Appointment will only be scheduled if customer does not already have an appointment at that time.
- Select a row in the table to update or delete the appointment using the form below the table.
- Select reset button to deselect the appointment and clear the form.
- If you delete an appointment, you will be asked to confirm delete.
Customers Page
- User can view, add, update, or delete a customer here.
- To add a new customer, fill out the form and click Add/Update. Customer ID will be generated automatically, and form items will be validated to ensure correct format.
- Select a row in the table to update or delete the customer in the form below.
- Use the reset button to deselect the customer and clear the form.
- Use the delete button to delete selected customer. Customer will only be deleted if they do not have any appointments. If you delete a customer, you will be asked to confirm delete.
Reports Page
- View Reports 1, 2, and 3 by selecting a tab.
- Report 1: Show Appointment totals by month and type. Select a month using the combo box and see a total number of appointments for each appointment type.
    - Select "All months" in combo box to see a total number of appointments by type for all months.
- Report 2: Show an appointment schedule for each contact. Select the contact from the combo box to see their schedule.
- Report 3: Show only customer IDs if they have an appointment scheduled, then show each customer's total past appointments and total upcoming appointments.
Closing
- Upon, application close, user will be logged out and database will disconnect.
