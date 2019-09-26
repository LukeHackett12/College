numeral(0).
numeral(s(X)) :- numeral(X).
numeral(X+Y) :- numeral(X), numeral(Y).

add(0,X,X).
add(s(X),Y,s(Z)) :- add(X,Y,Z).

add2(X+Z,Y,R) :- add((X),(Z),A),
			add(A,Y,R).

add2(X,Y+Z,R) :- add((Y),(Z),A),
			add(X,A,R).
			
add2(0,X+Y,R) :- add((X),(Y),R).
			
add2(X+Y,P+Q,R) :- add((X),(Y),A),
				add((P),(Q),B),
				add(A,B,R).

