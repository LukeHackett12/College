`timescale 1ns/100ps
`default_nettype none

module FiniteStateMachine (output out_z, input in_x, in_y, clk, reset_b);

parameter S0 = 2'b00, S1 = 2'b01, S2 = 2'b10, S3 = 2'b11;

assign out_z = ((state == S2) || (state == S3));

reg[1:0] state, next_state;

always @ (posedge clk, negedge reset_b)
	if(reset_b == 0) state <= S0;
    else state <= next_state;

always @ (state, in_x, in_y)
    case (state)
        S0: if(!in_x && !in_y) next_state <= S0;
            else if(!in_x && in_y) next_state <= S0;
            else if(in_x && !in_y) next_state <= S3;
            else next_state <= S1;
        S1: if(!in_x && !in_y) next_state <= S0;
            else if(!in_x && in_y) next_state <= S0;
            else if(in_x && !in_y) next_state <= S2;
            else next_state <= S2;
        S2: if(!in_x && !in_y) next_state <= S0;
            else if(!in_x && in_y) next_state <= S0;
            else if(in_x && !in_y) next_state <= S3;
            else next_state <= S3;
        S3: if(!in_x && !in_y) next_state <= S0;
            else if(!in_x && in_y) next_state <= S0;
            else if(in_x && !in_y) next_state <= S3;
            else next_state <= S3;
        endcase
endmodule
