----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11.02.2019 11:58:05
-- Design Name: 
-- Module Name: multiplexer2_16_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity multiplexer2_16_tb is
end multiplexer2_16_tb;

architecture Behavioral of multiplexer2_16_tb is


component multiplexer2_16
    Port (
           s : in  std_logic;
           in1 : in  std_logic_vector(15 downto 0);
           in2 : in  std_logic_vector (15 downto 0);
           z : out  std_logic_vector (15 downto 0)
    );

end component;

--Inputs
   signal s : std_logic;
   signal in1 : std_logic_vector(15 downto 0);
   signal in2 : std_logic_vector(15 downto 0);
   
 	--Outputs
   signal z :   std_logic_vector(15 downto 0);
   
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
--   constant Clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: multiplexer2_16 PORT MAP (
          s => s,
          in1 => in1,
          in2 => in2,
          z => z
        );

   stim_proc: process
   begin	
      wait for 10 ns;
      
       in1 <= "0101010101010101";
       in2 <= "1010101010101010";
   
       wait for 10 ns;
       s <='0';
       wait for 10 ns;
       s <= '1';
 --     wait;
   end process;

END;

