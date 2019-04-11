----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 07.03.2019 16:14:27
-- Design Name: 
-- Module Name: multiplexer3_1 - Behavioral
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

entity multiplexer2_8 is
  Port ( 
    in0, in1 : in std_logic_vector(7 downto 0);
    s : in std_logic;
    z : out std_logic_vector(7 downto 0)
  );
end multiplexer2_8;

architecture Behavioral of multiplexer2_8 is

begin
    z <= 	in0 after 1ns when (s <= '0') else
			in1 after 1ns when (s <= '1');

end Behavioral;
