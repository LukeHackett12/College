----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 07.03.2019 16:09:24
-- Design Name: 
-- Module Name: FunctionUnit - Behavioral
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

entity Datapath is
  Port ( 
    data_in, constant_in : in std_logic_vector(15 downto 0);
    control_word : in std_logic_vector(16 downto 0);
    Clk : in std_logic;
    address_out, data_out : out std_logic_vector(15 downto 0);
    v, c, n, z : out std_logic
  );
end Datapath;

architecture Behavioral of Datapath is
    
    component FunctionUnit is
        Port ( 
            a_in, b_in : in std_logic_vector(15 downto 0);
            function_select : in std_logic_vector(4 downto 0);
            v, c, n, z : out std_logic;
            f : out std_logic_vector(15 downto 0)
        );
    end component;
    
    component register_file
        Port ( Clk, load_enabled : in std_logic;
            addr_a, addr_b, dest_d : in std_logic_vector(2 downto 0);
            data : in std_logic_vector(15 downto 0);
            out_a, out_b : out std_logic_vector(15 downto 0) 
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
    
    signal a_data, b_data, const_choice, func_out, d_bus : std_logic_vector(15 downto 0);

begin
    regfile_0 : register_file Port map (
        Clk => Clk,
        load_enabled => control_word(0),
        addr_a => control_word(13 downto 11),
        addr_b => control_word(10 downto 8),
        dest_d => control_word(16 downto 14),
        data => d_bus,
        out_a => a_data,
        out_b => b_data
    ); 
    
    multiplexer2_16_0 : multiplexer2_16 Port map (
        s => control_word(7),
        in1 => constant_in,
        in2 => b_data,
        z => const_choice
    );
    
    functionUnit_0 : FunctionUnit Port map (
        a_in => a_data,
        b_in => const_choice,
        function_select => control_word(6 downto 2),
        v => v,
        c => c,
        n => n,
        z => z,
        f => func_out
    );
    
    multiplexer2_16_1 : multiplexer2_16 Port map (
        s => control_word(1),
        in1 => func_out,
        in2 => data_in,
        z => d_bus
    );
    
    address_out <= a_data;
    data_out <= const_choice;

end Behavioral;
