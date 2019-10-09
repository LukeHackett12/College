numeral(0).
numeral(s(X)) :- numeral(X).
numeral(p(X)) :- numeral(X).
numeral(X+Y) :- numeral(X), numeral(Y).
numeral(X-Y) :- numeral(X), numeral(Y).
numeral(-X) :- numeral(X).

add(0,X,X).
add(s(X),Y,s(Z)) :- add(X,Y,Z).
add(p(X),Y,p(Z)) :- add(X,Y,Z).

/* Start add2 */

/* add2 on - */

add2(-X,Y,Z) :- minus(X,A),
                add2(A,Y,Z).

add2(X,-Y,Z) :- minus(Y,A),
                add2(X,A,Z).

/* end add2 - */

/* add2 on successor */

add2(s(X+Y),Z,s(R)) :- add2(X+Y,Z,R).
add2(X,s(Y+Z),s(R)) :- add2(X,Y+Z,R).
add2(s(X+Y),s(P+Q),s(s(R))) :- add2(X+Y,P+Q,R).

/* end add2 successor */

/* add2 on predecessor */

add2(p(s(X)),Y,Z) :- add2(X,Y,Z).
add2(X, p(s(Y)),Z) :- add2(X,Y,Z).

add2(s(p(X)),Y,Z) :- add2(X,Y,Z).
add2(X,s(p(Y)),Z) :- add2(X,Y,Z).

/* end add2 predecessor */

/* add2 general predicates */

add2(s(X),p(Y),Z) :- add2(X,Y,Z).
add2(p(X),s(Y),Z) :- add2(X,Y,Z).

add2(X+Z,Y,R) :- add(X,Z,A),
			add2(A,Y,R).
add2(X,Y+Z,R) :- add(Y,Z,A),
			add2(X,A,R). 
add2(X+Y,P+Q,R) :- add(X,Y,A),
				add(P,Q,B),
				add2(A,B,R).

add2(X-Z,Y,R) :- subtract(X,Z,A),
			add2(A,Y,R).
add2(X,Y-Z,R) :- subtract(Y,Z,A),
			add2(X,A,R).
add2(X-Y,P-Q,R) :- subtract(X,Y,A),
				subtract(P,Q,B),
				add2(A,B,R).

add2(X, Y, Z) :- add(X, Y, Z).

/* end add2 general predicates */
/* End add2 */

/* Start Minus */
minus(0,0).
minus(s(p(X)),Y) :- minus(X,Y).
minus(p(s(X)),Y) :- minus(X,Y).
minus(s(X),p(Y)) :- minus(X,Y).
minus(p(X),s(Y)) :- minus(X,Y).
/* End Minus */

/* Start Subtract */
subtract(-X,Y,Z) :- minus(X,A),
					subtract2(A,Y,Z).
subtract(X,-Y,Z) :- minus(Y,A),
					subtract2(X,A,Z).
subtract(X,Y,Z)  :- minus(Y,A),
					add2(X,A,Z).
/* End Subtract */
