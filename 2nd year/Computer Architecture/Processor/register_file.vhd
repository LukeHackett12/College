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

entity register_file is
    Port ( Clk, load_enabled : in std_logic;
        addr_a, addr_b, dest_d : in std_logic_vector(2 downto 0);
        data : in std_logic_vector(15 downto 0);
        out_a, out_b : out std_logic_vector(15 downto 0)
    );
end register_file;

architecture Behaviour of register_file is
  
  component reg16
  port ( D : in std_logic_vector(15 downto 0);
         load0, load1, Clk : in std_logic;
         Q: out std_logic_vector(15 downto 0));
  end component;
  
  component decoder_3to8
  port ( s : in   std_logic_vector(2 downto 0);
         z0 : out  std_logic;
        z1 : out  std_logic;
        z2 : out  std_logic;
        z3 : out  std_logic;
        z4 : out  std_logic;
        z5 : out  std_logic;
        z6 : out  std_logic;
        z7 : out  std_logic);
  end component;
  
  component multiplexer8_16
  port ( s : in  std_logic_vector (2 downto 0);
           in0 : in  std_logic_vector (15 downto 0);
           in1 : in  std_logic_vector (15 downto 0);
           in2 : in  std_logic_vector (15 downto 0);
           in3 : in  std_logic_vector (15 downto 0);
           in4 : in  std_logic_vector (15 downto 0);
           in5 : in  std_logic_vector (15 downto 0);
           in6 : in  std_logic_vector (15 downto 0);
           in7 : in  std_logic_vector (15 downto 0);
           z : out  std_logic_vector (15 downto 0));
   end component;
   
   component multiplexer2_16
   port ( s : in  std_logic;
           in1 : in  std_logic_vector(15 downto 0);
           in2 : in  std_logic_vector (15 downto 0);
           z : out  std_logic_vector (15 downto 0));
   end component;
  
  signal load_reg0, load_reg1, load_reg3, load_reg2,
  load_reg4, load_reg5, load_reg6, load_reg7 : std_logic;
  
  signal reg0_q, reg1_q, reg2_q, reg3_q, reg4_q,
  reg5_q, reg6_q, reg7_q, src_reg : std_logic_vector(15 downto 0);
  
begin
  
  reg000: reg16 port map(
    D => data,
    load0 => load_reg0,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg0_q
  );
  reg001: reg16 port map(
    D => data,
    load0 => load_reg1,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg1_q
  );
  reg010: reg16 port map(
    D => data,
    load0 => load_reg2,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg2_q
  );
  reg011: reg16 port map(
    D => data,
    load0 => load_reg3,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg3_q
  );
  reg100: reg16 port map(
    D => data,
    load0 => load_reg4,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg4_q
  );
  reg101: reg16 port map(
    D => data,
    load0 => load_reg5,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg5_q
  );
  reg110: reg16 port map(
    D => data,
    load0 => load_reg6,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg6_q
  );
  reg111: reg16 port map(
    D => data,
    load0 => load_reg7,
    load1 => load_enabled,
    Clk => Clk,
    Q => reg7_q
  );
  
  dest_decoder : decoder_3to8 port map(
    s(0) => dest_d(0),
    s(1) => dest_d(1),
    s(2) => dest_d(2),
    z0 => load_reg0,
    z1 => load_reg1,
    z2 => load_reg2,
    z3 => load_reg3,
    z4 => load_reg4,
    z5 => load_reg5,
    z6 => load_reg6,
    z7 => load_reg7
  );
  
  inst_multiplexer8_16_a : multiplexer8_16 port map(
    s(0) => addr_a(0),
    s(1) => addr_a(1),
    s(2) => addr_a(2),
    in0 => reg0_q,
    in1 => reg1_q,
    in2 => reg2_q,
    in3 => reg3_q,
    in4 => reg4_q,
    in5 => reg5_q,
    in6 => reg6_q,
    in7 => reg7_q,
    z => out_a
  );
  
  inst_multiplexer8_16_b : multiplexer8_16 port map(
    s(0) => addr_b(0),
    s(1) => addr_b(1),
    s(2) => addr_b(2),
    in0 => reg0_q,
    in1 => reg1_q,
    in2 => reg2_q,
    in3 => reg3_q,
    in4 => reg4_q,
    in5 => reg5_q,
    in6 => reg6_q,
    in7 => reg7_q,
    z => out_b
  );
 
end Behaviour;
