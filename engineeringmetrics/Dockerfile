FROM gitpod/workspace-full

# Install Java 17 using SDKMAN
RUN sdk install java 17.0.7-tem && sdk use java 17.0.7-tem

# Set JAVA_HOME for Maven to work with Java 17
ENV JAVA_HOME="/home/gitpod/.sdkman/candidates/java/current"
