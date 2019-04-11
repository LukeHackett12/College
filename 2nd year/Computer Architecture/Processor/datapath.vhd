----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/23/2018 02:58:39 PM
-- Design Name: 
-- Module Name: Datapath - Behavioral
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Datapath is
    Port (
        RW, MB, MD, MM : in std_logic;
        TDDR, TASA, TBSB : in std_logic_vector(3 downto 0);
        FS : in std_logic_vector(4 downto 0);
        PCin : in std_logic_vector(15 downto 0);
        Clk : in std_logic;
        Constantin : in std_logic_vector(2 downto 0);
        Datain : in std_logic_vector(15 downto 0);
        Addressout : out std_logic_vector(15 downto 0);
        Dataout : out std_logic_vector(15 downto 0);
        V, C, N, Z : out std_logic
    );
end Datapath;

architecture Behavioral of Datapath is

    COMPONENT register_file PORT (
        addr_a, addr_b, dest_d : in std_logic_vector(3 downto 0);
        Clk : in std_logic;
        load_enabled : in std_logic;
        data : in std_logic_vector(15 downto 0);
        out_a, out_b : out std_logic_vector(15 downto 0)
    );
    END COMPONENT;
    
    component FunctionUnit is
        Port ( 
            a_in, b_in : in std_logic_vector(15 downto 0);
            function_select : in std_logic_vector(4 downto 0);
            v, c, n, z : out std_logic;
            f : out std_logic_vector(15 downto 0)
        );
    end component;
    
    COMPONENT multiplexer2_16 PORT (
        in1, in2 : in std_logic_vector(15 downto 0);
        s : in std_logic;
        Z : out std_logic_vector(15 downto 0)
    );
    END COMPONENT;
    
    COMPONENT ZeroFill PORT (
        sb_in : in std_logic_vector(2 downto 0);
        zero_fill_out : out std_logic_vector(15 downto 0)
    );
    END COMPONENT;
    
    signal BusA, BusB1, BusB2, BusD, muxDin, muxBin : std_logic_vector(15 downto 0);

begin

    reg_file : register_file PORT MAP (
        dest_d => TDDR,
        addr_a => TASA,
        addr_b => TBSB,
        Clk => Clk,
        load_enabled => RW,
        data => BusD,
        out_a => BusA,
        out_b => BusB1
    );
    
    zf : ZeroFill PORT MAP (
        SB_in => Constantin,
        zero_fill_out => muxBin    
    );
    
    muxB : multiplexer2_16 PORT MAP (
        in1 => BusB1,
        in2 => muxBin,
        s => MB,
        Z => BusB2
    );
    
    Dataout <= BusB2;
    
    func_unit : FunctionUnit PORT MAP (
        a_in => BusA,
        b_in => BusB2,
        function_select => fs,
        V => V,
        C => C,
        N => N,
        Z => Z,
        F => muxDin
    );
    
    muxD : multiplexer2_16 PORT MAP (
        in1 => muxDin,
        in2 => Datain,
        s => MD,
        Z => BusD
    );
    
    muxM : multiplexer2_16 PORT MAP (
        in1 => BusA,
        in2 => PCin,
        s => MM,
        Z => Addressout
    );

end Behavioral;
