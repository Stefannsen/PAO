package ConfigDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDataUsingStatemnt {

    public void createTableAuthor()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS author" +
                "(id int PRIMARY KEY AUTO_INCREMENT, lastName varchar(40), firstName varchar(40), nationality varchar(40))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTablePublisher()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS publisher" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(40), country varchar(40))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableSection()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS section" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(40))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableBook()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS book" +
                "(id int PRIMARY KEY AUTO_INCREMENT, bookName varchar(40), nrOfCopies int, crtNrOfCopies int," +
                "sectionId int , authorId int, publisherId int, CONSTRAINT fk_section FOREIGN KEY(sectionId) REFERENCES section(id), " +
                "CONSTRAINT fk_author FOREIGN KEY(authorId) REFERENCES author(id), CONSTRAINT fk_publisher FOREIGN KEY(publisherId) " +
                "REFERENCES publisher(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableCustomer()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS customer" +
                "(id int PRIMARY KEY AUTO_INCREMENT, lastName varchar(40), firstName varchar(40), cnp varchar(14))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableNormalReader()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS normal_reader" +
                "(id int PRIMARY KEY, CONSTRAINT fk_cus_nor FOREIGN KEY(id) REFERENCES customer(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableVIPReader()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS vip_reader" +
                "(id int PRIMARY KEY, CONSTRAINT fk_cus_vip FOREIGN KEY(id) REFERENCES customer(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableStudent()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS student_reader" +
                "(id int PRIMARY KEY, universityName varchar(40), " +
                "yearOfStudy varchar(40), CONSTRAINT fk_cus_stu FOREIGN KEY(id) REFERENCES customer(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableBorrowInfo()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS borrow_info" +
                "(customerId int, bookId int, borrowDate DATE, returnDate DATE, " +
                "PRIMARY KEY(customerId, bookId), CONSTRAINT fk_cus_bi FOREIGN KEY(customerId) REFERENCES customer(id), " +
                "CONSTRAINT fk_book_bi FOREIGN KEY(bookId) REFERENCES book(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableAudit()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS audit" +
                "(id int PRIMARY KEY AUTO_INCREMENT, functionName varchar(40), timeStamp TIMESTAMP )";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


//    public void addAuthor() {
//        String insertPersonSql = "INSERT INTO author(lastName, firstName, nationality) VALUES ('Creanga', 'Ion', 'Romania')";
//        Connection connection = DatabaseConfiguration.getDatabaseConnection();
//
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(insertPersonSql);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public void getAllAuthors()    {
        String selectSql = "SELECT * FROM author";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Last Name: " + resultSet.getString(2));
                System.out.println("First Name: " + resultSet.getString(3));
                System.out.println("Nationality: " + resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllPublishers()    {
        String selectSql = "SELECT * FROM publisher";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Publisher's Name: " + resultSet.getString(2));
                System.out.println("Country: " + resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllSections()    {
        String selectSql = "SELECT * FROM section";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Section Name: " + resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllBooks()    {
        String selectSql = "SELECT * FROM book";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Title: " + resultSet.getString(2));
                System.out.println("NrOfCopies: " + resultSet.getInt(3));
                System.out.println("CrtNrOfCopies: " + resultSet.getInt(4));
                System.out.println("Section id: " + resultSet.getInt(5));
                System.out.println("Author id: " + resultSet.getInt(6));
                System.out.println("Publisher id: " + resultSet.getInt(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllCustomers()    {
        String selectSql = "SELECT * FROM customer";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Last Name: " + resultSet.getString(2));
                System.out.println("First Name: " + resultSet.getString(3));
                System.out.println("CNP: " + resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllNormalReaders()    {
        String selectSql = "SELECT c.id, c.lastName, c.firstName, c.cnp" +
                " FROM customer c, normal_reader nr WHERE c.id = nr.id";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Last Name: " + resultSet.getString(2));
                System.out.println("First Name: " + resultSet.getString(3));
                System.out.println("CNP: " + resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllVIPReaders()    {
        String selectSql = "SELECT c.id, c.lastName, c.firstName, c.cnp" +
                " FROM customer c, vip_reader vr WHERE c.id = vr.id";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Last Name: " + resultSet.getString(2));
                System.out.println("First Name: " + resultSet.getString(3));
                System.out.println("CNP: " + resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllStudents()    {
        String selectSql = "SELECT c.id, c.lastName, c.firstName, c.cnp, sr.universityName, sr.yearOfStudy" +
                " FROM customer c, student_reader sr WHERE c.id = sr.id";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("Last Name: " + resultSet.getString(2));
                System.out.println("First Name: " + resultSet.getString(3));
                System.out.println("CNP: " + resultSet.getString(4));
                System.out.println("University name: " + resultSet.getString(5));
                System.out.println("Year: " + resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllBorrowInfos()    {
        String selectSql = "SELECT * FROM borrow_info";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                System.out.println("Customer iD: " + resultSet.getInt(1));
                System.out.println("Book ID: " + resultSet.getInt(2));
                System.out.println("Borrow Date: " + resultSet.getDate(3));
                System.out.println("Return Date: " + resultSet.getDate(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
