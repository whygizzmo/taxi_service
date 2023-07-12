package kg.booster.TaxiProj.models.enities;

import kg.booster.TaxiProj.models.enums.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Taxi {
    long id;
    String manufacturer;
    String model;
    String color;
    double maxSpeed;
    double pricePerKmh;
    Category category;
}
