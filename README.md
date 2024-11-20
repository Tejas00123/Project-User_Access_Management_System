Project Setup
==============

1)create maven web project  using maven-archetype-webapp
2)add jakarta.servelt api jar dependency in pom.xml
3)update version 8 to 17 and update projcet
4)bring src/main/java, src/test/java folders to project structure
  right click=>build path=>config build path=>order and export tab=>select library,and maven depe two checboxes apply close
5)configure tomcat server 10.1
6)add postgres jdbc driver into pom.xml file 
           
            <dependency>
                <groupId>org.postgresql</groupId>
                <artifactId>postgresql</artifactId>
                <version>42.7.4</version>
            </dependency>
        
application development
=========================

src/main/java => for the source code
src/test/java => for unit test code
src/main/webapp=>public area code(html,js,css,img,etc..files)



tables
=======

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT CHECK (role IN ('Employee', 'Manager', 'Admin')) NOT NULL
);


CREATE TABLE software (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    access_levels TEXT NOT NULL
);

CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    software_id INT NOT NULL,
    access_type TEXT NOT NULL,
    reason TEXT NOT NULL,
    status TEXT DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (software_id) REFERENCES software(id)
);
