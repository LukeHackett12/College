----------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Dr. Michael Manzke
-- 
-- Create Date:    11:42:30 02/23/2012 
-- Design Name: 
-- Module Name:    decoder - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
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
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity decoder is
    Port ( s : in   STD_LOGIC_VECTOR(2 downto 0);
           z0 : out  std_logic;
           z1 : out  std_logic;
           z2 : out  std_logic;
           z3 : out  std_logic;
           z4 : out  std_logic;
           z5 : out  std_logic;
           z6 : out  std_logic;
           z7 : out  std_logic);
end decoder;

architecture Behaviour of decoder is
  
begin
    z0 <= not(s(2)) and not(s(1)) and not(s(0)) after 5ns; 
    z1 <= not(s(2)) and not(s(1)) and s(0) after 5ns; 
    z2 <= not(s(2)) and s(1) and not(s(0)) after 5ns;
    z3 <= not(s(2)) and s(1) and s(0) after 5ns;
    z4 <= s(2) and not(s(1)) and not(s(0)) after 5ns;
    z5 <= s(2) and not(s(1)) and s(0) after 5ns;
    z6 <= s(2) and s(1) and not(s(0)) after 5ns;
    z7 <= s(2) and s(1) and s(0) after 5ns;
    
end Behaviour;

