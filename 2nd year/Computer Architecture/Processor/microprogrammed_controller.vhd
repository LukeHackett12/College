----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/10/2018 12:53:44 PM
-- Design Name: 
-- Module Name: microprogrammed_control - Behavioral
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

entity microprogrammed_control is
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
end microprogrammed_control;

architecture Behavioral of microprogrammed_control is

component ControlAddressRegister port (
    reset : in std_logic;
    opcode : in std_logic_vector(7 downto 0);
    load : in std_logic;
    Clk : in std_logic;
    address : inout std_logic_vector(7 downto 0)
);
end component;

component ProgrammeCounter Port(	
    extendIn : in std_logic_vector(15 downto 0);
    PL, PI, reset : in std_logic;
    pc_out : out std_logic_vector(15 downto 0)
);

end component;

component Extend Port(
    dr : in  std_logic_vector(2 downto 0);
    sb : in  std_logic_vector(2 downto 0);
    epc : out  std_logic_vector(15 downto 0)
);
end component;

component InstructionRegister Port(
     IR : in  std_logic_vector(15 downto 0);
     IL : in  std_logic;
     opcode : out  std_logic_vector(6 downto 0);
     DR : out  std_logic_vector(2 downto 0);
     SA : out  std_logic_vector(2 downto 0);
     SB : out  std_logic_vector(2 downto 0)
);
end component;

component ControlMemory256x28 Port(
         in_car : IN  std_logic_vector(7 downto 0);
         MW : OUT  std_logic;
         MM : OUT  std_logic;
         RW : OUT  std_logic;
         MD : OUT  std_logic;
         MB : OUT  std_logic;
         TB : OUT  std_logic;
         TA : OUT  std_logic;
         TD : OUT  std_logic;
         PL : OUT  std_logic;
         PI : OUT  std_logic;
         IL : OUT  std_logic;
         MC : OUT  std_logic;
         FS : OUT  std_logic_vector(4 downto 0);
         MS : OUT  std_logic_vector(2 downto 0);
         NA : OUT  std_logic_vector(7 downto 0)
        );
end component;

component multiplexer8_1 port (
    in0, in1, in2, in3, in4, in5, in6, in7 : in std_logic;
    s : in std_logic_vector(2 downto 0);
    Z : out std_logic
);
end component;

component multiplexer2_8 port (
    in0, in1 : in std_logic_vector(7 downto 0);
    s : in std_logic;
    Z : out std_logic_vector(7 downto 0)
);
end component;


    signal extout, pcval : std_logic_vector(15 downto 0);
    signal PI, PL, IL, muxSout, MC : std_logic;
    signal DR, SA, SB, MS : std_logic_vector(2 downto 0);
    signal opcode, muxCout, car_out, NA : std_logic_vector(7 downto 0);
    signal notZC : std_logic_vector(1 downto 0);
    signal dr_sb : std_logic_vector(5 downto 0);

begin

    progcount : ProgrammeCounter port map (
        reset => reset,
        extendIn => extout,
        PI => PI,
        PL => PL,
        pc_out => pcval
    );
    
    PCout <= pcval;
        
    ext : Extend port map (
        dr => DR,
        sb => SB,
        epc => extout
    );
    
    TDDRout(2 downto 0) <= DR;
    TASAout(2 downto 0) <= SA;
    TBSBout(2 downto 0) <= SB;
    
    ir : InstructionRegister port map (
        IR => instruction_in,
        IL => IL,
        opcode => opcode(6 downto 0),
        DR => DR,
        SA => SA,
        SB => SB
    );
    
    opcode(7) <= '0';
    
    muxC : multiplexer2_8 port map (
        in0 => NA,
        in1 => opcode,
        s => MC,
        Z => muxCout
    );
    
    ctrlreg : ControlAddressRegister port map (
        reset => reset,
        opcode => muxCout,
        load => muxSout,
        Clk => Clk,
        address => car_out
    );
    
    ctrl_mem : ControlMemory256x28 port map (
        in_car => car_out,
        MW => MWout,
        MM => MMout,
        RW => RWout,
        MD => MDout,
        FS => FSout,
        MB => MBout,
        TB => TBSBout(3),
        TA => TASAout(3),
        TD => TDDRout(3),
        PL => PL,
        PI => PI,
        IL => IL,
        MC => MC,
        MS => MS,
        NA => NA
    );
    
    notZC(0) <= not VCNZ(2);
    notZC(1) <= not VCNZ(0);
    
    muxS : multiplexer8_1 port map (
        in0 => '0',
        in1 => '1',
        in2 => VCNZ(2),
        in3 => VCNZ(3),
        in4 => VCNZ(0),
        in5 => VCNZ(1),
        in6 => notZC(0),
        in7 => notZC(1),
        s => MS,
        Z => muxSout
    );
    


end Behavioral;
