numeral(0).
numeral(s(X)) :- numeral(X).
numeral(p(X)) :- numeral(X).
numeral(X+Y) :- numeral(X), numeral(Y).

add(0,X,X).
add(s(X),Y,s(Z)) :- add(X,Y,Z).
add(p(X),Y,p(Z)) :- add(X,Y,Z).

/* Start add2 */
/* add2 on successor */

add2(s(X+Y),Z,s(R)) :- add2(X+Y,Z,R).
add2(X,s(Y+Z),s(R)) :- add2(X,Y+Z,R).
add2(s(X+Y),s(P+Q),s(s(R))) :- add2(X+Y,P+Q,R).

/* end add2 successor */

/* add2 general predicates */

add2(X+Z,Y,R) :- add(X,Z,A),
			add2(A,Y,R).
add2(X,Y+Z,R) :- add(Y,Z,A),
			add2(X,A,R).
add2(X+Y,P+Q,R) :- add(X,Y,A),
				add(P,Q,B),
				add2(A,B,R).

add2(X, Y, Z) :- add(X, Y, Z).

/* end add2 general predicates */
/* End add2 */