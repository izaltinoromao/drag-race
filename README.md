
<h1 align="center">
  Drag Race Application
  <br>
</h1>

<h4 align="center">Application to register data from a drag race using Java SpringBoot.</h4>

<p align="center">
  
</p>

<p align="center">
  <a href="#key-features">End-Points</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#download">Download</a> •
  <a href="#credits">Credits</a> •
  <a href="#related">Related</a> •
  <a href="#license">License</a>
</p>

## End-Points
* More details at swagger documentation
* Register a new Drag
    - POST: /drag-race/newdrag
* List all drags
    - GET: /drag-race/listdrags
* List drag by driver
    - GET: /drag-race/drag
* Set the Winners
    - POST: /drag-race/setwinners
* List all the time winners
    - GET: /drag-race/timewinners
* List all the speed winners
    - GET: /drag-race/speedwinners
* Reset the race
    - DELETE: /drag-race/resetrace


## How To Use

To clone and run this application, you'll need [Git](https://git-scm.com), [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Maven](https://maven.apache.org/download.cgi) and [Docker](https://docs.docker.com/desktop/install/windows-install/) installed on your computer. From your command line:

```bash
# Clone this repository
$ git clone https://github.com/izaltinoromao/drag-race.git

# Go into the repository
$ cd drag-race

# Install dependencies
$ mvn install

# Generate the package
$ mvn package -DskipTests

# Build a docker image from the docker file
$ docker build -t <image name>:<version> .

# Run the docker-compose file
$ docker-compose up
```

## Credits

This software uses the following open source packages:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [SpringBoot](https://spring.io/)
- [JUnit](https://junit.org/junit5/)
- [Swagger.io](https://swagger.io/)



