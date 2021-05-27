package database;

import categories.Category;

import java.sql.*;
import java.util.ArrayList;

public class Repositories {
    public Category insertCategory(Category category)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into categories(name) VALUES(?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getNume());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                category.setID(resultSet.getInt(1));
            }
            resultSet.close();
            return category;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the category: " + category + exception);
        }
    }

    public String deleteCategory(String categoryName)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE from categories WHERE name = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, categoryName);

            preparedStatement.execute();
            return categoryName;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while inserting: " + exception + categoryName);
        }
    }

    public ArrayList<Category> findAll()
    {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM categories";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Category cat = new Category(resultSet.getString("name"));
                cat.setID(resultSet.getInt("id"));
                categories.add(cat);
            }

            resultSet.close();
            return categories;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all categories. "+exception);
        }
    }

    public boolean updateCategory(Category category) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "{?= call update_category(?,?)}";

            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(2, category.getID());
            callableStatement.setString(3, category.getNume());
            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.executeUpdate();
            int response = callableStatement.getByte(1);

            return response == 1;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to updated the category with id: " + category+exception);
        }
    }
}
