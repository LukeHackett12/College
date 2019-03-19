

--------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Luke Hackett
--
-- Create Date:   11:50:59 02/23/2012
-- Design Name:   
-- Module Name:   register_file_tb
-- Project Name:  Register file
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: register_file
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
entity ALU_tb IS
end ALU_tb;
 
architecture behaviour OF ALU_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    component ALU
    Port ( 
        a_in, b_in : in std_logic_vector(15 downto 0);
        g_in : in std_logic_vector(3 downto 0);
        v, c : out std_logic;
        g_out : out std_logic_vector(15 downto 0)
    );
    end component;
    
    signal a_in, b_in, g_out : std_logic_vector(15 downto 0);
    signal g_in : std_logic_vector(3 downto 0);
    signal v, c : std_logic;
 
begin

    uut: ALU port map (
          a_in => a_in,
          b_in => b_in, 
          g_in => g_in,
          v => v,
          c => c, 
          g_out => g_out
    );

   stim_proc: process

    begin

    a_in <= x"FFAA";
    b_in <= x"000F";
    g_in <= "0000";
    
    wait for 50ns;
    g_in <= "0001";
    
    wait for 50ns;
    g_in <= "0010";
    
    wait for 50ns;
    g_in <= "0010";
    
    wait for 50ns;
    g_in <= "0011";
    
    wait for 50ns;
    g_in <= "0100";
    
    wait for 50ns;
    g_in <= "0101";
    
    wait for 50ns;
    g_in <= "0110";
    
    wait for 50ns;
    g_in <= "0111";
    
    wait for 50ns;
    g_in <= "1000";
    
    wait for 50ns;
    g_in <= "1001";
    
    wait for 50ns;
    g_in <= "1010";
    
    wait for 50ns;
    g_in <= "1011";
    
    wait for 50ns;
    g_in <= "1100";
    
    wait for 50ns;
    g_in <= "1101";
    
    wait for 50ns;
    g_in <= "1110";
    
    wait for 50ns;
    g_in <= "1111";
    --     wait;
   end process;   

end;
