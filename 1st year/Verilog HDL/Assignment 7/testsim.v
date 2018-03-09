module t_OddFunction;
	reg clk = 1'b0;
	wire A;
	reg x, y;

initial
	begin
		$dumpfile("sim.vcd");
		$dumpvars(0, t_OddFunction);
		x = 1'b1; y = 1'b0;
		#100 x = 1'b1; y = 1'b1;
		#100 x = 1'b0; y = 1'b1;
		#100 x = 1'b0; y = 1'b0;
	end

initial #400 $finish;

initial begin clk = 0; forever #5 clk = ~clk; end

	OddFunction M1 (A, x, y, clk); // Instance name required

endmodule
