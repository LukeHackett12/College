uv --> [], l(0).
l(Count) --> [2], r(Count).
l(Count) --> [0], l(succ(Count)).
l(Count) --> [1], l(Count).
r(0) --> [].
r(0) --> [0].
r(Count) --> [0], r(Count).
r(succ(succ(Count))) --> [1], r(Count).


street --> houses(_,_,_).
houses(A, B, C) --> column(A, A1), 
                    nationality(B, B1), 
                    pet(C, C1), 
                    column(A1, A2), 
                    nationality(B1, B2), 
                    pet(C1, C2), 
                    column(A2, _), 
                    nationality(B2, _), 
                    pet(C2, _).

column(r, b_g) --> [red].
column(b, g_r) --> [blue].
column(g, r_b) --> [green].
column(b_g, g_end) --> [blue].
column(b_g, b_end) --> [green].
column(g_r, r_end) --> [green].
column(g_r, g_end) --> [red].
column(r_b, b_end) --> [red].
column(r_b, r_end) --> [blue].
column(r_end, end) --> [red].
column(g_end, end) --> [green].
column(b_end, end) --> [blue].

nationality(eng, spa_jap) --> [english].
nationality(spa, jap_eng) --> [spanish].
nationality(jap, eng_spa) --> [japanese].
nationality(spa_jap, jap_end) --> [spanish].
nationality(spa_jap, spa_end) --> [japanese].
nationality(jap_eng, jap_end) --> [english].
nationality(jap_eng, eng_end) --> [japanese].
nationality(eng_spa, eng_end) --> [spanish].
nationality(eng_spa, spa_end) --> [english].
nationality(eng_end, end) --> [english].
nationality(spa_end, end) --> [spanish].
nationality(jap_end, end) --> [japanese].

pet(jag, sna_zeb) --> [jaguar].
pet(sna, zeb_jag) --> [snail].
pet(zeb, jag_sna) --> [zebra].
pet(sna_zeb, zeb_end) --> [snail].
pet(sna_zeb, sna_end) --> [zebra].
pet(zeb_jag, jag_end) --> [zebra].
pet(zeb_jag, zeb_end) --> [jaguar].
pet(jag_sna, sna_end) --> [jaguar].
pet(jag_sna, jag_end) --> [snail].
pet(jag_end, end) --> [jaguar].
pet(sna_end, end) --> [snail].
pet(zeb_end, end) --> [zebra].


mkList(1, [1]).
mkList(N, [N|T]) :- N > 1, 
                    M is N-1, 
                    mkList(M, T).

s(N) --> {mkList(N, L)},
            {member(Num,L)}, 
            {Rest is N - Num}, 
            {Rest > 0},
            [Num], 
            s(Rest).