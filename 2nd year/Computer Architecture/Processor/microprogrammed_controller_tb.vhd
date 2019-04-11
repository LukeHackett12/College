----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03.04.2019 20:01:05
-- Design Name: 
-- Module Name: microprogrammed_controller_tb - Behavioral
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

entity microprogrammed_controller_tb is
--  Port ( );
end microprogrammed_controller_tb;

architecture Behavioral of microprogrammed_controller_tb is

component microprogrammed_control
    Port (
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

-- inputs
    signal reset : std_logic;
    signal Clk : std_logic := '0';
    signal VCNZ : std_logic_vector(3 downto 0);
    signal instruction_in : std_logic_vector(15 downto 0);
    
    -- outputs
    signal TDDRout, TASAout, TBSBout : std_logic_vector(3 downto 0);
    signal FSout : std_logic_vector(4 downto 0);
    signal MBout, MDout, MMout, RWout, MWout : std_logic;
    signal PCout : std_logic_vector(15 downto 0);
    
    constant clk_period : time := 110 ns;

begin

    Clk <= not Clk after clk_period/2;
    
    uut : microprogrammed_control PORT MAP (
        reset => reset,
        Clk => Clk,
        VCNZ => VCNZ,
        instruction_in => instruction_in,
        TDDRout => TDDRout,
        TASAout => TASAout,
        TBSBout => TBSBout,
        FSout => FSout,
        MBout => MBout,
        MDout => MDout,
        MMout => MMout,
        RWout => RWout,
        MWout => MWout,
        PCout => PCout
    );
    
    stim_proc : process
    begin

    -- opcode = 0000000, DR = 010, SA = 100, SB = 001
    reset <= '1';
    VCNZ <= "0000";
    instruction_in <= "0000000010100001";
    wait for clk_period;
    
    reset <= '0';
    wait for clk_period*2;
    
    instruction_in <= "0000001010100001";
    wait for clk_period*2;
    
    instruction_in <= "0000010010100001";
    wait for clk_period*2;

    end process;


end Behavioral;
