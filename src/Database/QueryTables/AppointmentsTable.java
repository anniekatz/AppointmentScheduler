package Database.QueryTables;

import Database.QueryUtils;
import Model.Appointment;
import Model.ReportModels.Report1;
import Model.ReportModels.Report2;
import Model.ReportModels.Report3;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Class is used to query the appointments table in database.
 */
public class AppointmentsTable {

    /**
     * This method is used to query the appointments table in database and return each record in an ObservableList.
     * @return returns an ObservableList of Appointments.
     */
    public static ObservableList<Appointment> getAppointments() {
        // Initialize Observable List
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        // Create query to get all appointments
        try {
            String query = "SELECT * FROM appointments;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();

            // Loop through records to get data for all appointments
            while (RS.next()) {
                int appointmentID = RS.getInt("Appointment_ID");
                String title = RS.getString("Title");
                String description = RS.getString("Description");
                String location = RS.getString("Location");
                String type = RS.getString("Type");
                LocalDateTime start = RS.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = RS.getTimestamp("End").toLocalDateTime();
                int customerID = RS.getInt("Customer_ID");
                int userID = RS.getInt("User_ID");
                int contactID = RS.getInt("Contact_ID");

                // Add each appointment to Observable List
                Appointment newAppointment = new Appointment(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
                appointmentList.add(newAppointment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return appointmentList;
    }

    /**
     * This method is used by appointment controller to add an appointment to the database.
     * @param title - String title of the appointment
     * @param description - String description of the appointment
     * @param location - String location of the appointment
     * @param type - String type of the appointment
     * @param start - LocalDateTime start of the appointment
     * @param end - LocalDateTime end of the appointment
     * @param customerID - int customerID of the appointment
     * @param userID - int userID of the appointment
     * @param contactID - int contactID of the appointment
     */
    public static void addAppointment(String title, String description, String location, String type, String start, String end, int customerID, int userID, int contactID) {
        try {
            // Insert into appointments table; DB will automatically assign an appointment ID
            String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES ('" + title + "', '" + description + "', '" + location + "', '" + type + "', '" + start + "', '" + end + "', '" + customerID + "', '" + userID + "', '" + contactID + "');";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used by appointment controller to update an existing appointment in the database.
     * @param appointmentID - int appointmentID of the appointment to be updated
     * @param title - String title of the appointment
     * @param description - String description of the appointment
     * @param location - String location of the appointment
     * @param type - String type of the appointment
     * @param start - LocalDateTime start of the appointment
     * @param end - LocalDateTime end of the appointment
     * @param customerID - int customerID of the appointment
     * @param userID - int userID of the appointment
     * @param contactID - int contactID of the appointment
     */
    public static void updateAppointment(int appointmentID, String title, String description, String location, String type, String start, String end, int customerID, int userID, int contactID) {
        try {
        // Update Appointment in appointments table where Appointment_ID = appointmentID
        String query = "UPDATE appointments SET Title = '" + title + "', Description = '" + description + "', Location = '" + location + "', Type = '" + type + "', Start = '" + start + "', End = '" + end + "', Customer_ID = '" + customerID + "', User_ID = '" + userID + "', Contact_ID = '" + contactID + "' WHERE Appointment_ID = '" + appointmentID + "';";
        QueryUtils.setPS(query);
        PreparedStatement PS = QueryUtils.getPS();
        PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used by appointment controller to delete an appointment from the database.
     * @param appointmentID - int appointmentID of the appointment to be deleted
     */
    public static void deleteAppointment(int appointmentID) {
        try {
            // Delete appointment from appointments table if appointment ID matches
            String query = "DELETE FROM appointments WHERE Appointment_ID = '" + appointmentID + "';";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used by appointment controller to generate Report 1 based on appointments table.
     * It counts the number of existing appointments by month and type.
     * @param month - String month to be used in query
     * @return returns an ObservableList of Report1.
     */
    public static ObservableList<Report1> generateReport1(String month) {
        // Initialize empty Observable List
        ObservableList<Report1> report1List = FXCollections.observableArrayList();
        // Create query to get num appointments for given month by type
        try {
            String query = "SELECT Type, COUNT(*) AS Total FROM appointments WHERE MONTHNAME(Start) = '" + month + "' GROUP BY Type;";
            if (month.equals("All Months")) {
                query = "SELECT Type, COUNT(*) AS Total FROM appointments GROUP BY Type;";
            }
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through records to add to Report 1 list
            while (RS.next()) {
                String type = RS.getString("Type");
                int total = RS.getInt("Total");
                Report1 newReport = new Report1(type, total);
                report1List.add(newReport);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return report1List;
    }


    /**
     * This method is used by appointment controller to generate Report 2 based on appointments table.
     * It returns an appointment schedule for a given contact
     * @param contactID - contact to be used in the query
     * @return returns an ObservableList of Report2.
     */
    public static ObservableList<Report2> generateReport2(int contactID) {
        // Initialize empty Observable List
        ObservableList<Report2> report2List = FXCollections.observableArrayList();
        // Query to get appointments by contact ID
        try {
            String query = "SELECT * FROM appointments WHERE Contact_ID = '" + contactID + "';";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // loop through result set and add to Report 2 list
            while (RS.next()) {
                int appointmentID = RS.getInt("Appointment_ID");
                String title = RS.getString("Title");
                String type = RS.getString("Type");
                String description = RS.getString("Description");
                LocalDateTime start = RS.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = RS.getTimestamp("End").toLocalDateTime();
                int customerID = RS.getInt("Customer_ID");
                Report2 newReport = new Report2(appointmentID, title, type, description, start, end, customerID);
                report2List.add(newReport);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return report2List;
    }

    /**
     * This method is used by appointment controller to generate Report 3 based on appointments table.
     * It returns the total number of upcoming and past appointments for each customer.
     * @return returns an ObservableList of Report3.
     */
    public static ObservableList<Report3> generateReport3() {
        ObservableList<Report3> report3List = FXCollections.observableArrayList();
        try {
            // Query to get total number of appointments, future appointments, and past appointments per customer ID
            String query = "SELECT Customer_ID, Count(*) as TotalCount, sum(case when Start > CURRENT_TIMESTAMP then 1 else 0 end) as TotalFuture, sum(case when Start < CURRENT_TIMESTAMP then 1 else 0 end) as TotalPast from appointments group by Customer_ID;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through result set and add to Report 3 list
            while (RS.next()) {
                int customerID = RS.getInt("Customer_ID");
                int totalFuture = RS.getInt("TotalFuture");
                int totalPast = RS.getInt("TotalPast");
                Report3 newReport = new Report3(customerID, totalFuture, totalPast);
                report3List.add(newReport);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return report3List;
    }
}