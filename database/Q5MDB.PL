/* Question */
question(gay_question):-
	write('Are you a gay?').
/* Answers */
answer(gay):-
	write('Yes iam').
answer(not_gay):-
	write('No im not').
answer(mb_gay):-
	write('Maybe').
/* Knowledge */
gay_question(Answer):-
	write(gay_question, Answer).
gay_question(Answer):-
	\+ progress(gay_question,_),
	ask(gay_question, Answer, [gay, not_gay, mb_gay]).
