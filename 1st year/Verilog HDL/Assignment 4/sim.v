`timescale 1ns/100ps

module AdderSub (sum_diff, carry, A, B, select);
	output reg[3:0] sum_diff;
	output reg carry;
	input select;
	input[3:0] A, B;

	always @(select, A, B)
		case(select)	
			1'b0:	assign{carry, sum_diff} = A+B;
			1'b1:	assign{carry, sum_diff} = A-B;
		endcase
endmodule
