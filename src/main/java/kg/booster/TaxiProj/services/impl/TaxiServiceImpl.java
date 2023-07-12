package kg.booster.TaxiProj.services.impl;

import kg.booster.TaxiProj.models.enities.Taxi;
import kg.booster.TaxiProj.services.TaxiService;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TaxiServiceImpl implements TaxiService {
    String URL ="jdbc:postgresql://localhost:5433/taxi_service_db";
    String USERNAME ="postgres";
    String PASSWORD ="postgres";

    @Override
    public List<Taxi> saveTaxi(Taxi taxi, List<Taxi> taxis) {
        taxi.setId(taxis.size() + 1);
        taxis.add(taxi);

        return taxis;
    }
    @Override
    public Taxi saveTaxi(Taxi taxi){
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String  sqlQuery = "INSERT INTO taxi (manufacturer,model,color,max_speed,price_per_kmh,category)" +
                    "VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,taxi.getManufacturer());
            preparedStatement.setString(2,taxi.getModel());
            preparedStatement.setString(3,taxi.getColor());
            preparedStatement.setDouble(4,taxi.getMaxSpeed());
            preparedStatement.setDouble(5,taxi.getPricePerKmh());
            preparedStatement.setString(6, String.valueOf(taxi.getCategory()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            long id = 0 ;
            if (resultSet.next()){
                id = resultSet.getLong(1);
            }



            taxi.setId(id);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taxi;

    }

    @Override
    public Taxi findById(long taxiId, List<Taxi> taxis) {

        Taxi taxi = new Taxi();
        for (int i = 0; i < taxis.size(); i++) {
            if (taxis.get(i).getId() == taxiId) {
                taxi = taxis.get(i);
            }
        }

        return taxi;
    }

    @Override
    public List<Taxi> getAllTaxi(List<Taxi> taxis) {
        List<Taxi> sortedTaxi = new ArrayList<>();
        sortedTaxi = taxis.stream().sorted(Comparator
                .comparing(Taxi::getManufacturer)).toList();
        return sortedTaxi;
    }

    @Override
    public String calculate(long id, double distance, List<Taxi> taxis) {
        Taxi taxi = findById(id, taxis);

        double price = taxi.getPricePerKmh() * distance;

        return "total price = " + price +" c";
    }
}
