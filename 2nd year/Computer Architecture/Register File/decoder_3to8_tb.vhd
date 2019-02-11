----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11.02.2019 11:58:05
-- Design Name: 
-- Module Name: decoder_3to8_tb - Behavioral
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

entity decoder_3to8_tb is
end decoder_3to8_tb;

architecture Behavioral of decoder_3to8_tb is

COMPONENT decoder_3to8
    Port ( s : in   std_logic_vector(2 downto 0);
           z0 : out  std_logic;
           z1 : out  std_logic;
           z2 : out  std_logic;
           z3 : out  std_logic;
           z4 : out  std_logic;
           z5 : out  std_logic;
           z6 : out  std_logic;
           z7 : out  std_logic);
END COMPONENT;
    

   --Inputs
   signal s : std_logic_vector(2 downto 0);
   
 	--Outputs
   signal z0 :   std_logic;
   signal z1 :   std_logic;
   signal z2 :   std_logic;
   signal z3 :   std_logic;
   signal z4 :   std_logic;
   signal z5 :   std_logic;
   signal z6 :   std_logic;
   signal z7 :   std_logic;


   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
--   constant Clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: decoder_3to8 PORT MAP (
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
       wait for 10 ns;
       s <= "000";
       wait for 10 ns;
       s <= "001";
       wait for 10 ns;
       s <= "010";
       wait for 10 ns;
       s <= "011";
       wait for 10 ns;
       s <= "100";
       wait for 10 ns;
       s <= "101";
       wait for 10 ns;
       s <= "110";
       wait for 10 ns;
       s <= "111";
 --     wait;
   end process;

END;
