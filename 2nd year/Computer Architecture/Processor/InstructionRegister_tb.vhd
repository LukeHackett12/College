LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
ENTITY Instructions_TB IS
END Instructions_TB;
 
ARCHITECTURE behavior OF Instructions_TB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT InstructionRegister
    PORT(
         IR : in  std_logic_vector(15 downto 0);
         IL : in  std_logic;
         opcode : out  std_logic_vector(6 downto 0);
         DR : out  std_logic_vector(2 downto 0);
         SA : out  std_logic_vector(2 downto 0);
         SB : out  std_logic_vector(2 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal IR : std_logic_vector(15 downto 0) := (others => '0');
   signal IL : std_logic := '0';

 	--Outputs
   signal opcode : std_logic_vector(6 downto 0);
   signal DR : std_logic_vector(2 downto 0);
   signal SA : std_logic_vector(2 downto 0);
   signal SB : std_logic_vector(2 downto 0);
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: InstructionRegister PORT MAP (
          IR => IR,
          IL => IL,
          opcode => opcode,
          DR => DR,
          SA => SA,
          SB => SB
        );
		  
   -- Stimulus process
   stim_proc: process
   begin		
		wait for 10ns;
		IR <= "1111111000001010";
		
		wait for 5ns;
		IL <= '1';
		
		wait for 10ns;
		IR <= "0000000110110000";
		IL <= '0';
		
		wait for 5ns;
		IL <= '1';

      wait;
   end process;

END;