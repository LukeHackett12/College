----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/08/2018 12:56:18 PM
-- Design Name: 
-- Module Name: extend_tb - Behavioral
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

entity extend_tb is
end extend_tb;

architecture Behavioral of extend_tb is

    COMPONENT extend PORT (
        dr, sb : in std_logic_vector(2 downto 0);
        epc : out std_logic_vector(15 downto 0)
    );
    END COMPONENT;
    
    -- inputs
    signal dr, sb : std_logic_vector(2 downto 0);
    
    -- outputs
    signal epc : std_logic_vector(15 downto 0);

begin

    uut : extend PORT MAP (
        dr => dr,
        sb => sb,
        epc => epc
    );
    
    stim_proc : process
    begin
    
    dr <= "101";
    sb <= "100";
    wait for 10 ns;

    end process;

end Behavioral;
