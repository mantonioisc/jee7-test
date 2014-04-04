#My JEE7 sample app

* Requires Java 7 and JEE 7, it works for Glassfish 4 for now. In the future I would add more containers as more servers support JEE7

* Needs PostgreSQL 9.3.4 to work

* Notice just javaee-api is needed to work, in contrast with JEE 6 which needed a lot more of dependencies to work (also needed different implementations to work, this is a well received improvement)


##Instructions

###Create the following resource
 Using Glassfish **asadmin**

    asadmin create-custom-resource --restype java.util.Properties --factoryclass org.glassfish.resources.custom.factory.PropertiesFactory --property "sample.property"="This property comes from glassfish-resources.xml file":"string.property"="The quick brown fox jumps over the lazy dog.":"int.property"="1111":"double.property"="11111.11":"true.boolean.property"="true":"false.boolean.property"="false":"defaultToFieldName"="no was given in the annotation"  project.properties


###Configure PostgreSQL driver
1. Download the driver from [here](http://jdbc.postgresql.org/download.html)
2. Copy **postgresql-9.3-1101.jdbc41.jar** to:

        $GLASSFISH_HOME%\Glassfish\domains\domain1\lib\

###Create JDBC datasource in Glassfish
Run these two **asadmin** commands:

    asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property "serverName=localhost:portNumber=5432:databaseName=games:user=postgres:password=admin" Games
    asadmin create-jdbc-resource --connectionpoolid Games jdbc/Games

Change database user and password if needed

###Run DDL statements in pgAdmin
1. Run the contents of **postgresql-ddl.sql** to create the database structure.
2. Run the contents of **postgresql-insert.sql** to create some sample data.

###To use Glassfish plugin
Add passwordFile or adminPassword to user **settings.xml**.
The passwordFile of a Glassfish installation is usually at:

    $GLASSFISH_HOME%\Glassfish\domains\domain1\config\keyfile

##Features

###Shows hot to use @Producer's and CDI injection in a more effective way.
To produce simple values that come from a java.util.Properties that exists in JNDI. Also shows how to inject a Logger instances instead of traditional Logger declaration.

###Shows how to process contract first web services with JAX-WS maven plugin.

###Shows how to use arquillian to write tests using Glassfish 4.
It requires **arquillian.xml** to reference the **glassfish-resources.xml** file, in this file we declare the resources needed they must be the same for the full Glassfish instance. We need to declare **maven-failsafe-plugin** plugin to have integrations tests run, maven does not run IT by default.

###Shows how to use glassfish-maven-plugin.
Notice Glassfish 4 has some minor problems with the current plugin version which is for version 3.

###It also has a clean example of how to write a unit test using junit 4.
