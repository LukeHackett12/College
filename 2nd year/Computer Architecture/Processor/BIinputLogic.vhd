----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    13:57:43 03/01/2016 
-- Design Name: 
-- Module Name:    logic_circuit_b - Behavioral 
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

entity BInputLogic is
	Port(
		b : in STD_LOGIC_VECTOR(15 downto 0);
		s_in : in STD_LOGIC_VECTOR(1 downto 0);
		y : out STD_LOGIC_VECTOR(15 downto 0)
	);
end BInputLogic;

architecture Behavioral of BInputLogic is
	
	--mux 2-1 component
	Component multiplexer2_1
	Port(
		s, in_0, in_1 : in STD_LOGIC;
		z : out STD_LOGIC
	);
	End Component;

begin
	mux00: multiplexer2_1 PORT MAP(
		s => b(0),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(0)
	);
	
	mux01: multiplexer2_1 PORT MAP(
		s => b(1),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(1)
	);
	
	mux02: multiplexer2_1 PORT MAP(
		s => b(2),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(2)
	);
	
	mux03: multiplexer2_1 PORT MAP(
		s => b(3),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(3)
	);
	
	mux04: multiplexer2_1 PORT MAP(
		s => b(4),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(4)
	);
	
	mux05: multiplexer2_1 PORT MAP(
		s => b(5),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(5)
	);
	
	mux06: multiplexer2_1 PORT MAP(
		s => b(6),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(6)
	);
	
	mux07: multiplexer2_1 PORT MAP(
		s => b(7),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(7)
	);
	
	mux08: multiplexer2_1 PORT MAP(
		s => b(8),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(8)
	);
	
	mux09: multiplexer2_1 PORT MAP(
		s => b(9),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(9)
	);
	
	mux10: multiplexer2_1 PORT MAP(
		s => b(10),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(10)
	);
	
	mux11: multiplexer2_1 PORT MAP(
		s => b(11),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(11)
	);
	
	mux12: multiplexer2_1 PORT MAP(
		s => b(12),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(12)
	);
	
	mux13: multiplexer2_1 PORT MAP(
		s => b(13),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(13)
	);
	
	mux14: multiplexer2_1 PORT MAP(
		s => b(14),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(14)
	);
	
	mux15: multiplexer2_1 PORT MAP(
		s => b(15),
		in_0 => s_in(0),
		in_1 => s_in(1),
		z => y(15)
	);

end Behavioral;