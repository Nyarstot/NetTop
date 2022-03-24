/* Question */

question(expansion):-
    write('Are you going to expand your network in the future?'), nl.

/* Answers */

answer(yes):-
    write('Yes').
answer(no):-
    write('No').

/* Knowledge */

expansion(Answer):-
    progress(expansion, Answer).
expansion(Answer):-
    \+ progress(expansion,_),
    ask(expansion, Answer, [yes, no]).