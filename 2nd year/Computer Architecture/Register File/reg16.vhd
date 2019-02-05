----------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Dr. Michael Manzke
-- 
-- Create Date:    11:42:30 02/23/2012 
-- Design Name: 
-- Module Name:    reg16 - Behavioral 
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity reg16 is
    Port ( D : in std_logic_vector(15 downto 0);
        load, Clk : in std_logic;
        Q: out std_logic_vector(15 downto 0));
end reg16;

architecture Behaviour of reg16 is
  
begin

    process(Clk)
    begin
        if(rising_edge(Clk)) then
            if load='1' then
                Q <= D after 5ns;
            end if;
        end if;
    end process;
    
end Behaviour;
