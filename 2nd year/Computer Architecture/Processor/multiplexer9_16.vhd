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

entity multiplexer9_16 is
port ( s : in  std_logic_vector (3 downto 0);
           in0 : in  std_logic_vector (15 downto 0);
           in1 : in  std_logic_vector (15 downto 0);
           in2 : in  std_logic_vector (15 downto 0);
           in3 : in  std_logic_vector (15 downto 0);
           in4 : in  std_logic_vector (15 downto 0);
           in5 : in  std_logic_vector (15 downto 0);
           in6 : in  std_logic_vector (15 downto 0);
           in7 : in  std_logic_vector (15 downto 0);
           in8 : in  std_logic_vector (15 downto 0);
           z : out  std_logic_vector (15 downto 0));
end multiplexer9_16;

architecture Behavioral of multiplexer9_16 is

begin

   process ( s,in1,in2,in3,in4,in5,in6,in7,in8)
		begin
		case  s is
			when "0000" => z <= in0;
			when "0001" => z <= in1;
			when "0010" => z <= in2;
			when "0011" => z <= in3;
			when "0100" => z <= in4;
			when "0101" => z <= in5;
			when "0110" => z <= in6;
			when "0111" => z <= in7;
			when "1000" => z <= in8;
			when others => z <= in0;
		end case;
	end process;

end Behavioral;
