package kg.booster.TaxiProj.controllers;

import kg.booster.TaxiProj.models.enities.Taxi;
import kg.booster.TaxiProj.services.TaxiService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/taxi")
public class TaxiController {
   private final TaxiService taxiService;
    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    List<Taxi>taxiList = new ArrayList<>();

    @PostMapping("/save")
    public Taxi saveTaxi(@RequestBody Taxi taxi){
        return taxiService.saveTaxi(taxi);
    }

    @GetMapping("/getTaxi{id}")
    public Taxi findById(@PathVariable long id){
        return taxiService.findById(id,taxiList);
    }


    @GetMapping("/getAll")
    public List<Taxi> getAllTaxi(){

        return taxiService.getAllTaxi(taxiList);
    }

    @GetMapping("/calc")
    public String calculate(@RequestParam long id, @RequestParam double distance){
        return  taxiService.calculate(id,distance,taxiList);

    }
}
