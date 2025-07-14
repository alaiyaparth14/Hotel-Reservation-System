package Project;

import java.sql.*;
import java.util.Scanner;


public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";

    private static final String userName = "root";

    private static final String password = "User@123";

    public static void main(String[] args) throws ClassNotFoundException,InterruptedException{
         try{
             Class.forName("com.mysql.cj.jdbc.Driver");
         }catch (ClassNotFoundException e){
             System.out.println(e.getMessage());
         }

         try {
            Connection connection =  DriverManager.getConnection(url,userName,password);
            while (true){
                System.out.println("\n ======WELCOME TO HOTEL RESERVATION SYSTEM PROJECT=======");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a Room.");
                System.out.println("2. View Reserve a Room.");
                System.out.println("3. Get Room Number Details.");
                System.out.println("4. Update Room Number Details.");
                System.out.println("5. Delete Room Reservation.");
                System.out.println("0. Exit");
                System.out.print("Please select option : ");
                int choice = sc.nextInt();
                System.out.println();
                switch (choice){
                    case 1 :
                        reserveRoom(connection,sc);
                        break;
                    case 2:
                        viewReserveRoom(connection);
                        break;
                    case 3:
                        getRoomNumberDetails(connection,sc);
                        break;
                    case 4:
                        updateRoomNoDetails(connection,sc);
                        break;
                    case 5:
                        deleteRoomNumberDetails(connection,sc);
                        break;
                    case 0 :
                    default:
                        exit();
                        sc.close();
                        connection.close();
                        return;
                }
            }
         }catch (SQLException e){
             System.out.println(e.getMessage());
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
    }

    private static void reserveRoom(Connection connection,Scanner sc){
        System.out.print("Please enter your name : ");
        String guest_name = sc.next();
        System.out.print("Please enter your room number : ");
        int roomNumber = sc.nextInt();
        System.out.print("Please enter your contact number :  ");
        String contactNumber = sc.next();

        String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES ('" + guest_name + "', " + roomNumber + ", '" + contactNumber + "')";

        try(Statement st = connection.createStatement()){
            int rowAffected = st.executeUpdate(sql);
            if (rowAffected>0){
                System.out.println("Reservation Successfully.....");
            }else{
                System.out.println("Reservation failed");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void viewReserveRoom(Connection connection){

        String sql = "Select reservation_id,guest_name,room_number,contact_number,reservation_date from reservations";

        try(Statement st = connection.createStatement()) {
              ResultSet rs = st.executeQuery(sql);
              while (rs.next()){
                  int reservationId = rs.getInt("reservation_id");
                  String guest_name = rs.getString("guest_name");
                  int room_number = rs.getInt("room_number");
                  String reservation_date = rs.getString("reservation_date").toString();

                  System.out.println("reservationId : " + reservationId);
                  System.out.println("guestName : " + guest_name);
                  System.out.println("roomNumber : " + room_number);
                  System.out.println("reservationDate : " + reservation_date);

                  System.out.println();
              }
              rs.close();
              st.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getRoomNumberDetails(Connection connection,Scanner scanner){
        System.out.print("Please enter your reservationId : ");
        int rerservationId = scanner.nextInt();
        System.out.print("Please enter your guest_name : ");
        String guest_name = scanner.next();

        String sql = "SELECT room_number FROM reservations WHERE reservation_id = " + rerservationId + " AND guest_name = '" + guest_name + "'";

        try(Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                int room_number = rs.getInt("room_number");
                System.out.println("Room number for reservation id " + rerservationId + " and guest " + guest_name + " your roomNo is : " + room_number);
            }else{
                System.out.println("Reservation not found for given id and given name");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void updateRoomNoDetails(Connection connection,Scanner sc){
        System.out.print("Please enter your reservationId : ");
        int reservation_id = sc.nextInt();
        System.out.print("Please enter your room_number : ");
        int room_Number = sc.nextInt();
        System.out.print("Please enter your name : ");
        String guest_Name = sc.next();
        System.out.print("Please enter your contact number : ");
        String contact_Number = sc.next();

        String sql = "UPDATE reservations SET room_number = " + room_Number +
                ", guest_name = '" + guest_Name + "'" +
                ", contact_number = '" + contact_Number + "'" +
                " WHERE reservation_id = " + reservation_id;
        try(Statement st = connection.createStatement()) {
           int rowAffected =  st.executeUpdate(sql);
           if (rowAffected>0){
               System.out.println("Update reservation details successfully");
           }else{
               System.out.println("updatation fail");
           }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void deleteRoomNumberDetails(Connection connection,Scanner sc){
        System.out.print("Please enter your reservationId : ");
        int reservation_id = sc.nextInt();

        String sql = "Delete from reservations where reservation_id=" + reservation_id;
        try(Statement statement = connection.createStatement()){
           int rowAffected = statement.executeUpdate(sql);
           if (rowAffected>0) {
               System.out.println("deleted record successfully " + rowAffected + "row(s) affected");
           }else{
               System.out.println("failed deletion");
           }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void exit() throws InterruptedException{
        System.out.print("Existing System");
        int i = 5;
        while (i!=0){
            System.out.print(".");
            Thread.sleep(400);
            i--;
        }
        System.out.println();
        System.out.println("======<Thank You For Using Our Application=========>!");
    }



}
