----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/11/2018 01:01:14 PM
-- Design Name: 
-- Module Name: processor - Behavioral
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

entity processor is
    Port( reset, Clk : in std_logic;
            PC_Count : out std_logic_vector(15 downto 0);
            instruction : out std_logic_vector(15 downto 0));
end processor;

architecture Behavioral of processor is

    component Datapath Port (
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
    end component;
    
    component microprogrammed_control port (
        reset : in std_logic;
        Clk : in std_logic;
        VCNZ : in std_logic_vector(3 downto 0);
        instruction_in : in std_logic_vector(15 downto 0);
        TDDRout, TASAout, TBSBout : out std_logic_vector(3 downto 0);
        FSout : out std_logic_vector(4 downto 0);
        MBout, MDout, MMout, RWout, MWout : out std_logic;
        PCout : out std_logic_vector(15 downto 0)
    );
    end component;

    component Memory512x16 port(
         address_mem : IN  std_logic_vector(15 downto 0);
         write_data : IN  std_logic_vector(15 downto 0);
         mem_write : IN  std_logic;
         read_data : OUT  std_logic_vector(15 downto 0)
        );
    end component;
    
    signal TDDR, TASA, TBSB, VCNZ : std_logic_vector(3 downto 0);
    signal FS : std_logic_vector(4 downto 0);
    signal PC, Address, memdatain, memdataout : std_logic_vector(15 downto 0);
    signal RW, MB, MD, MM, MW, notMW : std_logic;

begin

    
    dp : Datapath port map (
        Clk => Clk,
        TDDR => TDDR,
        TASA => TASA,
        TBSB => TBSB,
        FS => FS,
        PCin => PC,
        Constantin => TBSB(2 downto 0),
        Datain => memdataout,
        RW => RW,
        MB => MB,
        MD => MD,
        MM => MM,
        Z => VCNZ(0),
        N => VCNZ(1),
        C => VCNZ(2),
        V => VCNZ(3),
        Dataout => memdatain,
        Addressout => Address
    );
    
    ctrl : microprogrammed_control port map (
        reset => reset,
        Clk => Clk,
        VCNZ => VCNZ,
        instruction_in => memdataout,
        TDDRout => TDDR,
        TASAout => TASA,
        TBSBout => TBSB,
        FSout => FS,
        MBout => MB,
        MDout => MD,
        MMout => MM,
        RWout => RW,
        MWout => MW,
        PCout => PC
    );
    
    notMW <= not MW;
    
    mem : Memory512x16 port map (
        address_mem => Address,
        write_data => memdatain,
        read_data => memdataout,
        mem_write => MW
    );
    
    PC_Count <= PC;
    instruction <= memdataout; 


end Behavioral;
