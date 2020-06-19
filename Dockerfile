FROM 		tomcat:9.0-jdk11-corretto

MAINTAINER 	Jeff Heckel (jheckel@uillinois.edu)

COPY 		target/maven-webapp.war /usr/local/tomcat/webapps/

