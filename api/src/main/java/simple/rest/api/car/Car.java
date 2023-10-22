package simple.rest.api.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;

@Table(name = "car")
@Entity(name = "Car")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Car {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Getter
    @JsonIgnore
    @Column(name = "updated_at")
    private Long updatedAt;


    public Car(String data) {
        this.type = data;
        this.updatedAt = System.currentTimeMillis();
    }
}