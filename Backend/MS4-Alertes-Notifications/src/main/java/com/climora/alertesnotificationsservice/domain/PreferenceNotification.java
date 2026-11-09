package com.climora.alertesnotificationsservice.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "preferences_notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceNotification {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userId;
    private String email;
    private Boolean emailActif;
}