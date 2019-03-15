

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
 
entity register_file_tb IS
end register_file_tb;
 
architecture behaviour OF register_file_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    component register_file
    Port ( Clk, load_enabled : in std_logic;
        addr_a, addr_b, dest_d : in std_logic_vector(2 downto 0);
        data : in std_logic_vector(15 downto 0);
        out_a, out_b : out std_logic_vector(15 downto 0) 
    );
    end component;

   --Inputs
   signal Clk, load_enabled : std_logic;
   signal addr_a, addr_b, dest_d : std_logic_vector(2 downto 0);
        
   signal data : std_logic_vector(15 downto 0);
 	--Outputs
   signal out_a, out_b : std_logic_vector(15 downto 0);


 
begin

    uut: register_file port map (
          Clk => Clk,
          load_enabled => load_enabled,
          addr_a => addr_a,
          addr_b => addr_b,
          dest_d => dest_d,
          data => data,
          out_a => out_a,
          out_b => out_b
        );

   stim_proc: process

   begin
     dest_d <= "000";
     data <= "1111111100000000";
     Clk <= '0';
     load_enabled <= '1';
     
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;

     dest_d <= "001";
     data <= "0000000011111111";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     dest_d <= "010";
     data <= "1111000011110000";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     dest_d <= "011";
     data <= "0000111100001111";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     dest_d <= "100";
     data <= "1100110011001100";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     dest_d <= "101";
     data <= "0011001100110011";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     dest_d <= "110";
     data <= "1010101010101010";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     dest_d <= "111";
     data <= "0101010101010101";
     Clk <= '0';
          
     wait for 10 ns;
   
     Clk <= '1';
      
     wait for 10 ns;
     
     addr_a <= "000";
     addr_b <= "111";
     
     wait for 10 ns;

     addr_a <= "001";
     addr_b <= "110";
     
     wait for 10 ns;
     
     addr_a <= "010";
     addr_b <= "101";
     
     wait for 10 ns;
     
     addr_a <= "011";
     addr_b <= "100";
     
     wait for 10 ns;
     
     addr_a <= "100";
     addr_b <= "011";
     
     wait for 10 ns;
     
     addr_a <= "101";
     addr_b <= "010";
     
     wait for 10 ns;
     
     addr_a <= "110";
     addr_b <= "001";
     
     wait for 10 ns;
     
     addr_a <= "111";
     addr_b <= "000";
     
     wait for 10 ns;
     
    --     wait;
   end process;   

end;
