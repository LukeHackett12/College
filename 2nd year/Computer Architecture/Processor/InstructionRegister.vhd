library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity InstructionRegister is
	Port(	IR : in std_logic_vector(15 downto 0);
			IL : in std_logic;
			opcode :  out std_logic_vector(6 downto 0);
			DR, SA, SB : out std_logic_vector(2 downto 0)
			);
end InstructionRegister;

architecture Behavioral of InstructionRegister is

begin
	opcode <= IR(15 downto 9) after 2ns when IL = '1';
	DR <= IR(8 downto 6) after 2ns when IL = '1';
	SA <= IR(5 downto 3) after 2ns when IL = '1';
	SB <= IR(2 downto 0) after 2ns when IL = '1';

end Behavioral;