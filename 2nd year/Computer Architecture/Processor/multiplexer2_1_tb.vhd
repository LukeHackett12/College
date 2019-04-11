----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/13/2018 05:55:05 PM
-- Design Name: 
-- Module Name: mux2_tb - Behavioral
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

entity multiplexer2_1_tb is
end multiplexer2_1_tb;

architecture Behavioral of multiplexer2_1_tb is

-- Component Declaration for Unit Under Test (UUT)
    COMPONENT multiplexer2_1 PORT(
        in_0, in_1 : in std_logic;
        s : in std_logic;
        Q : out std_logic
    );
    END COMPONENT;
    
    -- inputs
    signal x0, x1, s : std_logic;
    
    -- outputs
    signal Q : std_logic;

begin

    -- Instantiate the Unit Under Test
    uut : multiplexer2_1 PORT MAP(
        s => s,
        in_0 => x0,
        in_1 => x1,
        Q => Q
    );
    
    stim_proc: process
    begin
    
    x0 <= '1';
    x1 <= '0';
    s <= '0';
    wait for 5 ns;
    s <= '1';
    wait for 5 ns;

    end process;

end Behavioral;
