package com.climora.alertesnotificationsservice.domain;
import com.climora.alertesnotificationsservice.domain.enums.TypeMesure;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "regles_alerte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegleAlerte {
    @Id
    private String id;
    private String userId;
    private Long villeId;
    private TypeMesure typeMesure;
    private Double seuil;
    private Boolean active;
    @DBRef(lazy = true)
    @JsonManagedReference("regle-alertes")
    private List<AlerteDeclenchee> alertes;
}