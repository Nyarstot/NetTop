/* This file is a part of NETTOP expert system.
*
*  Author: Kozlov Nikita
*/

start_system:- 
    sleep(0.2),
    write('                             NETTOP EXPERT SYSTEM                       '), nl,
    sleep(0.2),
    write('                      PLEASE ANSWER SOME FURTHER QUESTION                 '), nl,nl,nl.

ask(Question):-
    write(Question), nl,
    read(Output), nl,
    ((Output == yes; Output == no))
    ->
        assert(yes(Question));
        assert(no(Question), fail).

:- dynamic yes/1, no/1.

verify(Order):-
    (yes(Order):-
    ->
        true;
        (no(Order)
        ->
            fail;
            ask(Order)
        )
    ).

undo :- retract(yes(_)), fail.
undo :- retract(no(_)), fail.
undo. 

/* Hypothesis */

choose(bus):- bus, !.
choose(star):- star, !.
choose(mesh):- mesh, !.
choose(peer_to_peer):- peer_to_peer, !.
choose(ring):- ring, !.
choose(tree):- tree, !.
choose(hybrid):- hybrid, !.
choose(not).

/* Questions */

quest(home):- verify("Are you going to make home network? ").
quest(corporate):- verify("Are you going to make corporate network? ").
quest(compute):- verify("Are you going to make network to compute something? ").
quest(classroom):- verify("Are you going to make network for computer classroom? ").
quest(two_computers):- verify("Do you have not more than two computers? ").
quest(five_computers):- verify("Do you have about five computers? ").
quest(ten_computers):- verify("Do you have about ten computers? ").
quest(more_computers):- verify("Do you have more than thirty computers? ").
quest(each_other):- verify("You need computers to be connected to each other? ").
quest(central_server):- verify("You need computers to be connected to a central server? ").
quest(hundred_dollars):- verify("Are you willing to spend about 100$ to setup your network? ").
quest(three_dollars):- verify("Are you willing to spend about 300$ to setup your network? ").
quest(eight_dollars):- verify("Are you willing to spend about 800% to setup your network? ").
quest(thousand_dollars):- verify("Are you willing to spend about 1000$ to setup your network? ").
quest(ten_meters):- verify("Is the distance you want to run your network cable about 10 meters? ").
quest(forty_meters):- verify("Is the distance you want to run your network cable about 40 meters? ").
quest(sixty_meters):- verify("Is the distance you want to run your network cable about 60 meters? ").
quest(hundred_meters):- verify("Is the distance you want to run your network cable about or more than 100 meters? ").
quest(yes_expand):- verify("Are you going to expand your network in the future? ").
quest(coaxial_cable):- verify("Are you going to use coaxial cable in your network? ").
quest(pair_cable):- verify("Are you going to use twisted pair cable in your network? ").
quest(fiber_cable):- verify("Are you going to use optic fiber cable in your network? ").

/* Knowledge */

bus:-
    verify(home);
    verify(classroom),
    verify(two_computers),
    verify(hundred_dollars),
    verify(ten_meters),
    verify(yes_expand),
    verify(coaxial_cable).

peer_to_peer:-
    verify(home),

