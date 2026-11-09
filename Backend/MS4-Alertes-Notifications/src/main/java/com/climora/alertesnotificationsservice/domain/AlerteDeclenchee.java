package com.climora.alertesnotificationsservice.domain;
import com.climora.alertesnotificationsservice.domain.enums.OrigineAlerte;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;
@Document(collection = "alertes_declenchees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlerteDeclenchee {
    @Id
    private String id;
    private OrigineAlerte origine;
    private String message;
    private LocalDateTime dateDeclenchement;
    @DBRef(lazy = true)
    @JsonBackReference("regle-alertes")
    private RegleAlerte regle;
    @DBRef(lazy = true)
    @JsonManagedReference("alerte-notifications")
    private List<Notification> notifications;
}