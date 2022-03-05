package finki.bazi.tunetalk.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import finki.bazi.tunetalk.model.Genre;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Genre.class)
public abstract class Genre_ {
    public static volatile SingularAttribute<Genre, Integer> genreId;
    public static volatile SingularAttribute<Genre, String> genreName;

    public static final String ID = "genreId";
    public static final String GENRENAME = "genreName";
}
