`timescale 1ns/100ps

module Compare (A, B, Y);
	input [3:0] A, B;
	output reg[5:0] Y;

	always @ (A, B)

	begin : compare
		if(A == B) assign Y[5] = 1'b1;
		else assign Y[5] = 1'b0;
		if (A != B) assign Y[4] = 1'b1;
		else assign Y[4] = 1'b0;
		if (A > B) assign Y[3] = 1'b1;
		else assign Y[3] = 1'b0;
		if (A < B) assign Y[2] = 1'b1;
		else assign Y[2] = 1'b0;
		if (A >= B) assign Y[1] = 1'b1;
		else assign Y[1] = 1'b0;
		if (A <= B) assign Y[0] = 1'b1;
		else assign Y[0] = 1'b0;
	end

endmodule
