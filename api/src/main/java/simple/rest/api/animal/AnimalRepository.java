package simple.rest.api.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.rest.api.car.Car;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
