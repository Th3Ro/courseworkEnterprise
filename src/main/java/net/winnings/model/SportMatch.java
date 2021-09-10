package net.winnings.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * SportMatch entity class from database
 * @author Nosolenko
 * @version 1.0
 */
@Entity
public class SportMatch {

    /** Match id field, which is the primary key with automatic generation of values */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** Match name field */
    @Column(nullable = false)
    private String name;
    /** Match start date field */
    @Column(nullable = false)
    private Date dateOfMatch;
    /** Odds field for the first team */
    @Column(nullable = false)
    private float firstCoefficient;
    /** Odds field for the second team */
    @Column(nullable = false)
    private float secondCoefficient;
    /** End of the match field */
    @Column(nullable = false)
    private boolean isEnd;

    /**
     * Constructor - creating a new user
     */
    public SportMatch() {
    }

    /**
     * Constructor - creating a new object with specific values
     * @param name - match name
     * @param dateOfMatch - match start date
     * @param firstCoefficient - odds for the first team
     * @param secondCoefficient - odds for the second team
     */
    public SportMatch(String name, Date dateOfMatch, float firstCoefficient, float secondCoefficient) {
        this.name = name;
        this.dateOfMatch = dateOfMatch;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.isEnd = false;
    }

    /**
     * Field value retrieval function {@link SportMatch#id}
     * @return returns the match id
     */
    public Long getId() {
        return id;
    }

    /**
     * Match id determination procedure {@link SportMatch#id}
     * @param id - match id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Field value retrieval function {@link SportMatch#name}
     * @return returns the name of the match
     */
    public String getName() {
        return name;
    }

    /**
     * Match Name Determination Procedure {@link SportMatch#name}
     * @param name - match name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Field value retrieval function {@link SportMatch#dateOfMatch}
     * @return returns the start date of the match
     */
    public Date getDateOfMatch() {
        return dateOfMatch;
    }

    /**
     * The procedure for determining the date of the start of the match {@link SportMatch#dateOfMatch}
     * @param dateOfMatch - match start date
     */
    public void setDateOfMatch(Date dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    /**
     * Field value retrieval function {@link SportMatch#firstCoefficient}
     * @return returns the odds of the first team
     */
    public float getFirstCoefficient() {
        return firstCoefficient;
    }

    /**
     * The procedure for determining the coefficient of the first team {@link SportMatch#firstCoefficient}
     * @param firstCoefficient - first team odds
     */
    public void setFirstCoefficient(float firstCoefficient) {
        this.firstCoefficient = firstCoefficient;
    }

    /**
     * Field value retrieval function {@link SportMatch#secondCoefficient}
     * @return returns the coefficient of the second team
     */
    public float getSecondCoefficient() {
        return secondCoefficient;
    }

    /**
     * The procedure for determining the coefficient of the second team {@link SportMatch#secondCoefficient}
     * @param secondCoefficient - second team odds
     */
    public void setSecondCoefficient(float secondCoefficient) {
        this.secondCoefficient = secondCoefficient;
    }

    /**
     * Field value retrieval function {@link SportMatch#isEnd}
     * @return returns an indicator of the end of the match
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * The procedure for determining the coefficient of the second team {@link SportMatch#isEnd}
     * @param end - match end indicator
     */
    public void setEnd(boolean end) {
        isEnd = end;
    }
}
