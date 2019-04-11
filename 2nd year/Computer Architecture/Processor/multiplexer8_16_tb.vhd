----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11.02.2019 11:58:05
-- Design Name: 
-- Module Name: multiplexer8_16_tb - Behavioral
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

entity multiplexer8_16_tb is
end multiplexer8_16_tb;

architecture Behavioral of multiplexer8_16_tb is

component multiplexer8_16 

     Port ( s : in  STD_LOGIC_VECTOR (2 downto 0);
           in0 : in  STD_LOGIC_VECTOR (15 downto 0);
           in1 : in  STD_LOGIC_VECTOR (15 downto 0);
           in2 : in  STD_LOGIC_VECTOR (15 downto 0);
           in3 : in  STD_LOGIC_VECTOR (15 downto 0);
           in4 : in  STD_LOGIC_VECTOR (15 downto 0);
           in5 : in  STD_LOGIC_VECTOR (15 downto 0);
           in6 : in  STD_LOGIC_VECTOR (15 downto 0);
           in7 : in  STD_LOGIC_VECTOR (15 downto 0);
           z : out  STD_LOGIC_VECTOR (15 downto 0));

end component;
    
  signal s :  STD_LOGIC_VECTOR (2 downto 0);
  signal in0 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in1 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in2 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in3 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in4 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in5 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in6 :   STD_LOGIC_VECTOR (15 downto 0);
  signal in7 :   STD_LOGIC_VECTOR (15 downto 0);
  signal z :   STD_LOGIC_VECTOR (15 downto 0);

begin

    uut: multiplexer8_16 PORT MAP (
          s => s,
          in0 => in0,
          in1 => in1,
          in2 => in2,
          in3 => in3,
          in4 => in4,
          in5 => in5,
          in6 => in6,
          in7 => in7,
          z => z
    );
    
  stim_proc: process
   begin	
      wait for 10 ns;
      
        in0 <= "0000000000000000";
        in1 <= "0000000011111111";
        in2 <= "0000111100001111";
        in3 <= "0011001100110011";
        in4 <= "1111111111111111";
        in5 <= "1110001110001110";
        in6 <= "1111111100000011";
        in7 <= "1101010101010100";
   
       wait for 10 ns;
       s <="000";
       wait for 10 ns;
       s <="001";
       wait for 10 ns;
       s <="010";
       wait for 10 ns;
       s <="011";
       wait for 10 ns;
       s <="100";
       wait for 10 ns;
       s <="101";
       wait for 10 ns;
       s <="110";
       wait for 10 ns;
       s <="111";
  
 --     wait;
   end process;




end Behavioral;
