package finki.bazi.tunetalk.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import finki.bazi.tunetalk.model.Artist;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Artist.class)
public abstract class Artist_ {
    public static volatile SingularAttribute<Artist, Integer> artistId;
    public static volatile SingularAttribute<Artist, String> artistName;
    public static volatile SingularAttribute<Artist, Integer> age;
    public static volatile SingularAttribute<Artist, String> realName;
    public static volatile SingularAttribute<Artist, String> description;
    public static volatile SingularAttribute<Artist, Boolean> verified;

    public static final String ID = "artistId";
    public static final String ARTISTNAME = "artistName";
    public static final String AGE = "age";
    public static final String REALNAME = "realname";
    public static final String DESCRIPTION = "description";
    public static final String VERIFIED = "verified";

}
