`timescale 1ns/100ps

module BCD_Adder (Sum, Carry_out, Addend, Augend, Carry_in);

	output [3:0] Sum;
	output Carry_out;
	input [3:0] Addend;
	input [3:0] Augend;
	input Carry_in;

	wire [3:0] SumOne, Correction;
	wire a, b, c;

	assign{c, SumOne} = Addend + Augend + Carry_in;
	and G1 (a, SumOne[3], SumOne[2]);
	and G2 (b, SumOne[3], SumOne[1]);
	or G3 (Carry_out, a, b, c);

	assign Correction[3] = 1'b0;
	assign Correction[2] = Carry_out;
	assign Correction[1] = Carry_out;
	assign Correction[0] = 1'b0;

	assign Sum = SumOne + Correction;

endmodule
