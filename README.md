# Tokoro (Place)

This is a very simple example to get you started using PostGIS with Dropwizard, an excellent way to create Java-based web services. 
In this case, a spatial service -- very handy for using with OpenLayers or other services. This is by no means a complicated example 
application, more just a bootstrap example for getting started. 

Setup should be pretty simple:

1. Create a database with PostGIS extensions
2. Create a new schema named 'tokoro' (it's a good idea to use schemas with PostGIS to make data migration easier in the future)
3. Edit src/main/resources/tokoro.yml with your PostgreSQL settings (username, host, password, db, etc.)
4. Run the migration via _db migrate src/main/resources/tokoro.yml_
5. Start the server via _server src/main/resources/tokoro.yml_

