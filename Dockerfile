FROM tomcat:11.0.1-jdk21-temurin-noble

# Copier le fichier .war dans le répertoire webapps de Tomcat
COPY gradle/wrapper/gradle-wrapper.jar app.jar

# Exposer le port par défaut de Tomcat
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]