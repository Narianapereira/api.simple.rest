package simple.rest.api.car;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.rest.api.car.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
