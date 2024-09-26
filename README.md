# Read me

The original idea is from  
https://medium.com/jpa-java-persistence-api-guide/hibernate-optimization-with-batchsize-and-batch-size-configuration-579bf759fc05

f hibernate.jdbc.batch_size is set to 20, Hibernate can optimize database interactions by sending batches of up to 20 INSERT statements together.