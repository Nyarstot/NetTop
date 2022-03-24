/* 
*  This file is a part of NETTOP expert system.
*  Author: Kozlov Nikita
*  Date: 3.12.2022
*/

/* BASIC FUNCTIONS */

:- initialization(start_system).

:- consult('database/include.pl').

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
    find_topology(Topology), nl,
    progress(cable_type, T), run_calculator(T, Price),
    write('You probably looking for: '),
    describe(Topology), nl, nl, nl,
    write('Estimated price: '), write(Price), write('$'), nl, nl, nl.

ask(Question, Answer, Choices):-
    question(Question),
    answers(Choices, 0),
    write('Answer:> '), read(Index),
    parse(Index, Choices, Response),
    asserta(progress(Question, Response)),
    Response = Answer.

/* RULES FOR THE KNOWLEDGE BASE */

topology(peer_to_peer):-
    what(home),
    connection(each_other),
    expansion(no),
    cable_type(twisted).

/* TOPOLOGY DESCRIPTIONS */

describe(peer_to_peer):-
    write('Peer to peer network'), nl,
    write('This is a simple type of network where computers are able to communicate with one another and share what is on or attached to their computer with other users.').

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

/* CALCULATOR */

run_calculator(Cable, Answer):-
    write('Measure the length of the walls along which the network cable is going to be laid, how many meters did you get in total?'), nl,
    write('Answer:> '), read(Meters), nl,
    write('How many compputers you going to network?'), nl,
    write('Answer:> '), read(Amount), nl,
    calculate(Cable, Meters, Amount, A), Answer = A,
    write(Answer), nl.

calculate(Cable, Meters, Amount, Answer):-
    M = (Meters + (Amount*2)),
    (Cable = twisted
    ->
        Answer is M * 0.43;
        Cable = coaxial
        ->
            Answer is M * 0.56;
            Answer is M * 0.76
    ).

