

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
 
entity multiplexer2_8_tb IS
end multiplexer2_8_tb;
 
architecture behaviour OF multiplexer2_8_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    component multiplexer2_8
    Port ( 
        in0, in1 : in std_logic_vector(7 downto 0);
        s: in std_logic;
        z : out std_logic_vector(7 downto 0)
    );
    end component;
    
    signal in0, in1 :  std_logic_vector(7 downto 0);
    signal    s :  std_logic;
    signal     z :  std_logic_vector(7 downto 0);
 
begin

    uut: multiplexer2_8 port map (
          in0 => in0,
          in1 => in1,
          s => s,
          z => z
    );

   stim_proc: process

    begin

        in0 <= "11111111";
        in1 <= "00000000";
        
        s <= '0';
        
        wait for 10 ns;
        
        s <= '1';
        
        wait for 10 ns;
    --     wait;
   end process;   

end;
