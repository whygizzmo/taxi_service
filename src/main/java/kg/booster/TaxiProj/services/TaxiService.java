package kg.booster.TaxiProj.services;

import kg.booster.TaxiProj.models.enities.Taxi;

import java.util.List;

public interface TaxiService {
    public List<Taxi> saveTaxi(Taxi taxi,List<Taxi> taxis);

    Taxi saveTaxi(Taxi taxi);

    public Taxi findById(long taxiId, List<Taxi> taxis);
    public List<Taxi>getAllTaxi(List<Taxi>taxis);
    public String calculate(long id, double distance,List<Taxi>taxis);
}
