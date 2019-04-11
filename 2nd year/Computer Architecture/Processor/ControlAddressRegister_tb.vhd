LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
ENTITY ControlAddressRegister_TB IS
END ControlAddressRegister_TB;
 
ARCHITECTURE behavior OF ControlAddressRegister_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT ControlAddressRegister
    Port(	
	       opcode : in std_logic_vector(7 downto 0);
	       load, reset, Clk : in std_logic;
	       address : inout std_logic_vector(7 downto 0)
            );
    END COMPONENT;
    

   --Inputs
   signal opcode : std_logic_vector(7 downto 0);
   signal load : std_logic := '0';
   signal Clk : std_logic := '0';
   signal reset : std_logic := '0';

 	--Outputs
   signal address : std_logic_vector(7 downto 0);
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: ControlAddressRegister PORT MAP (
          opcode => opcode,
          load => load,
          Clk => Clk,
          reset => reset,
          address => address
        );
		  
   -- Stimulus process
   stim_proc: process
   begin		
		wait for 5ns;
		reset <= '1';
		
		wait for 30ns;
		reset <= '0';
		
		wait for 30ns;
		opcode <= x"10";
		
		wait for 30ns;
		opcode <= x"A2";
		load <= '1';

      wait;
   end process;

END;
