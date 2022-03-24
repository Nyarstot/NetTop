/* Question */

question(connection):-
    write('Choose your type of connection'), nl.

/* Answers */

answer(central_server):-
    write('Central server').
answer(each_other):-
    write('To each other').

/* Knowledge */

connection(Answer):-
    progress(connection, Answer).
connection(Answer):-
    \+ progress(connection,_),
    ask(connection, Answer, [central_server, each_other]).