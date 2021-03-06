library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Memory512x16 is
	Port(	address_mem : in STD_LOGIC_VECTOR(15 downto 0);
			write_data : in STD_LOGIC_VECTOR(15 downto 0);
			mem_write : in STD_LOGIC;
			read_data : out STD_LOGIC_VECTOR(15 downto 0)
			);
end Memory512x16;

architecture Behavioral of Memory512x16 is
	--512 bit memory array
	type mem_array is array(0 to 511) of  STD_LOGIC_VECTOR(15 downto 0);
begin
	mem_process : process(address_mem, write_data, mem_write)
	variable data_mem : mem_array := (
		--module 00
		"0000001000000000", -- load reg0 with 0
        "0000011010000000", -- increment reg0 and store in reg2
        "0000000000010111", -- add 7 to reg2 and store in reg0
        "0000110000000010", -- branch to line 2 if zero
        "0000100110000000", -- reg6 = not reg0
        "0000010000110000", -- store reg0 at reg6 in memory
        "0000111000000010", -- unconditionally branch to line 2
		x"0000", --7
		x"0000", --8 
		x"0000", --9 
		x"0000", --A 
		x"0000", --B 
		x"0000", --C 
		x"0000", --D 
		x"0000", --E 
		x"0000", --F 
		
		--module 01
		x"0000",
		x"0000",
		x"0000", 
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		x"0000",
		
		--module 02
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 03
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 04
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 05
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 06
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 07
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 08
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 09
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 0A
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 0B
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 0C
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 0D
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 0E
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 0F
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 10
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 11
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 12
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 13
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 14
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 15
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 16
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 17
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 18
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 19
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 1A
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 1B
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 1C
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 1D
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 1E
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		
		--module 1F
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000",
		x"0000", x"0000", x"0000", x"0000"
	);
	
	variable addr : integer range 0 to 511;
	variable addr_out : STD_LOGIC_VECTOR(15 downto 0);
	
	begin
		addr := conv_integer(address_mem(8 downto 0));
		addr_out := data_mem(addr);
		if mem_write = '1' then
			data_mem(addr) := write_data;
		else read_data <= addr_out;
		end if;
	end process;

end Behavioral;
