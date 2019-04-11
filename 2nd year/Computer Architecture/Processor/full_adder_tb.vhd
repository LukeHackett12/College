----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/13/2018 10:37:43 AM
-- Design Name: 
-- Module Name: full_adder_tb - Behavioral
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

entity full_adder_tb is
end full_adder_tb;

architecture Behavioral of full_adder_tb is

-- Component Declaration for Unit Under Test (UUT)
    COMPONENT full_adder Port(
        x, y, z : in std_logic;
        s, c : out std_logic
    );
    END COMPONENT;

    -- inputs
    signal X, Y, z : std_logic;
    
    -- outputs
    signal s, c : std_logic;

begin

    -- Instantiate the Unit Under Test
    uut : full_adder PORT MAP(
        X => X,
        Y => Y,
        z => z,
        c => c,
        s => s
    );
    
    stim_proc : process
    begin
    
    X <= '0';
    Y <= '0';
    z <= '0';
    wait for 5 ns;
    
    X <= '1';
    wait for 5 ns;
    
    Y <= '1';
    wait for 5 ns;
    
    z <= '1';
    wait for 5 ns;
    
    Y <= '0';
    wait for 5 ns;
    
    X <= '0';
    wait for 5 ns;
    
    Y <= '1';
    wait for 5 ns;
    
    z <= '0';
    wait for 5 ns;
    
    
    end process;

end Behavioral;
