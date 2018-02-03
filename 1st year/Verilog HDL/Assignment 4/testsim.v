module t_AdderSub;
	wire [3:0] sum_diff;
	wire carry;
	reg select;
	reg[3:0] A, B;

AdderSub M1 (sum_diff, carry, select, A, B); // Instance name required

initial
	begin
		$dumpfile("sim.vcd");
		$dumpvars(0, AdderSub);
		A = 4'b1000; B = 4'b1000; select = 1'b0;
		#100 select = 1'b1;
		#100 A = 4'b1001; B = 4'b0001;
		#100 select = 1'b0;
	end

initial #200 $finish;
endmodule
