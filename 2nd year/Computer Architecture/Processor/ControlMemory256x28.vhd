----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 26.03.2019 13:51:19
-- Design Name: 
-- Module Name: ControlMemory256x28 - Behavioral
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ControlMemory256x28 is Port( 
    MW : out std_logic; 
    MM : out std_logic; 
    RW : out std_logic; 
    MD : out std_logic; 
    FS : out std_logic_vector(4 downto 0); 
    MB : out std_logic; 
    TB : out std_logic; 
    TA : out std_logic; 
    TD : out std_logic; 
    PL : out std_logic; 
    PI : out std_logic; 
    IL : out std_logic; 
    MC : out std_logic; 
    MS : out std_logic_vector(2 downto 0); 
    NA : out std_logic_vector(7 downto 0); 
    IN_CAR : in std_logic_vector(7 downto 0)); 
end ControlMemory256x28;

architecture Behavioral of ControlMemory256x28 is 
type mem_array is array(0 to 255) of std_logic_vector(27 downto 0);

begin

    memory_m: process(IN_CAR) 
    variable ControlMemory256x28 : mem_array:=( 
    --Module 0
	x"C020224", --0 ADI 
	x"C02000C", --1 LDR 
	x"C020001", --2 STR
	x"C020014", --3 INC 
	x"C0200E4", --4 NOT 
	x"C020024", --5 ADD 
	x"C0E0000", --6 Branch if ZERO
	x"C022000", --7 Branch unconditionally
	X"DDDDDDD", --8
    X"0000000", --9
    X"1111111", --A
    X"0000000", --B
    X"2222222", --C
    X"0000000", --D
    X"3333333", --E
    X"0000000", --F
	
	--Module 1
	x"0000000", --0 
	x"0000000", --1 
	x"0000000", --2 
	x"0000000", --3 
	x"0000000", --4 
	x"0000000", --5 
	x"0000000", --6
	x"0000000", --7 
	x"0000000", --8 
	x"0000000", --9 
	x"0000000", --A  
	x"0000000", --B 
	x"0000000", --C 
	x"0000000", --D 
	x"0000000", --E 
	x"0000000", --F 
	
	--Module 2
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 3
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 4
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 5
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 6
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 7
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 8
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module 9
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module A
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module B
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module C
	x"C10C002", --0 IF fetching 
	x"0030000", --1 Exit signal 
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module D
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module E
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
	x"0000000", --F
	
	--Module F
	x"0000000", --0
	x"0000000", --1
	x"0000000", --2
	x"0000000", --3
	x"0000000", --4
	x"0000000", --5
	x"0000000", --6
	x"0000000", --7
	x"0000000", --8
	x"0000000", --9
	x"0000000", --A
	x"0000000", --B
	x"0000000", --C
	x"0000000", --D
	x"0000000", --E
    x"0000000" --F
    );
    
variable addr : integer; 
variable control_out : std_logic_vector(27 downto 0);
    
begin
	addr := conv_integer(in_car);
	control_out := ControlMemory256x28(addr);
	MW <= control_out(0);
	MM <= control_out(1);
	RW <= control_out(2);
	MD <= control_out(3);
	FS <= control_out(8 downto 4);
	MB <= control_out(9);
	
	TB <= control_out(10);
	TA <= control_out(11);
	TD <= control_out(12);
	PL <= control_out(13);
	PI <= control_out(14);
	IL <= control_out(15);
	MC <= control_out(16);
	MS <= control_out(19 downto 17);
	NA <= control_out(27 downto 20);
end process;

end Behavioral;