# Read me

The original idea is from  
https://medium.com/jpa-java-persistence-api-guide/hibernate-optimization-with-batchsize-and-batch-size-configuration-579bf759fc05

# Id Annotation

See https://vladmihalcea.com/postgresql-serial-column-hibernate-identity/

Do not use

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

You should prefer using the SEQUENCE generator over IDENTITY no matter if you use PostgreSQL, Oracle, or SQL Server.

So use

```
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
@SequenceGenerator(name = "seqGen", sequenceName = "author_id_seq", allocationSize = 1)
private Long id;
```

If hibernate.jdbc.batch_size is set to 20, Hibernate can optimize database interactions by sending batches of up to 20
INSERT statements together.