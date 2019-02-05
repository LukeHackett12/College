----------------------------------------------------------------------------------
-- Company: Trinity College
-- Engineer: Dr. Michael Manzke
-- 
-- Create Date:    11:42:30 02/23/2012 
-- Design Name: 
-- Module Name:    register_file - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity register_file is
    Port ( src_s0, src_s1, src_s2,
        des_A0, des_A1, des_A2, Clk: in std_logic;
        data_src : in std_logic;
        data : in std_logic_vector(15 downto 0);
        reg0, reg1, reg2, reg3, reg4, 
        reg5, reg6, reg7 : out std_logic_vector(15 downto 0) 
    );
end register_file;

architecture Behaviour of register_file is
  
  component reg16
  port ( D : in std_logic_vector(15 downto 0);
         load, Clk : in std_logic;
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
  reg5_q, reg6_q, reg7_q, data_src_mux_out, src_reg : std_logic_vector(15 downto 0);
  
begin
  
  reg000: reg16 port map(
    D => data_src_mux_out,
    load => load_reg0,
    Clk => Clk,
    Q => reg0_q
  );
  reg001: reg16 port map(
    D => data_src_mux_out,
    load => load_reg1,
    Clk => Clk,
    Q => reg1_q
  );
  reg010: reg16 port map(
    D => data_src_mux_out,
    load => load_reg2,
    Clk => Clk,
    Q => reg2_q
  );
  reg011: reg16 port map(
    D => data_src_mux_out,
    load => load_reg3,
    Clk => Clk,
    Q => reg3_q
  );
  reg100: reg16 port map(
    D => data_src_mux_out,
    load => load_reg4,
    Clk => Clk,
    Q => reg4_q
  );
  reg101: reg16 port map(
    D => data_src_mux_out,
    load => load_reg5,
    Clk => Clk,
    Q => reg5_q
  );
  reg110: reg16 port map(
    D => data_src_mux_out,
    load => load_reg6,
    Clk => Clk,
    Q => reg6_q
  );
  reg111: reg16 port map(
    D => data_src_mux_out,
    load => load_reg7,
    Clk => Clk,
    Q => reg7_q
  );
  
  dest_decoder : decoder_3to8 port map(
    s(0) => des_A0,
    s(1) => des_A1,
    s(2) => des_A2,
    z0 => load_reg0,
    z1 => load_reg1,
    z2 => load_reg2,
    z3 => load_reg3,
    z4 => load_reg4,
    z5 => load_reg5,
    z6 => load_reg6,
    z7 => load_reg7
  );
  
  inst_multiplexer8_16 : multiplexer8_16 port map(
    s(0) => src_s0,
    s(1) => src_s1,
    s(2) => src_s2,
    in0 => reg0_q,
    in1 => reg1_q,
    in2 => reg2_q,
    in3 => reg3_q,
    in4 => reg4_q,
    in5 => reg5_q,
    in6 => reg6_q,
    in7 => reg7_q,
    z => src_reg
  );
  
  data_multiplexer2_16 : multiplexer2_16 port map (
    s => data_src,
    in1 => data,
    in2 => src_reg,
    z => data_src_mux_out
  );
  
  reg0 <= reg0_q;
  reg1 <= reg1_q;
  reg2 <= reg2_q;
  reg3 <= reg3_q;
  reg4 <= reg4_q;
  reg5 <= reg5_q;
  reg6 <= reg6_q;
  reg7 <= reg7_q;
    
end Behaviour;
