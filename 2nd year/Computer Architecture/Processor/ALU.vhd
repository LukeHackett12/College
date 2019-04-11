--------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Luke Hackett
--
-- Create Date:   11:50:59 02/23/2012
-- Design Name:   
-- Module Name:   register_file
-- Project Name:  Register file
-- Target Device:  
-- Tool versions:  
-- Description:   

-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ALU is
    Port ( 
        a_in, b_in : in std_logic_vector(15 downto 0);
        g_in : in std_logic_vector(3 downto 0);
        v, c : out std_logic;
        g_out : out std_logic_vector(15 downto 0)
    );
end ALU;

architecture Behaviour of ALU is
  
  component ripple_adder
    Port (  a, b: in std_logic_vector(15 downto 0);
        c_in: in std_logic;
        s : out std_logic_vector(15 downto 0);
        c_out, v_out : out std_logic
    );
    end component;
  
  component ABInputLogic
  Port(
        a_in, b_in : in std_logic_vector(15 downto 0);
        s_in : in std_logic_vector(1 downto 0);
        ab_out : out std_logic_vector(15 downto 0)
    );  
  end component;
  
  component BInputLogic is
	Port(
		b : in STD_LOGIC_VECTOR(15 downto 0);
		s_in : in STD_LOGIC_VECTOR(1 downto 0);
		y : out STD_LOGIC_VECTOR(15 downto 0)
	);
    end component;
    
    component multiplexer2_16
    Port (
           s : in  std_logic;
           in1 : in  std_logic_vector(15 downto 0);
           in2 : in  std_logic_vector (15 downto 0);
           z : out  std_logic_vector (15 downto 0)
    );
    end component;

    signal ab_logic_result,  b_logic_result, ripple_adder_result: std_logic_vector(15 downto 0);

begin

    ab_logic_0 : ABInputLogic Port map (
         a_in => a_in,
         b_in => b_in,
         s_in => g_in(2 downto 1),
         ab_out => ab_logic_result
    );
    
    b_logic_0 : BInputLogic Port map (
        b => b_in,
		s_in => g_in(2 downto 1),
		y => b_logic_result
    );
    
     ripple_adder_0 : ripple_adder Port map (
        a => a_in,
        b => b_logic_result, 
        c_in => g_in(0),
        s => ripple_adder_result,
        c_out => c,
        v_out => v
    );
    
    mux_2_16 : multiplexer2_16 Port map (
         s => g_in(3),
         in1 => ripple_adder_result, 
         in2 => ab_logic_result,
         z => g_out
    );
  
end Behaviour;
