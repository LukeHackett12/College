`timescale 1ns/100ps

module AdderSub (sum_diff, carry, select, A, B);
    output[3:0] sum_diff;
    output carry;
    input select;
    input[3:0] A, B;

    assign{carry, sum_diff} = A+B;
    //assign{carry, sum_diff} = A-B;

endmodule
