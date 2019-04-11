LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
ENTITY ProgrammeCounter_TB IS
END ProgrammeCounter_TB;
 
ARCHITECTURE behavior OF ProgrammeCounter_TB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT ProgrammeCounter
    PORT(
         extendIn : in  std_logic_vector(15 downto 0);
         PL : in  std_logic;
         PI : in  std_logic;
         reset : in  std_logic;
         pc_out : out  std_logic_vector(15 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal extendIn : std_logic_vector(15 downto 0);
   signal PL : std_logic := '0';
   signal PI : std_logic := '0';
   signal reset : std_logic := '0';

 	--Outputs
   signal pc_out : std_logic_vector(15 downto 0);
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: ProgrammeCounter PORT MAP (
          extendIn => extendIn,
          PL => PL,
          PI => PL,
          reset => reset,
          pc_out => pc_out
        );

   -- Stimulus process
   stim_proc: process
   begin		
		wait for 5ns;
		reset <= '1';
	    extendIn <= x"0000";
		
		wait for 5ns;
		reset <= '0';
		
		wait for 5ns;
		PI <= '1';
		extendIn <= x"0002";
		
		wait for 20ns;
		PI <= '0';
		PL <= '1';
		extendIn <= x"000F";

      wait;
   end process;

END;