----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 06.03.2019 10:52:55
-- Design Name: 
-- Module Name: multiplexer2_1 - Behavioral
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

entity multiplexer2_1 is
    Port(
        s, in_0, in_1 : in STD_LOGIC;
        z : out STD_LOGIC
    );
end multiplexer2_1;

architecture Behavioral of multiplexer2_1 is
begin

    process(s, in_0, in_1)
    begin
        case s is
            when '1' => z <= in_0;
            when '0' => z <= in_1;
            when others => z <= in_0;
        end case;
    end process;

end Behavioral;
