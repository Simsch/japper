# Japper
Japper is a JDBC mapper which can handle a JDBC `ResultSet` and convert* it to a given class (Entity).
The mapper does not have any other dependency.

## Getting started
Japper is partly using `javax.persistence` Annotations in order to do the mapping. It is **not** implementing the jpa specifications.

 * Add the dependency of japper to your project (TODO)
 * Add javax.persistence dependency

The code usage is like the following:
```
ResultSet resultSet = ...;
Person person = Mapper.into(Person.class).map(resultSet);
```

```
@NoArgsConstructor
public class PersonEntity {
  @Transient
  private String notDatabaseRelevant;
  @Column(name = "last_name")
  private String sureName;
  @Convert(converter = ObjectToStringConverter.class)
  private Object actualAString;
}

public class ObjectToStringConverter implements AttributeConverter<Object, String> { ... }
```
Be aware that the entity needs a default constructor in order to be instantiated. This means either lombok\`s `@NoArgsConstructor` or a default constructor by hand.

### Spring JDBC Template
```
jdbcTemplate.query("select * from person", (rs, rowNum) -> Mapper.into(Person.class).map(rs))
```

### Naming Conventions
The naming convention is jpa like, means that camel case variables inside the entity are getting converted to database columns with underscore:
```
 +-----------+-----------+
 | Entity    | Database  |
 +-----------+-----------+
 | sureName  | sure_name |
 +-----------+-----------+
 | SureName  | sure_name |
 +-----------+-----------+
 | SURE_NAME | sure_name |
 +-----------+-----------+
 | SURENAME  | surename  |
 +-----------+-----------+
 
```
Keep in mind that the `ResultSet` [specification](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html) states that it is case insensitive, so it
does not matter if the column name is upper or lower case.

### Supported Annotations
 * `javax.persistence.Transient`: If the variable should not be taken into account at all
 * `javax.persistence.Column`: Used to specify a different name as the database column (if the name attribute is empty the annotation is ignored)
 * `javax.persistence.Convert`: Converter if the datatype should be changed from the database into the entity class. A converter has to have the converter attribute present.

### Java 8 DateTime Api
The mapper supports the DateTime Api for
 * `LocalDateTime`
 * `LocalDate`
 
If there is the need of others which are not implemented yet, feel free to use a converter (shown above).
 
## Built With
* Java
* Gradle

## Authors
* **Simon Schober** - *Initial Work*

## License
This project is licensed under the MIT License - see the [LICENSE.MD](LICENSE.MD) file for details
