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
            data_in, constant_in : in std_logic_vector(15 downto 0);
            control_word : in std_logic_vector(16 downto 0);
            Clk : in std_logic;
            address_out, data_out : out std_logic_vector(15 downto 0);
            v, c, n, z : out std_logic
        );
    end component;

    signal data_in, constant_in : std_logic_vector(15 downto 0);
    signal control_word : std_logic_vector(16 downto 0);
    signal Clk :  std_logic;
    signal address_out, data_out : std_logic_vector(15 downto 0);
    signal v, c, n, z : std_logic;

    constant Clk_period : time := 10 ns;

begin

     uut: Datapath port map (
          data_in => data_in,
          constant_in => constant_in,
          control_word => control_word,
          Clk => Clk,
          address_out => address_out,
          data_out => data_out,
          v => v,
          c => c,
          n => n,
          z => z
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

        data_in <= x"AAAA";
		constant_in <= x"0000";
		control_word <= "00000000100000011";
		
		wait for 50ns;
		data_in <= x"F0F0";
		control_word <= "00100000100000011";
		
		wait for 50ns;
		control_word <= "01000000110001001";
		
		wait for 50ns;
        control_word <= "00000001010000000";
        
	-- control_word <= "01001001001000000";


       wait;
   end process;   

end Behavioral;
