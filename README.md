# JavaIntroFitnessExample
Fitness centre example from the book "Learn Java In One Day And Learn It Well"

Chapter 12

Overview (abridged):
A basic membership management program for a fitness centre. 
Three outlets: Club Mercury, Club Neptune and Club Jupiter. 
Two types of members: Single Club Members and Multi Club Members
-> a single club member has access to only one of the three clubs, a multi club member has access to all three clubs

The membership fee depends on whether they are a single or multi club member
For single club members, the fees also depend on which club they have access to

Multi club members are awarded membership points for joining the club. Upon sign up they are awarded 100 points.
-> The program will not handle the redemption process

This application uses a csv file to store information about each member. Whenever we launch the application we'll read the information from the csv and transform it to a LinkedList. When we add a member to the LinkedList or remove a member from it, we'll update the csv file

