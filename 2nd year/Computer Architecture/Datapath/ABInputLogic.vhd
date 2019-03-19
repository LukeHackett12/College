----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 06.03.2019 09:54:26
-- Design Name: 
-- Module Name: ABInputLogic - Behavioral
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

entity ABInputLogic is
    Port(
        a_in, b_in : in std_logic_vector(15 downto 0);
        s_in : in std_logic_vector(1 downto 0);
        ab_out : out std_logic_vector(15 downto 0)
    );  
end ABInputLogic;

architecture Behavioral of ABInputLogic is

begin
    
    process(s_in,a_in,b_in)
        begin
        case s_in is
            when "00" => ab_out <= a_in AND b_in after 1 ns;
            when "01" => ab_out <= a_in OR b_in after 1 ns;
            when "10" => ab_out <= a_in XOR b_in after 1 ns;
            when "11" => ab_out <= not a_in after 1 ns;
            when others => ab_out <= not a_in after 1 ns;
        end case;
    end process;

end Behavioral;
