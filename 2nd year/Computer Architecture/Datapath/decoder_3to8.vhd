--------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Luke Hackett
--
-- Create Date:   11:50:59 02/23/2012
-- Design Name:   
-- Module Name:   decoder_3to8
-- Project Name:  Register file
-- Target Device:  
-- Tool versions:  
-- Description:   

-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity decoder_3to8 is
    Port ( s : in   STD_LOGIC_VECTOR(2 downto 0);
           z0 : out  std_logic;
           z1 : out  std_logic;
           z2 : out  std_logic;
           z3 : out  std_logic;
           z4 : out  std_logic;
           z5 : out  std_logic;
           z6 : out  std_logic;
           z7 : out  std_logic);
end decoder_3to8;

architecture Behaviour of decoder_3to8 is
  
begin
    z0 <= not(s(2)) and not(s(1)) and not(s(0)) after 1ns; 
    z1 <= not(s(2)) and not(s(1)) and s(0) after 1ns; 
    z2 <= not(s(2)) and s(1) and not(s(0)) after 1ns;
    z3 <= not(s(2)) and s(1) and s(0) after 1ns;
    z4 <= s(2) and not(s(1)) and not(s(0)) after 1ns;
    z5 <= s(2) and not(s(1)) and s(0) after 1ns;
    z6 <= s(2) and s(1) and not(s(0)) after 1ns;
    z7 <= s(2) and s(1) and s(0) after 1ns;
    
end Behaviour;

