--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   09:51:36 03/08/2016
-- Design Name:   
-- Module Name:   C:/Users/Ed/CS2022/Proj1b/logic_circuit_b_TB.vhd
-- Project Name:  Proj1b
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: logic_circuit_b
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
 
ENTITY BInputLogic_TB IS
END BInputLogic_TB;
 
ARCHITECTURE Behavior OF BInputLogic_TB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT BInputLogic
    PORT(
         b : IN  std_logic_vector(15 downto 0);
         s_in : IN  std_logic_vector(1 downto 0);
         y : OUT  std_logic_vector(15 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal b : std_logic_vector(15 downto 0) := (others => '0');
   signal s_in : std_logic_vector(1 downto 0) := (others => '0');

 	--Outputs
   signal y : std_logic_vector(15 downto 0);
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: BInputLogic PORT MAP (
          b => b,
          s_in => s_in,
          y => y
        );

   -- Stimulus process
   stim_proc: process
   begin		
		b <= x"AAAA";
		s_in <= "00";
		
		wait for 5ns;
		s_in <= "01";
		
		wait for 5ns;
		s_in <= "10";
		
		wait for 5ns;
		s_in <= "11";

      wait;
   end process;

END;