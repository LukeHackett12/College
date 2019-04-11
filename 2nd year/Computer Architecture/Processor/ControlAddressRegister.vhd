----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/03/2018 03:44:15 PM
-- Design Name: 
-- Module Name: CAR - Behavioral
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

entity ControlAddressRegister is
    Port (
        opcode : in std_logic_vector(7 downto 0);
        load : in std_logic;
        Clk : in std_logic;
        reset : in std_logic;
        address : inout std_logic_vector(7 downto 0)
    );
end ControlAddressRegister;


architecture Behavioral of ControlAddressRegister is

begin

process(Clk)

begin
    if reset = '1' then
        address <= X"C0";
    else
        if(rising_edge(Clk)) then
            if load = '1' then
                address <= opcode;
            else
                address <= address + 1 after 5 ns;
            end if;
        end if;
    end if;
end process;

end Behavioral;
