`timescale 1ns/100ps


module Circuit_1 (A, B, C, F1, F2);
	output F1, F2;
	input A, B, C;
	wire w1, w2, w3, w4, w5, w6, w7, w8;
	and  T2 (w1, A, B, C);
	or  T1 (w2, A, B, C);
	and T3 (w3, A, B);
	and T4 (w4, A, C);
	and T5 (w5, B, C);
	or  T6(F2, w3, w4, w5);
	not T7(w7, F2);
	and T8(w8, w2 ,w7);
	or  T9(F1, w8, w1);

endmodule

