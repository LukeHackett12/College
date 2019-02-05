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
-- VHDL Test Bench Created by ISE for module: mux4_4bit
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
 
ENTITY decoder_tb IS
END decoder_tb;
 
ARCHITECTURE behavior OF decoder_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT decoder
    PORT(
         s : in std_logic_vector(2 downto 0) := (others => '0');
        z0 : out  std_logic;
        z1 : out  std_logic;
        z2 : out  std_logic;
        z3 : out  std_logic;
        z4 : out  std_logic;
        z5 : out  std_logic;
        z6 : out  std_logic;
        z7 : out  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal s : std_logic_vector(2 downto 0);
   
 	--Outputs
 	signal z0, z1, z2, z3, z4, z5, z6, z7 : std_logic;

   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
--   constant Clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: decoder PORT MAP (
          s => s,
          z0 => z0,
          z1 => z1,
          z2 => z2,
          z3 => z3,
          z4 => z4,
          z5 => z5,
          z6 => z6,
          z7 => z7
        );

   stim_proc: process
   begin	
        s <= "000";
   
       wait for 20 ns;
        s <= "001";
        
         wait for 20 ns;
        s <= "010";
        
         wait for 20 ns;
        s <= "011";
        
         wait for 20 ns;
        s <= "100";
        
         wait for 20 ns;
        s <= "101";
        
         wait for 20 ns;
        s <= "110";
        
         wait for 20 ns;
        s <= "111";
        
        wait for 20 ns;
 --     wait;
   end process;

END;
