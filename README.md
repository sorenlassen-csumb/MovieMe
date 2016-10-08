# MovieMe

You will need Java 8 to run the Neo4j instance.

1. Download Neo4j with command: wget http://dist.neo4j.org/neo4j-community-3.0.6-unix.tar.gz
2. Unzip and untar with command: tar -zxvf neo4j-community-3.0.6-unix.tar.gz
3. Move the extracted folder neo4j-community-3.0.6 to your desired location.
4. Run Neo4j server with command path/to/neo4j-community-3.0.6/bin/neo4j start
5. Access Neo4j Web UI tool by going to address: http://localhost:7474/browser/
6. Sign in with default username: neo4j and default password: neo4j
7. When prompted to change password, change to MovieMe
8. Run python script found in directory server/pyscripts/insert-db.py
9. To see nodes, run query "MATCH (n) RETURN n" in the Neo4j Web UI tool

Run Spring Boot application with command 'mvn spring-boot:run'

Once the Spring Boot application is running, it will listen for requests on port 8080.
Test the rest endpoint by typing http://localhost:8080/rest on your browser or use a rest client such as [Postman] (https://www.getpostman.com).

Close running Spring Boot application with ctrl+c
