/* Question */

question(cable_type):-
    write('What type of cable you wish to have?'), nl.

/* Answers */

answer(coaxial):-
    write('Coaxial cable').
answer(twisted):-
    write('Twisted pair cable').
answer(optical):-
    write('Optical cable').

/* Knowledge */

cable_type(Answer):-
    progress(cable_type, Answer).
cable_type(Answer):-
    \+ progress(cable_type,_),
    ask(cable_type, Answer, [coaxial, twisted, optical]).