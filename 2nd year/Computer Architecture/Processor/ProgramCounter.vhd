library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity ProgrammeCounter is
	Port(	extendIn : in std_logic_vector(15 downto 0);
			PL, PI, reset : in std_logic;
			pc_out : out std_logic_vector(15 downto 0)
			);
end ProgrammeCounter;

architecture Behavioral of ProgrammeCounter is
begin
	process(reset, PL, PI)
	variable current : std_logic_vector(15 downto 0);
	variable temp : integer;
	variable tempAdded : std_logic_vector(15 downto 0);
	
	begin
		if(reset = '1') then current := x"0000";
		elsif(PL = '1') then 
			current := current + extendIn;
		elsif(PI = '1') then
			temp := conv_integer(current); 
			temp := temp + conv_integer(1);
			tempAdded := conv_std_logic_vector(temp, 16);
			current := tempAdded;
		end if;
		pc_out <= current after 2ns;
	end process;

end Behavioral;