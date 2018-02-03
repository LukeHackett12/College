
// Test bench for Simple_Circuit_prop_delay

module t_Circuit_2;
	//wire D, E;
	reg A, B, C, D;
Circuit_2 M1 (A, B, C, D, Out_1, Out_2, Out_3); // Instance name required

initial
	begin
		$dumpfile("sim.vcd");
		$dumpvars(0, t_Circuit_2);
		A = 1'b0; B = 1'b0; C = 1'b0; D = 1'b0;
		#100 A = 1'b1; B = 1'b1; C = 1'b1; D = 1'b1;
	end

initial #200 $finish;
endmodule
