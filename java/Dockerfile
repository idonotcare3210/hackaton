FROM openjdk
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac test.java
CMD ["java", "test"]
EXPOSE 80