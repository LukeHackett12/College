----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11.02.2019 11:58:05
-- Design Name: 
-- Module Name: multiplexer9_16_tb - Behavioral
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

entity multiplexer8_1_tb is
end multiplexer8_1_tb;

architecture Behavioral of multiplexer8_1_tb is

component multiplexer8_1 
    port ( s : in  std_logic_vector(2 downto 0);
           in0 : in  std_logic;
           in1 : in  std_logic;
           in2 : in  std_logic;
           in3 : in  std_logic;
           in4 : in  std_logic;
           in5 : in  std_logic;
           in6 : in  std_logic;
           in7 : in  std_logic;
           z : out  std_logic);
end component;
    
  signal s :  STD_LOGIC_VECTOR (2 downto 0);
  signal in0 :   STD_LOGIC;
  signal in1 :   STD_LOGIC;
  signal in2 :   STD_LOGIC;
  signal in3 :   STD_LOGIC;
  signal in4 :   STD_LOGIC;
  signal in5 :   STD_LOGIC;
  signal in6 :   STD_LOGIC;
  signal in7 :   STD_LOGIC;
  signal z :  STD_LOGIC;

begin

    uut: multiplexer8_1 PORT MAP (
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
      
        in0 <= '0';
        in1 <= '1';
        in2 <= '0';
        in3 <= '1';
        in4 <= '0';
        in5 <= '1';
        in6 <= '0';
        in7 <= '1';
   
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
