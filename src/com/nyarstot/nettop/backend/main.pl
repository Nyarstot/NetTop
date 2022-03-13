/* 
*  This file is a part of NETTOP expert system.
*  Author: Kozlov Nikita
*  Date: 3.12.2022
*/

/* BASIC FUNCTIONS */

intro_message:-
    sleep(0.2),
    write('                             NETTOP EXPERT SYSTEM                       '), nl,
    sleep(0.2),
    write('                      PLEASE ANSWER SOME FURTHER QUESTION                 '), nl,nl,nl.

find_topology(Topology):-
    topology(Topology), !.

% Store user answers to be able to track his progress
:- dynamic(progress/2).

% Clear stored user progress
% reset_answers must always return true; because retract can return either true
% or false, we fail the first and succeed with the second.
reset_answers:-
    retract(progress(_,_)),
    fail.
reset_answers.

start_system:-
    intro_message,
    reset_answers,
    find_topology(Topology),
    write('You probably looking for: '),
    describe(Topology), nl.

ask(Question, Answer, Choices):-
    question(Question),
    answers(Choices, 0),
    read(Index),
    parse(Index, Choices, Response),
    asserta(progress(Question, Response)),
    Response = Answer.

/* RULES FOR THE KNOWLEDGE BASE */

topology(peer_to_peer):-
    why(home).

/* QUESTIONS FOR THE KNOWLEDGE BASE */

question(why):-
    write('What type of network suits your needs?'), nl.

/* ANSWERS FOR THE KNOWLEDGE BASE */

answer(home):-
    write('Home network').
answer(corporate):-
    write('Corporate network').
answer(compute):-
    write('Computing network').
answer(classroom):-
    write('Classroom network').

/* TOPOLOGY DESCRIPTIONS */

describe(peer_to_peer):-
    write('Peer to peer network'), nl,
    write('This is a simple type of network where computers are able to communicate with one another and share what is on or attached to their computer with other users.').

/* ASSIGN AN ANSWER TO QUESTIONS FROM THE KNOWLEDGE BASE */

why(Answer):-
    progress(why, Answer).
why(Answer):-
    \+ progress(why,_),
    ask(why, Answer, [home, corporate, compute, classroom]).

/* ANSWER OUTPUT */
answers([],_).
answers([First|Rest], Index):-
    write('['), write(Index), write(']'), write(' '), answer(First), nl,
    NextIndex is Index + 1,
    answers(Rest, NextIndex).

parse(0, [First|_], First).
parse(Index, [First|Rest], Response):-
    Index > 0,
    NextIndex is Index - 1,
    parse(NextIndex, Rest, Response).