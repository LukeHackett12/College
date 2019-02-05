--------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Dr. Michael Manzke
--
-- Create Date:   11:50:59 02/23/2012
-- Design Name:   
-- Module Name:   C:/Xilinx/12.4/ISE_DS/ISE/ISEexamples/MichaelsMultiplexer/multiplexer_tb.vhd
-- Project Name:  MichaelsMultiplexer
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: multiplexer
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
entity register_file_tb IS
end register_file_tb;
 
architecture behavior OF register_file_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    component register_file
    Port ( src_s0, src_s1, src_s2, src_s3,
        des_A0, des_A1, des_A2, Clk: in std_logic;
        data_src : std_logic_vector(1 downto 0);
        data : std_logic_vector(15 downto 0);
        reg0, reg1, reg2, reg3, reg4, 
        reg5, reg6, reg7 : out std_logic_vector(15 downto 0) 
    );
    end component;

   --Inputs
   signal src_s0, src_s1, src_s2, src_s3,
        des_A0, des_A1, des_A2, Clk : std_logic;
        
   signal data_src : std_logic_vector(1 downto 0);
   signal data : std_logic_vector(15 downto 0);
 	--Outputs
   signal reg0, reg1, reg2, reg3, reg4, 
        reg5, reg6, reg7 : std_logic_vector(15 downto 0);


 
begin

    uut: register_file port map (
          src_s0 => src_s0,
          src_s1 => src_s1,
          src_s2 => src_s2,
          src_s3 => src_s3,
          des_A0 => des_A0,
          des_A1 => des_A1,
          des_A2 => des_A2,
          Clk => Clk,
          data_src => data_src,
          data => data,
          reg0 => reg0,
          reg1 => reg1,
          reg2 => reg2,
          reg3 => reg3,
          reg4 => reg4,
          reg5 => reg5,
          reg6 => reg6,
          reg7 => reg7
        );

   stim_proc: process

   begin
     
    --     wait;
   end process;   

end;
