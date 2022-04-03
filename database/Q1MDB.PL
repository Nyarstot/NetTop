/* Question */

question(what):-
    write('What type of network suits your needs?'), nl.

/* Answers */

answer(home):-
    write('Home network').
answer(corporate):-
    write('Corporate network').
answer(compute):-
    write('Computing network').
answer(classroom):-
    write('Classroom network').

/* Knowledge */

what(Answer):-
    progress(what, Answer).
what(Answer):-
    \+ progress(what,_),
    ask(what, Answer, [home, corporate, compute, classroom]).