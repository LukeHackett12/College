----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 22.03.2019 15:49:53
-- Design Name: 
-- Module Name: multiplexer9_16 - Behavioral
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

entity multiplexer8_1 is
port ( s : in  std_logic_vector(2 downto 0);
           in0 : in  std_logic;
           in1 : in  std_logic;
           in2 : in  std_logic;
           in3 : in  std_logic;
           in4 : in  std_logic;
           in5 : in  std_logic;
           in6 : in  std_logic;
           in7 : in  std_logic;
           z : out  std_logic);
end multiplexer8_1;

architecture Behavioral of multiplexer8_1 is

begin

   process ( s,in1,in2,in3,in4,in5,in6,in7)
		begin
		case s is
			when "000" => z <= in0;
			when "001" => z <= in1;
			when "010" => z <= in2;
			when "011" => z <= in3;
			when "100" => z <= in4;
			when "101" => z <= in5;
			when "110" => z <= in6;
			when "111" => z <= in7;
			when others => z <= in0;
		end case;
	end process;

end Behavioral;
