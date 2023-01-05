![jetbulb](https://yt3.ggpht.com/DF4O-d8c6YeimxONGzKUSm2-1SlxrXQfThh-B-wSBRJIVj4MfZ3X4IB01mif8LB1mtKDnnB2pg=s88-c-k-c0x00ffffff-no-rj)
#### Jet Bulb
# GoIt java dev 6 final project
## My notebook


## **Foreword**

As with any team project, a feature of this project is teamwork. In addition to solidifying our skills in the following stack of Java Core technologies, SQL, Spring (MVC, Data, Security, Boot), Thyme Leaf, Gradle, Git, AWS, we also mastered and solidified a team development style during this project. So you'll be able to see many branches and pool-requests in the code.
The desire to create a convenient application to create, store and share your thoughts and ideas prompted us to implement this project.
It will be very useful for Internet users with an active life position and diverse interests: work, hobbies, interests, communication, rest, travel and so on.
In order not to forget interesting thoughts and not to miss important moments in each of these areas, this service will be an indispensable helper.

## **Description**

This project implements a web service for storing text notes with the ability to send and access these notes via a link.

This service assumes 2 types of users: normal user and administrator.
Ordinary users are provided with the following capabilities:
- create, edit, delete, search, sort notes and send a link of their own public notes and view other users' public notes by link.

The administrator, in addition to these features, is provided with the following capabilities:
- edit and delete notes created by any users;
- get a list of all notes, search by name.

## **How to use the project**

1. Authorization and registration.

When you enter the project link (regardless of the endpoint), you will be redirected to the login page.
If this is the first time you are accessing the site, you will be prompted to register. In the case of successful registration, your account will be automatically saved in the database with the role ROLE_USER, which implies limited capabilities (see above).
Use the following credentials to log in to the project as an administrator:
- E-mail - admin@admin.com
- Password - super_secret_password.


2. Page with a list of all notes

After successful registration, you will be automatically redirected to the login page. When you enter valid credentials, you will be redirected to a page with all your notes. A list of all your notes will appear on this page. The line for each note contains the following information:
- note title,
- the content of the note,
- the note's access type,
- possible actions with the note (edit, delete, share link).
  For each field the possibility of sorting in descending order is implemented.
  You can also search for the necessary note by its name.
  Additional buttons on this page - create note and leave account.

3. Create/edit note page.

On this page you can enter/edit the name of the note and its content.
The content can be a plain text, a hyperlink, a picture. Or a mixture of all of these.
Use the radio button to select the type of access to this note.
Clicking the "Save" button puts all the data which were entered when you change/create the note into the database.

## **How to deploy a project on the local machine**

Software requirements.
- Java17 or higher. Earlier versions of Java may not work correctly.
- PostreSQL 11 or higher.

The project is implemented in 2 profiles: for development (dev) and for production (prod).
Both profiles use different settings and different databases.
You can select a profile in IntelliJ IDEA
Run/Debug Configuration -> Run.
The bootRun parameter is the dev profile;
The bootRunProd parameter is the prod profile.

No settings need to be applied to the dev profile, because the H2 database is used in memory saving mode. Therefore, every restart erases the data in the database.

The prod profile uses the PostgreSQL database in hard disk save mode. Therefore, you need to configure access to this database.
First, create the database and specify all access parameters to it in the environment variables, viz:
1. DB_URL - database connection address (must include the name of the database).
   for example - jdbc:postgresql://localhost:5432/testdb
   where
   5432  - is the port to access your PostgreSQL
   testdb - is a name of your database
2. DB_USERNAME - user name to connect to the database
3. DB_PASSWORD - password to connect to the database

You can set Environment Variables in IntelliJ IDEA
Run/Debug Configuration -> Environment variables.

Since the program uses migrations to create the database structure and fill it with the initial values, you need to clear all the tables in the database each time you restart the program in the prod profile.