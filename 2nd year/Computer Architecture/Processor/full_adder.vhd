----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 20.02.2019 11:57:37
-- Design Name: 
-- Module Name: full_adder - Behavioral
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


-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity full_adder is
    Port(
        x, y, z : in std_logic;
        s, c : out std_logic
    );
end full_adder;

architecture Behavioral of full_adder is    
    component half_adder 
    port(
        x, y : in std_logic;
        s, c : out std_logic
    );
    end component;
    
    signal hs, hc, tc: std_logic;

begin
    ha1: half_adder
        port map (x,y,hs,hc);
    ha2: half_adder
        port map (z, hs, s, tc);
        
    c <= tc or hc;
end Behavioral;

