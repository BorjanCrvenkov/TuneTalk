package finki.bazi.tunetalk.model.metamodel;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Album.class)
public abstract class Album_ {
    public static volatile SingularAttribute<Artist, Integer> albumId;
    public static volatile SingularAttribute<Artist, String> albumName;
    public static volatile SingularAttribute<Artist, LocalDate> dateReleased;
    public static volatile SingularAttribute<Artist, Float> rating;
    public static volatile SingularAttribute<Artist, Boolean> verified;

    public static final String ID = "albumId";
    public static final String ALBUMNAME = "albumName";
    public static final String DATERELEASED = "dateReleased";
    public static final String RATING = "rating";
    public static final String VERIFIED = "verified";

}
