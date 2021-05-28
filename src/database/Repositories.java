package database;

import categories.Category;
import distributors.Distributor;
import products.Vegetable;
import stores.Store;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Repositories {
    private ArrayList<Category> categories = findAll();
    private ArrayList<Distributor> distributors = findAllDistributors();

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
            throw new RuntimeException("Something went wrong while deleting: " + exception + categoryName);
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

    public boolean updateCategory(Category category)
    {
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

    public Distributor insertDistributor(Distributor distributor)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into distributors(name,location) VALUES(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, distributor.getNume());
            preparedStatement.setString(2, distributor.getLocation());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                distributor.setID(resultSet.getInt(1));
            }
            resultSet.close();
            return distributor;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the distributor: " + distributor + exception);
        }
    }

    public String deleteDistributor(String distributorName)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE from distributors WHERE name = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, distributorName);

            preparedStatement.execute();
            return distributorName;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting: " + exception + distributorName);
        }
    }

    public ArrayList<Distributor> findAllDistributors()
    {
        ArrayList<Distributor> distributors = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM distributors";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Distributor dist = new Distributor(resultSet.getString("name"),resultSet.getString("location"));
                dist.setID(resultSet.getInt("id"));
                distributors.add(dist);
            }

            resultSet.close();
            return distributors;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all distributors. "+exception);
        }
    }

    public boolean updateDistributor(Distributor distributor)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "{?= call update_distributor(?,?,?)}";

            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(2, distributor.getID());
            callableStatement.setString(3, distributor.getNume());
            callableStatement.setString(4,distributor.getLocation());
            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.executeUpdate();
            int response = callableStatement.getByte(1);

            return response == 1;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to updated the distributor with id: " + distributor+exception);
        }
    }

    public Store insertStore(Store store)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into stores(name,location) VALUES(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, store.getName());
            preparedStatement.setString(2, store.getLocation());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                store.setID(resultSet.getInt(1));
            }
            resultSet.close();
            return store;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the store: " + store + exception);
        }
    }

    public String deleteStore(String storeName)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "DELETE from stores WHERE name = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, storeName);

            preparedStatement.execute();
            return storeName;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting: " + exception + storeName);
        }
    }

    public ArrayList<Store> findAllStores()
    {
        ArrayList<Store> stores = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM stores";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Store store = new Store(resultSet.getString("location"),resultSet.getString("name"));
                store.setID(resultSet.getInt("id"));
                stores.add(store);
            }

            resultSet.close();
            return stores;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all stores. "+exception);
        }
    }

    public boolean updateStore(Store store)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "{?= call update_store(?,?,?)}";

            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(2, store.getID());
            callableStatement.setString(3, store.getName());
            callableStatement.setString(4,store.getLocation());
            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.executeUpdate();
            int response = callableStatement.getByte(1);

            return response == 1;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to updated the store with id: " + store+exception);
        }
    }

    public Vegetable insertVegetable(Vegetable vegetable)
    {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into vegetables(name,price,categoryID,distributorID,quantity,type,expire,origin) VALUES(?,?,?,?,?,?,?,?)";

//            String expireData = vegetable.getExpire().toString();
//            System.out.println(expireData);

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vegetable.getDenumire());
            preparedStatement.setDouble(2, vegetable.getPrice());
            preparedStatement.setInt(3, vegetable.getCategory().getID());
            preparedStatement.setInt(4, vegetable.getDistributor().getID());
            preparedStatement.setInt(5, vegetable.getQuantity());
            preparedStatement.setString(6, vegetable.getType());
            preparedStatement.setString(7, vegetable.getExpire().toString());
            preparedStatement.setString(8, vegetable.getOrigin());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                vegetable.setID(resultSet.getInt(1));
            }
            resultSet.close();
            return vegetable;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the vegetable: " + vegetable + exception);
        }
    }

    public ArrayList<Vegetable> findAllVegetables()
    {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM vegetables";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Category localCategory = null;
                int categoryID = resultSet.getInt("categoryID");
//                System.out.println(categoryID);
//                System.out.println(categories.toString());
                for(var x:categories)
                {
                    if(x.getID() == categoryID)
                    {
                        localCategory = x;
                        break;
                    }
                }

                Distributor localDistributor = null;
                int distributorID = resultSet.getInt("distributorID");
                for(var x:distributors)
                {
                    if(x.getID() == distributorID)
                    {
                        localDistributor = x;
                        break;
                    }
                }

//                System.out.println(resultSet.getString("expire"));
                String data = resultSet.getString("expire");
                int day, month, year, hr, min;
                day = Integer.parseInt(data.substring(0,4));
                month = Integer.parseInt(data.substring(5, 7));
                year = Integer.parseInt(data.substring(8, 10));
                hr = 0;
                min = 0;
                LocalDateTime localExpire;
                localExpire = LocalDateTime.of(day, month, year, hr, min);
//                System.out.println(localExpire);

                Vegetable vegetable = new Vegetable(resultSet.getString("name"), resultSet.getDouble("price"), localCategory, localDistributor,
                        resultSet.getInt("quantity"), resultSet.getString("type"), localExpire, resultSet.getString("origin"));
                vegetable.setID(resultSet.getInt("id"));
                vegetables.add(vegetable);
            }

            resultSet.close();
            return vegetables;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all vegetables. "+exception);
        }
    }
}
