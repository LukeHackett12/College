LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
ENTITY Memory512x16_tb IS
END Memory512x16_tb;
 
ARCHITECTURE behavior OF Memory512x16_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT Memory512x16
    PORT(
         address_mem : IN  std_logic_vector(15 downto 0);
         write_data : IN  std_logic_vector(15 downto 0);
         mem_write : IN  std_logic;
         read_data : OUT  std_logic_vector(15 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal address_mem : std_logic_vector(15 downto 0) := (others => '0');
   signal write_data : std_logic_vector(15 downto 0) := (others => '0');
   signal mem_write : std_logic := '0';

 	--Outputs
   signal read_data : std_logic_vector(15 downto 0);
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: Memory512x16 PORT MAP (
          address_mem => address_mem,
          write_data => write_data,
          mem_write => mem_write,
          read_data => read_data
        );

   -- Stimulus process
   stim_proc: process
   begin		
		wait for 10ns;
		address_mem <= x"0000";
		
		wait for 10ns;
		address_mem <= x"0001";
		
		wait for 10ns;
		address_mem <= x"0006";
		
		wait for 10ns;
		address_mem <= x"0007";

      wait;
   end process;

END;
