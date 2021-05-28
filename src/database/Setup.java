package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {

    public void setup(){
        createCategoriesTable();
        createDistributorsTable();
        createStoresTable();
        createVegetablesTable();
        createVegetableStoreTable();
        createUpdateCategory();
        createUpdateDistributor();
        createUpdateStore();
    }

    private void createCategoriesTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS categories (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(255)\n" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDistributorsTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS distributors (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(255),\n" +
                "    location VARCHAR(255)\n" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createStoresTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS stores (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(255),\n" +
                "    location VARCHAR(255)\n" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createVegetablesTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS vegetables (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    name VARCHAR(255),\n" +
                "    price DOUBLE,\n" +
                "    categoryID INT,\n" +
                "    distributorID INT,\n" +
                "    quantity INT,\n" +
                "    type VARCHAR(255),\n" +
                "    expire VARCHAR(255),\n" +
                "    origin VARCHAR(255)\n" +
                ")";

//        " FOREIGN KEY (categoryID) REFERENCES categories(id) ON DELETE CASCADE,\n"+
//        " FOREIGN KEY (distributorID) REFERENCES distributors(id) ON DELETE CASCADE\n"+

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createVegetableStoreTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS product_store (\n" +
                "    store_id INT,\n" +
                "    product_id INT,\n" +
                " FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE,\n"+
                " FOREIGN KEY (product_id) REFERENCES vegetables(id) ON DELETE CASCADE\n"+
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUpdateCategory()
    {

        String remove = "drop function if exists update_category\n";
        String query = "CREATE FUNCTION update_category(req_id int, name varchar(255)) RETURNS int(11)\n" +
                "DETERMINISTIC\n" +
                "BEGIN\n" +
                "update categories\n" +
                "set name = name\n" +
                "where id = req_id;\n" +
                "RETURN row_count();\n" +
                "END";
            try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(remove);
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
                }
    }

    private void createUpdateDistributor()
    {

        String remove = "drop function if exists update_distributor\n";
        String query = "CREATE FUNCTION update_distributor(req_id int, name varchar(255), location varchar(255)) RETURNS int(11)\n" +
                "DETERMINISTIC\n" +
                "BEGIN\n" +
                "update distributors\n" +
                "set name = name,\n" +
                "location = location\n" +
                "where id = req_id;\n" +
                "RETURN row_count();\n" +
                "END";
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(remove);
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUpdateStore()
    {

        String remove = "drop function if exists update_store\n";
        String query = "CREATE FUNCTION update_store(req_id int, name varchar(255), location varchar(255)) RETURNS int(11)\n" +
                "DETERMINISTIC\n" +
                "BEGIN\n" +
                "update stores\n" +
                "set name = name,\n" +
                "location = location\n" +
                "where id = req_id;\n" +
                "RETURN row_count();\n" +
                "END";
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(remove);
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
