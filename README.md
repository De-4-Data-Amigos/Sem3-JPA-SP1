![](C:\Users\rasmu\Downloads\HobbyLogo.drawio.png)

Content:

[1] Business Model

[2] Domain model

[3] EER-diagram of the database

[4] Collaboration & Project control

[5] Project Requirements

***********************

[1] Business Model

This project, HobbySammen, is made for Ældresagen, who wants an app/a program for older people, where they can find people with similar interests and link up.
The app is made to prevent loneliness, which is one of the biggest health risks for older people, and the idea is that the app 
will make it easier for people to meet people with similar interests. 

The program is thought to be made for mobilephones, to remove the requirement of an ethernet connection,
a computer, writing down the name of a webpage and so on. Things that most people have more at hand on a mobilephone, especially in regards to their age and how they live.


***********************

[2] Domain model

![](C:\Users\rasmu\Downloads\HobbySammen.png)

Explanation:

Person to hobby is a * - * (BiDirectional, ManyToMany) relation

Person to PersonDetails is a 1 - 1 (OnetoOne) relation



***********************
[3] EER-diagram

![](C:\Users\rasmu\Downloads\HobbySammenEERdiagram.png)

***********************

[4] Collaboration & Project Control

We spent the first day mapping out the project, creating first a businesscase, then the domain model and lastly the EER-diagram was made.

We set up the project together, ensuring we all were in on creating entity-classes, dao-classes, testing of these and the database itself.

Most of our time spent on the project were at CPH Business in Lyngby, usually 9.30-14'ish, and then communicated from home over Discord in our usual group.
In regards to control, we did not setup a Kanban board for this project and that was mostly due to it's shorter size, in comparison to our 2 semester exam project spanning over a month, 
where we heavily used Githubs own project-board.


We did however use our Discord chat group for this, usually creating a to-do for the day and one for the next day (see picture below):
![](C:\Users\rasmu\Downloads\DiscordStyring.png)

We used Github for our coding-work, different branches to ensure control of what gets pushed to Main, and only pushed stuff into Main once it was tested and finished.

Victor was our lead in regards to ensuring this, as branches and such can be a bit confusing at times, even though we've used it before.


***********************

[5] Technical requirements 

    JPA
    JPQL
    Maven
    JDK 17^
    JUnit 5
    Docker
    PostgresSQL
    pgAdmin
    Lombok


