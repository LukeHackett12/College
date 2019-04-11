----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 23.03.2019 18:26:59
-- Design Name: 
-- Module Name: Extend - Behavioral
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

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Extend is
	Port(	
	       DR : in std_logic_vector(2 downto 0);
	       SB : in std_logic_vector(2 downto 0);
	       epc : out std_logic_vector(15 downto 0)
            );
end Extend;

architecture Behavioral of Extend is
	signal extend : std_logic_vector(15 downto 0);
begin
	extend(15 downto 6) <= "0000000000" after 5 ns;
    extend(5 downto 3) <= DR after 5 ns;
    extend(2 downto 0) <= SB after 5 ns;
	epc <= extend;

end Behavioral;
