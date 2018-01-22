
// Test bench for Simple_Circuit_prop_delay

module t_Circuit_1;
	wire D, E;
	reg A, B, C;
Circuit_1 M1 (A, B, C, F1, F2); // Instance name required

initial
	begin
		$dumpfile("sim.vcd");
		$dumpvars(0, t_Circuit_1);
		A = 1'b0; B = 1'b0; C = 1'b0;
		#100 A = 1'b1; B = 1'b1; C = 1'b1;
	end

initial #200 $finish;
endmodule

