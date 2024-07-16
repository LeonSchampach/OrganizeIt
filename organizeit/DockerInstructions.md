# Spring Boot to Docker Image Instructions

## 1. Build the Application Jar

Build your Spring Boot application to create the jar file. If you're using Maven, you can do this with:

```bash
./mvnw clean package
```

## 2. Create a Dockerfile

In the root directory of your Spring Boot project, create a file named `Dockerfile` with the following content:

```
# Use an official OpenJDK runtime as a parent image 
FROM openjdk:17-jdk-alpine 

# Set the working directory inside the container 
WORKDIR /app 

# Copy the packaged jar file into the container at /app 
ARG JAR_FILE=target/organizeit-0.0.1-SNAPSHOT.jar 
COPY ${JAR_FILE} app.jar 

# Make port 8080 available to the world outside this container 
EXPOSE 8080 

# Set a Label for your name (Optional) 
LABEL authors="John Doe" 

# Run the jar file 
ENTRYPOINT ["java", "-jar", "app.jar"]
```

- `organizeit-0.0.1-SNAPSHOT.jar`: The `.jar` file that was created in Step 1

## 3. Build the Docker Image

With the Dockerfile in place and your application jar built, you can now build the Docker image
using the Docker CLI. Run the following command in the root directory of your project (where the
Dockerfile is located):

```bash
docker build -t retzino/organizeit:latest .
```

- `retzino`: Docker Hub Username
- `organizeit`: Application name in lowercase letters

Yes, the dot at the end is important. It specifies the build context, which means Docker will look in the current directory for the Dockerfile and any other files it needs to include in the image.

## 4. Run the Docker Container

#### a. Use Docker Desktop to run a Container (Don't forget to assign a port).
#### b. Or run a Container using the following command:

```bash
docker run -p 8080:8080 retzino/organizeit:latest
```

- `retzino`: Docker Hub Username
- `organizeit`: Application name in lowercase letters

## 5. Push the Docker Image to Docker Hub (Optional)

If you want to push your Docker Image to Docker Hub, first log in to your Docker Hub account:

```bash
docker login
```

Then push the image:

```bash
docker push retzino/organizeit:latest
```

- `retzino`: Docker Hub Username
- `organizeit`: Application name in lowercase letters

# Additional Tip

Use a `.dockerignore` file to exclude files and directories that are not needed in the Docker
image, similar to `.gitignore`.

```
*.md
*.iml
.git
```