# Utilisation d'une image légère Eclipse Temurin pour Java 21
FROM eclipse-temurin:21-jre-alpine

# Création d'un utilisateur non-root pour la sécurité
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copie du JAR généré par Maven
COPY target/*.jar app.jar

# Profil de production par défaut
ENV SPRING_PROFILES_ACTIVE=prod

# Optimisation de la mémoire pour les conteneurs
ENV JAVA_OPTS="-XX:+UseParallelGC -Xms256m -Xmx512m"

EXPOSE 9095

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]