package com.climora.previsions.domain.entity;

import com.climora.previsions.domain.enums.ConditionPrevue;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prevision_jours")
public class PrevisionJour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_prevision", nullable = false)
    private LocalDate date;

    private Double tempMin;
    private Double tempMax;
    private Integer probabilitePluie;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_meteo")
    private ConditionPrevue condition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prevision_id")
    private Prevision prevision;

    protected PrevisionJour() {
    }

    public PrevisionJour(LocalDate date, Double tempMin, Double tempMax,
                         Integer probabilitePluie, ConditionPrevue condition) {
        this.date = date;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.probabilitePluie = probabilitePluie;
        this.condition = condition;
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Double getTempMin() { return tempMin; }
    public void setTempMin(Double tempMin) { this.tempMin = tempMin; }
    public Double getTempMax() { return tempMax; }
    public void setTempMax(Double tempMax) { this.tempMax = tempMax; }
    public Integer getProbabilitePluie() { return probabilitePluie; }
    public void setProbabilitePluie(Integer probabilitePluie) { this.probabilitePluie = probabilitePluie; }
    public ConditionPrevue getCondition() { return condition; }
    public void setCondition(ConditionPrevue condition) { this.condition = condition; }
    public Prevision getPrevision() { return prevision; }
    void setPrevision(Prevision prevision) { this.prevision = prevision; }
}
