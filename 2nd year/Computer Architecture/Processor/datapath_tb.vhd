----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 07.03.2019 17:39:35
-- Design Name: 
-- Module Name: datapath_tb - Behavioral
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

entity datapath_tb is
--  Port ( );
end datapath_tb;

architecture Behavioral of datapath_tb is

    component Datapath is
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
    end component;

    -- inputs
    signal RW, MB, MD, MM : std_logic;
    signal TDDR, TASA, TBSB : std_logic_vector(3 downto 0);
    signal FS : std_logic_vector(4 downto 0);
    signal PCin : std_logic_vector(15 downto 0);
    signal Clk : std_logic := '0';
    signal Constantin : std_logic_vector(2 downto 0);
    signal Datain : std_logic_vector(15 downto 0);
    
    -- outputs
    signal Addressout, Dataout : std_logic_vector(15 downto 0);
    signal V, C, N, Z : std_logic;
    
    constant clk_period : time := 110ns;

begin

     uut: Datapath port map (
        TDDR => TDDR,
        TASA => TASA,
        TBSB => TBSB,
        FS => FS,
        PCin => PCin,
        MM => MM,
        MD => MD,
        RW => RW,
        MB => MB,
        Clk => Clk,
        Constantin => Constantin,
        Datain => Datain,
        Addressout => Addressout,
        Dataout => Dataout,
        V => V,
        C => C,
        N => N,
        Z => Z
    );

  Clk_process :process
   begin
		Clk <= '0';
		wait for Clk_period/2;
		Clk <= '1';
		wait for Clk_period/2;
    end process;

   stim_proc: process

    begin

   MM <= '0';
    
    -- reg01 = 4112
    RW <= '1';
    TDDR <= "0001";
    MD <= '1';
    Datain <= X"4112";
    wait for clk_period;
    
    -- reg02 = 0x2114
    TDDR <= "0010";
    Datain <= X"2114";
    wait for clk_period;
    
    -- reg00 = reg01 + reg02
    TDDR <= "0000";
    TASA <= "0001";
    TBSB <= "0010";
    MB <= '0';
    MD <= '0';
    FS <= "00010";
    wait for clk_period;
    
    -- dispaly reg00 (0x2226)
    RW <= '0';
    TBSB <= "0000";
    wait for clk_period;
    
    -- reg04 = reg00 + 1
    RW <= '1';
    MB <= '1';
    Constantin <= "001";
    TDDR <= "0100";
    TASA <= "0000";
    wait for clk_period;
    
    -- display reg04 (0x2227)
    RW <= '0';
    MB <= '0';
    TBSB <= "0100";
    wait for clk_period;
    
    -- address out = PC
    MM <= '1';
    PCin <= X"4321";
    wait for clk_period;
    
    end process;

end Behavioral;
