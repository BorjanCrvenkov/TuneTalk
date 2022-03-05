package finki.bazi.tunetalk.model.metamodel;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import finki.bazi.tunetalk.model.Song;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Song.class)
public abstract class Song_ {
    public static volatile SingularAttribute<Song, Integer> songId;
    public static volatile SingularAttribute<Song, String> title;
    public static volatile SingularAttribute<Song, LocalDate> dateReleased;
    public static volatile SingularAttribute<Song, Float> rating;
    public static volatile SingularAttribute<Song, String> lyrics;
    public static volatile SingularAttribute<Song, Boolean> verified;

    public static final String ID = "songId";
    public static final String TITLE = "title";
    public static final String DATERELEASED = "dateReleased";
    public static final String RATING = "rating";
    public static final String LYRICS = "lyrics";
    public static final String VERIFIED = "verified";

}
