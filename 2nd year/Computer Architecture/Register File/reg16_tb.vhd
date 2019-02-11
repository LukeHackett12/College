----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11.02.2019 11:56:15
-- Design Name: 
-- Module Name: reg16_tb - Behavioral
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

entity reg16_tb is
end reg16_tb;

architecture Behavioral of reg16_tb is

component reg16
    Port ( d : in STD_LOGIC_VECTOR (15 downto 0);
           clk : in STD_LOGIC;
           load : in STD_LOGIC;
           q : out STD_LOGIC_VECTOR (15 downto 0));
end component;

    signal d : std_logic_vector(15 downto 0);
    signal clk, load : std_logic;
    signal q : std_logic_vector(15 downto 0);

begin

    uut: reg16 PORT MAP (
            d => d,
            clk => clk,
            load => load,
            q => q
        );
        
    stim_proc: process
    begin	
       wait for 10 ns;
       d <= "1111111111111111";
       load <= '0';
       clk <= '0';
       
       wait for 10 ns;
       load <= '1';
       
       wait for 10 ns;
       
       clk <= '1';
       
   end process;

end Behavioral;
