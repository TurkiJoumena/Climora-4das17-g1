package com.climora.alertesnotificationsservice.domain;
import com.climora.alertesnotificationsservice.domain.enums.CanalNotification;
import com.climora.alertesnotificationsservice.domain.enums.StatutNotification;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;
    private String userId;
    private CanalNotification canal;
    private StatutNotification statut;
    private LocalDateTime dateEnvoi;
    @DBRef(lazy = true)
    @JsonBackReference("alerte-notifications")
    private AlerteDeclenchee alerte;
}